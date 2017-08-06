/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2015  Kristian Kutin
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

package jmul.misc.state;


import static jmul.misc.checks.ParameterCheckHelper.checkExceptionCause;
import static jmul.misc.checks.ParameterCheckHelper.checkExceptionMessage;


/**
 * A custom exception class for illegal state transitions.
 *
 * @author Kristian Kutin
 */
public class IllegalStateTransitionException extends IllegalArgumentException {

    /**
     * The default constructor.
     */
    public IllegalStateTransitionException() {

        super();
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aMessage
     */
    public IllegalStateTransitionException(String aMessage) {

        super(checkExceptionMessage(aMessage));
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aCause
     */
    public IllegalStateTransitionException(Throwable aCause) {

        super(checkExceptionCause(aCause));
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aMessage
     * @param aCause
     */
    public IllegalStateTransitionException(String aMessage, Throwable aCause) {

        super(checkExceptionMessage(aMessage), checkExceptionCause(aCause));
    }

}
