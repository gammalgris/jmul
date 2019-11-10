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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.persistence.file;


import java.io.File;

import java.io.IOException;

import java.util.Collection;

import jmul.xml.query.XPathQuery;


/**
 * This interface describes an entity which manages files of one kind (i.e. the
 * same file suffix). Depending on the implementation the files may reside in a
 * specified base directory or be distribited to various subfolders within this
 * base directory.<br>
 * <br>
 * A caller only needs to specify a unique name and the file manager will take
 * care of the rest (i.e. manage the unique identifiers and their associated
 * file names).
 *
 * @author Kristian Kutin
 */
public interface FileManager {

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
    boolean existsFile(String aUniqueIdentifier);

    /**
     * The method adds a new file. The file manager will determine a file name
     * (including a file suffix and complete directory path).
     *
     * @param aUniqueIdentifier
     *        a unique identifier which will be associated with a file
     *
     * @return a file
     */
    File newFile(String aUniqueIdentifier);

    /**
     * The method returns the name of the file which is associated with the
     * specified identifier.
     *
     * @param aUniqueIdentifier
     *        a unique identifier which is associated with a file
     *
     * @return a file
     */
    File getFile(String aUniqueIdentifier);

    /**
     * The method returns the unique identifier which is associated with the
     * specified file.
     *
     * @param aFile
     *        a file which is managed by this file manager
     *
     * @return a unique identifier which is associated with a file or
     *         <code>null</code> if no such association exists
     *
     * @throws IOException
     *         can be thrown if canonical file paths cannot be obtained
     */
    String getUniqueIdentifier(File aFile) throws IOException;

    /**
     * The method returns a list of folders which contain all managed files.
     * Every folder contains a distinct set of files. A concrete implementation
     * must specify the expected results. This list can then be used to perform
     * various file queries.
     *
     * @return a list of folders which contain all managed files
     */
    Collection<File> getFolders();

    /**
     * Prepares the file manager for shutdown (i.e. running threads will be
     * stopped).
     */
    void shutdown();

    /**
     * The method returns all files which meet the specified criteria.
     *
     * @param someQueries
     *        all queries which will be performed on all files
     *
     * @return a list of files which meet the specified criteria
     */
    Collection<File> findFiles(XPathQuery... someQueries);

}
