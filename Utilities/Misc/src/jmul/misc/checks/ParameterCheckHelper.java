/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.misc.checks;

import jmul.misc.exceptions.EmptyArrayParameterException;
import jmul.misc.exceptions.EmptyStringParameterException;
import jmul.misc.exceptions.NullArrayParameterException;
import jmul.misc.exceptions.NullParameterException;


/**
 * A helper class for checking parameters.
 *
 * @author Kristian Kutin
 */
public final class ParameterCheckHelper {

    /**
     * The default constructor.
     */
    private ParameterCheckHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks the specified parameter.
     *
     * @param aMessage
     *
     * @return the message
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static String checkExceptionMessage(String aMessage) {

        if (aMessage == null) {

            throw new NullParameterException();
        }

        if (aMessage.trim().isEmpty()) {

            throw new EmptyStringParameterException();
        }

        return aMessage;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aCause
     *
     * @return a throwable
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static Throwable checkExceptionCause(Throwable aCause) {

        if (aCause == null) {

            throw new NullParameterException();
        }

        return aCause;
    }

    /**
     * Checks the specified parameter.
     *
     * @param someCauses
     *
     * @return some throwables
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static Throwable[] checkExceptionCauses(Throwable[] someCauses) {

        if (someCauses == null) {

            throw new NullArrayParameterException();
        }

        if (someCauses.length == 0) {

            throw new EmptyArrayParameterException();
        }

        return someCauses;
    }

}
