/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package jmul.document.type;


import java.util.List;


/**
 * This interface describes an entity which represents a document type.
 *
 * @author Kristian Kutin
 */
public interface DocumentType {

    /**
     * Returns a short description of the document type.
     *
     * @return a short description
     */
    String getDocumentTypeDescription();

    /**
     * Returns all file extensions which are associated with this document
     * type.
     *
     * @return the file extension
     */
    List<String> getFileExtensions();

    /**
     * Checks if the specified file name matches the file extension of this
     * document type.
     *
     * @param aFileName
     *        the file name
     *
     * @return <code>true</code> if the file extensions match, else
     *         <code>false</code>
     */
    boolean matchesFileExtension(String aFileName);

}
