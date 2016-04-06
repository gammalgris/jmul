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

package jmul.string;


import jmul.misc.exceptions.EmptyStringParameterException;
import jmul.misc.exceptions.NullParameterException;


/**
 * This exception is thrown if an unknown placeholder is being processed.
 *
 * @author Kristian Kutin
 */
public class UnknownPlaceholderException extends IllegalArgumentException {

    /**
     * The default constructor.
     *
     * @param aMessage
     *        a message containing a concrete description of the exception
     */
    public UnknownPlaceholderException(String aMessage) {

        super(checkParameter(aMessage));
    }

    /**
     * Checks the specified parameter.
     *
     * @param aMessage
     *
     * @return the message
     *
     * @thorws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    private static String checkParameter(String aMessage) {

        if (aMessage == null) {

            throw new NullParameterException();
        }

        if (aMessage.trim().isEmpty()) {

            throw new EmptyStringParameterException();
        }

        return aMessage;
    }

}
