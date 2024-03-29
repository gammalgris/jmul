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


import java.util.Set;


/**
 * This interface describes a state.
 */
public interface State {

    /**
     * Returns the name of this state.
     *
     * @return a state name
     */
    String getStateName();

    /**
     * Returns all allowed transition destinations.
     *
     * @return all allowed transition destinations
     */
    Set<State> getAllowedTransitions();

    /**
     * Checks if a transition to the specified state is allowed.
     *
     * @param newState
     *        the target of a state transition
     *
     * @return <code>true</code> if a transition is possible, else
     *         <code>false</code>
     */
    boolean isAllowedTransition(State newState);

    /**
     * Performs a state transitions and returns the new state.
     *
     * @param newState
     *        the new state
     *
     * @return the new state
     *
     * @throws IllegalStateTransitionException
     *         is thrown if the current state doesn't allow a transition to the
     *         specified state
     */
    State transitionTo(State newState);

}
