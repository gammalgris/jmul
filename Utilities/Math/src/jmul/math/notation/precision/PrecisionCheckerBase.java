/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package jmul.math.notation.precision;


import jmul.math.notation.NotationHelper;
import jmul.math.notation.NotationProperties;
import jmul.math.notation.PrecisionTypes;


/**
 * A base implementation for a precision checker.
 *
 * @author Kristian Kutin
 */
abstract class PrecisionCheckerBase implements PrecisionChecker {

    /**
     * The default constructor.
     */
    public PrecisionCheckerBase() {

        super();
    }

    /**
     * Returns the identified precision type (i.e. the lowest possible precision type to store
     * the number) for the specified number string.
     *
     * @param aString
     *
     * @return a precision
     */
    @Override
    public PrecisionTypes checkPrecision(String aString) {

        NotationProperties properties = NotationHelper.checkString(aString);

        return checkPrecision(aString, properties);
    }

}
