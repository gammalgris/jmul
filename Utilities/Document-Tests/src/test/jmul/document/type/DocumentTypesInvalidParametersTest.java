/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package test.jmul.document.type;


import java.util.ArrayList;
import java.util.Collection;

import jmul.checks.exceptions.NullFileNameParameterException;

import jmul.document.type.DocumentTypes;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains negative tests regarding document types.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DocumentTypesInvalidParametersTest {

    /**
     * A file name.
     */
    private String fileName;

    /**
     * The expected exception.
     */
    private Class expectedExceptionType;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param aFileName
     *        a file name
     * @param anExpectedExceptionType
     *        the expected exception type
     */
    public DocumentTypesInvalidParametersTest(String aFileName, Class anExpectedExceptionType) {

        fileName = aFileName;
        expectedExceptionType = anExpectedExceptionType;
    }

    /**
     * Tests getting the document type with invalid parameters.
     */
    @Test
    public void testGetDocumentType() {

        try {

            DocumentTypes.getDocumentType(fileName);

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();

            assertEquals(expectedExceptionType, actualExceptionType);
            return;
        }

        fail("An exception is excepted but no exception was thrown!");
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null, NullFileNameParameterException.class });
        parameters.add(new Object[] { "", IllegalArgumentException.class });
        parameters.add(new Object[] { " ", IllegalArgumentException.class });

        return parameters;
    }

}
