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

package test.jmul.math.hash;


import jmul.math.hash.HashHelper;

import static jmul.misc.checks.EqualityHelper.equalObjects;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * The class contains tests to check the correct implementation of calculating
 * the hash code.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class HashCodeTest {

    /**
     * Tests the caluclation of the hash code for different objects but with the
     * same contents (i.e. non-<code>null</code> values).
     */
    @Test
    public void testHashCodeEqualObjects() {

        DummyPerson person1 = new DummyPerson("John", "Doe");
        DummyPerson person2 = new DummyPerson("John", "Doe");

        assertTrue(person1.equals(person2));
        assertTrue(person2.equals(person1));

        assertEquals(person1.hashCode(), person2.hashCode());
    }

    /**
     * Tests the caluclation of the hash code for different objects with
     * different contents (i.e. non-<code>null</code> values).
     */
    @Test
    public void testHashCodeDifferentObjects() {

        DummyPerson person1 = new DummyPerson("John", "Doe");
        DummyPerson person2 = new DummyPerson("Jane", "Doe");

        assertFalse(person1.equals(person2));
        assertFalse(person2.equals(person1));

        assertNotEquals(person1.hashCode(), person2.hashCode());
    }

    /**
     * Tests the caluclation of the hash code for different objects but with the
     * same contents (one field is <code>null</code>).
     */
    @Test
    public void testNullFields() {

        DummyPerson person1 = new DummyPerson(null, "Doe");
        DummyPerson person2 = new DummyPerson(null, "Doe");

        assertTrue(person1.equals(person2));
        assertTrue(person2.equals(person1));

        assertEquals(person1.hashCode(), person2.hashCode());
    }

}


class DummyPerson {

    private final String firstName;

    private final String lastName;

    public DummyPerson(String aFirstName, String aLastName) {

        firstName = aFirstName;
        lastName = aLastName;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof DummyPerson) {

            DummyPerson other = (DummyPerson) o;

            return equalObjects(this.getFirstName(), other.getFirstName()) &&
                   equalObjects(this.getLastName(), other.getLastName());
        }

        return false;
    }

    @Override
    public int hashCode() {

        return HashHelper.calculateHashCode(this.getClass(), getFirstName(), getLastName());
    }

    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("person[");

        buffer.append("first name=");
        if (getFirstName() != null) {

            buffer.append("\"");
        }
        buffer.append(getFirstName());
        if (getFirstName() != null) {

            buffer.append("\"");
        }

        buffer.append("; last name=");
        if (getLastName() != null) {

            buffer.append("\"");
        }
        buffer.append(getLastName());
        if (getLastName() != null) {

            buffer.append("\"");
        }

        buffer.append("]");

        return String.valueOf(buffer);
    }

}
