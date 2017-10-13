/*
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


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static jmul.string.Constants.SEMICOLON;


/**
 * This enumeration contains entries for all known document types.
 *
 * TODO
 * The enumeration is missing more file types. Add as required.
 *
 * @author Kristian Kutin
 */
public enum DocumentTypes implements DocumentType {


    TEXT("text"),
    CSV("csv"),
    WORD("word"),
    XML("xml"),
    XSD("xsd"),
    IMAGE("image"),
    WEBSITE("website"),
    UNKNOWN("unknown"), ;


    /**
     * A suffix for a property key.
     */
    private static final String DESCRIPTION_SUFFIX = ".description";

    /**
     * A suffix for a property key.
     */
    private static final String FILE_EXTENSIONS_SUFFIX = ".fileExtensions";

    /**
     * A description for this document type.
     */
    private final String description;

    /**
     * All associated file extensions.
     */
    private final List<String> fileExtensions;

    /**
     * Creates a new enumeration element according to the specified parameters.
     *
     * @param entryName
     */
    private DocumentTypes(String entryName) {

        ResourceBundle bundle = ResourceBundle.getBundle(DocumentTypes.class.getName());

        description = getString(bundle, entryName, DESCRIPTION_SUFFIX);
        fileExtensions = Collections.unmodifiableList(getList(bundle, entryName, FILE_EXTENSIONS_SUFFIX));
    }

    /**
     * Retrieves the specified value from a resource bundle. If no such value exists an exception
     * is thrown.
     *
     * @param aBundle
     * @param anEntryName
     * @param aSuffix
     *
     * @return a value from a resource bundle
     */
    private static String getString(ResourceBundle aBundle, String anEntryName, String aSuffix) {

        String key = anEntryName + aSuffix;
        String value = aBundle.getString(key);

        if (value == null) {

            String message = "No value is associated with the specified key \"" + key + "\"!";
            throw new IllegalArgumentException(message);
        }

        value = value.trim();

        return value;
    }

    /**
     * Retrieves the specified list from a resource bundle. If no such list exists an exception
     * is thrown.
     *
     * @param aBundle
     * @param anEntryName
     * @param aSuffix
     *
     * @return a list from a resource bundle
     */
    private static List<String> getList(ResourceBundle aBundle, String anEntryName, String aSuffix) {

        String value = getString(aBundle, anEntryName, aSuffix);

        String[] array = value.trim().split(SEMICOLON);
        List<String> list = new ArrayList<>();

        for (String s : array) {

            list.add(s.trim());
        }

        return list;
    }


    /**
     * Returns a short description of the document type.
     *
     * @return a short description
     */
    @Override
    public String getDocumentTypeDescription() {

        return description;
    }

    /**
     * Returns all file extensions which are associated with this document
     * type.
     *
     * @return
     */
    @Override
    public List<String> getFileExtensions() {

        return fileExtensions;
    }

    /**
     * Checks if the specified file name matches the file extension of this
     * document type.
     *
     * @param aFileName
     *
     * @return <code>true</code> if the file extensions match, else
     *         <code>false</code>
     */
    @Override
    public boolean matchesFileExtension(String aFileName) {

        for (String s : getFileExtensions()) {

            if (aFileName.endsWith(s)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Returns a string representation for this enumeration element.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append(getDocumentTypeDescription());
        buffer.append(" (");

        boolean first = true;
        for (String fileExtension : getFileExtensions()) {

            if (first) {

                first = false;

            } else {

                buffer.append(SEMICOLON);
            }

            buffer.append(fileExtension);
        }

        buffer.append(").");

        return String.valueOf(buffer);
    }

    /**
     * Checks if a known document type can be associated with the specified file name.
     * If no such document type exists an exception is thrown.
     *
     * @param aFileName
     *
     * @return a document type
     */
    public static DocumentType getDocumentType(String aFileName) {

        for (DocumentType documentType : DocumentTypes.values()) {

            for (String fileExtension : documentType.getFileExtensions()) {

                if (aFileName.endsWith(fileExtension)) {

                    return documentType;
                }
            }
        }

        return UNKNOWN;
    }

}
