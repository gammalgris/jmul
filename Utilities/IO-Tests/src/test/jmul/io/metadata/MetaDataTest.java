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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.io.metadata;


import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;

import java.time.ZoneId;

import java.util.Date;

import jmul.io.metadata.MetaData;
import jmul.io.metadata.MetaDataImpl;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


/**
 * This class contains tests to check the meta data of a file.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class MetaDataTest {

    /**
     * Tests if the file name (including path) is correct.
     */
    @Test
    public void testFileName() {

        String fileName = "testdata-io\\file1.txt";
        File file = new File(fileName);

        MetaData metadata = null;
        String expectedCanonicalName = null;

        try {

            metadata = new MetaDataImpl(fileName);
            expectedCanonicalName = file.getCanonicalPath();

        } catch (IOException e) {

            fail(e.getMessage());
        }

        assertEquals(expectedCanonicalName, metadata.getFileName());
    }

    /**
     * Tests if the file name (without path and without file extension) is correct.
     */
    @Test
    public void testName() {

        String fileName = "testdata-io\\file1.txt";
        MetaData metadata = null;

        try {

            metadata = new MetaDataImpl(fileName);

        } catch (IOException e) {

            fail(e.getMessage());
        }

        assertEquals("file1", metadata.getName());
    }

    /**
     * Tests if the path (without file name) is correct.
     */
    @Test
    public void testPath() {

        String fileName = "testdata-io\\file1.txt";
        File file = new File(fileName);
        MetaData metadata = null;
        String expectedPath = null;

        try {

            metadata = new MetaDataImpl(fileName);
            expectedPath = (new File(file.getParent())).getCanonicalPath();

        } catch (IOException e) {

            fail(e.getMessage());
        }

        assertEquals(expectedPath, metadata.getPath());
    }

    /**
     * Tests if the file extension is correct.
     */
    @Test
    public void testFileExtension() {

        String fileName = "testdata-io\\file1.txt";
        MetaData metadata = null;

        try {

            metadata = new MetaDataImpl(fileName);

        } catch (IOException e) {

            fail(e.getMessage());
        }

        assertEquals(".txt", metadata.getFileExtension());
    }

    /**
     * Tests if the owner is correct.<br>
     * <br>
     * <i><b>Note:</b><br>
     * This test will fail if the user that creates the testdata and the user that executes
     * the unit tests are not the same. The metadata of created test files will reflect that.</i>
     */
    @Test
    public void testOwner() {

        String currentUser = System.getProperty("user.name");
        String fileName = "testdata-io\\file1.txt";
        MetaData metadata = null;

        try {

            metadata = new MetaDataImpl(fileName);

        } catch (IOException e) {

            fail(e.getMessage());
        }

        assertEquals(currentUser, metadata.getOwner());
    }

    /**
     * Tests if the last modification time is correct.
     */
    @Test
    public void tesLastModifiedTime() {

        String fileName = "testdata-io\\file1.txt";
        MetaData metadata = null;

        LocalDateTime threshold = LocalDateTime.now().minusHours(1L);
        Date thresholdDate = Date.from(threshold.atZone(ZoneId.systemDefault()).toInstant());

        try {

            metadata = new MetaDataImpl(fileName);

        } catch (IOException e) {

            fail(e.getMessage());
        }

        assertTrue(thresholdDate.before(metadata.getLastModifiedTime()));
    }

    /**
     * Tests if the file attribute 'size' can be determined and is correct.
     */
    @Test
    public void testAttributeSize() {

        String fileName = "testdata-io\\file1.txt";
        MetaData metadata = null;
        String attributeName = "size";
        Object expectedValue = new Long(13);
        Object actualValue = null;

        try {

            metadata = new MetaDataImpl(fileName);

        } catch (IOException e) {

            fail(e.getMessage());
        }

        try {

            actualValue = metadata.getAttribute(attributeName);

        } catch (IOException e) {

            fail(e.getMessage());
        }

        assertEquals(expectedValue, actualValue);
    }

}
