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

package test.jmul.io.archives;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;

import jmul.io.FileHelper;
import jmul.io.archives.ArchiveHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This class conains tests to check the creation of an archive file.
 *
 * @author Kristian Kutin
 */
public class CreateArchiveTest {

    /**
     * Tests the creation of an archive file with valid file input.
     *
     * @throws IOException
     *         is thrown if an error occurs while creating the archive
     */
    @Test
    public void testCreateArchive() throws IOException {

        String archiveName = "testdata-io\\out\\archive1.zip";

        String baseDirectory = "testdata-io\\";
        String[] inputFiles = new String[] {
            "testdata-io\\file1.txt", "testdata-io\\file2.txt", "testdata-io\\file3.bat" };

        ArchiveHelper.createArchive(archiveName, baseDirectory, inputFiles);
        File archiveFile = new File(archiveName);

        assertTrue(FileHelper.existsFile(archiveFile));

        assertTrue(archiveFile.exists());
        assertFalse(!archiveFile.exists());

        assertTrue(archiveFile.isFile());

        List<File> allFiles = Arrays.asList(archiveFile.getParentFile().listFiles());
        assertTrue(allFiles.contains(archiveFile));
        assertFalse(!allFiles.contains(archiveFile));

        assertEquals(403, archiveFile.length());
    }

    /**
     * Tests the creation of an archive file with invalid file input (file doesn't exist).
     *
     * @throws IOException
     *         is thrown if an error occurs while creating the archive
     */
    @Test(expected = FileNotFoundException.class)
    public void testCreateArchiveNoInputFile() throws IOException {

        String archiveName = "testdata-io\\out\\archive2.zip";

        String baseDirectory = "testdata-io\\";
        String[] inputFiles = new String[] { "testdata-io\\file1.pdf" };

        ArchiveHelper.createArchive(archiveName, baseDirectory, inputFiles);
        File archiveFile = new File(archiveName);

        assertFalse(FileHelper.existsFile(archiveFile));

        assertTrue(!archiveFile.exists());
        assertFalse(archiveFile.exists());

        assertTrue(archiveFile.isFile());

        List<File> allFiles = Arrays.asList(archiveFile.getParentFile().listFiles());
        assertTrue(!allFiles.contains(archiveFile));
        assertFalse(allFiles.contains(archiveFile));

        assertEquals(1, archiveFile.length());
    }

}
