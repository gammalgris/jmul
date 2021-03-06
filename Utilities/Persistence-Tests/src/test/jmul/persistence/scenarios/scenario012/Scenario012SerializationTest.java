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

package test.jmul.persistence.scenarios.scenario012;


import java.io.IOException;

import jmul.io.deserialization.Deserializer;
import jmul.io.serialization.Serializer;

import jmul.test.classification.ModuleTest;

import jmul.transformation.TransformationException;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.jmul.datatypes.scenarios.scenario012.Person;
import test.jmul.persistence.SerializationTestBase;


/**
 * This class contains tests to check the serialization of objects.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class Scenario012SerializationTest extends SerializationTestBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = ROOT_DIRECTORY + "Serialization\\Scenario-012";

    /**
     * The file where the generated IDs are persisted.
     */
    private static final String OUTPUT_FILE = "output";

    /**
     * An XML serializer.
     */
    private Serializer serializer;

    /**
     * An XML deserializer.
     */
    private Deserializer deserializer;

    /**
     * Preparations before this test suite.
     */
    @BeforeClass
    public static void setUp() {

        initBaseDirectory(BASEDIR);
    }

    /**
     * Cleanup after this test suite.
     */
    @AfterClass
    public static void tearDown() {

    }

    /**
     * Preparations before a test.
     */
    @Before
    public void setUpTest() {

        serializer = initXmlSerializer();
        deserializer = initXmlDeserializer();
    }

    /**
     * Cleanup after a test.
     */
    @After
    public void tearDownTest() {

        serializer = null;
        deserializer = null;
    }

    /**
     * Tests the serialization of a person entity (i.e. the root node possesses several
     * class members).
     */
    @Test(expected = TransformationException.class)
    public void testSerializePerson() {

        String fileName = getOutputFileName(BASEDIR, OUTPUT_FILE);

        Person person = newPerson("John", "Doe", "1.1.2000", "male");
        Person copy = null;

        try {

            serializer.serialize(fileName, person);
            copy = (Person) deserializer.deserialize(fileName);

        } catch (IOException e) {

            fail(e.toString());
        }
    }

    /**
     * Creates a new person according to the specified parameters.
     *
     * @param aFirstName
     * @param aLastName
     * @param aBirthDate
     * @param aGender
     *
     * @return a new person
     */
    private static Person newPerson(String aFirstName, String aLastName, String aBirthDate, String aGender) {

        Person p = new Person();
        p.setFirstName(aFirstName);
        p.setLastName(aLastName);
        p.setBirthDate(aBirthDate);

        return p;
    }

}
