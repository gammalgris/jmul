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

package test.jmul.datatypes.legacy.department;


import test.jmul.datatypes.legacy.organisation.OrganisationDetails;


/**
 * This interface describes a department and the employees that are assigned
 * to this department.<br>
 * <br>
 * <i>This code is used for testing purposes only.</i>
 *
 * @author Kristian Kutin
 */
public interface DepartmentGenderDetails extends OrganisationDetails {

    /**
     * The method determines how many employees with the specified job title
     * work in this department.
     *
     * @param aTitle
     *        a job title
     * @param aGender
     *        a gender
     *
     * @return the number of employees with the specified job title.
     */
    int employed(String aTitle, String aGender);

    /**
     * Calculates the total monthly salary costs of all employees with the
     * specified job title.
     *
     * @param aTitle
     *        a job title
     * @param aGender
     *        a gender
     *
     * @return total monthly salary costs for all employees with the specified
     *         job title
     */
    float calculateTotalSalaries(String aTitle, String aGender);

}
