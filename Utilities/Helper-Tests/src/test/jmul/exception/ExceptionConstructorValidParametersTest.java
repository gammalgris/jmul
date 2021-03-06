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

package test.jmul.exception;


import java.io.File;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.ws.WebServiceException;

import jmul.checks.exceptions.EmptyArrayParameterException;
import jmul.checks.exceptions.EmptyStringParameterException;
import jmul.checks.exceptions.NullArrayParameterException;
import jmul.checks.exceptions.NullDirectoryParameterException;
import jmul.checks.exceptions.NullFileParameterException;
import jmul.checks.exceptions.NullListParameterException;
import jmul.checks.exceptions.NullNumberException;
import jmul.checks.exceptions.NullParameterException;
import jmul.checks.exceptions.UnexpectedSizeException;

import jmul.csv.reader.CsvStructureException;

import jmul.io.CopyFileException;
import jmul.io.CoupledStreamsException;
import jmul.io.FileDeletionException;
import jmul.io.NestedStreamsException;
import jmul.io.archives.ArchiveException;

import jmul.math.formula.parser.FormulaParsingException;
import jmul.math.hash.archive.ExistingEntryException;
import jmul.math.hash.archive.UnknownClassException;

import jmul.metainfo.annotations.Modified;

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

import jmul.string.QuoteNotClosedException;
import jmul.string.UnknownPlaceholderException;
import jmul.string.UnresolvedPlaceholderException;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.FailedTestException;
import jmul.test.exceptions.SetUpException;
import jmul.test.exceptions.TearDownException;

import jmul.time.StopwatchException;

import jmul.web.page.PageHandlerException;
import jmul.web.page.PageLoaderException;
import jmul.web.soap.CodeGeneratorException;
import jmul.web.soap.TooManyClassesException;
import jmul.web.soap.WebServiceProxyException;

import jmul.xml.writer.WriterException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
public class ExceptionConstructorValidParametersTest {

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
    public ExceptionConstructorValidParametersTest(ConstructorInvoker aConstructorInvoker, Object... someParameters) {

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
     * Returns a message which has been specified within the parameters.
     *
     * @return a message
     */
    public String getMessage() {

        String message = null;

        for (Object o : parameters) {

            if (o instanceof String) {

                message = (String) o;
                break;
            }
        }

        return message;
    }

    /**
     * Returns a cause which has been specified within the parameters.
     *
     * @return a cause
     */
    public Throwable getCause() {

        Throwable cause = null;

        for (Object o : parameters) {

            if (o instanceof Throwable) {

                cause = (Throwable) o;
                break;
            }
        }

        return cause;
    }

    /**
     * Tests the instantiation of an exception.
     */
    @Test()
    public void testInstantiation() {

        String messagePrefix = constructorInvoker.getAssociatedClass().getName() + "@testInstantiation";

        try {

            Exception e = (Exception) constructorInvoker.invoke(parameters);

            String expectedMessage = getMessage();

            if (expectedMessage != null) {

                if (FileDeletionException.class.equals(constructorInvoker.getAssociatedClass())) {

                    assertTrue(messagePrefix + "  check exception message", e.getMessage().startsWith(getMessage()));

                } else {

                    assertEquals(messagePrefix + "  check exception message", getMessage(), e.getMessage());
                }
            }

            assertEquals(messagePrefix + "  check exception cause", getCause(), e.getCause());

        } catch (InvocationTargetException e) {

            fail(messagePrefix);

        } catch (IllegalAccessException e) {

            fail(messagePrefix);

        } catch (InstantiationException e) {

            fail(messagePrefix);

        } catch (NoSuchMethodException e) {

            fail(messagePrefix);
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


        // Exceptions from package Document-CSV

        addDefaultTestCases(parameters, CsvStructureException.class);


        // Exceptions from package Formula

        addDefaultConstructorTestCase(parameters, FormulaParsingException.class);


        // Exceptions from package Math

        addClassParameterTestCases(parameters, ExistingEntryException.class);
        addClassParameterTestCases(parameters, UnknownClassException.class);
        addSizesParametersTestCases(parameters, UnexpectedSizeException.class);


        // Exceptions from package Misc

        addDefaultConstructorTestCase(parameters, EmptyArrayParameterException.class);
        addDefaultConstructorTestCase(parameters, EmptyStringParameterException.class);
        addDefaultTestCases2(parameters, InitializationException.class);
        addDefaultTestCases2(parameters, jmul.misc.exceptions.InstantiationException.class);
        //MultipleCausesException is not handled here
        addDefaultConstructorTestCase(parameters, NullArrayParameterException.class);
        addDefaultConstructorTestCase(parameters, NullFileParameterException.class);
        addDefaultConstructorTestCase(parameters, NullParameterException.class);
        addDefaultTestCases(parameters, IllegalStateTransitionException.class);
        addDefaultTestCases(parameters, UnknownStateException.class);
        addDefaultConstructorTestCase(parameters, NullDirectoryParameterException.class);
        addDefaultConstructorTestCase(parameters, NullNumberException.class);
        addDefaultConstructorTestCase(parameters, NullListParameterException.class);


        // Exceptions from package Network

        addDefaultTestCases(parameters, NetworkException.class);


        // Exception from package IO

        addDefaultTestCases(parameters, ArchiveException.class);
        addDefaultTestCases(parameters, CopyFileException.class);
        addDefaultTestCases(parameters, CoupledStreamsException.class);
        addFileDeletionExceptionTestCases(parameters);
        addDefaultTestCases(parameters, NestedStreamsException.class);


        // Exception from package Persistence

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


        // Exception from package Test

        addDefaultTestCases(parameters, FailedTestException.class);
        addDefaultTestCases(parameters, SetUpException.class);
        addDefaultTestCases(parameters, TearDownException.class);
        addDefaultTestCases2(parameters, InvalidIDException.class);


        // Exception from package Time

        addDefaultTestCases(parameters, StopwatchException.class);


        // Exceptions from package Web

        addDefaultTestCases(parameters, CodeGeneratorException.class);
        addDefaultTestCases3(parameters, TooManyClassesException.class);
        addDefaultTestCases(parameters, WebServiceProxyException.class);
        addDefaultTestCases(parameters, WebServiceException.class);
        addDefaultTestCases(parameters, PageHandlerException.class);
        addDefaultTestCases(parameters, PageLoaderException.class);


        // Exceptions from package Xml

        addDefaultTestCases(parameters, WriterException.class);


        return parameters;
    }

    /**
     * Adds testcases according for the specified exception class.
     *
     * @param someParameters
     * @param anExceptionClass
     */
    private static void addDefaultTestCases(@Modified Collection<Object[]> someParameters, Class anExceptionClass) {

        addDefaultConstructorTestCase(someParameters, anExceptionClass);
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
    private static void addDefaultTestCases2(@Modified Collection<Object[]> someParameters, Class anExceptionClass) {

        addMessageOnlyTestCases(someParameters, anExceptionClass);
        addMessageCauseCombinationsTestCases(someParameters, anExceptionClass);
    }

    /**
     * Adds testcases according for the specified exception class.
     *
     * @param someParameters
     * @param anExceptionClass
     */
    private static void addDefaultTestCases3(@Modified Collection<Object[]> someParameters, Class anExceptionClass) {

        addDefaultConstructorTestCase(someParameters, anExceptionClass);
        addMessageOnlyTestCases(someParameters, anExceptionClass);
        addMessageCauseCombinationsTestCases(someParameters, anExceptionClass);
    }

    /**
     * Adds test cases according for the specified exception class.
     *
     * @param someParameters
     * @param anExceptionClass
     */
    private static void addDefaultConstructorTestCase(@Modified Collection<Object[]> someParameters,
                                                      Class anExceptionClass) {

        Class[] signature = ConstructorSignatures.getDefaultConstructorSignature();

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature), new Object[] { } });
    }

    /**
     * Adds test cases according for the specified exception class.
     *
     * @param someParameters
     * @param anExceptionClass
     */
    private static void addMessageOnlyTestCases(@Modified Collection<Object[]> someParameters, Class anExceptionClass) {

        Class[] signature = ConstructorSignatures.getMessageConstructorSignature();

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature),
                                          new Object[] { "Hello" } });
    }

    /**
     * Adds test cases according for the specified exception class.
     *
     * @param someParameters
     * @param anExceptionClass
     */
    private static void addCauseOnlyTestCases(@Modified Collection<Object[]> someParameters, Class anExceptionClass) {

        Class[] signature = ConstructorSignatures.getCauseConstructorSignature();

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature),
                                          new Object[] { new RuntimeException() } });
    }

    /**
     * Adds test cases according for the specified exception class.
     *
     * @param someParameters
     * @param anExceptionClass
     */
    private static void addMessageCauseCombinationsTestCases(@Modified Collection<Object[]> someParameters,
                                                             Class anExceptionClass) {

        Class[] signature = ConstructorSignatures.getMessageCauseConstructorSignature();

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature),
                                          new Object[] { "Hello", new RuntimeException() } });
    }

    /**
     * Adds test cases for a specific exception.
     *
     * @param someParameters
     */
    private static void addFileDeletionExceptionTestCases(@Modified Collection<Object[]> someParameters) {

        Class exceptionClass = FileDeletionException.class;
        Class[] signature = ConstructorSignatures.getMessageFileConstructorSignature();

        someParameters.add(new Object[] { new ConstructorInvoker(exceptionClass, signature),
                                          new Object[] { "Hello", new File("test.txt") } });

    }

    /**
     * Adds test cases for a specific exception.
     *
     * @param someParameters
     */
    private static void addClassParameterTestCases(@Modified Collection<Object[]> someParameters,
                                                   Class anExceptionClass) {

        Class[] signature = ConstructorSignatures.getClassParameterConstructor();

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature),
                                          new Object[] { Object.class } });
    }

    /**
     * Adds test cases for a specific exception.
     *
     * @param someParameters
     */
    private static void addSizesParametersTestCases(@Modified Collection<Object[]> someParameters,
                                                    Class anExceptionClass) {

        Class[] signature = ConstructorSignatures.getSizesParametersConstructor();

        someParameters.add(new Object[] { new ConstructorInvoker(anExceptionClass, signature), new Object[] { 2, 1 } });
    }

}
