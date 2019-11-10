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

package test.jmul.io;


import java.io.File;

import java.util.ArrayList;
import java.util.Collection;

import jmul.checks.exceptions.NullParameterException;

import jmul.io.FileHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class tests checking a file.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class FileExistsInvalidParameters2Test {

    /**
     * A relative or absolute path to a file or directory.
     */
    private final File path;

    /**
     * The expected exception.
     */
    private final Class expectedExceptionType;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param aPath
     *        the path which is checked
     * @param theExpectedExceptionType
     *        the expected exception type raised by the method which is tested
     */
    public FileExistsInvalidParameters2Test(File aPath, Class theExpectedExceptionType) {

        super();

        path = aPath;
        expectedExceptionType = theExpectedExceptionType;
    }

    /**
     * Tests the specified path and compares the result with the expected result.
     */
    @Test
    public void testPath() {

        try {

            FileHelper.existsFile(path);

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();

            String message =
                "The test failed! Expected exception: " + expectedExceptionType + "; actual exception: " +
                actualExceptionType;
            assertTrue(message, expectedExceptionType.isAssignableFrom(actualExceptionType));
            return;
        }

        {
            String message = "An exception is expected but none was thrown!";
            fail(message);
        }
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null, NullParameterException.class });

        return parameters;
    }

}
