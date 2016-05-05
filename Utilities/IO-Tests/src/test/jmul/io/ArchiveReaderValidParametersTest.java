/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package test.jmul.io;


import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.charset.StandardCharsets;

import jmul.io.ArchiveReader;
import jmul.io.ArchiveReaderImpl;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;


/**
 * Tests if a {@link jmul.io.ArchiveReader} works correctly.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ArchiveReaderValidParametersTest {

    /**
     * Reads a resource from an archive.
     */
    @Test
    public void testLoadResource() {

        String archiveName = "testdata-io\\archive1.zip";
        String archiveEntry = "testdata-io/config1.properties";
        String expectedLine = "key1=hello\r\n";

        try {

            ArchiveReader reader = new ArchiveReaderImpl(archiveName);
            byte[] data = reader.loadEntry(archiveEntry);

            String actualLine = new String(data, StandardCharsets.UTF_8);
            assertEquals(expectedLine, actualLine);

        } catch (FileNotFoundException e) {

            fail("Archive doesn't exist!");

        } catch (IOException e) {

            fail(String.valueOf(e.getClass()));
        }
    }

}
