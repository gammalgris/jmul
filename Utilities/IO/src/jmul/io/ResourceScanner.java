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


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * A utility class which is used to scan for resources of a specified kind.
 *
 * @author Kristian Kutin
 */
public class ResourceScanner {

    /**
     * Informations about a specific kind of resources.
     */
    private ResourceType resourceType;

    /**
     * A cache for resource files within a directory tree.
     */
    private Map<String, Collection<File>> ressourceCache;

    /**
     * A cache for jar archives within a directory tree.
     */
    private Map<String, JarResources> archiveCache;

    /**
     * Constructs a resource scanner.
     *
     * @param aResourceType
     *        the kind of resources which the scanner should look for
     */
    public ResourceScanner(ResourceType aResourceType) {

        resourceType = aResourceType;

        scanClasspathForRessources();
    }

    /**
     * To reduce i/o operations the directory tree is scanned and the directory
     * informations are cached.
     */
    private void scanClasspathForRessources() {

        // Scan the directory tree and group all entries according to their
        // names.
        ressourceCache = new HashMap<String, Collection<File>>();
        Collection<File> classpaths = PathHelper.getClasspath();

        for (File directory : classpaths) {

            Collection<File> foundFiles = FileHelper.getFiles(directory, resourceType.getFileSuffix(), true);

            for (File file : foundFiles) {

                String filename = file.getName();

                if (ressourceCache.containsKey(filename)) {

                    Collection<File> subset = ressourceCache.get(filename);
                    subset.add(file);

                } else {

                    Collection<File> subset = new ArrayList<File>();
                    subset.add(file);
                    ressourceCache.put(filename, subset);

                }
            }
        }

        // scan all archives
        archiveCache = new HashMap<String, JarResources>();
        Collection<File> archives = PathHelper.getArchives();

        for (File archive : archives) {

            try {

                String archiveName = archive.getCanonicalPath();
                JarResources archiveDetails = new JarResources(archiveName);
                archiveCache.put(archiveName, archiveDetails);

            } catch (IOException e) {

                // Ignore potential exceptions
                continue;
            }

        }
    }

    /**
     * Returns informations about the resource type.
     *
     * @return informations about the resource type
     */
    public ResourceType getResourceType() {

        return resourceType;
    }

    /**
     * Returns all found resource files.
     *
     * @return all found resource files
     */
    public Map<String, Collection<File>> getFoundResources() {

        return ressourceCache;
    }

    /**
     * Returns all found archive files.
     *
     * @return all found archive files
     */
    public Map<String, JarResources> getFoundArchives() {

        return archiveCache;
    }

}
