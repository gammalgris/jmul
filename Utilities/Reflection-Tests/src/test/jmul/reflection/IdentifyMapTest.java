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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
public class IdentifyMapTest {

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
    public IdentifyMapTest(Class anInput, Class anExpectedClass) {

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
     * Tests if a concrete map implementation can be identified correctly.
     */
    @Test
    public void test() {

        Class actualClass = ContainerHelper.getConcreteMapImplementation(input);
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

        parameters.add(new Object[] { MapA.class, null });
        parameters.add(new Object[] { MapB.class, null });
        parameters.add(new Object[] { MapC.class, HashMap.class });
        parameters.add(new Object[] { MapD.class, HashMap.class });
        parameters.add(new Object[] { NoCollection.class, null });

        return parameters;
    }

}


abstract class MapA implements Map {

}


class MapB implements Map {

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
    public boolean containsKey(Object key) {
        // TODO Implement this method
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Implement this method
        return false;
    }

    @Override
    public Object get(Object key) {
        // TODO Implement this method
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        // TODO Implement this method
        return null;
    }

    @Override
    public Object remove(Object key) {
        // TODO Implement this method
        return null;
    }

    @Override
    public void putAll(Map m) {
        // TODO Implement this method
    }

    @Override
    public void clear() {
        // TODO Implement this method
    }

    @Override
    public Set keySet() {
        // TODO Implement this method
        return Collections.emptySet();
    }

    @Override
    public Collection values() {
        // TODO Implement this method
        return Collections.emptySet();
    }

    @Override
    public Set entrySet() {
        // TODO Implement this method
        return Collections.emptySet();
    }
}


class MapC extends HashMap {

}


class MapD extends MapC {

}


class NoMap {

}
