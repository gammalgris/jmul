/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package test.jmul.helper;


import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


/**
 * A helper class for running tests.
 */
public final class TestRunnerHelper {

    /**
     * The default constructor.
     */
    private TestRunnerHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * The method executes the specified test classes.
     *
     * @param someTestClasses
     */
    public static void runTests(Class... someTestClasses) {

        Result result = JUnitCore.runClasses(someTestClasses);

        System.out.println();

        boolean success = result.wasSuccessful();
        if (success) {

            System.out.println("Tests were successful.");

        } else {

            System.out.println("Tests were not successful.");

            List<Failure> failures = result.getFailures();
            for (Failure failure : failures) {

                System.out.println("  " + failure);
            }
        }
    }

}
