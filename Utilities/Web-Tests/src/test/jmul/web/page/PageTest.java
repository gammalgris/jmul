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

package test.jmul.web.page;


import java.io.File;
import java.io.IOException;

import jmul.test.classification.UnitTest;

import jmul.web.page.Page;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests the data structure that contains page informations
 * (i.e. informations about the underlying file).
 *
 * @author Kristian Kutin
 */
@UnitTest
public class PageTest {

    /**
     * Tests creating a new page instance with valid parameters.
     *
     * @throws IOException
     *         the file system is accessed when resolving paths. Errors can
     *         happen when doing an IO Operation on the file system.
     */
    @Test
    public void testCreatingNewPage() throws IOException {

        File baseDirectory = new File(".\\web-content\\");
        File filePath = new File(".\\web-content\\test\\hello-world.html");

        Page page = new Page(baseDirectory, filePath);
        assertEquals(baseDirectory, page.baseDirectory());
        assertEquals(filePath, page.filePath());
        assertEquals("/test/hello-world.html", page.determineWebPath());
        assertEquals(211, page.length());
    }

    /**
     * Tests creating a new page instance with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatingNewPageWithNullDirectoryParameter() {

        File baseDirectory = null;
        File filePath = new File(".\\web-content\\test\\hello-world.html");

        new Page(baseDirectory, filePath);
    }

    /**
     * Tests creating a new page instance with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatingNewPageWithNullFileParameter() {

        File baseDirectory = new File(".\\web-content\\");
        File filePath = null;

        new Page(baseDirectory, filePath);
    }

    /**
     * Tests creating a new page instance with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatingNewPageWithInvalidBaseDirectory() {
        
        File baseDirectory = new File(".\\web-content\\test\\hello-world.html");
        File filePath = new File(".\\web-content\\test\\hello-world.html");

        new Page(baseDirectory, filePath);
    }

    /**
     * Tests creating a new page instance with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatingNewPageWithInvalidFilePath() {
        
        File baseDirectory = new File(".\\web-content\\");
        File filePath = new File(".\\web-content\\");

        new Page(baseDirectory, filePath);
    }

    /**
     * Tests calling the static function for determining the web path.
     *
     * @throws IOException
     *         the file system is accessed when resolving paths. Errors can
     *         happen when doing an IO Operation on the file system.
     */
    @Test
    public void testDeterminePath() throws IOException {

        File baseDirectory = new File("./web-content");
        File filePath = new File("./web-content/test/hello-world.html");

        String result = Page.determineWebPath(baseDirectory, filePath);
        assertEquals("/test/hello-world.html", result);
    }

}
