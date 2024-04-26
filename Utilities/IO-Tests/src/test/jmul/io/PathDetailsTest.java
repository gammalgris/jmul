/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package test.jmul.io;


import java.io.File;
import java.io.IOException;

import jmul.io.PathDetails;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests the {@link jmul.io.PathDetails#} class.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class PathDetailsTest {

    /**
     * Tests creating a new instacne with the default constructor.
     *
     * @throws IOException
     *         the test doesn't operate on a file or directory. No exception is expected thus.
     */
    @Test
    public void testCreatePathDetails() throws IOException {

        File controlPath = new File(".");
        String expectedCanonicalPath = controlPath.getCanonicalPath();
        String expectedPath = controlPath.getPath();

        PathDetails pathDetails = new PathDetails();

        assertEquals(expectedCanonicalPath, pathDetails.normalizedPath);
        assertEquals(expectedPath, pathDetails.path);
    }

    /**
     * Tests creating a new instance with invalid parameters (i.e. null).
     *
     * @throws IOException
     *         the test doesn't operate on a file or directory. No exception is expected thus.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatePathDetailsWithNullStringPathParameter() throws IOException {

        String path = null;

        new PathDetails(path);
    }

    /**
     * Tests creating a new instance with invalid parameters (i.e. null).
     *
     * @throws IOException
     *         the test doesn't operate on a file or directory. No exception is expected thus.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatePathDetailsWithNullFilePathParameter() throws IOException {

        File path = null;

        new PathDetails(path);
    }

    /**
     * Test creating a new instance with a valid parameter.
     *
     * @throws IOException
     *         the test doesn't operate on a file or directory. No exception is expected thus.
     */
    @Test
    public void testCreatePathDetailsWithPath() throws IOException {

        String path = ".\\a.txt";
        File controlPath = new File(path);
        String expectedCanonicalPath = controlPath.getCanonicalPath();
        String expectedPath = controlPath.getPath();

        PathDetails pathDetails = new PathDetails(path);

        assertEquals(expectedCanonicalPath, pathDetails.normalizedPath);
        assertEquals(expectedPath, pathDetails.path);
    }

    /**
     * Tests creating a new instance with a valid parameter.
     *
     * @throws IOException
     *         the test doesn't operate on a file or directory. No exception is expected thus.
     */
    @Test
    public void testCreatePathDetailsWithFile() throws IOException {

        String path = ".\\a.txt";
        File controlPath = new File(path);
        String expectedCanonicalPath = controlPath.getCanonicalPath();
        String expectedPath = controlPath.getPath();

        PathDetails pathDetails = new PathDetails(path);

        assertEquals(expectedCanonicalPath, pathDetails.normalizedPath);
        assertEquals(expectedPath, pathDetails.path);
    }

}
