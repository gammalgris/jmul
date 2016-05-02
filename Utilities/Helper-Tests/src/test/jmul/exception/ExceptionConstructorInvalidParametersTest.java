/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
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

package test.jmul.exception;


import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.io.ArchiveException;

import jmul.string.UnknownPlaceholderException;
import jmul.string.UnresolvedPlaceholderException;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests the instantiation of an exception.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ExceptionConstructorInvalidParametersTest {

    /**
     * An entity for invoking a specific constructor.
     */
    public ConstructorInvoker constructorInvoker;

    /**
     * Parameters to pass to the constructor.
     */
    public Object[] parameters;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aConstructorInvoker
     * @param someParameters
     */
    public ExceptionConstructorInvalidParametersTest(ConstructorInvoker aConstructorInvoker, Object... someParameters) {

        constructorInvoker = aConstructorInvoker;
        parameters = someParameters;
    }

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Tests the instantiation of an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInstantiation() throws Throwable {

        try {

            constructorInvoker.invoke(parameters);
            fail();

        } catch (InvocationTargetException e) {

            throw e.getCause();

        } catch (IllegalAccessException e) {

            fail();

        } catch (InstantiationException e) {

            fail();

        } catch (NoSuchMethodException e) {

            fail();
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


        parameters.add(new Object[] {
                       new ConstructorInvoker(ArchiveException.class, ConstructorSignatures.EXCEPTION_CONSTRUCTOR1),
                       new Object[] { null }
        });

        parameters.add(new Object[] {
                       new ConstructorInvoker(ArchiveException.class, ConstructorSignatures.EXCEPTION_CONSTRUCTOR1),
                       new Object[] { "" }
        });

        parameters.add(new Object[] {
                       new ConstructorInvoker(ArchiveException.class, ConstructorSignatures.EXCEPTION_CONSTRUCTOR1),
                       new Object[] { " " }
        });


        parameters.add(new Object[] {
                       new ConstructorInvoker(ArchiveException.class, ConstructorSignatures.EXCEPTION_CONSTRUCTOR2),
                       new Object[] { null }
        });


        parameters.add(new Object[] {
                       new ConstructorInvoker(ArchiveException.class, ConstructorSignatures.EXCEPTION_CONSTRUCTOR3),
                       new Object[] { null, new RuntimeException() }
        });

        parameters.add(new Object[] {
                       new ConstructorInvoker(ArchiveException.class, ConstructorSignatures.EXCEPTION_CONSTRUCTOR3),
                       new Object[] { "", new RuntimeException() }
        });

        parameters.add(new Object[] {
                       new ConstructorInvoker(ArchiveException.class, ConstructorSignatures.EXCEPTION_CONSTRUCTOR3),
                       new Object[] { " ", new RuntimeException() }
        });

        parameters.add(new Object[] {
                       new ConstructorInvoker(ArchiveException.class, ConstructorSignatures.EXCEPTION_CONSTRUCTOR3),
                       new Object[] { "Hello", null }
        });


        parameters.add(new Object[] {
                       new ConstructorInvoker(UnresolvedPlaceholderException.class,
                                              ConstructorSignatures.EXCEPTION_CONSTRUCTOR1), new Object[] { null }
        });

        parameters.add(new Object[] {
                       new ConstructorInvoker(UnresolvedPlaceholderException.class,
                                              ConstructorSignatures.EXCEPTION_CONSTRUCTOR1), new Object[] { "" }
        });

        parameters.add(new Object[] {
                       new ConstructorInvoker(UnresolvedPlaceholderException.class,
                                              ConstructorSignatures.EXCEPTION_CONSTRUCTOR1), new Object[] { " " }
        });


        parameters.add(new Object[] {
                       new ConstructorInvoker(UnknownPlaceholderException.class,
                                              ConstructorSignatures.EXCEPTION_CONSTRUCTOR1), new Object[] { null }
        });

        parameters.add(new Object[] {
                       new ConstructorInvoker(UnknownPlaceholderException.class,
                                              ConstructorSignatures.EXCEPTION_CONSTRUCTOR1), new Object[] { "" }
        });

        parameters.add(new Object[] {
                       new ConstructorInvoker(UnknownPlaceholderException.class,
                                              ConstructorSignatures.EXCEPTION_CONSTRUCTOR1), new Object[] { " " }
        });


        return parameters;
    }

}
