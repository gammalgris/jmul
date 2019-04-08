/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package test.jmul.document.type;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import jmul.document.type.DocumentType;
import jmul.document.type.DocumentTypes;
import static jmul.document.type.DocumentTypes.CSV;
import static jmul.document.type.DocumentTypes.IMAGE;
import static jmul.document.type.DocumentTypes.TEXT;
import static jmul.document.type.DocumentTypes.UNKNOWN;
import static jmul.document.type.DocumentTypes.WEBSITE;
import static jmul.document.type.DocumentTypes.WORD;
import static jmul.document.type.DocumentTypes.XML;
import static jmul.document.type.DocumentTypes.XSD;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains tests to check document type elements.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DocumentTypesValidParametersTest {

    /**
     * A file name.
     */
    private String fileName;

    /**
     * The expected document type.
     */
    private DocumentType documentType;

    /**
     * The expected file extensions.
     */
    private List<String> fileExtensions;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param aFileName
     * @param aDocumentType
     * @param someFileExtensions
     */
    public DocumentTypesValidParametersTest(String aFileName, DocumentType aDocumentType, String[] someFileExtensions) {

        super();

        fileName = aFileName;
        documentType = aDocumentType;
        fileExtensions = Arrays.asList(someFileExtensions);
    }

    /**
     * Tests a document type element.
     */
    @Test
    public void testDocumentType() {

        // Get a document type by file name
        DocumentType actualDocumentType = DocumentTypes.getDocumentType(fileName);
        assertNotNull(actualDocumentType);

        // Check if that it is of the expected type
        assertEquals("The document types don't match!", documentType, actualDocumentType);

        // Check the file extensions
        List<String> actualFileExtensions = actualDocumentType.getFileExtensions();
        assertNotNull(actualFileExtensions);

        if (actualDocumentType != UNKNOWN) {

            assertFalse("There are no file suffixes associated with the document type!",
                        actualFileExtensions.isEmpty());
        }

        assertMatchingLists(fileExtensions, actualFileExtensions);

        for (String fileExtension : fileExtensions) {

            assertTrue(actualDocumentType.matchesFileExtension(fileExtension));
        }

        String actualDescription = actualDocumentType.getDocumentTypeDescription();
        assertNotNull(actualDescription);
        assertFalse(actualDescription.trim().isEmpty());
    }

    /**
     * Compares two lists. If the two lists don't match an assert fails.
     *
     * @param firstList
     * @param secondList
     */
    private static void assertMatchingLists(List<String> firstList, List<String> secondList) {

        assertNotNull(firstList);
        assertNotNull(secondList);
        assertEquals("The lists are of different sizes!", firstList.size(), secondList.size());

        List<String> a = new ArrayList<>(firstList);
        List<String> b = new ArrayList<>(secondList);

        a.removeAll(secondList);
        assertTrue("The first list contains elements that the second list doesn't have!", a.isEmpty());

        b.removeAll(firstList);
        assertTrue("The second list contains elements that the first list doesn't have!", b.isEmpty());

        // By reaching this comment the two lists should be equal and no more checks are required.
    }

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "test.txt", TEXT, new String[] { ".txt" } });
        parameters.add(new Object[] { "test.csv", CSV, new String[] { ".csv" } });
        parameters.add(new Object[] { "test.doc", WORD, new String[] { ".doc", ".docx" } });
        parameters.add(new Object[] { "test.docx", WORD, new String[] { ".doc", ".docx" } });
        parameters.add(new Object[] { "test.xml", XML, new String[] { ".xml" } });
        parameters.add(new Object[] { "test.xsd", XSD, new String[] { ".xsd" } });
        parameters.add(new Object[] { "test.jpg", IMAGE, new String[] { ".jpg", ".gif", ".bmp", ".svg" } });
        parameters.add(new Object[] { "test.gif", IMAGE, new String[] { ".jpg", ".gif", ".bmp", ".svg" } });
        parameters.add(new Object[] { "test.bmp", IMAGE, new String[] { ".jpg", ".gif", ".bmp", ".svg" } });
        parameters.add(new Object[] { "test.svg", IMAGE, new String[] { ".jpg", ".gif", ".bmp", ".svg" } });
        parameters.add(new Object[] { "test.html", WEBSITE, new String[] { ".htm", ".html" } });
        parameters.add(new Object[] { "test.htm", WEBSITE, new String[] { ".htm", ".html" } });
        parameters.add(new Object[] { "test.zip", UNKNOWN, new String[] { } });

        return parameters;
    }

}
