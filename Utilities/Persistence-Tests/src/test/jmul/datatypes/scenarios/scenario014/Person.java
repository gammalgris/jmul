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

package test.jmul.datatypes.scenarios.scenario014;


import jmul.transformation.xml.annotations.RootNode;


/**
 * This class contains basic information about a person.<br />
 * <br />
 * <i>Note:<br />
 * This class is used for testing purposes only.
 * </i>
 *
 * @author Kristian Kutin
 */
@RootNode(declaredType = Person.class)
public class Person {

    /**
     * A first name.
     */
    private String firstName;

    /**
     * A last name.
     */
    private String lastName;

    /**
     * A birth date.
     */
    private String birthDate;

    /**
     * A gender.
     */
    private String gender;

    /**
     * The default constructor.
     */
    public Person() {

        super();
    }

    /**
     * A setter method.
     *
     * @param aFirstName
     */
    public void setFirstName(String aFirstName) {

        firstName = aFirstName;
    }

    /**
     * A getter method.
     *
     * @return a first name
     */
    public String getFirstName() {

        return firstName;
    }

    /**
     * A setter method.
     *
     * @param aLastName
     */
    public void setLastName(String aLastName) {

        this.lastName = aLastName;
    }

    /**
     * A getter method.
     *
     * @return a last name
     */
    public String getLastName() {

        return lastName;
    }

    /**
     * A setter method.
     *
     * @param aBirthDate
     */
    public void setBirthDate(String aBirthDate) {

        birthDate = aBirthDate;
    }

    /**
     * A getter method.
     *
     * @return a birth date
     */
    public String getBirthDate() {

        return birthDate;
    }

    /**
     * A setter method.
     *
     * @param aGender
     */
    private void setGender(String aGender) {

        gender = aGender;
    }

    /**
     * A getter method.
     *
     * @return a gender
     */
    private String getGender() {

        return gender;
    }

}
