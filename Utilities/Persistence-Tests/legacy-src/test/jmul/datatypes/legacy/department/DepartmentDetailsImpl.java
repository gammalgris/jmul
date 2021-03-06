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

package test.jmul.datatypes.legacy.department;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jmul.transformation.xml.annotations.ContainerInformations;
import jmul.transformation.xml.annotations.MapInformations;
import jmul.transformation.xml.annotations.RootNode;

import test.jmul.datatypes.legacy.employee.Employee;

import jmul.string.StringConcatenator;


/**
 * An implementation of detpartment details.<br>
 * <br>
 * <i>This code is used for testing purposes only.</i>
 *
 * @author Kristian Kutin
 */
@RootNode(declaredType = DepartmentDetails.class)
public class DepartmentDetailsImpl implements DepartmentDetails {

    /**
     * All employees of this department.
     */
    @ContainerInformations(declaredElementType = Employee.class)
    private Collection<Employee> unsortedWorkforce;

    /**
     * All employees of this department sorted by job title.
     */
    @MapInformations(declaredKeyType = String.class, declaredValueType = Collection.class)
    private Map<String, Collection<Employee>> sortedWorkforce;

    /**
     * The default constructor.
     */
    public DepartmentDetailsImpl() {

        unsortedWorkforce = new ArrayList<Employee>();
        sortedWorkforce = new HashMap<String, Collection<Employee>>();
    }

    /**
     * Adds a new employee to the company.
     *
     * @param anEmployee
     *        a new employee
     */
    public void addEmployee(Employee anEmployee) {

        if (unsortedWorkforce.contains(anEmployee)) {

            StringConcatenator message =
                new StringConcatenator("The specified employee (", anEmployee.getLastName(), ", ",
                                       anEmployee.getFirstName(), ") already works in this department!");
            throw new IllegalArgumentException(message.toString());
        }


        unsortedWorkforce.add(anEmployee);


        String title = anEmployee.getTitle();
        Collection<Employee> employees = sortedWorkforce.get(title);

        if (employees == null) {

            employees = new ArrayList<Employee>();
            sortedWorkforce.put(title, employees);
        }

        employees.add(anEmployee);
    }

    /**
     * Removes an employee.
     *
     * @param anEmployee
     *        a former employee
     */
    public void removeEmployee(Employee anEmployee) {

        if (!unsortedWorkforce.contains(anEmployee)) {

            StringConcatenator message =
                new StringConcatenator("The specified employee (", anEmployee.getLastName(), ", ",
                                       anEmployee.getFirstName(), ") doesn't work for this department!");
            throw new IllegalArgumentException(message.toString());
        }


        unsortedWorkforce.remove(anEmployee);


        String title = anEmployee.getTitle();
        Collection<Employee> employees = sortedWorkforce.get(title);
        employees.remove(anEmployee);
    }

    /**
     * Calculates the total monthly salary costs of the company.
     *
     * @return total monthly salary costs
     */
    public float calculateTotalSalaries() {

        float total = 0.0f;

        for (Employee employee : unsortedWorkforce) {

            total += employee.getSalary();
        }

        return total;
    }

    /**
     * Returns all employees which are currently employed at this company.
     *
     * @return all currently employed employees
     */
    public Collection<Employee> getAllEmployees() {

        return getUnsortedWorkforce();
    }

    /**
     * The method determines how many employees with the specified job title
     * work in this department.
     *
     * @param aTitle
     *        a job title
     *
     * @return the number of employees with the specified job title.
     */
    public int employed(String aTitle) {

        int total = 0;

        Collection<Employee> employees = sortedWorkforce.get(aTitle);

        return (employees == null) ? total : employees.size();
    }

    /**
     * Calculates the total monthly salary costs of all employees with the
     * specified job title.
     *
     * @param aTitle
     *        a job title
     *
     * @return total monthly salary costs for all employees with the specified
     *         job title
     */
    public float calculateTotalSalaries(String aTitle) {

        float total = 0.0f;

        Collection<Employee> employees = sortedWorkforce.get(aTitle);

        if (employees == null) {

            return total;
        }


        for (Employee employee : employees) {

            total += employee.getSalary();
        }

        return total;
    }

    /**
     * A setter method.
     *
     * @param unsortedWorkforce
     *        the department's workforce
     */
    public void setUnsortedWorkforce(Collection<Employee> unsortedWorkforce) {

        this.unsortedWorkforce = unsortedWorkforce;
    }

    /**
     * A getter method.
     *
     * @return the department's workforce
     */
    public Collection<Employee> getUnsortedWorkforce() {

        return unsortedWorkforce;
    }

    /**
     * A setter method.
     *
     * @param sortedWorkforce
     *        the department's workforce
     */
    public void setSortedWorkforce(Map<String, Collection<Employee>> sortedWorkforce) {

        this.sortedWorkforce = sortedWorkforce;
    }

    /**
     * A getter method.
     *
     * @return the department's workforce
     */
    public Map<String, Collection<Employee>> getSortedWorkforce() {

        return sortedWorkforce;
    }

}
