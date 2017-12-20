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

package test.jmul.reflection;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import jmul.reflection.ContainerHelper;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class tests the container helper.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class IdentifyCollectionTest {

    /**
     * The input.
     */
    private Class input;

    /**
     * The expected result.
     */
    private Class expectedClass;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param anInput
     * @param anExpectedClass
     */
    public IdentifyCollectionTest(Class anInput, Class anExpectedClass) {

        input = anInput;
        expectedClass = anExpectedClass;
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
     * Tests if a concrete collection implementation can be identified correctly.
     */
    @Test
    public void test() {

        Class actualClass = ContainerHelper.getConcreteCollectionImplementation(input);
        assertEquals(expectedClass, actualClass);
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { CollectionA.class, null });
        parameters.add(new Object[] { CollectionB.class, null });
        parameters.add(new Object[] { CollectionC.class, ArrayList.class });
        parameters.add(new Object[] { CollectionD.class, ArrayList.class });
        parameters.add(new Object[] { NoCollection.class, null });

        return parameters;
    }

}


abstract class CollectionA implements Collection {

}

class CollectionB implements Collection {

    @Override
    public int size() {
        // TODO Implement this method
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean contains(Object o) {
        // TODO Implement this method
        return false;
    }

    @Override
    public Iterator iterator() {
        // TODO Implement this method
        return null;
    }

    @Override
    public Object[] toArray() {
        // TODO Implement this method
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        // TODO Implement this method
        return new Object[0];
    }

    @Override
    public boolean add(Object e) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean remove(Object o) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        // TODO Implement this method
        return false;
    }

    @Override
    public void clear() {
        // TODO Implement this method
    }

}


class CollectionC extends ArrayList {

}


class CollectionD extends CollectionC {

}


class NoCollection {

}
