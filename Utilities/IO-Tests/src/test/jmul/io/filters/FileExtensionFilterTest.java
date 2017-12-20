/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

package test.jmul.io.filters;


import java.io.File;
import java.io.FileFilter;

import jmul.io.filters.FileExtensionFilter;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains tests to check if the file extension filter works properly.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class FileExtensionFilterTest extends FileFilterTestBase {

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        setFileFilter(new FileExtensionFilter("txt"));
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {

        setFileFilter(null);
    }

    /**
     * Tests if the filter correctly recognizes a file with the expected file extension.
     * No actual call to the underlying file system is made.
     */
    @Test
    public void testFilterOnValidFileName() {

        FileFilter filter = getFileFilter();

        File path = new File("testdata-io\\file1.txt");
        boolean actualResult = filter.accept(path);

        assertTrue(actualResult);
    }

    /**
     * Tests if the filter correctly ignores a file. No actual call to the underlying
     * file system is made.
     */
    @Test
    public void testFilterOnInvalidFileName() {

        FileFilter filter = getFileFilter();

        File path = new File("testdata-io\\file3.bat");
        boolean actualResult = filter.accept(path);

        assertFalse(actualResult);
    }

    /**
     * Tests if the filter correctly ignores a directory. No actual call to the underlying
     * file system is made.
     */
    @Test
    public void testFilterOnDirectoryName() {

        FileFilter filter = getFileFilter();

        File path = new File("testdata-io\\folder1");
        boolean actualResult = filter.accept(path);

        assertFalse(actualResult);
    }

    /**
     * Tests if the filter correctly identifies files. A call to the underlying
     * file system is made.
     */
    @Test
    public void testFilterOnFileSystem() {

        FileFilter filter = getFileFilter();

        File basedir = new File("testdata-io");
        File[] results = basedir.listFiles(filter);

        assertEquals(2, results.length);
    }

}
