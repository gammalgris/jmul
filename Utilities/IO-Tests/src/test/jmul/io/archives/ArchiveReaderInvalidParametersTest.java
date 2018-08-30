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

package test.jmul.io.archives;


import java.io.FileNotFoundException;
import java.io.IOException;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.io.archives.ArchiveException;
import jmul.io.archives.ArchiveReader;
import jmul.io.archives.ArchiveReaderImpl;

import jmul.test.classification.UnitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import jmul.reflection.constructors.ConstructorInvoker;
import jmul.reflection.constructors.ConstructorSignatures;

import static test.jmul.helper.TestFailureHelper.failTest;


/**
 * Tests invoking an {@link jmul.io.archives.ArchiveReader} with invalid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ArchiveReaderInvalidParametersTest {

    /**
     * The class of an archive reader implementation.
     */
    private Class readerClass;

    /**
     * An archive name.
     */
    private String archiveName;

    /**
     * An entry name.
     */
    private String entryName;

    /**
     *  An expected exception.
     */
    private Class expectedException;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param aReaderClass
     * @param anArchiveName
     * @param anEntryName
     * @param anExpectedException
     */
    public ArchiveReaderInvalidParametersTest(Class aReaderClass, String anArchiveName, String anEntryName,
                                              Class anExpectedException) {

        readerClass = aReaderClass;
        archiveName = anArchiveName;
        entryName = anEntryName;
        expectedException = anExpectedException;
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
     * Tries reading a resource from an archive.
     */
    @Test
    public void testLoadResource() {

        Class[] signature = ConstructorSignatures.getFileNameConstructorSignature();

        ConstructorInvoker<ArchiveReader> constructorInvoker =
            new ConstructorInvoker<ArchiveReader>(readerClass, signature);

        ArchiveReader reader = null;
        Throwable actualException = null;

        try {

            reader = constructorInvoker.invoke(archiveName);

        } catch (InvocationTargetException e) {

            actualException = e.getTargetException();

        } catch (IllegalAccessException e) {

            actualException = e;

        } catch (InstantiationException e) {

            actualException = e;

        } catch (NoSuchMethodException e) {

            actualException = e;
        }


        if (actualException != null) {

            if (isExpectedException(actualException)) {

                return;

            } else {

                failTest(expectedException, actualException);
            }
        }


        try {

            reader.loadEntry(entryName);

        } catch (ArchiveException e) {

            actualException = e;

        } catch (IllegalArgumentException e) {

            actualException = e;

        } catch (IOException e) {

            actualException = e;
        }


        if (actualException != null) {

            if (isExpectedException(actualException)) {

                try {

                    reader.close();

                } catch (IOException e) {

                    // ignore exception
                }

                return;
            }
        }


        try {

            reader.close();

        } catch (IOException e) {

            // ignore exception
        }


        failTest(expectedException, actualException);
    }

    /**
     * Checks if the specified exception is the expected exception.
     *
     * @param anException
     *
     * @return <code>true</code> if the specified exception is the expected exception,
     *         else <code>false</code>
     */
    private boolean isExpectedException(Throwable anException) {

        Class exceptionClass = anException.getClass();
        return expectedException.isAssignableFrom(exceptionClass);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();


        parameters.add(new Object[] { ArchiveReaderImpl.class, null, "testdata-io/config1.properties",
                                      IllegalArgumentException.class });

        parameters.add(new Object[] { ArchiveReaderImpl.class, "", "testdata-io/config1.properties",
                                      IllegalArgumentException.class });

        parameters.add(new Object[] { ArchiveReaderImpl.class, " ", "testdata-io/config1.properties",
                                      IllegalArgumentException.class });

        parameters.add(new Object[] { ArchiveReaderImpl.class, " testdata-io\\archive1.zip",
                                      "testdata-io/config1.properties", IllegalArgumentException.class });

        parameters.add(new Object[] { ArchiveReaderImpl.class, "testdata-io\\archive1.zip ",
                                      "testdata-io/config1.properties", IllegalArgumentException.class });

        parameters.add(new Object[] { ArchiveReaderImpl.class, "testdata-io\\folder1", "testdata-io/config1.properties",
                                      FileNotFoundException.class });

        parameters.add(new Object[] { ArchiveReaderImpl.class, "testdata-io\\file1.txt",
                                      "testdata-io/config1.properties", IOException.class });


        parameters.add(new Object[] { ArchiveReaderImpl.class, "testdata-io\\archive1.zip", null,
                                      IllegalArgumentException.class });

        parameters.add(new Object[] { ArchiveReaderImpl.class, "testdata-io\\archive1.zip", "",
                                      IllegalArgumentException.class });

        parameters.add(new Object[] { ArchiveReaderImpl.class, "testdata-io\\archive1.zip", " ",
                                      IllegalArgumentException.class });

        parameters.add(new Object[] { ArchiveReaderImpl.class, "testdata-io\\archive1.zip",
                                      " testdata-io/config1.properties", IllegalArgumentException.class });

        parameters.add(new Object[] { ArchiveReaderImpl.class, "testdata-io\\archive1.zip",
                                      "testdata-io/config1.properties ", IllegalArgumentException.class });

        parameters.add(new Object[] { ArchiveReaderImpl.class, "testdata-io\\archive1.zip", "testdata-io/file1.txt",
                                      IOException.class });


        return parameters;
    }

}
