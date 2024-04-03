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

import jmul.logging.ConsoleLogger;
import jmul.logging.Logger;

import jmul.network.http.ResponseCodes;

import jmul.test.classification.UnitTest;

import jmul.web.page.Page;
import jmul.web.page.PageLoader;
import jmul.web.page.PageLoadingResult;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests page loaders.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class PageLoaderTest {

    /**
     * Tests loading a page and then checks the loaded page content.
     */
    @Test
    public void testCreatingPageLoaderWithValidParameters() {

        Logger logger = new ConsoleLogger();

        File baseDirectory = new File(".\\web-content\\");
        File filePath = new File(".\\web-content\\test\\hello-world.html");
        Page page = new Page(baseDirectory, filePath);

        PageLoader loader = new PageLoader(logger, page);

        PageLoadingResult result = loader.loadContent();
        assertEquals(true, result.pageWasLoaded());
        assertEquals(ResponseCodes.RC200, result.responseCode());
        byte[] content = result.pageContent();
        assertEquals(211, content.length);
    }

    /**
     * Tests creating a loader insatne with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatingPageLoaderWithNullLoggerReference() {

        Logger logger = null;

        File baseDirectory = new File(".\\web-content\\");
        File filePath = new File(".\\web-content\\test\\hello-world.html");
        Page page = new Page(baseDirectory, filePath);

        new PageLoader(logger, page);
    }

    /**
     * Tests creating a loader insatne with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatingPageLoaderWithNullPageParameter() {

        Logger logger = new ConsoleLogger();

        Page page = null;

        new PageLoader(logger, page);
    }

}
