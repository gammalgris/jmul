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


import jmul.transformation.xml.annotations.Exempted;
import jmul.transformation.xml.annotations.RootNode;


/**
 * An implementation of a person.<br>
 * <br>
 * <i>Note:<br />
 * This class is used for testing purposes only.
 * <ol>
 *   <li>has no public default constructor</li>
 *   <li>has public getter methods</li>
 *   <li>not all setter methods are public</li>
 *   <li>can be serialized on its own</li>
 *   <li>cannot be deserialized because of (1)</li>
 *   <li>declares a class member with the same name as used in a parent class</li>
 * </ol></i>
 *
 * @author Kristian Kutin
 */
@RootNode(declaredType = Person.class)
public class PersonImpl3 extends PersonImpl implements Person {

    /**
     * The class member contains informations about this person.<br>
     * <br>
     * <i>The parent class possesses a field with the same name.</i>
     */
    private String firstName;

    /**
     * A dirty flag.
     */
    @Exempted
    private boolean dirtyFlag;

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
    public PersonImpl3(String aFirstName, String aLastName, String aBirthdate, String aGender) {

        super(aFirstName, aLastName, aBirthdate, aGender);

        dirtyFlag = false;
    }

    /**
     * Sets the person's first name
     *
     * @param firstName
     *        a first name
     */
    public void setFirstName(String firstName) {

        super.setFirstName(firstName);
        setChanged(true);
    }

    /**
     * Returns a person's first name.
     *
     * @return a first name
     */
    public String getFirstName() {

        return super.getFirstName();
    }

    /**
     * Sets a person's last name.
     *
     * @param lastName
     *        a last name
     */
    public void setLastName(String lastName) {

        super.setLastName(lastName);
        setChanged(true);
    }

    /**
     * Returns a person's last name.
     *
     * @return a last name
     */
    public String getLastName() {

        return super.getLastName();
    }

    /**
     * Sets a person's birthdate.
     *
     * @param birthdate
     *        a birthdate
     */
    public void setBirthdate(String birthdate) {

        super.setBirthdate(birthdate);
        setChanged(true);
    }

    /**
     * Returns a person's birthdate.
     *
     * @return a birthdate
     */
    public String getBirthdate() {

        return super.getBirthdate();
    }

    /**
     * Sets a person's gender.
     *
     * @param gender
     *        a gender
     */
    public void setGender(String gender) {

        super.setGender(gender);
        setChanged(true);
    }

    /**
     * Returns a person's gender.
     *
     * @return a gender
     */
    public String getGender() {

        return super.getGender();
    }

    /**
     * A setter method.
     *
     * @param aFlag
     */
    private void setChanged(boolean aFlag) {

        dirtyFlag = aFlag;
    }

    /**
     * Determines if this object has been changed.
     *
     * @return <code>true</code> if this object has been changed, else
     *         <code>false</code>
     */
    public boolean hasChanged() {

        return dirtyFlag;
    }

    /**
     * Accept all changes.
     */
    public void acceptChanges() {

        setChanged(false);
    }

}
