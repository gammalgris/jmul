/*
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


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jmul.string.StringConcatenator;


/**
 * A utility class to retrieve informations from jar archives.
 *
 * @author Kristian Kutin
 */
public final class JarResources {

    /**
     * The filename of a jar archive.
     */
    private final String archiveName;

    /**
     * Contains an archive directory.
     */
    private Map<String, ArchiveEntry> archiveDirectory;

    /**
     * Groups entries with similar names.
     */
    private Map<String, Collection<String>> shortcuts;

    /**
     * Constructs a jar resource.
     *
     * @param anArchiveName
     *        the name of a jar archive
     */
    public JarResources(String anArchiveName) {

        archiveName = anArchiveName;

        archiveDirectory = new HashMap<String, ArchiveEntry>();
        shortcuts = new HashMap<String, Collection<String>>();

        init();
    }

    /**
     * Performs initialisation tasks (e.g. initializing archive directory).
     */
    private void init() {

        Collection<ArchiveEntry> archiveEntries = ArchiveEntry.scanArchive(archiveName);

        for (ArchiveEntry archiveEntry : archiveEntries) {

            String name = archiveEntry.getName();
            archiveDirectory.put(name, archiveEntry);


            String shortName = archiveEntry.getShortName();

            boolean existsShortcut = shortcuts.containsKey(shortName);
            if (!existsShortcut) {
                shortcuts.put(shortName, new ArrayList<String>());
            }

            Collection<String> references = shortcuts.get(shortName);
            references.add(name);
        }
    }

    /**
     * Retrieve the byte content of a specific archive entry.
     *
     * @param aResourceName
     *        the name of an archive entry
     *
     * @return a byte array
     */
    public byte[] getResource(String aResourceName) {

        boolean isKnown = archiveDirectory.containsKey(aResourceName);
        boolean isKnownShortcut = shortcuts.containsKey(aResourceName);


        if (isKnown) {

            ArchiveEntry entry = archiveDirectory.get(aResourceName);
            return entry.getData();

        } else if (isKnownShortcut) {

            Collection<String> references = shortcuts.get(aResourceName);
            boolean moreThanOneReference = (references.size() > 1);

            if (moreThanOneReference) {

                StringConcatenator message =
                    new StringConcatenator("The archive \"", archiveName,
                                           "\" contains several entries with the name \"",
                                           aResourceName, "\"!");
                throw new IllegalArgumentException(message.toString());
            }

            String reference = references.iterator().next();
            ArchiveEntry entry = archiveDirectory.get(reference);
            return entry.getData();

        } else {

            StringConcatenator message =
                new StringConcatenator("The archive ", archiveName,
                                       " doesn't have an entry with the name \"",
                                       aResourceName, "\"!");
            throw new IllegalArgumentException(message.toString());
        }
    }

    /**
     * Returns all entries with a specific file suffix. Upper and lower case
     * characters are treated the same.
     *
     * @param aSuffix
     *        a file suffix
     *
     * @return all entries with the specified file suffix
     */
    public Collection<String> getResourceNamesWithSuffix(String aSuffix) {

        Collection<String> foundEntries = new ArrayList<String>();

        for (String entry : archiveDirectory.keySet()) {

            boolean matchingSuffix =
                entry.toLowerCase().endsWith(aSuffix.toLowerCase());
            if (matchingSuffix) {
                foundEntries.add(entry);
            }
        }

        return foundEntries;
    }

}
