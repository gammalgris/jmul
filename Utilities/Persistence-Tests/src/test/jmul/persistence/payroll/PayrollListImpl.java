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

package test.jmul.persistence.payroll;


import java.util.ArrayList;
import java.util.Collection;

import jmul.persistence.annotations.ContainerInformations;

import test.jmul.persistence.employee.Employee;

import jmul.string.StringConcatenator;


/**
 * An implementation of a payroll list.<br>
 * <br>
 * <i>This code is used for testing purposes only.</i>
 *
 * @author Kristian Kutin
 */
public class PayrollListImpl implements PayrollList {

    /**
     * All employees which are listed in the company's payroll list.
     */
    @ContainerInformations(declaredElementType = Employee.class)
    private Collection<Employee> employees;

    /**
     * The default constrcutor.
     */
    public PayrollListImpl() {

        employees = new ArrayList<Employee>();
    }

    /**
     * Adds an employee to the company's payroll list.
     *
     * @param anEmployee
     *        an employee
     */
    public void addEmployee(Employee anEmployee) {

        if (employees.contains(anEmployee)) {

            StringConcatenator message =
                new StringConcatenator("The specified employee (", anEmployee.getLastName(), ", ",
                                       anEmployee.getFirstName(), ") is already on the company's payroll list!");
            throw new IllegalArgumentException(message.toString());
        }

        employees.add(anEmployee);
    }

    /**
     * Removes an employee from the company's payroll list.
     *
     * @param anEmployee
     *        an employee
     */
    public void removeEmployee(Employee anEmployee) {

        if (!employees.contains(anEmployee)) {

            StringConcatenator message =
                new StringConcatenator("The specified employee (", anEmployee.getLastName(), ", ",
                                       anEmployee.getFirstName(), ") is not on the company's payroll list!");
            throw new IllegalArgumentException(message.toString());
        }

        employees.remove(anEmployee);
    }

    /**
     * Calculates the total monthly salary costs of the company.
     *
     * @return total monthly salary costs
     */
    public float calculateTotalSalaries() {

        float total = 0.0f;

        for (Employee employee : employees) {

            total += employee.getSalary();
        }

        return total;
    }

    /**
     * A setter method.
     *
     * @param employees
     *        all employees which are on the company's payroll list
     */
    public void setEmployees(Collection<Employee> employees) {

        this.employees = employees;
    }

    /**
     * A getter method.
     *
     * @return all employees which are currently on the company's payroll list
     */
    public Collection<Employee> getEmployees() {

        return employees;
    }

}
