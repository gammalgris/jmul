/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

package test.jmul.datatypes.scenarios.interfaces;


import jmul.test.classification.Testdata;


/**
 * This interface describes an employee.<br>
 * <br>
 * <i>This code is used for testing purposes only.</i>
 *
 * @author Kristian Kutin
 */
@Testdata
public interface Employee extends Person {

    /**
     * Sets the employee's job title.
     *
     * @param title
     *        a job title
     */
    void setJobTitle(String title);

    /**
     * Returns the employee's job title.
     *
     * @return a jon title
     */
    String getJobTitle();

    /**
     * Sets the employee's monthly salary.
     *
     * @param salary
     *        a salary
     */
    void setSalary(float salary);

    /**
     * Returns the employee's monthly salary.
     *
     * @return a salary
     */
    float getSalary();

}
