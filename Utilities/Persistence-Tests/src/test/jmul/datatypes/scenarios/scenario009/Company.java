/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

package test.jmul.datatypes.scenarios.scenario009;


import java.util.Map;

import jmul.transformation.xml.annotations.RootNode;

import test.jmul.datatypes.scenarios.interfaces.Employee;


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
    private EmployeeMap employees;

    /**
     * The default company.
     */
    public Company() {

        super();

        employees = new EmployeeMap();
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
     * Creates an individual ID for the specified employee.
     *
     * @param anEmployee
     *
     * @return an ID
     */
    private static String createId(Employee anEmployee) {

        StringBuffer buffer = new StringBuffer();

        int prefixLength = Math.min(anEmployee.getFirstName().length(), 2);
        int suffixLength = Math.min(anEmployee.getLastName().length(), 6);

        String prefix = anEmployee.getFirstName().substring(0, prefixLength - 1);
        String suffix = anEmployee.getLastName().substring(0, suffixLength - 1);
        String number = anEmployee.getBirthDate().replaceAll("[^0-9]", "");

        buffer.append(prefix);
        buffer.append(suffix);
        buffer.append(number);

        return buffer.toString();
    }

    /**
     * Adds a new employee to the company.
     *
     * @param anEmployee
     *        a new employee
     */
    public void addEmployee(Employee anEmployee) {

        String id = createId(anEmployee);

        if (!employees.containsKey(id)) {

            employees.put(id, anEmployee);
        }
    }

    /**
     * Removes an employee.
     *
     * @param anEmployee
     *        a former employee
     */
    public void removeEmployee(Employee anEmployee) {

        String id = createId(anEmployee);

        employees.remove(id);
    }

    /**
     * A setter method.
     *
     * @param employees
     *        all employed employees
     */
    public void setEmployees(Map<String, Employee> allEmployees) {

        for (String id : allEmployees.keySet()) {

            Employee e = allEmployees.get(id);
            employees.put(id, e);
        }
    }

    /**
     * A getter method.
     *
     * @return all currently employed employees
     */
    public Map<String, Employee> getEmployees() {

        return employees;
    }

}
