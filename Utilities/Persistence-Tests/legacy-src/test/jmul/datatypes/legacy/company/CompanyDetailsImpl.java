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

package test.jmul.datatypes.legacy.company;


import java.util.ArrayList;
import java.util.Collection;

import jmul.transformation.xml.annotations.ContainerInformations;
import jmul.transformation.xml.annotations.RootNode;

import test.jmul.datatypes.legacy.employee.Employee;
import test.jmul.datatypes.legacy.payroll.PayrollList;
import test.jmul.datatypes.legacy.payroll.PayrollListImpl;


/**
 * An implementation of company details.<br>
 * <br>
 * <i>Note:<br />
 * This class is used for testing purposes only.
 * <ol>
 *   <li>has a public default constructor</li>
 *   <li>all getter methods are public</li>
 *   <li>all setter methods are public</li>
 *   <li>can be serialized on its own</li>
 *   <li>can be deserialized on its own</li>
 * </ol></i>
 *
 * @author Kristian Kutin
 */
@RootNode(declaredType = CompanyDetails.class)
public class CompanyDetailsImpl implements CompanyDetails {

    /**
     * Contains all currently employed employees.
     */
    @ContainerInformations(declaredElementType = Employee.class)
    private Collection<Employee> employees;

    /**
     * The company's payroll list.
     */
    private PayrollList payrollList;

    /**
     * The default constructor.
     */
    public CompanyDetailsImpl() {

        employees = new ArrayList<Employee>();
        payrollList = new PayrollListImpl();
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
            payrollList.addEmployee(anEmployee);
        }
    }

    /**
     * Removes an employee.
     *
     * @param anEmployee
     *        a former employee
     */
    public void removeEmployee(Employee anEmployee) {

        if (employees.contains(anEmployee)) {

            employees.remove(anEmployee);
            payrollList.removeEmployee(anEmployee);
        }
    }

    /**
     * Calculates the total monthly salary costs of the company.
     *
     * @return total monthly salary costs
     */
    public float calculateTotalSalaries() {

        return payrollList.calculateTotalSalaries();
    }

    /**
     * Returns all employees which are currently employed at this company.
     *
     * @return all currently employed employees
     */
    public Collection<Employee> getAllEmployees() {

        return getEmployees();
    }

    /**
     * A setter method.
     *
     * @param payrollList
     *        the company's new payroll list
     */
    public void setPayrollList(PayrollList payrollList) {

        this.payrollList = payrollList;
    }

    /**
     * A getter method.
     *
     * @return the company's current payroll list
     */
    public PayrollList getPayrollList() {

        return payrollList;
    }

    /**
     * A setter method.
     *
     * @param employees
     *        all employed employees
     */
    public void setEmployees(Collection<Employee> employees) {

        this.employees = employees;
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
