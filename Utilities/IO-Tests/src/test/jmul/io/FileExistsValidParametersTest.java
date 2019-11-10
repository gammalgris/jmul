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


import java.util.ArrayList;
import java.util.Collection;

import jmul.io.FileHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
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
public class FileExistsValidParametersTest {

    /**
     * A relative or absolute path to a file or directory.
     */
    private final String path;

    /**
     * The expected result.
     */
    private final boolean expectedResult;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param aPath
     *        a path which is checked
     * @param theExpectedResult
     *        the expected result of the check
     */
    public FileExistsValidParametersTest(String aPath, boolean theExpectedResult) {

        super();

        path = aPath;
        expectedResult = theExpectedResult;
    }

    /**
     * Tests the specified path and compares the result with the expected result.
     */
    @Test
    public void testPath() {

        boolean actualResult = FileHelper.existsFile(path);

        String message = "The test didn't produce the expected result!";
        assertEquals(message, expectedResult, actualResult);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "testdata-io\\file1.txt", true });
        parameters.add(new Object[] { "testdata-io\\doesnt_exist.txt", false });
        parameters.add(new Object[] { "testdata-io\\folder1", false });
        parameters.add(new Object[] { "", false });

        return parameters;
    }

}
