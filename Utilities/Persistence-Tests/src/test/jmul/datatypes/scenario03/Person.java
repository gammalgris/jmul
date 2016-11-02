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

package test.jmul.datatypes.scenario03;


import jmul.persistence.annotations.Exempted;
import jmul.persistence.annotations.RootNode;


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
     * A flag to indicate changes.
     */
    @Exempted
    private boolean dirtyFlag;

    /**
     * The default constructor.
     */
    public Person() {

        super();

        dirtyFlag = false;
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

        lastName = aLastName;
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
    public void setGender(String aGender) {

        gender = aGender;
    }

    /**
     * A getter method.
     *
     * @return a gender
     */
    public String getGender() {

        return gender;
    }

    /**
     * A setter method.
     *
     * @param isChanged
     */
    public void setDirtyFlag(boolean isChanged) {

        dirtyFlag = isChanged;
    }

    /**
     * A getter method.
     *
     * @return <code>true</code> if the person was changed, else <code>false</code>
     */
    public boolean isDirtyFlag() {

        return dirtyFlag;
    }

}
