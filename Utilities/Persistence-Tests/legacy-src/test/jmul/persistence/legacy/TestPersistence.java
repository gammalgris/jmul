/*
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

package test.jmul.persistence.legacy;


import jmul.misc.id.ID;
import jmul.misc.id.StringID;

import jmul.persistence.InvalidRootNodeException;
import jmul.persistence.PersistenceContainer;
import jmul.persistence.PersistenceContainerImpl;
import jmul.persistence.PersistenceException;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import test.jmul.datatypes.legacy.department.DepartmentDetails;
import test.jmul.datatypes.legacy.department.DepartmentDetailsImpl2;
import test.jmul.datatypes.legacy.department.DepartmentGenderDetails;
import test.jmul.datatypes.legacy.employee.EmployeeImpl6;


/**
 * A collection of test cases to test the functionalities of a persistence
 * manager.
 *
 * @author Kristian Kutin
 */
public class TestPersistence extends SerializationBase {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = DRIVE + "\\Test\\Sequential\\Persistence";

    /**
     * Test the storing and retrieval of an object.
     */
    @Test
    public void testObjectPersistence() {


        PersistenceContainer<DepartmentDetails> persistenceManager =
            new PersistenceContainerImpl<DepartmentDetails>(DepartmentDetails.class, BASEDIR);

        // Serialize an object first.

        DepartmentDetails original = new DepartmentDetailsImpl2();
        original.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        original.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));


        ID id = null;

        try {

            id = persistenceManager.store(original);

        } catch (InvalidRootNodeException e) {

            // Ignore this exception.

        } catch (PersistenceException e) {

            // Ignore this exception.
        }


        // Stop the persistence manager.
        persistenceManager.shutdown();
        persistenceManager = null;


        // Get a new persistence manager.

        persistenceManager = new PersistenceContainerImpl<DepartmentDetails>(DepartmentDetails.class, BASEDIR);

        DepartmentDetails clone = null;

        try {

            clone = persistenceManager.get(id);

        } catch (PersistenceException e) {

            // Ignore this exception.
        }

        assertEquals(original, clone);


        // Stop the persistence manager again.
        persistenceManager.shutdown();
        persistenceManager = null;
    }

    /**
     * Test the retrieval of an object. This is only provided for manual tests
     * to check if the deserialisation works.
     */
    public void testObjectRetrieval() {


        PersistenceContainer<DepartmentGenderDetails> persistenceManager =
            new PersistenceContainerImpl<DepartmentGenderDetails>(DepartmentGenderDetails.class, BASEDIR);

        ID id = new StringID("_a0_");
        DepartmentGenderDetails object = null;

        try {

            object = persistenceManager.get(id);

        } catch (PersistenceException e) {

            // Ignore this exception.
        }


        // Stop the persistence manager again.
        persistenceManager.shutdown();
        persistenceManager = null;
    }

    /**
     * The main method is only provided for purposes of manual testing.
     *
     * @param args
     *        command line arguments
     */
    public static void main(String[] args) {

        TestPersistence test = new TestPersistence();
        test.setUp();

        try {

            //test.testObjectPersistence();
            test.testObjectRetrieval();

        } catch (Throwable e) {

            test.tearDown();
            throw new RuntimeException(e);
        }

        test.tearDown();

        System.out.println("done.");
    }

}
