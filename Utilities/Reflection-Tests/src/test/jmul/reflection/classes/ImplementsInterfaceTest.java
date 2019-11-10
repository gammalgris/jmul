/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.reflection.classes;


import java.util.ArrayList;
import java.util.Collection;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class tests the operation
 * {@link jmul.reflection.classes.ClassDefinitionImpl#implementsInterface}.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ImplementsInterfaceTest {

    /**
     * The assumed parent interface.
     */
    private Class assumedParentInterface;

    /**
     * The child class which which is to be tested.
     */
    private Class childClass;

    /**
     * The expected result.
     */
    private boolean expectedResult;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param anAssumedParentInterface
     * @param aChildClass
     * @param anExpectedResult
     */
    public ImplementsInterfaceTest(Class anAssumedParentInterface, Class aChildClass, boolean anExpectedResult) {

        assumedParentInterface = anAssumedParentInterface;
        childClass = aChildClass;
        expectedResult = anExpectedResult;
    }

    /**
     * Tests the operation.
     */
    @Test
    public void testImplementsInterface() {

        try {

            ClassDefinition cc = ClassHelper.getClass(childClass);
            ClassDefinition pi = ClassHelper.getClass(assumedParentInterface);

            boolean actualResult = cc.implementsInterface(pi);
            assertEquals(expectedResult, actualResult);

        } catch (ClassNotFoundException e) {

            fail(e.getMessage());
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

        parameters.add(new Object[] { InterfaceA2.class, ClassA2.class, true });
        parameters.add(new Object[] { InterfaceA2.class, ClassB2.class, true });
        parameters.add(new Object[] { InterfaceA2.class, ClassC2.class, true });
        parameters.add(new Object[] { InterfaceA2.class, ClassD2.class, true });
        parameters.add(new Object[] { InterfaceA2.class, ClassE2.class, false });

        parameters.add(new Object[] { InterfaceB2.class, ClassA2.class, false });
        parameters.add(new Object[] { InterfaceB2.class, ClassB2.class, true });
        parameters.add(new Object[] { InterfaceB2.class, ClassC2.class, false });
        parameters.add(new Object[] { InterfaceB2.class, ClassD2.class, true });
        parameters.add(new Object[] { InterfaceB2.class, ClassE2.class, false });

        parameters.add(new Object[] { InterfaceC2.class, ClassA2.class, false });
        parameters.add(new Object[] { InterfaceC2.class, ClassB2.class, false });
        parameters.add(new Object[] { InterfaceC2.class, ClassC2.class, false });
        parameters.add(new Object[] { InterfaceC2.class, ClassD2.class, false });
        parameters.add(new Object[] { InterfaceC2.class, ClassE2.class, false });

        parameters.add(new Object[] { ClassA2.class, ClassA2.class, false });
        parameters.add(new Object[] { ClassA2.class, ClassB2.class, false });
        parameters.add(new Object[] { ClassA2.class, ClassC2.class, false });
        parameters.add(new Object[] { ClassA2.class, ClassD2.class, false });
        parameters.add(new Object[] { ClassA2.class, ClassE2.class, false });

        return parameters;
    }

}


/*
 * Below are several classes and interface declarations for the purpose
 * of checking various inheritence relations.
 */

interface InterfaceA2 {

}

interface InterfaceB2 extends InterfaceA2 {

}

interface InterfaceC2 {

}

class ClassA2 implements InterfaceA2 {

}

class ClassB2 implements InterfaceB2 {

}

class ClassC2 extends ClassA2 {

}

class ClassD2 extends ClassB2 {

}

class ClassE2 {

}
