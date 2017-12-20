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

package test.jmul.exception;


import java.io.File;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.csv.reader.CsvStructureException;

import jmul.io.archives.ArchiveException;
import jmul.io.CopyFileException;
import jmul.io.CoupledStreamsException;
import jmul.io.FileDeletionException;
import jmul.io.NestedStreamsException;

import jmul.math.formula.parser.FormulaParserException;
import jmul.math.hash.archive.ExistingEntryException;
import jmul.math.hash.archive.UnknownClassException;

import jmul.misc.annotations.Modified;
import jmul.misc.exceptions.InitializationException;
import jmul.misc.generators.GeneratorException;
import jmul.misc.state.IllegalStateTransitionException;
import jmul.misc.state.UnknownStateException;

import jmul.network.NetworkException;

import jmul.persistence.InvalidIDException;
import jmul.persistence.InvalidRootNodeException;
import jmul.persistence.PersistenceException;

import jmul.reflection.classes.MissingAccessorException;
import jmul.reflection.constructors.ConstructorInvoker;
import jmul.reflection.constructors.ConstructorSignatures;

import static jmul.string.Constants.COMMA;
import jmul.string.QuoteNotClosedException;
import jmul.string.UnknownPlaceholderException;
import jmul.string.UnresolvedPlaceholderException;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.FailedTestException;
import jmul.test.exceptions.SetUpException;
import jmul.test.exceptions.TearDownException;

import jmul.time.StopwatchException;

import jmul.web.WebServerException;
import jmul.web.page.PageHandlerException;
import jmul.web.page.PageLoaderException;
import jmul.web.soap.CodeGeneratorException;
import jmul.web.soap.TooManyClassesException;
import jmul.web.soap.WebServiceProxyException;

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

        String messagePrefix =
            constructorInvoker.getAssociatedClass().getName() + "@testInstantiation" + getSignatureString();

        try {

            constructorInvoker.invoke(parameters);
            fail(messagePrefix);

        } catch (InvocationTargetException e) {

            throw e.getCause();

        } catch (IllegalAccessException e) {

            fail(messagePrefix);

        } catch (InstantiationException e) {

            fail(messagePrefix);

        } catch (NoSuchMethodException e) {

            fail(messagePrefix);
        }
    }

    /**
     * Returns the signatature of the constructor invocation.
     *
     * @return a signature string
     */
    public String getSignatureString() {

        StringBuilder buffer = new StringBuilder("(");
        boolean first = true;

        for (Object o : parameters) {

            if (first) {

                first = false;

            } else {

                buffer.append(COMMA);
            }

            if (o == null) {

                buffer.append(o);

            } else {

                buffer.append(o.getClass().getName());
            }
        }

        buffer.append(")");

        return buffer.toString();
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();


        // Exceptions from package Document-CSV

        addDefaultTestCases(parameters, CsvStructureException.class);


        // Exceptions from package Formula

        addDefaultTestCases(parameters, FormulaParserException.class);


        // Exceptions from package Math

        addClassParameterTestCases(parameters, ExistingEntryException.class);
        addClassParameterTestCases(parameters, UnknownClassException.class);


        // Exceptions from package Misc

        //addDefaultTestCases(parameters, EmptyArrayParameterException.class);
        //addDefaultTestCases(parameters, EmptyStringParameterException.class);
        addDefaultTestCases2(parameters, InitializationException.class);
        addDefaultTestCases2(parameters, jmul.misc.exceptions.InstantiationException.class);
        //addDefaultTestCases(parameters, NullArrayParameterException.class);
        //addDefaultTestCases(parameters, NullFileParameterException.class);
        //addDefaultTestCases(parameters, NullParameterException.class);
        addDefaultTestCases(parameters, IllegalStateTransitionException.class);
        addDefaultTestCases(parameters, UnknownStateException.class);
        //addDefaultTestCases(parameters, NullDirectoryParameterException.class);
        //addDefaultTestCases(parameters, NullNumberException.class);


        // Exceptions from package Network

        addDefaultTestCases(parameters, NetworkException.class);


        // Exceptions from package IO

        addDefaultTestCases(parameters, ArchiveException.class);
        addDefaultTestCases(parameters, CopyFileException.class);
        addDefaultTestCases(parameters, CoupledStreamsException.class);
        addFileDeletionExceptionTestCases(parameters);
        addDefaultTestCases(parameters, NestedStreamsException.class);


        // Exceptions from package Persistence

        addDefaultTestCases2(parameters, GeneratorException.class);
        addDefaultTestCases(parameters, InvalidIDException.class);
        addDefaultTestCases(parameters, InvalidRootNodeException.class);
        addDefaultTestCases(parameters, PersistenceException.class);


        // Exceptions from package Reflection

        addDefaultTestCases(parameters, MissingAccessorException.class);


        // Exceptions from package String

        addMessageOnlyTestCases(parameters, UnresolvedPlaceholderException.class);
        addMessageOnlyTestCases(parameters, UnknownPlaceholderException.class);
        addDefaultTestCases(parameters, QuoteNotClosedException.class);


        // Exceptions from package Test

        addDefaultTestCases(parameters, FailedTestException.class);
        addDefaultTestCases(parameters, SetUpException.class);
        addDefaultTestCases(parameters, TearDownException.class);
        addDefaultTestCases(parameters, InvalidIDException.class);


        // Exceptions from package Time

        addDefaultTestCases(parameters, StopwatchException.class);


        // Exceptions from package Web

        addDefaultTestCases(parameters, CodeGeneratorException.class);
        addDefaultTestCases2(parameters, TooManyClassesException.class);
        addDefaultTestCases(parameters, WebServiceProxyException.class);
        addDefaultTestCases(parameters, WebServerException.class);
        addDefaultTestCases(parameters, PageHandlerException.class);
        addDefaultTestCases(parameters, PageLoaderException.class);


        return parameters;
    }

    /**
     * Adds test cases according for the specified exception class.
     *
     * @param someParameters
     * @param anExceptionClass
     */
    private static void addDefaultTestCases(Collection<Object[]> someParameters, Class anExceptionClass) {

        addMessageOnlyTestCases(someParameters, anExceptionClass);
        addCauseOnlyTestCases(someParameters, anExceptionClass);
        addMessageCauseCombinationsTestCases(someParameters, anExceptionClass);
    }

    /**
     * Adds test cases according for the specified exception class.
     *
     * @param someParameters
     * @param anExceptionClass
     */
    private static void addDefaultTestCases2(Collection<Object[]> someParameters, Class anExceptionClass) {

        addMessageOnlyTestCases(someParameters, anExceptionClass);
        addMessageCauseCombinationsTestCases(someParameters, anExceptionClass);
    }

    /**
     * Adds test cases according for the specified exception class.
     *
     * @param someParameters
     * @param anExceptionClass
     */
    private static void addMessageOnlyTestCases(Collection<Object[]> someParameters, Class anExceptionClass) {

        Class[] signature = ConstructorSignatures.getMessageConstructorSignature();

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature), new Object[] { null } });

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature), new Object[] { "" } });

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature), new Object[] { " " } });
    }

    /**
     * Adds test cases according for the specified exception class.
     *
     * @param someParameters
     * @param anExceptionClass
     */
    private static void addCauseOnlyTestCases(Collection<Object[]> someParameters, Class anExceptionClass) {

        Class[] signature = ConstructorSignatures.getCauseConstructorSignature();

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature), new Object[] { null } });
    }

    /**
     * Adds test cases according for the specified exception class.
     *
     * @param someParameters
     * @param anExceptionClass
     */
    private static void addMessageCauseCombinationsTestCases(Collection<Object[]> someParameters,
                                                             Class anExceptionClass) {

        Class[] signature = ConstructorSignatures.getMessageCauseConstructorSignature();

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature),
                                          new Object[] { null, new RuntimeException() } });

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature),
                                          new Object[] { "", new RuntimeException() } });

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature),
                                          new Object[] { " ", new RuntimeException() } });

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature),
                                          new Object[] { "Hello", null } });
    }

    /**
     * Adds test cases for a specific exception.
     *
     * @param someParameters
     */
    private static void addFileDeletionExceptionTestCases(Collection<Object[]> someParameters) {

        Class exceptionClass = FileDeletionException.class;
        Class[] signature = ConstructorSignatures.getMessageFileConstructorSignature();

        someParameters.add(new Object[] { new ConstructorInvoker(exceptionClass, signature),
                                          new Object[] { null, new File("test.txt") } });

        someParameters.add(new Object[] { new ConstructorInvoker(exceptionClass, signature),
                                          new Object[] { "", new File("test.txt") } });

        someParameters.add(new Object[] { new ConstructorInvoker(exceptionClass, signature),
                                          new Object[] { " ", new File("test.txt") } });

        someParameters.add(new Object[] { new ConstructorInvoker(exceptionClass, signature),
                                          new Object[] { "Hello", null } });

    }

    /**
     * Adds test cases for a specific exception.
     *
     * @param someParameters
     */
    private static void addClassParameterTestCases(@Modified Collection<Object[]> someParameters,
                                                   Class anExceptionClass) {

        Class[] signature = ConstructorSignatures.getClassParameterConstructor();

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature), new Object[] { null } });
    }

}
