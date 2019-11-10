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

package test.jmul.text.writer;


import java.io.File;
import java.io.IOException;

import java.nio.charset.StandardCharsets;

import jmul.document.text.TextDocument;
import jmul.document.text.TextDocumentImpl;
import static jmul.document.type.DocumentTypes.TEXT;

import jmul.misc.text.Text;
import jmul.misc.text.TextImpl;

import jmul.test.classification.UnitTest;

import jmul.text.writer.TextDocumentWriter;
import jmul.text.writer.TextDocumentWriterImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * The class contains tests which read text files.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class WriteTextFileTest {

    /**
     * Tests reading a text file with a custom line separator.
     *
     * @throws IOException
     *         is thrown if an error occurrs while trying to read from the
     *         specified file
     */
    @Test
    public void testWriteTextFile() throws IOException {

        Text content = new TextImpl("Hello World!");
        TextDocument document = new TextDocumentImpl(TEXT, StandardCharsets.UTF_8, "||", content);
        TextDocumentWriter writer = new TextDocumentWriterImpl();

        String filename = "testdata-text/created-example01.txt";

        writer.writeTo(filename, document);

        File file = new File(filename);
        assertTrue(file.exists());
        assertFalse(file.isDirectory());
        assertTrue(file.isFile());
    }

}
