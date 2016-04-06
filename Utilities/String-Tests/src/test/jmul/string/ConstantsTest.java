/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package test.jmul.string;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import jmul.string.Constants;

import static org.junit.Assert.fail;
import org.junit.Test;


/**
 * Tests the utility class.
 *
 * @author Kristian Kutin
 */
public class ConstantsTest {

    /**
     * Checks if instantiating the utility class fails.
     */
    @Test
    public void testInstantiation() {

        try {

            Constants.class.newInstance();

        } catch (IllegalAccessException e) {

            return;

        } catch (InstantiationException e) {

            fail(e.getMessage());
        }

        fail("The default constructor should be private!");
    }

    /**
     * Checks if instantiating the utility class fails.
     */
    @Test(expected = InvocationTargetException.class)
    public void testInstantiation2() throws NoSuchMethodException, InstantiationException, IllegalAccessException,
                                            InvocationTargetException {

        Constructor<Constants> c = Constants.class.getDeclaredConstructor();
        c.setAccessible(true);
        c.newInstance();

        fail("The constructor is supposed to throw a java.lang.UnsupportedOperationException!");
    }

}
