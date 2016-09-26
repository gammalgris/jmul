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

package test.jmul.persistence.person;


/**
 * An implementation of a person.<br>
 * <br>
 * <i>This class is used for testing purposes only.</i>
 *
 * @author Kristian Kutin
 */
public class PersonImpl implements Person {

    /**
     * The class member contains informations about this person.
     */
    private String firstName;

    /**
     * The class member contains informations about this person.
     */
    private String lastName;

    /**
     * The class member contains informations about this person.
     */
    private String birthdate;

    /**
     * The class member contains informations about this person.
     */
    private String gender;

    /**
     * The default constructor.
     */
    public PersonImpl() {

        this(null, null, null, null);
    }

    /**
     * Constructs a person.
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
    public PersonImpl(String aFirstName, String aLastName, String aBirthdate, String aGender) {

        firstName = aFirstName;
        lastName = aLastName;
        birthdate = aBirthdate;
        gender = aGender;
    }

    /**
     * Sets the person's first name
     *
     * @param firstName
     *        a first name
     */
    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    /**
     * Returns a person's first name.
     *
     * @return a first name
     */
    public String getFirstName() {

        return firstName;
    }

    /**
     * Sets a person's last name.
     *
     * @param lastName
     *        a last name
     */
    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    /**
     * Returns a person's last name.
     *
     * @return a last name
     */
    public String getLastName() {

        return lastName;
    }

    /**
     * Sets a person's birthdate.
     *
     * @param birthdate
     *        a birthdate
     */
    public void setBirthdate(String birthdate) {

        this.birthdate = birthdate;
    }

    /**
     * Returns a person's birthdate.
     *
     * @return a birthdate
     */
    public String getBirthdate() {

        return birthdate;
    }

    /**
     * Sets a person's gender.
     *
     * @param gender
     *        a gender
     */
    public void setGender(String gender) {

        this.gender = gender;
    }

    /**
     * Returns a person's gender.
     *
     * @return a gender
     */
    public String getGender() {

        return gender;
    }

    /**
     * Checks if the specified object equals this object.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if both objects are equal, else
     *         <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof Person) {

            Person other = (Person) o;

            return (this.getFirstName().equals(other.getFirstName()) &&
                    this.getLastName().equals(other.getLastName()) &&
                    this.getBirthdate().equals(other.getBirthdate()) && this.getGender().equals(other.getGender()));
        }

        return false;
    }

    /**
     * Calculates a hash value for this object.
     *
     * @return a hash value
     */
    @Override
    public int hashCode() {

        int hash = 17;

        hash *= getFirstName().hashCode();
        hash *= getLastName().hashCode();
        hash *= getBirthdate().hashCode();
        hash *= getGender().hashCode();

        return hash;
    }

}
