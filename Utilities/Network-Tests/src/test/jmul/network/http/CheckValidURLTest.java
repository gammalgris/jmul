/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2015  Kristian Kutin
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

package test.jmul.network.http;


import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Collection;

import jmul.network.http.CheckURL;
import jmul.network.http.ResponseCode;
import jmul.network.http.ResponseCodes;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains several tests for the CheckURL utility class.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class CheckValidURLTest {

    /**
     * Returns a matrix of URLs and expected results codes.
     *
     * @return a matrix of URLs and  expected result codes
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "http://www.spiegel.de/", new Integer(200) });
        parameters.add(new Object[] { "http://www.sspiegel.dde/", new Integer(503) });

        parameters.add(new Object[] { "http://intranet-ref.zka.bfinv.de", new Integer(503) });
        parameters.add(new Object[] { "http://intranet-ref.zka.bfinv.doof", new Integer(503) });
        parameters.add(new Object[] { "https://b--ws6s901.izn.zka.bfinv.de/ED/Citrix/XenApp/site", new Integer(200) });
        parameters.add(new Object[] { "http://10.188.43.150/IZNRS/Citrix/XenApp", new Integer(200) });
        parameters.add(new Object[] {
                       "http://piav-operativ.prep.bka.extrapol.de:8137/piav_notification/services/meldungsdienst",
                       new Integer(200)
        });

        return parameters;
    }

    /**
     * The URL which is to be checked.
     */
    private final URL url;

    /**
     * The expected response code.
     */
    private final ResponseCode expectedResponseCode;

    /**
     * Creates a new test.
     *
     * @param aURLString
     * @param aResponseCodeValue
     */
    public CheckValidURLTest(String aURLString, int aResponseCodeValue) {

        try {

            url = new URL(aURLString);

        } catch (MalformedURLException e) {

            String message = "Test couldn't be set up!";
            throw new RuntimeException(message, e);
        }

        expectedResponseCode = ResponseCodes.getResponseCode(aResponseCodeValue);
    }

    /**
     * Checks if a specified URL returns the expected result code.
     */
    @Test
    public void testCheckURL() {

        try {

            ResponseCode actualResponseCode = CheckURL.checkURL(url);
            assertEquals(expectedResponseCode, actualResponseCode);

        } catch (IOException e) {

            fail(String.valueOf(e));
        }
    }

}
