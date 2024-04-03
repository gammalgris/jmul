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


import jmul.network.http.ResponseCode;
import jmul.network.http.ResponseCodes;

import jmul.test.classification.UnitTest;

import jmul.web.page.PageLoadingResult;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests the page loading result wrapper.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class PageLoadingResultTest {

    /**
     * Tests creating a wrapper instance with valid parameters.
     */
    @Test
    public void testCreatingSuccessfulResultWithValidParameters() {

        String path = "/test.html";
        byte[] content = { (byte) 1 };

        PageLoadingResult result = new PageLoadingResult(path, content);

        assertEquals(path, result.path());
        assertEquals(ResponseCodes.RC200, result.responseCode());
        assertEquals(content, result.pageContent());
        assertEquals(true, result.pageWasLoaded());
    }

    /**
     * Tests creating a wrapper instance with valid parameters.
     */
    @Test
    public void testCreatingFailedResultWithValidParameters() {

        String path = "/test.html";
        ResponseCode responseCode = ResponseCodes.RC500;

        PageLoadingResult result = new PageLoadingResult(path, responseCode);

        assertEquals(path, result.path());
        assertEquals(responseCode, result.responseCode());
        assertEquals(null, result.pageContent());
        assertEquals(false, result.pageWasLoaded());
    }

    /**
     * Tests creating a wrapper instance with null parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatingSuccessfulResultWithNullPath() {

        String path = null;
        byte[] content = { (byte) 1 };

        new PageLoadingResult(path, content);
    }

    /**
     * Tests creating a wrapper instance with null parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatingSuccessfulResultWithNullContent() {

        String path = "/test.html";
        byte[] content = null;

        new PageLoadingResult(path, content);
    }

    /**
     * Tests creating a wrapper instance with null parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatingFailedResultWithNullPath() {

        String path = null;
        ResponseCode responseCode = ResponseCodes.RC500;

        new PageLoadingResult(path, responseCode);
    }

    /**
     * Tests creating a wrapper instance with null parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatingFailedResultWithNullResultCode() {

        String path = "/test.html";
        ResponseCode responseCode = null;

        new PageLoadingResult(path, responseCode);
    }

    /**
     * Tests creating a wrapper instance with wrong result code.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatingFailedResultWithWrongResultCode() {

        String path = "/test.html";
        ResponseCode responseCode = ResponseCodes.RC200;

        new PageLoadingResult(path, responseCode);
    }

}
