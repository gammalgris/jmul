/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package test.jmul.persistence.scenarios.scenario022;


import static jmul.math.Constants.MINUTE;

import jmul.persistence.PersistenceContainer;
import jmul.persistence.PersistenceContainerImpl;

import jmul.test.classification.ModuleTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.jmul.datatypes.scenarios.interfaces.Person;


/**
 * This test scenario tests concurrent write and read operation on a
 * persistence container.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class Scenario022PersistenceTest {

    private String baseDirectory;

    private volatile PersistenceContainer<Person> container;

    private volatile TaskResultCollector collector;

    @Before
    public void setUp() {

        baseDirectory = ".";
        container = new PersistenceContainerImpl<Person>(Person.class, baseDirectory);
        collector = new TaskResultCollector();
    }

    @After
    public void tearDown() {

        container.shutdown();
    }

    /**
     * Tests concurrent read write oprations during an objcetc's lifecycle.
     */
    @Test
    void testConcurrentReadWriteOperations() {

        /*long testDuration = MINUTE * 5;
        int maxObjectsCount = 500;*/
        long testDuration = MINUTE * 5;
        int maxObjectsCount = 1;

        TestSetup testSetup = new TestSetup(container, collector, testDuration, maxObjectsCount);
        testSetup.startTest();
    }

    /**
     * The main method for manual testing purposes.
     *
     * @param args
     */
    public static void main(String[] args) {

        Scenario022PersistenceTest test = new Scenario022PersistenceTest();

        test.setUp();
        test.testConcurrentReadWriteOperations();
        test.tearDown();
    }

}
