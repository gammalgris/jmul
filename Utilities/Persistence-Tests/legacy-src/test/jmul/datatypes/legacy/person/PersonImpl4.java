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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.datatypes.legacy.person;


import jmul.transformation.xml.annotations.RootNode;


/**
 * An implementation of a person.<br>
 * <br>
 * <i>Note:<br />
 * This class is used for testing purposes only.
 * <ol>
 *   <li>has no public default constructor</li>
 *   <li>has public getter methods</li>
 *   <li>one setter method is missing (uniqueID)</li>
 *   <li>can be serialized on its own</li>
 *   <li>cannot be deserialized because of (1 and (3))</li>
 * </ol></i>
 *
 * @author Kristian Kutin
 */
@RootNode(declaredType = Person.class)
public class PersonImpl4 extends PersonImpl implements Person {

    /**
     * The next available ID.
     */
    private static int NEXT_ID = 0;

    /**
     * A unique ID for this object.
     */
    private final int uniqueID;

    /**
     * Constructs a dummy person.
     *
     * @param aFirstName
     *        a person's first name
     * @param aLastName
     *        a person's last name
     * @param aBirthdate
     *        a person's birth date
     * @param aGender
     *        a person's gender
     */
    public PersonImpl4(String aFirstName, String aLastName, String aBirthdate, String aGender) {

        super(aFirstName, aLastName, aBirthdate, aGender);

        uniqueID = NEXT_ID;
        NEXT_ID++;
    }

    /**
     * Returns the id for this object.
     *
     * @return a unique ID
     */
    public int getUniqueID() {

        return uniqueID;
    }

}
