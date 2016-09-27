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

package test.jmul.datatypes.employee;


import test.jmul.datatypes.person.Person;
import test.jmul.datatypes.person.PersonImpl;


/**
 * An implementation of an employee.<br>
 * <br>
 * <i>This class is used for testing purposes only.</i>
 *
 * @author Kristian Kutin
 */
public class EmployeeImpl6 implements Employee {

    /**
     * The class member contains informations about this person.
     */
    private Person person;

    /**
     * The salary of this employee.
     */
    private float salary;

    /**
     * The job title of this employee.
     */
    private String title;

    /**
     * The default constructor.
     */
    public EmployeeImpl6() {

        this(null, null, null, null, 0.00F, null);
    }

    /**
     * Constructs a dummy person.
     *
     * @param aFirstName
     *        the employee's first name
     * @param aLastName
     *        the employee's last name
     * @param aBirthdate
     *        the employee's birth date
     * @param aGender
     *        the employee's gender
     * @param aSalary
     *        the employee's salary
     * @param aTitle
     *        the employee's job title
     */
    public EmployeeImpl6(String aFirstName, String aLastName, String aBirthdate, String aGender, float aSalary,
                         String aTitle) {

        super();

        person = new PersonImpl(aFirstName, aLastName, aBirthdate, aGender);
        salary = aSalary;
        title = aTitle;
    }

    /**
     * Sets the person's first name
     *
     * @param firstName
     *        a first name
     */
    public void setFirstName(String firstName) {

        person.setFirstName(firstName);
    }

    /**
     * Returns a person's first name.
     *
     * @return a first name
     */
    public String getFirstName() {

        return person.getFirstName();
    }

    /**
     * Sets a person's last name.
     *
     * @param lastName
     *        a last name
     */
    public void setLastName(String lastName) {

        person.setLastName(lastName);
    }

    /**
     * Returns a person's last name.
     *
     * @return a last name
     */
    public String getLastName() {

        return person.getLastName();
    }

    /**
     * Sets a person's birthdate.
     *
     * @param birthdate
     *        a birthdate
     */
    public void setBirthdate(String birthdate) {

        person.setBirthdate(birthdate);
    }

    /**
     * Returns a person's birthdate.
     *
     * @return a birthdate
     */
    public String getBirthdate() {

        return person.getBirthdate();
    }

    /**
     * Sets a person's gender.
     *
     * @param gender
     *        a gender
     */
    public void setGender(String gender) {

        person.setGender(gender);
    }

    /**
     * Returns a person's gender.
     *
     * @return a gender
     */
    public String getGender() {

        return person.getGender();
    }

    /**
     * Sets the employee's monthly salary.
     *
     * @param salary
     *        a salary
     */
    public void setSalary(float salary) {

        this.salary = salary;
    }

    /**
     * Returns the employee's monthly salary.
     *
     * @return a salary
     */
    public float getSalary() {

        return salary;
    }

    /**
     * Sets the employee's job title.
     *
     * @param title
     *        a job title
     */
    public void setTitle(String title) {

        this.title = title;
    }

    /**
     * Returns the employee's job title.
     *
     * @return a jon title
     */
    public String getTitle() {

        return title;
    }

    /**
     * A setter method.
     *
     * @param person
     *        a subset of informations about the employee
     */
    public void setPerson(Person person) {

        this.person = person;
    }

    /**
     * A getter method.
     *
     * @return a subset of informations about the employee
     */
    public Person getPerson() {

        return person;
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

        if (o instanceof Employee) {

            Employee other = (Employee) o;

            return (this.getFirstName().equals(other.getFirstName()) &&
                    this.getLastName().equals(other.getLastName()) &&
                    this.getBirthdate().equals(other.getBirthdate()) && this.getGender().equals(other.getGender()) &&
                    (this.getSalary() == other.getSalary()) && this.getTitle().equals(other.getTitle()));
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
        hash *= getSalary();
        hash *= getTitle().hashCode();

        return hash;
    }

}
