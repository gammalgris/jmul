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

package test.jmul.datatypes.payroll;


import test.jmul.datatypes.employee.Employee;


/**
 * This interface describes a payroll list.<br>
 * <br>
 * <i>This code is used for testing purposes only.</i>
 *
 * @author Kristian Kutin
 */
public interface PayrollList {

    /**
     * Adds an employee to the company's payroll list.
     *
     * @param anEmployee
     *        an employee
     */
    void addEmployee(Employee anEmployee);

    /**
     * Removes an employee from the company's payroll list.
     *
     * @param anEmployee
     *        an employee
     */
    void removeEmployee(Employee anEmployee);

    /**
     * Calculates the total monthly salary costs of the company.
     *
     * @return total monthly salary costs
     */
    float calculateTotalSalaries();

}
