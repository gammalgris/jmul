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

package jmul.io;


import java.io.File;

import java.util.ArrayList;
import java.util.Collection;


/**
 * A utility class.
 *
 * @author Kristian Kutin
 */
public final class PathHelper {

    /**
     * A file suffix.
     */
    private static final String JAR_ARCHIVE_EXTENSION = "jar";

    /**
     * A system property key.
     */
    private static final String CLASSPATH = "java.class.path";

    /**
     * A system property key.
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * A system property key.
     */
    public static final String PATH_SEPARATOR = System.getProperty("path.separator");

    /**
     * The default constructor.
     */
    private PathHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns the current java classpath.
     *
     * @return the current java classpath
     */
    public static Collection<File> getClasspath() {

        // get the current java classpath
        String fullClasspath = System.getProperty(CLASSPATH);

        // split the string in several substrings
        String[] paths = fullClasspath.split(PATH_SEPARATOR);

        // traverse all entries
        Collection<File> classpath = new ArrayList<>();
        for (String path : paths) {

            File element = new File(path);

            boolean isDirectory = element.isDirectory();
            boolean isFile = element.isFile();

            // make a distinction between directories and jar archives
            if (isDirectory) {

                boolean isKnown = classpath.contains(element);
                if (!isKnown) {
                    classpath.add(element);
                }

            } else if (isFile) {

                String fullPath = element.getPath();
                String nameOnly = element.getName();
                String pathOnly = fullPath.replace(nameOnly, "");

                File directory = new File(pathOnly);

                boolean isKnown = classpath.contains(directory);
                if (!isKnown) {
                    classpath.add(directory);
                }
            }
        }

        return classpath;
    }

    /**
     * Returns all jar archives which are found within the java classpath.
     *
     * @return jar archives
     */
    public static Collection<File> getArchives() {

        Collection<File> classpath = getClasspath();

        Collection<File> archives = new ArrayList<>();

        for (File directory : classpath) {

            String path = directory.getAbsolutePath();

            archives.addAll(FileHelper.getFiles(path, JAR_ARCHIVE_EXTENSION, true));
        }

        return archives;
    }

}
