/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package test.jmul.text.reader;


import java.util.ArrayList;
import java.util.Collection;

import jmul.test.classification.UnitTest;

import jmul.text.reader.TextDocumentReaderImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests the instantiation of a reader with various invalid input parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class InstantiationWithInvalidParameters3Test {

    /**
     * An input parameter.
     */
    private String lineSeparator;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aLineSeparator
     */
    public InstantiationWithInvalidParameters3Test(String aLineSeparator) {

        lineSeparator = aLineSeparator;
    }

    /**
     * Tests the instantiation of a reader with invalid input parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInstantiation() {

        new TextDocumentReaderImpl(lineSeparator);
    }

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null });
        parameters.add(new Object[] { "" });

        return parameters;
    }

}
