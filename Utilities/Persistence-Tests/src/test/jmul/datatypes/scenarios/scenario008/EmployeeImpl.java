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

package test.jmul.datatypes.scenarios.scenario008;


import test.jmul.datatypes.scenarios.interfaces.Employee;


/**
 * This class contains basic information about a person.<br />
 * <br />
 * <i>Note:<br />
 * This class is used for testing purposes only.
 * </i>
 *
 * @author Kristian Kutin
 */
public class EmployeeImpl extends PersonImpl implements Employee {

    /**
     * A job title.
     */
    private String jobTitle;

    /**
     * A monthly salary.
     */
    private float salary;

    /**
     * The default constructor.
     */
    public EmployeeImpl() {

        super();
    }

    /**
     * A setter method.
     *
     * @param aJobTitle
     */
    public void setJobTitle(String aJobTitle) {

        jobTitle = aJobTitle;
    }

    /**
     * A getter method.
     *
     * @return a job title
     */
    public String getJobTitle() {

        return jobTitle;
    }

    /**
     * A setter method.
     *
     * @param salary
     */
    public void setSalary(float aSalary) {

        salary = aSalary;
    }

    /**
     * A getter method.
     *
     * @return a monthly salary
     */
    public float getSalary() {

        return salary;
    }

}
