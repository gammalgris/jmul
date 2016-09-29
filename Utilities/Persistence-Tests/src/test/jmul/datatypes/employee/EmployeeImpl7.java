/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package test.jmul.datatypes.employee;


import jmul.persistence.annotations.RootNode;

import test.jmul.datatypes.person.Person;
import test.jmul.datatypes.person.PersonImpl;


/**
 * An implementation of an employee.<br>
 * <br>
 * <i>This code is used for testing purposes only.</i>
 *
 * @author Kristian Kutin
 */
@RootNode(declaredType = Employee.class)
public class EmployeeImpl7 extends EmployeeImpl {

    public EmployeeImpl7(String aFirstName, String aLastName, String aBirthdate, String aGender, float aSalary,
                         String aTitle) {
        super(aFirstName, aLastName, aBirthdate, aGender, aSalary, aTitle);
    }

    /**
     * A setter method.
     *
     * @param person
     *        a subset of informations about the employee
     */
    public void setPerson(Person person) {

        setFirstName(person.getFirstName());
        setLastName(person.getLastName());
        setBirthdate(person.getBirthdate());
        setGender(person.getGender());
    }

    /**
     * A getter method.
     *
     * @return a subset of informations about the employee
     */
    public Person getPerson() {

        return new PersonImpl(getFirstName(), getLastName(), getBirthdate(), getGender());
    }

}
