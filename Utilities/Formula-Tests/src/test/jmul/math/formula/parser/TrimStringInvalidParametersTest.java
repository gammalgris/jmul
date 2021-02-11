/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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

package test.jmul.math.formula.parser;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.formula.parser.ParserHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests trimming strings with invalid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class TrimStringInvalidParametersTest {

    /**
     * Some input.
     */
    private final String input;

    /**
     * The expected exception type.
     */
    private final Class expectedExceptionType;

    /**
     *
     * @param anInput
     * @param theExpectedExceptiontype
     */
    public TrimStringInvalidParametersTest(String anInput, Class theExpectedExceptiontype) {

        super();

        input = anInput;
        expectedExceptionType = theExpectedExceptiontype;
    }

    /**
     * Tests trimming srings.
     */
    @Test
    public void trimString() {

        try {

            ParserHelper.trimString(input);
            fail(String.format("An exception (%s) is expectd but none was thrown!", expectedExceptionType));

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();
            if (!expectedExceptionType.isAssignableFrom(actualExceptionType)) {

                fail(String.format("A different exception (%s) was thrown than was expected (%s)!", actualExceptionType,
                                   expectedExceptionType));
            }
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

        parameters.add(new Object[] { null, NullPointerException.class });

        return parameters;
    }

}
