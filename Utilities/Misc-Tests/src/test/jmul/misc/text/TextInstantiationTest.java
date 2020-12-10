/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2020  Kristian Kutin
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

package test.jmul.misc.text;


import jmul.checks.exceptions.NullParameterException;

import jmul.misc.text.ModifiableTextImpl;
import jmul.misc.text.Text;
import jmul.misc.text.TextImpl;

import static jmul.string.Constants.EMPTY_STRING;
import static jmul.string.Constants.NEW_LINE;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


/**
 * Tests instantiating text implementations.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class TextInstantiationTest {

    /**
     * Tests instantiating an unmodifiable text implementation.
     */
    @Test
    public void testInstantiationImplementation1() {

        Text text = new TextImpl();
        assertNotNull(text);

        assertEquals(0, text.lines());
        assertEquals(0, text.getContent().size());

        assertEquals(0, text.getContentAsString().length());
        assertEquals("", text.getContentAsString());
    }

    /**
     * Tests instantiating an unmodifiable text implementation.
     */
    @Test(expected = NullParameterException.class)
    public void testInstantiationImplementation1AndInitializationWithInvalidArgument() {

        String[] p = null;
        new TextImpl(p);
    }

    /**
     * Tests instantiating an unmodifiable text implementation.
     */
    @Test
    public void testInstantiationImplementation1AndInitializationWithInvalidArgument2() {

        String t;

        String[] p = new String[] { null };
        Text text = new TextImpl(p);

        assertNotNull(text);

        assertEquals(1, text.lines());
        assertEquals(1, text.getContent().size());

        t = EMPTY_STRING + NEW_LINE;
        assertEquals(t.length(), text.getContentAsString().length());
        assertEquals(t, text.getContentAsString());
        assertEquals(EMPTY_STRING.length(), text.getLine(0).length());
        assertEquals(EMPTY_STRING, text.getLine(0));
    }

    /**
     * Tests instantiating an unmodifiable text implementation.
     */
    @Test
    public void testInstantiationImplementation1AndInitializationWithInvalidArgument3() {

        String[] p = new String[] { };
        Text text = new TextImpl(p);

        assertNotNull(text);

        assertEquals(0, text.lines());
        assertEquals(0, text.getContent().size());

        assertEquals(0, text.getContentAsString().length());
        assertEquals("", text.getContentAsString());
    }

    /**
     * Tests instantiating and initializing an unmodifiable text implementation.
     */
    @Test
    public void testInstantiationImplementation1AndInitializationWithSingleLine() {

        String s;
        String t;

        s = "Hello";
        Text text = new TextImpl(s);
        assertNotNull(text);

        assertEquals(1, text.lines());
        assertEquals(1, text.getContent().size());

        t = s + NEW_LINE;
        assertEquals(t.length(), text.getContentAsString().length());
        assertEquals(t, text.getContentAsString());
        assertEquals(s.length(), text.getLine(0).length());
        assertEquals(s, text.getLine(0));
    }

    /**
     * Tests instantiating and initializing an unmodifiable text implementation.
     */
    @Test
    public void testInstantiationImplementation1AndInitializationWithTwoLines() {

        String s1;
        String s2;
        String t;

        s1 = "Hello";
        s2 = "World";
        Text text = new TextImpl(new String[] { s1, s2 });
        assertNotNull(text);

        assertEquals(2, text.lines());
        assertEquals(2, text.getContent().size());

        t = s1 + NEW_LINE + s2 + NEW_LINE;
        assertEquals(t.length(), text.getContentAsString().length());
        assertEquals(t, text.getContentAsString());
        assertEquals(s1.length(), text.getLine(0).length());
        assertEquals(s1, text.getLine(0));
        assertEquals(s2.length(), text.getLine(1).length());
        assertEquals(s2, text.getLine(1));
    }

    /**
     * Tests instantiating a modifiable text implementation.
     */
    @Test
    public void testInstantiationImplementation2() {

        Text text = new ModifiableTextImpl();
        assertNotNull(text);

        assertEquals(0, text.lines());
        assertEquals(0, text.getContent().size());

        assertEquals(0, text.getContentAsString().length());
        assertEquals("", text.getContentAsString());
    }

    /**
     * Tests instantiating an unmodifiable text implementation.
     */
    @Test(expected = NullParameterException.class)
    public void testInstantiationImplementation2AndInitializationWithInvalidArgument() {

        String[] p = null;
        new ModifiableTextImpl(p);
    }

    /**
     * Tests instantiating an unmodifiable text implementation.
     */
    @Test
    public void testInstantiationImplementation2AndInitializationWithInvalidArgument2() {

        String t;

        String[] p = new String[] { null };
        Text text = new ModifiableTextImpl(p);

        assertNotNull(text);

        assertEquals(1, text.lines());
        assertEquals(1, text.getContent().size());

        t = EMPTY_STRING + NEW_LINE;
        assertEquals(t.length(), text.getContentAsString().length());
        assertEquals(t, text.getContentAsString());
        assertEquals(EMPTY_STRING.length(), text.getLine(0).length());
        assertEquals(EMPTY_STRING, text.getLine(0));
    }

    /**
     * Tests instantiating an unmodifiable text implementation.
     */
    @Test
    public void testInstantiationImplementation2AndInitializationWithInvalidArgument3() {

        String[] p = new String[] { };
        Text text = new ModifiableTextImpl(p);

        assertNotNull(text);

        assertEquals(0, text.lines());
        assertEquals(0, text.getContent().size());

        assertEquals(0, text.getContentAsString().length());
        assertEquals("", text.getContentAsString());
    }

    /**
     * Tests instantiating and initializing an unmodifiable text implementation.
     */
    @Test
    public void testInstantiationImplementation2AndInitializationWithSingleLine() {

        String s;
        String t;

        s = "Hello";
        Text text = new ModifiableTextImpl(s);
        assertNotNull(text);

        assertEquals(1, text.lines());
        assertEquals(1, text.getContent().size());

        t = s + NEW_LINE;
        assertEquals(t.length(), text.getContentAsString().length());
        assertEquals(t, text.getContentAsString());
        assertEquals(s.length(), text.getLine(0).length());
        assertEquals(s, text.getLine(0));
    }

    /**
     * Tests instantiating and initializing an unmodifiable text implementation.
     */
    @Test
    public void testInstantiationImplementation2AndInitializationWithTwoLines() {

        String s1;
        String s2;
        String t;

        s1 = "Hello";
        s2 = "World";
        Text text = new ModifiableTextImpl(new String[] { s1, s2 });
        assertNotNull(text);

        assertEquals(2, text.lines());
        assertEquals(2, text.getContent().size());

        t = s1 + NEW_LINE + s2 + NEW_LINE;
        assertEquals(t.length(), text.getContentAsString().length());
        assertEquals(t, text.getContentAsString());
        assertEquals(s1.length(), text.getLine(0).length());
        assertEquals(s1, text.getLine(0));
        assertEquals(s2.length(), text.getLine(1).length());
        assertEquals(s2, text.getLine(1));
    }

}
