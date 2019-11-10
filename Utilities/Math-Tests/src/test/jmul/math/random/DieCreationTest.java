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

package test.jmul.math.random;


import java.util.ArrayList;
import java.util.Collection;

import static jmul.math.random.DiceFactory.createDie;
import jmul.math.random.Die;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Performs tests which check the creation of a die.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DieCreationTest {

    /**
     * Returns a matrix of input parameters.
     *
     * @return a matrix of input parameters
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { 1 });
        parameters.add(new Object[] { 4 });
        parameters.add(new Object[] { 6 });
        parameters.add(new Object[] { 8 });
        parameters.add(new Object[] { 10 });
        parameters.add(new Object[] { 12 });
        parameters.add(new Object[] { 20 });
        parameters.add(new Object[] { 100 });

        return parameters;
    }

    /**
     * The initial value of the number of sides for a die.
     */
    private final int sides;

    /**
     * Creates a new test according to the specified paramters.
     * @param sides
     */
    public DieCreationTest(int sides) {

        this.sides = sides;
    }

    /**
     * Checks the creation of a die.
     */
    @Test
    public void testCreation() {

        Die die = createDie(sides);

        assertNotNull(createErrorMessage1(), die);
        assertEquals(createErrorMessage2(die, sides), sides, die.getSides());
    }

    /**
     * Creates an error message for a failed test according to the specified parameters.
     *
     * @return an error message for a failed test
     */
    private static String createErrorMessage1() {

        StringBuffer buffer = new StringBuffer();

        buffer.append("The test has failed because no die was created (null)!");

        return String.valueOf(buffer);
    }

    /**
     * Creates an error message for a failed test according to the specified parameters.
     *
     * @param aDie
     * @param anInitialValue
     *
     * @return an error message for a failed test
     */
    private static String createErrorMessage2(Die aDie, int anInitialValue) {

        StringBuffer buffer = new StringBuffer();

        buffer.append("The test has failed because the created die has a wrong number of sides (die=");
        buffer.append(aDie);
        buffer.append(", ");
        buffer.append("expected number of sides=");
        buffer.append(anInitialValue);
        buffer.append(")!");

        return String.valueOf(buffer);
    }

}
