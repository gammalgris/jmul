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

package test.jmul.text.writer;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.document.text.TextDocument;
import jmul.document.text.TextDocumentImpl;
import static jmul.document.type.DocumentTypes.TEXT;

import jmul.misc.text.Text;
import jmul.misc.text.TextImpl;

import static jmul.string.Constants.NEW_LINE;
import static jmul.string.TextHelper.getDefaultCharset;

import jmul.test.classification.UnitTest;

import jmul.text.writer.TextDocumentWriter;
import jmul.text.writer.TextDocumentWriterImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests the instantiation of a reader with various valid input parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class WriteTextFileWithInvalidParametersTest {

    /**
     * A reader instance.
     */
    private TextDocumentWriter writer;

    /**
     * An input parameter.
     */
    private String fileName;

    /**
     * An input parameter.
     */
    private TextDocument document;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aFileName
     * @param aDocument
     */
    public WriteTextFileWithInvalidParametersTest(String aFileName, TextDocument aDocument) {

        fileName = aFileName;
        document = aDocument;
    }

    @Before
    public void setUp() {

        writer = new TextDocumentWriterImpl();
    }

    @After
    public void tearDown() {

        writer = null;
    }

    /**
     * Tests the instantiation of a reader with valid input parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWriteDocument() throws IOException {

        writer.writeTo(fileName, document);
    }

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null, newDocument() });
        parameters.add(new Object[] { "", newDocument() });
        parameters.add(new Object[] { "  ", newDocument() });
        parameters.add(new Object[] { "testdata-text/example03.txt", null });

        return parameters;
    }

    /**
     * Creates a new text document.
     *
     * @return a text document
     */
    private static TextDocument newDocument() {

        Text text = new TextImpl("Hello World!");
        return new TextDocumentImpl(TEXT, getDefaultCharset(), NEW_LINE, text);
    }

}
