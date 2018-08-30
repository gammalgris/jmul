/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package test.jmul.persistence;


import java.io.File;
import java.io.IOException;

import jmul.persistence.file.FileManager;

import jmul.test.classification.ModuleTest;

import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains tests to check the creation of files via a file manager.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class FileManagerFileTest extends FileManagerTestBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = ROOT_DIRECTORY + "File-Manager1";

    /**
     * A file manager.
     */
    private FileManager fileManager;

    /**
     * Preparations before a test.
     */
    @Before
    public void setUp() {

        initBaseDirectory(BASEDIR);
        fileManager = initFileManager(BASEDIR);
    }

    /**
     * Cleanup after a test.
     */
    @After
    public void tearDown() {

        fileManager.shutdown();
        fileManager = null;
    }

    /**
     * Test creating a new file via the file manager.
     */
    @Test
    public void testSingleFileCreation() {

        File file = fileManager.newFile("Test");
        assertNotNull(file);

        try {

            boolean result = file.createNewFile();
            assertTrue(result);

        } catch (IOException e) {

            fail();
        }
    }

}
