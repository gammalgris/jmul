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

package test.jmul.datatypes.person;


import jmul.persistence.annotations.RootNode;


/**
 * This class contains informations about a person.
 *
 * @author Kristian Kutin
 */
@RootNode(declaredType = Person.class)
public class PersonImpl5 extends PersonImpl implements Person {

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
    public PersonImpl5(String aFirstName, String aLastName, String aBirthdate, String aGender) {

        super(aFirstName, aLastName, aBirthdate, aGender);
    }

}
