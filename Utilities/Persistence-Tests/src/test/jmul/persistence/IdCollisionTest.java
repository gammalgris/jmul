/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package test.jmul.persistence;


import java.util.ArrayList;
import java.util.Collection;

import jmul.misc.id.ID;
import jmul.misc.id.IDGenerator;

import jmul.persistence.id.StringIDGenerator;

import static jmul.string.Constants.FILE_SEPARATOR;

import jmul.test.classification.LongRunning;
import jmul.test.classification.ModuleTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains tests to identify collisions when generating IDs.
 *
 * @author Kristian Kutin
 */
@LongRunning
@ModuleTest
public class IdCollisionTest extends FileManagerTestBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = ".\\Test\\ID-Generators";

    /**
     * The file where the generated IDs are persisted.
     */
    private static final String ID_FILE = BASEDIR + FILE_SEPARATOR + "idtest";

    /**
     * Preparations before a test.
     */
    @Before
    public void setUp() {

        initBaseDirectory(BASEDIR);
    }

    /**
     * Cleanup after a test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Tests the alternative ID generator for ID collisions.
     */
    @Test
    public void testDefaultGenerator() {

        IDGenerator generator = StringIDGenerator.getDefaultGenerator(ID_FILE);
        int iterations = 100000;

        int collisions = generateIDs(generator, iterations);
        String message = "With " + iterations + " there were " + collisions + " ID collisions.";
        assertEquals(message, 0, collisions);
    }

    /**
     * Tests the alternative ID generator for ID collisions.
     */
    @Test
    public void testAlternateGenerator() {

        IDGenerator generator = StringIDGenerator.getAlternativeGenerator(ID_FILE);
        int iterations = 100000;

        int collisions = generateIDs(generator, iterations);
        String message = "With " + iterations + " there were " + collisions + " ID collisions.";
        assertEquals(message, 0, collisions);
    }

    /**
     * The specified generator generates IDs repeatedly until the specified
     * iteration threshold is reached.
     *
     * @param aGenerator
     * @param iterations
     *
     * @return the number of ID collisions
     */
    private int generateIDs(IDGenerator aGenerator, int iterations) {

        Collection<ID> generatedIDs = new ArrayList<ID>();
        int collisions = 0;

        for (int a = 0; a < iterations; a++) {

            ID id = aGenerator.generateID();

            if (generatedIDs.contains(id)) {

                collisions++;

            } else {

                generatedIDs.add(id);
            }
        }

        return collisions;
    }

}
