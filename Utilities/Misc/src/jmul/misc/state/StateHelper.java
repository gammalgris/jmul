/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.misc.state;


import java.util.Iterator;


/**
 * A utility class.
 *
 * @author Kristian Kutin
 */
public final class StateHelper {

    /**
     * The default constructor.
     */
    private StateHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks the specified parameter.
     *
     * @param aState
     *        a state
     */
    public static void checkParameter(State aState) {

        if (aState == null) {

            String message = "No state (null) has been specified!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Creates a new exception for a state lookup which returns no
     * result.
     *
     * @param aState
     *        a state
     *
     * @return an exception
     */
    public static UnknownStateException newUnknownStateException(State aState) {

        return newUnknownStateException(aState.getStateName());
    }

    /**
     * Creates a new exception for a state lookup which returns no
     * result.
     *
     * @param aStateName
     *        a state name
     *
     * @return an exception
     */
    public static UnknownStateException newUnknownStateException(String aStateName) {

        StringBuilder message = new StringBuilder();
        message.append("The specified state \"");
        message.append(aStateName);
        message.append("\" is unknown!");

        return new UnknownStateException(String.valueOf(message));
    }

    /**
     * Creates a new exception for a failed state transition according to
     * the specified parameters.
     *
     * @param thisState
     *        a state
     * @param destinationState
     *        a state
     *
     * @return an exception
     */
    public static IllegalStateTransitionException newIllegalTransitionException(State thisState,
                                                                                State destinationState) {

        StringBuilder message = new StringBuilder();
        message.append("A transition from ");
        message.append(thisState.getStateName());
        message.append(" to ");
        message.append(destinationState.getStateName());
        message.append(" is not allowed (");
        message.append(thisState.toString());
        message.append(")!");

        return new IllegalStateTransitionException(String.valueOf(message));
    }

    /**
     * Returns a string representation for the specified state.
     *
     * @param aState
     *        a state
     *
     * @return a string representation
     */
    public static String newStringRepresentation(State aState) {

        StringBuilder buffer = new StringBuilder();
        buffer.append(aState.getStateName());

        Iterator<State> i = aState.getAllowedTransitions().iterator();

        if (i.hasNext()) {

            buffer.append(" (-> ");

        } else {

            return buffer.toString();
        }

        while (i.hasNext()) {

            State status = i.next();
            buffer.append(status.getStateName());

            if (i.hasNext()) {

                buffer.append(", ");
            }
        }

        buffer.append(")");

        return buffer.toString();
    }

}
