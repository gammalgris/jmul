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

package test.jmul.binary.reader;

import java.io.IOException;

import java.nio.ByteOrder;

import jmul.binary.reader.BinaryFileReader;
import jmul.binary.reader.BinaryFileReaderImpl;

import jmul.document.binary.BinaryFile;
import jmul.document.binary.structure.WordLengths;
import jmul.document.type.DocumentTypes;

import jmul.test.exceptions.FailedTestException;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * A test to check the general properties of a document.
 *
 * @author Kristian Kutin
 */
public class BinaryFileReaderTest {

    /**
     * Tests parsing a CSV file.
     */
    @Test
    public void testParseDocument() {

        String filename = "testdata-binary\\example01.csv";
        BinaryFileReader reader = new BinaryFileReaderImpl();

        BinaryFile document;

        try {

            document = reader.parseDocument(filename);

        } catch (IOException e) {

            throw new FailedTestException(e);
        }


        assertEquals(DocumentTypes.CSV, document.getDocumentType());
        assertEquals(ByteOrder.BIG_ENDIAN, document.getStructure().getByteOrder());
        assertEquals(WordLengths.WORD_32_BIT, document.getStructure().getWordLength());
        assertEquals(28, document.getSize());
    }

}
