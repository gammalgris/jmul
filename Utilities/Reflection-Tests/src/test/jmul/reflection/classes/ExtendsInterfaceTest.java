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
 * {@link jmul.reflection.classes.ClassDefinitionImpl#extendsInterface}.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ExtendsInterfaceTest {

    /**
     * The assumed parent interface.
     */
    private Class assumedParentInterface;

    /**
     * The child interface which which is to be tested.
     */
    private Class childInterface;

    /**
     * The expected result.
     */
    private boolean expectedResult;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param anAssumedParentInterface
     * @param aChildInterface
     * @param anExpectedResult
     */
    public ExtendsInterfaceTest(Class anAssumedParentInterface, Class aChildInterface, boolean anExpectedResult) {

        assumedParentInterface = anAssumedParentInterface;
        childInterface = aChildInterface;
        expectedResult = anExpectedResult;
    }

    /**
     * Tests the operation.
     */
    @Test
    public void testImplementsInterface() {

        try {

            ClassDefinition ci = ClassHelper.getClass(childInterface);
            ClassDefinition pi = ClassHelper.getClass(assumedParentInterface);

            boolean actualResult = ci.extendsInterface(pi);
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

        parameters.add(new Object[] { InterfaceA1.class, InterfaceB1.class, true });
        parameters.add(new Object[] { InterfaceA1.class, InterfaceC1.class, true });
        parameters.add(new Object[] { InterfaceA1.class, InterfaceD1.class, true });
        parameters.add(new Object[] { InterfaceA1.class, InterfaceE1.class, true });
        parameters.add(new Object[] { InterfaceA1.class, InterfaceF1.class, false });
        parameters.add(new Object[] { InterfaceA1.class, ClassA1.class, false });
        parameters.add(new Object[] { InterfaceA1.class, ClassB1.class, false });

        parameters.add(new Object[] { InterfaceC1.class, InterfaceA1.class, false });

        return parameters;
    }

}


/*
 * Below are several classes and interface declarations for the purpose
 * of checking various inheritence relations.
 */

interface InterfaceA1 {

}

interface InterfaceB1 extends InterfaceA1 {

}

interface InterfaceC1 extends InterfaceA1 {

}

interface InterfaceD1 extends InterfaceB1 {

}

interface InterfaceE1 extends InterfaceC1 {

}

interface InterfaceF1 {

}

class ClassA1 implements InterfaceA1 {

}

class ClassB1 {

}
