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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.text.reader;


import java.io.IOException;

import java.nio.charset.StandardCharsets;

import jmul.document.text.TextDocument;

import jmul.misc.text.Text;

import jmul.test.classification.UnitTest;

import jmul.text.reader.TextDocumentReader;
import jmul.text.reader.TextDocumentReaderImpl;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * The class contains tests which read text files.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ReadTextFileTest {

    /**
     * Tests reading a text file with a custom line separator.
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the
     *         specified file
     */
    @Test
    public void testReadTextFile() throws IOException {

        TextDocumentReader reader = new TextDocumentReaderImpl("||");

        TextDocument document = reader.readFrom("testdata-text/example01.txt");

        assertEquals(15, document.getSize());


        Text text = document.getContent();

        assertEquals(2, text.getContent().size());
        assertEquals("Hello", text.getContent().get(0));
        assertEquals("World!", text.getContent().get(1));
    }

    /**
     * Tests reading a text file.
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the
     *         specified file
     */
    @Test
    public void testReadTextFile2() throws IOException {

        TextDocumentReader reader = new TextDocumentReaderImpl();

        TextDocument document = reader.readFrom("testdata-text/example02.txt");

        assertEquals(16, document.getSize());


        Text text = document.getContent();

        assertEquals(2, text.getContent().size());
        assertEquals("Hello World!", text.getContent().get(0));
        assertEquals("", text.getContent().get(1));
    }

    /**
     * Tests reading a text file.
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the
     *         specified file
     */
    @Test
    public void testReadTextFile3() throws IOException {

        TextDocumentReader reader = new TextDocumentReaderImpl(StandardCharsets.UTF_8);

        TextDocument document = reader.readFrom("testdata-text/example03.txt");

        assertEquals(11, document.getSize());


        Text text = document.getContent();

        assertEquals(2, text.getContent().size());
        assertEquals("\u00c4\u00e4\u00d6\u00f6\u00dc\u00fc\u00df", text.getContent().get(0));
        assertEquals("", text.getContent().get(1));
    }

    /**
     * Tests reading a text file.
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the
     *         specified file
     */
    @Test
    public void testReadTextFile4() throws IOException {

        TextDocumentReader reader = new TextDocumentReaderImpl(StandardCharsets.ISO_8859_1);

        TextDocument document = reader.readFrom("testdata-text/example04.txt");

        assertEquals(11, document.getSize());


        Text text = document.getContent();

        assertEquals(2, text.getContent().size());
        assertEquals("\u00c4\u00e4\u00d6\u00f6\u00dc\u00fc\u00df", text.getContent().get(0));
        assertEquals("", text.getContent().get(1));
    }

    /**
     * Tests reading a text file.
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the
     *         specified file
     */
    @Test
    public void testReadTextFile5() throws IOException {

        TextDocumentReader reader = new TextDocumentReaderImpl(StandardCharsets.ISO_8859_1);

        TextDocument document = reader.readFrom("testdata-text/example05.txt");

        assertEquals(11, document.getSize());


        Text text = document.getContent();

        assertEquals(2, text.getContent().size());
        assertEquals("\u00c4\u00e4\u00d6\u00f6\u00dc\u00fc\u00df", text.getContent().get(0));
        assertEquals("", text.getContent().get(1));
    }

}
