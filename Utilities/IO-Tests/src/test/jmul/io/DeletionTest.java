/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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


import java.io.File;

import jmul.checks.exceptions.NullFileNameParameterException;
import jmul.checks.exceptions.NullFileParameterException;

import jmul.io.FileDeletionException;
import jmul.io.FileDeletionHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertFalse;
import org.junit.Test;


/**
 * This class contains several tests regarding the deletion utility.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class DeletionTest {

    /**
     * Tests deleting an empty folder.
     */
    @Test
    public void testDeleteEmptyFolder() {

        String directoryPath = "testdata-io/deletion/folder1";
        FileDeletionHelper.delete(directoryPath);

        File file = new File(directoryPath);

        String message = "The directory \"" + directoryPath + "\" still exists!";
        assertFalse(message, file.exists());
    }

    /**
     * Tests deleting a folder that doesn't exist.
     */
    @Test(expected = FileDeletionException.class)
    public void testDeleteNotExistingFolder() {

        String directoryPath = "testdata-io/deletion/folder4";
        FileDeletionHelper.delete(directoryPath);
    }

    /**
     * Tests deleting a folder that contains subdirectories and files.
     */
    @Test(expected = FileDeletionException.class)
    public void testDeleteFilledFolder() {

        String directoryPath = "testdata-io/deletion/folder2";
        FileDeletionHelper.delete(directoryPath);
    }

    /**
     * Tests recursively deleting a folder that contains subdirectories and
     * files.
     */
    @Test
    public void testDeleteRecursivelyFilledFolder() {

        String directoryPath = "testdata-io/deletion/folder4";
        FileDeletionHelper.delete(directoryPath, true);

        File file = new File(directoryPath);

        String message = "The directory \"" + directoryPath + "\" still exists!";
        assertFalse(message, file.exists());
    }

    /**
     * Tests deleting a file.
     */
    @Test
    public void testDeleteFile() {

        String filePath = "testdata-io/deletion/file1.txt";
        FileDeletionHelper.delete(filePath);

        File file = new File(filePath);

        String message = "The file \"" + filePath + "\" still exists!";
        assertFalse(message, file.exists());
    }

    /**
     * Tests deleting a protected file.
     */
    @Test(expected = FileDeletionException.class)
    public void testDeleteProtectedFile() {

        String filePath = "testdata-io/deletion/protected.txt";
        FileDeletionHelper.delete(filePath);
    }

    /**
     * Tests deleting an archivable file.
     */
    @Test
    public void testDeleteArchivableFile() {

        String filePath = "testdata-io/deletion/archivable.txt";
        FileDeletionHelper.delete(filePath);

        File file = new File(filePath);

        String message = "The file \"" + filePath + "\" still exists!";
        assertFalse(message, file.exists());
    }

    /**
     * Tests deleting a system file.
     */
    @Test
    public void testDeleteSystemFile() {

        String filePath = "testdata-io/deletion/sysfile.txt";
        FileDeletionHelper.delete(filePath);

        File file = new File(filePath);

        String message = "The file \"" + filePath + "\" still exists!";
        assertFalse(message, file.exists());
    }

    /**
     * Tests deleting a hidden file.
     */
    @Test
    public void testDeleteHiddenFile() {

        String filePath = "testdata-io/deletion/hidden.txt";
        FileDeletionHelper.delete(filePath);

        File file = new File(filePath);

        String message = "The file \"" + filePath + "\" still exists!";
        assertFalse(message, file.exists());
    }

    /**
     * Tests deleting a file which is not indexed.
     */
    @Test
    public void testDeleteNotIndexedFile() {

        String filePath = "testdata-io/deletion/notindexed.txt";
        FileDeletionHelper.delete(filePath);

        File file = new File(filePath);

        String message = "The file \"" + filePath + "\" still exists!";
        assertFalse(message, file.exists());
    }

    /**
     * Tests deleting a file which doesn't exist.
     */
    @Test(expected = FileDeletionException.class)
    public void testDeleteNotExistingFile() {

        String filePath = "testdata-io/deletion/notexisting.txt";
        FileDeletionHelper.delete(filePath);
    }

    /**
     * Tests deleting a symbolic link.
     */
    @Test(expected = FileDeletionException.class)
    public void testDeleteSymbolicLink() {

        String filePath = "testdata-io/deletion/folder6/folder7/link.txt";
        FileDeletionHelper.delete(filePath);
    }

    /**
     * Tests invoking the deletion function with a <code>null</code> parameter.
     */
    @Test(expected = NullFileNameParameterException.class)
    public void testDeleteNullPathParameter() {

        String path = null;
        FileDeletionHelper.delete(path);
    }

    /**
     * Tests invoking the deletion function with a <code>null</code> parameter.
     */
    @Test(expected = NullFileNameParameterException.class)
    public void testDeleteNullPathParameter2() {

        String path = null;
        FileDeletionHelper.delete(path, false);
    }

    /**
     * Tests invoking the deletion function with a <code>null</code> parameter.
     */
    @Test(expected = NullFileParameterException.class)
    public void testDeleteNullPathParameter3() {

        File path = null;
        FileDeletionHelper.delete(path);
    }

    /**
     * Tests invoking the deletion function with a <code>null</code> parameter.
     */
    @Test(expected = NullFileParameterException.class)
    public void testDeleteNullPathParameter4() {

        File path = null;
        FileDeletionHelper.delete(path, false);
    }

}
