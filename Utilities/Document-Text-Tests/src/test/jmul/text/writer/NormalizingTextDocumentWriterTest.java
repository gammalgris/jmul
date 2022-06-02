/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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

package test.jmul.text.writer;


import java.io.IOException;

import java.nio.charset.StandardCharsets;

import jmul.document.text.TextDocument;
import jmul.document.text.TextDocumentImpl;
import jmul.document.type.DocumentTypes;

import jmul.misc.text.Text;
import jmul.misc.text.TextImpl;

import jmul.string.Constants;

import jmul.test.classification.UnitTest;

import jmul.text.reader.TextDocumentReader;
import jmul.text.reader.TextDocumentReaderImpl;
import jmul.text.writer.NormalizingTextDocumentWriterImpl;
import jmul.text.writer.TextDocumentWriter;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * A test which checks the writing of a text document with the new normalizing document writer.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class NormalizingTextDocumentWriterTest {

    /**
     * A string containing special characters.
     */
    private static String TEST_LINE_1;

    /**
     * A string without special characters.
     */
    private static String TEST_LINE_2;

    /*
     * The static initializer.
     */
    static {

        TEST_LINE_1 = "\u00c4\u00e4\u00d6\u00f6\u00dc\u00fc\u00df";
        TEST_LINE_2 = "Hello World!";
    }

    /**
     * An instance of of a document writer.
     */
    private TextDocumentWriter writer;

    /**
     * An instance of a document reader.
     */
    private TextDocumentReader reader;

    /**
     * Sets everything up that is needed for the test.
     */
    @Before
    public void setUp() {

        writer = new NormalizingTextDocumentWriterImpl();
        reader = new TextDocumentReaderImpl();
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        writer = null;
        reader = null;
    }

    /**
     * Creates a test document.
     *
     * @return
     */
    private TextDocument createDocument() {

        Text content = new TextImpl(TEST_LINE_1, TEST_LINE_2);
        TextDocument document =
            new TextDocumentImpl(DocumentTypes.TEXT, StandardCharsets.UTF_8, Constants.NEW_LINE, content);

        return document;
    }

    /**
     * Test writing a document and checking the file content.
     */
    @Test
    public void testWriteDocument() throws IOException {

        String filename = "./testdata-text/output/normalized-test-output.txt";

        TextDocument document = createDocument();
        writer.writeTo(filename, document);

        TextDocument newDocument = reader.readFrom(filename);

        for (String line : document.getContent().getContent()) {
            
            System.out.println(line);
        }

        for (String line : newDocument.getContent().getContent()) {
            
            System.out.println(line);
        }

        assertEquals("Documents don't match!", document.getContent(), newDocument.getContent());
    }

}
