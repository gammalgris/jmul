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

package test.jmul.datatypes.scenario07;


import java.util.Collection;

import jmul.persistence.annotations.RootNode;

import test.jmul.datatypes.interfaces.Employee;


/**
 * This class contains basic information about a company.<br />
 * <br />
 * <i>Note:<br />
 * This class is used for testing purposes only.
 * </i>
 *
 * @author Kristian Kutin
 */
@RootNode(declaredType = Company.class)
public class Company {

    /**
     * A company name.
     */
    private String companyName;

    /**
     * Contains all currently employed employees.
     */
    private EmployeeList employees;

    /**
     * The default company.
     */
    public Company() {

        super();

        employees = new EmployeeList();
    }

    /**
     * A setter method.
     *
     * @param aCompanyName
     */
    public void setCompanyName(String aCompanyName) {

        companyName = aCompanyName;
    }

    /**
     * A getter method.
     *
     * @return a company name
     */
    public String getCompanyName() {

        return companyName;
    }

    /**
     * Adds a new employee to the company.
     *
     * @param anEmployee
     *        a new employee
     */
    public void addEmployee(Employee anEmployee) {

        if (!employees.contains(anEmployee)) {

            employees.add(anEmployee);
        }
    }

    /**
     * Removes an employee.
     *
     * @param anEmployee
     *        a former employee
     */
    public void removeEmployee(Employee anEmployee) {

        employees.remove(anEmployee);
    }

    /**
     * A setter method.
     *
     * @param employees
     *        all employed employees
     */
    public void setEmployees(Collection<Employee> allEmployees) {

        for (Employee e : allEmployees) {

            employees.add(e);
        }
    }

    /**
     * A getter method.
     *
     * @return all currently employed employees
     */
    public Collection<Employee> getEmployees() {

        return employees;
    }

}
