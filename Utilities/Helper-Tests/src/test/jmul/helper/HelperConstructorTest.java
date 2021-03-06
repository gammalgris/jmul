/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.helper;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.Collection;

import jmul.math.formula.parser.ParserHelper;

import jmul.math.formula.parser.transformation.tokentree2componenttree.TransformationHelper;

import jmul.test.classification.CodingStyleCheck;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests the constructor of helper classes.
 *
 * @author Kristian Kutin
 */
@CodingStyleCheck
@RunWith(Parameterized.class)
public class HelperConstructorTest {

    /**
     * A helper class.
     */
    private Class helperClass;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aHelperClass
     */
    public HelperConstructorTest(Class aHelperClass) {

        helperClass = aHelperClass;
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
     * Checks if the helper class is final.
     */
    @Test
    public void testClassModifiers() {

        {
            String message = "The helper class " + helperClass.getName() + " is not declared final!";
            assertTrue(message, Modifier.isFinal(helperClass.getModifiers()));
        }
    }

    /**
     * Checks if only the default constructor has been declared.
     */
    @Test
    public void testConstructors() {

        Constructor[] constructors = helperClass.getDeclaredConstructors();
        assertEquals(1, constructors.length);

        Constructor constructor = constructors[0];
        assertEquals(0, constructor.getParameterTypes().length);
    }

    /**
     * Checks if instantiating the utility class fails.
     */
    @Test(expected = IllegalAccessException.class)
    public void testConstructorAccess() throws InstantiationException, IllegalAccessException, NoSuchMethodException,
                                               InvocationTargetException {

        Constructor c = helperClass.getDeclaredConstructor();

        {
            String message = "The default constructor of " + helperClass.getName() + " is not private!";
            assertTrue(message, Modifier.isPrivate(c.getModifiers()));
        }

        c.newInstance();
    }

    /**
     * Checks if instantiating the utility class fails.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testInstantiation() throws Throwable {

        Constructor c = helperClass.getDeclaredConstructor();

        try {

            c.setAccessible(true);
            c.newInstance();
            fail();

        } catch (InvocationTargetException e) {

            throw e.getCause();

        } finally {

            c.setAccessible(false);
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

        parameters.add(new Object[] { jmul.concurrent.threads.ThreadHelper.class });
        parameters.add(new Object[] { jmul.math.Constants.class });
        parameters.add(new Object[] { jmul.math.MathHelper.class });
        parameters.add(new Object[] { jmul.math.bool.BooleanHelper.class });
        parameters.add(new Object[] { jmul.string.Constants.class });
        parameters.add(new Object[] { jmul.checks.ParameterCheckHelper.class });
        parameters.add(new Object[] { jmul.misc.state.StateHelper.class });
        parameters.add(new Object[] { jmul.network.http.CheckURL.class });
        parameters.add(new Object[] { jmul.network.ip.CheckIP.class });
        parameters.add(new Object[] { jmul.network.ftp.CheckFTP.class });
        parameters.add(new Object[] { jmul.io.archives.ArchiveHelper.class });
        parameters.add(new Object[] { jmul.io.FileHelper.class });
        parameters.add(new Object[] { jmul.io.FileDeletionHelper.class });
        parameters.add(new Object[] { jmul.io.PathHelper.class });
        parameters.add(new Object[] { jmul.web.WebServerRunner.class });
        parameters.add(new Object[] { jmul.transformation.xml.TransformationHelper.class });
        parameters.add(new Object[] { jmul.math.random.DiceFactory.class });
        parameters.add(new Object[] { jmul.math.rules.RuleFactory.class });
        parameters.add(new Object[] { jmul.math.prime.PrimeNumberHelper.class });
        parameters.add(new Object[] { jmul.math.NumberPropertiesHelper.class });
        parameters.add(new Object[] { jmul.math.hash.HashHelper.class });
        parameters.add(new Object[] { jmul.transformation.xml.rules.TransformationConstants.class });
        parameters.add(new Object[] { jmul.transformation.xml.annotations.AnnotationHelper.class });
        parameters.add(new Object[] { jmul.string.TextHelper.class });
        parameters.add(new Object[] { jmul.io.text.TextFileHelper.class });
        parameters.add(new Object[] { jmul.reflection.ContainerHelper.class });
        parameters.add(new Object[] { jmul.io.StreamsHelper.class });
        parameters.add(new Object[] { jmul.xml.XmlMarkupHelper.class });
        parameters.add(new Object[] { jmul.math.hash.Constants.class });
        parameters.add(new Object[] { jmul.misc.equality.EqualityHelper.class });
        parameters.add(new Object[] { jmul.network.http.UploadHelper.class });
        parameters.add(new Object[] { jmul.string.IndentationHelper.class });
        parameters.add(new Object[] { jmul.reflection.classes.AccessorHelper.class });
        parameters.add(new Object[] { jmul.transformation.xml.rules.xml2object.RuleHelper.class });
        parameters.add(new Object[] { jmul.math.notation.NotationHelper.class });
        parameters.add(new Object[] { jmul.document.csv.CsvHelper.class });
        parameters.add(new Object[] { jmul.reflection.ReflectionHelper.class });
        parameters.add(new Object[] { jmul.reflection.classes.ClassHelper.class });
        parameters.add(new Object[] { jmul.math.formula.parser.ParserHelper.class });
        parameters.add(new Object[] { jmul.math.formula.parser.transformation.tokentree2componenttree.TransformationHelper.class });
        parameters.add(new Object[] { jmul.math.formula.parser.transformation.tokensequence2tokentree.TransformationHelper.class });

        return parameters;
    }

}
