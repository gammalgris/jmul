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

import jmul.test.classification.UnitTest;

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
public class DieCreationFailureTest {

    /**
     * Returns a matrix of input parameters.
     *
     * @return a matrix of input parameters
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { 0 });
        parameters.add(new Object[] { -1 });

        return parameters;
    }

    /**
     * The initial value of the number of sides for a die.
     */
    private final int sides;

    /**
     * Creates a new test according to the specified paramters.
     *
     * @param sides
     */
    public DieCreationFailureTest(int sides) {

        this.sides = sides;
    }

    /**
     * Checks the creation of a die.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreation() {

        createDie(sides);
    }

}
