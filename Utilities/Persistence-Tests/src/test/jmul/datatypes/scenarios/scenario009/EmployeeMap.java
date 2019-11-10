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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.datatypes.scenarios.scenario009;


import java.util.HashMap;
import java.util.Map;

import jmul.test.classification.Testdata;

import jmul.transformation.xml.annotations.MapInformations;

import test.jmul.datatypes.scenarios.interfaces.Employee;


/**
 * This class is a container for employee data.<br />
 * <br />
 * <i>Note:<br />
 * This class is used for testing purposes only.
 * </i>
 *
 * @author Kristian Kutin
 */
@Testdata
@MapInformations(declaredKeyType = String.class, declaredValueType = Employee.class)
public class EmployeeMap extends HashMap<String, Employee> implements Map<String, Employee> {

    /**
     * The default constructor.
     */
    public EmployeeMap() {

        super();
    }

}
