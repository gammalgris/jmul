/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: kristian.kutin@arcor.de
 */

package jmul.persistence.file;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import jmul.io.FileHelper;

import jmul.string.TextHelper;

import jmul.xml.query.XPathQuery;


/**
 * An implementation of a file manager.<br>
 * <br>
 * This file manager distributes files to different subfolders. Each subfolder
 * can only contain a specified maximum number of files. Search operations can
 * thus be delegated to several threads where each thread will search the
 * assigned subfolder.<br>
 * <br>
 * Useful informations:<br>
 * <ul>
 *   <li><a href="http://stackoverflow.com/questions/3190232/java-file-i-o-throughput-decline">StackOverflow</a></li>
 * </ul>
 *
 * @author Kristian Kutin
 */
public class FileManagerImpl implements FileManager {

    /**
     * The file separator for this operating system.
     */
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * A property key.
     */
    private static final String TEMPLATE_PLACEHOLDER_KEY = "template.placeholder";

    /**
     * A property key.
     */
    private static final String TEMPLATE_SUBFOLDER_KEY = "template.subfolder";

    /**
     * A property key.
     */
    private static final String TEMPLATE_FILE_SUFFIX_KEY = "template.file.suffix";

    /**
     * A property key.
     */
    private static final String MAX_FILES_KEY = "maxFiles";

    /**
     * Counts the active file managers.
     */
    private static InstanceCounter<FileManager> instanceCounter;

    /*
     * The static initializer.
     */
    static {

        instanceCounter = new InstanceCounter<>();
    }

    /**
     * The placeholder which is used in a template.
     */
    private final String templatePlaceholder;

    /**
     * The template for subfolder names.
     */
    private final String templateSubfolder;

    /**
     * The file suffix for managed files.
     */
    private final String templateFileSuffix;

    /**
     * The maximum number of files which are allowed per subfolder.
     */
    private final int maxFiles;

    /**
     * The base directory.
     */
    private File baseDirectory;

    /**
     * Provides functionalities to extract the index from a subfolder name.
     */
    private IndexExtractor indexExtractor;

    /**
     * Contains all existing subfolders.
     */
    private Collection<File> subfolders;

    /**
     * An index which associates the existing subfolders with the number of
     * files which still can be created.
     */
    private Map<File, Integer> subfolderVacancies;

    /**
     * The index of the subfolder which was created most recently.
     */
    private int maxFolderIndex;

    /**
     * Constructs a file manager.
     *
     * @param aDirectoryName
     *        the base dir for this file manager
     */
    public FileManagerImpl(String aDirectoryName) {

        // Check forst if the file lookup mechanism needs to be activated

        if (instanceCounter.getActiveCount() == 0) {

            FileLookup.activateLookupMechanism();
        }

        instanceCounter.registerInstance(this);


        // COntinue with the ordinary initialization.

        baseDirectory = new File(aDirectoryName);
        baseDirectory.mkdirs();

        if (!baseDirectory.isDirectory()) {

            String message = TextHelper.concatenateStrings(aDirectoryName, " is no directory!");
            throw new IllegalArgumentException(message);
        }


        subfolders = null;
        subfolderVacancies = new HashMap<>();


        ResourceBundle bundle = ResourceBundle.getBundle(getClass().getName());

        templatePlaceholder = bundle.getString(TEMPLATE_PLACEHOLDER_KEY);
        templateSubfolder = bundle.getString(TEMPLATE_SUBFOLDER_KEY);
        templateFileSuffix = bundle.getString(TEMPLATE_FILE_SUFFIX_KEY);
        maxFiles = Integer.parseInt(bundle.getString(MAX_FILES_KEY));


        indexExtractor = new IndexExtractor();


        scanBaseDirectory();
    }

    /**
     * The method scans the base directory for subfolders and checks each
     * subfolder for vacancies.
     */
    private void scanBaseDirectory() {

        subfolderVacancies.clear();

        File[] foundDirectories = baseDirectory.listFiles(FileHelper.getDirectoryFilter());
        subfolders = new ArrayList<>(Arrays.asList(foundDirectories));


        maxFolderIndex = 0;

        for (File subfolder : subfolders) {

            File[] files = subfolder.listFiles(FileHelper.getFileFilter(templateFileSuffix));

            int population = files.length;
            int vacancies = maxFiles - population;

            if (vacancies > 0) {

                subfolderVacancies.put(subfolder, vacancies);
            }

            maxFolderIndex = Math.max(maxFolderIndex, indexExtractor.extractIndex(subfolder.getName()));
        }


        newSubfolder(); // Checks if a new subfolder needs to be created.
    }

    /**
     * Creates a new subfolder name.
     *
     * @return a subfolder name
     */
    private String newSubfolderName() {

        maxFolderIndex++;

        String subfolderName = templateSubfolder;
        subfolderName = subfolderName.replace(templatePlaceholder, String.valueOf(maxFolderIndex));

        return subfolderName;
    }

    /**
     * Adds a new subfolder.
     */
    private void newSubfolder() {

        if ((maxFolderIndex == 0) || (subfolderVacancies.isEmpty())) {

            File subfolder = new File(baseDirectory, newSubfolderName());
            subfolder.mkdirs();

            subfolders.add(subfolder);
            subfolderVacancies.put(subfolder, maxFiles);
        }
    }

    /**
     * Creates a new file name.
     *
     * @param aUniqueIdentifier
     *        a unique identifier which will be associated with a file
     *
     * @return a filename
     */
    private String newFileName(String aUniqueIdentifier) {

        return TextHelper.concatenateStrings(aUniqueIdentifier, ".", templateFileSuffix);
    }

    /**
     * Checks if a file exists which is associated with the specified unique
     * identifier.
     *
     * @param aUniqueIdentifier
     *        a unique identifier which will be associated with a file
     *
     * @return <code>true</code> if a file exists which is associated with the
     *         specified unique identifierelse <code>false</code>
     */
    @Override
    public boolean existsFile(String aUniqueIdentifier) {

        FileLookup lookup = new FileLookup();
        return lookup.existsFile(subfolders, newFileName(aUniqueIdentifier));
    }

    /**
     * The method adds a new file. The file manager will determine a file name
     * (including a file suffix and complete directory path).
     *
     * @param aUniqueIdentifier
     *        a unique identifier which will be associated with a file
     *
     * @return a file
     */
    @Override
    public File newFile(String aUniqueIdentifier) {

        File firstSubfolder = subfolderVacancies.keySet()
                                                .iterator()
                                                .next();
        int vacancies = subfolderVacancies.get(firstSubfolder);

        String filename =
            TextHelper.concatenateStrings(firstSubfolder.getAbsolutePath(), FILE_SEPARATOR,
                                          newFileName(aUniqueIdentifier));

        vacancies--;

        if (vacancies <= 0) {

            subfolderVacancies.remove(firstSubfolder);
            newSubfolder();

        } else {

            subfolderVacancies.put(firstSubfolder, vacancies);
        }

        return new File(filename);
    }

    /**
     * The method returns the name of the file which is associated with the
     * specified identifier.
     *
     * @param aUniqueIdentifier
     *        a unique identifier which is associated with a file
     *
     * @return a file
     */
    @Override
    public File getFile(String aUniqueIdentifier) {

        FileLookup lookup = new FileLookup();
        return lookup.getFile(subfolders, newFileName(aUniqueIdentifier));
    }

    /**
     * The method returns the unique identifier which is associated with the
     * specified file.
     *
     * @param aFile
     *        a file which is managed by this file manager
     *
     * @return a unique identifier which is associated with a file or
     *         <code>null</code> if no such association exists
     */
    @Override
    public String getUniqueIdentifier(File aFile) {

        // Following checks are required to determine an association (the order
        // in which to perform these checks is not important):
        //
        // 1) Check if specified parameter is a file.
        // 2) Check if the specified file resides in the same parent folder.
        // 3) Check if the specified file exists.
        // 4) Check if the specified file has the right file suffix.
        //
        // A file is expected to contain the unique identifier in its name.
        // Only if all checks are met the unique identifier can safely be
        // extracted from the file's name.

        if (aFile.isFile()) {

            String expectedPath = null;
            String specifiedPath = null;

            try {

                expectedPath = baseDirectory.getCanonicalPath();
                specifiedPath = aFile.getCanonicalPath();

            } catch (IOException e) {

                // Ignore this exception.
                return null;
            }

            if (specifiedPath.startsWith(expectedPath) && aFile.getName().endsWith(templateFileSuffix) &&
                aFile.exists()) {

                String filename = aFile.getName();

                String suffix = TextHelper.concatenateStrings(".", templateFileSuffix);
                return filename.replace(suffix, "");
            }
        }

        return null;
    }

    /**
     * The method returns a list of folders which contain all managed files.
     * Every folder contains a distinct set of files. A concrete implementation
     * must specify the expected results. This list can then be used to perform
     * various file queries.
     *
     * @return a list of folders which contain all managed files
     */
    @Override
    public Collection<File> getFolders() {

        return subfolders;
    }

    /**
     * Prepares the file manager for shutdown (i.e. running threads will be
     * stopped).
     */
    @Override
    public void shutdown() {

        instanceCounter.unregisterInstance(this);

        if (instanceCounter.getActiveCount() == 0) {

            FileLookup.deactivateLookupMechanism();
        }
    }

    /**
     * The method returns all files which meet the specified criteria.
     *
     * @param someQueries
     *        all queries which will be performed on all files
     *
     * @return a list of files which meet the specified criteria
     */
    @Override
    public Collection<File> findFiles(XPathQuery... someQueries) {

        FileLookup lookup = new FileLookup();
        return lookup.findFiles(subfolders, someQueries);
    }

    /**
     * This inner class encapsulates informations and methods to extract the
     * index from a subfolder name.
     */
    class IndexExtractor {

        /**
         * A utility constant.
         */
        private final String subfolderPrefix;

        /**
         * A utility constant.
         */
        private final String subfolderSuffix;

        /**
         * The default constructor.
         */
        IndexExtractor() {

            int indexEndPrefix = templateSubfolder.indexOf(templatePlaceholder);
            int indexStartSuffix = indexEndPrefix + templatePlaceholder.length();
            subfolderPrefix = templateSubfolder.substring(0, indexEndPrefix);
            subfolderSuffix = templateSubfolder.substring(indexStartSuffix);
        }

        /**
         * The method extracts the index from a subfolder name.
         *
         * @param aSubfolderName
         *        a subfodler name
         *
         * @return an index
         */
        int extractIndex(String aSubfolderName) {

            String indexString = aSubfolderName;
            indexString = indexString.replace(subfolderPrefix, "");
            indexString = indexString.replace(subfolderSuffix, "");
            return Integer.parseInt(indexString);
        }
    }

}

/**
 * This class counts the active instances of a specific object type.
 *
 * @param <T>
 *        The type of object which is to be counted
 *
 * @author Kristian Kutin
 */
class InstanceCounter<T> {

    /**
     * Keeps track of all registered insatnces.
     */
    private Collection<T> registeredInstances;

    /**
     * The default constructor.
     */
    public InstanceCounter() {

        registeredInstances = new ArrayList<>();
    }

    /**
     * Returns the number of active instances.
     *
     * @return the number of active instances
     */
    public int getActiveCount() {

        int count;

        synchronized (this) {

            count = registeredInstances.size();
        }

        return count;
    }

    /**
     * Registers the specified instance. If the specified instance was already
     * registered then this request is ignored.
     *
     * @param anInstance
     *        an instance
     */
    public void registerInstance(T anInstance) {

        synchronized (this) {

            if (!registeredInstances.contains(anInstance)) {

                registeredInstances.add(anInstance);
            }
        }
    }

    /**
     * Unregisters the specified instance. If the specified instance is not
     * registered then this request is ignored.
     *
     * @param anInstance
     *        an instance
     */
    public void unregisterInstance(T anInstance) {

        synchronized (this) {

            if (registeredInstances.contains(anInstance)) {

                registeredInstances.remove(anInstance);
            }
        }
    }

}
