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

package jmul.component;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jmul.misc.state.IllegalStateTransitionException;
import jmul.misc.state.State;
import jmul.misc.state.StateHelper;
import jmul.misc.state.UnknownStateException;


/**
 * The enumeration class contains the status values for a single component.
 *
 * @author Kristian Kutin
 */
 @Deprecated
public enum ComponentStates implements State {


    /**
     * The component is uninitialized.
     */
    UNINITIALIZED(StateNames.UNINITIALIZED_STRING, StateNames.INITIALIZATION_STRING),

    /**
     * The component is being initialized.
     */
    INITIALIZATION(StateNames.INITIALIZATION_STRING, StateNames.INITIALIZED_STRING, StateNames.FATAL_ERROR_STRING,
                   StateNames.RECOVERABLE_ERROR_STRING),

    /**
     * The initialization of the component is finished.
     */
    INITIALIZED(StateNames.INITIALIZED_STRING, StateNames.STARTING_STRING),

    /**
     * The component is being started.
     */
    STARTING(StateNames.STARTING_STRING, StateNames.RUNNING_STRING, StateNames.FATAL_ERROR_STRING,
             StateNames.RECOVERABLE_ERROR_STRING),

    /**
     * The component is up and running.
     */
    RUNNING(StateNames.RUNNING_STRING, StateNames.STOPPING_STRING, StateNames.FATAL_ERROR_STRING,
            StateNames.RECOVERABLE_ERROR_STRING),

    /**
     * The component is being shut down.
     */
    STOPPING(StateNames.STOPPING_STRING, StateNames.STOPPED_STRING, StateNames.FATAL_ERROR_STRING,
             StateNames.RECOVERABLE_ERROR_STRING),

    /**
     * The component is stopped.
     */
    STOPPED(StateNames.STOPPED_STRING),

    /**
     * An unrecoverable error occurred.
     */
    FATAL_ERROR(StateNames.FATAL_ERROR_STRING),

    /**
     * A recoverable error occurred.
     */
    RECOVERABLE_ERROR(StateNames.RECOVERABLE_ERROR_STRING, StateNames.INITIALIZATION_STRING), ;


    /**
     * The name which represents this state.
     */
    private final String stateName;

    /**
     * All state names which represent valid targets for a state transition.
     */
    private final Set<String> allowedTransitionNames;


    /**
     * Creates a new state according to the specified parameters.
     *
     * @param aStateName
     *        the name for this state
     * @param someDestinationStates
     *        names of destination states
     */
    private ComponentStates(String aStateName, String... someDestinationStates) {

        stateName = aStateName;


        Set<String> tmp = new HashSet<>();

        for (String destinationState : someDestinationStates) {

            tmp.add(destinationState);
        }

        allowedTransitionNames = Collections.unmodifiableSet(tmp);
    }

    /**
     * Checks if a transition to the specified state is allowed.
     *
     * @param newState
     *        the target of a state transition
     *
     * @return <code>true</code> if a transition is possible, else
     *         <code>false</code>
     */
    @Override
    public boolean isAllowedTransition(State newState) {

        StateHelper.checkParameter(newState);

        if (newState instanceof ComponentStates) {

            return isAllowedTransition(newState.getStateName());
        }

        throw StateHelper.newUnknownStateException(newState);
    }

    /**
     * Checks if a transition to the specified state is allowed.
     *
     * @param newStateName
     *        the target of a state transition
     *
     * @return <code>true</code> if a transition is possible, else
     *         <code>false</code>
     */
    private boolean isAllowedTransition(String newStateName) {

        return allowedTransitionNames.contains(newStateName);
    }

    /**
     * A getter method.
     *
     * @return the name of this state
     */
    @Override
    public String getStateName() {

        return stateName;
    }

    /**
     * Returns all allowed transition destinations.
     *
     * @return all allowed transition destinations
     */
    @Override
    public Set<State> getAllowedTransitions() {

        Set<State> states = new HashSet<>();

        for (String destinationName : allowedTransitionNames) {

            State state = getState(destinationName);
            states.add(state);
        }

        return Collections.unmodifiableSet(states);
    }

    /**
     * Returns a string representation for this enumeration element.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return StateHelper.newStringRepresentation(this);
    }

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
    @Override
    public State transitionTo(State newState) {

        if (isAllowedTransition(newState)) {

            return newState;
        }

        throw StateHelper.newIllegalTransitionException(this, newState);
    }

    /**
     * Identifies a state by the specified state name.
     *
     * @param aStateName
     *        the name of a state
     *
     * @return a state
     *
     * @throws UnknownStateException
     *         This exception is thrown if no such state exists.
     */
    public static State getState(String aStateName) {

        for (State state : values()) {

            if (state.getStateName().equals(aStateName)) {

                return state;
            }
        }

        throw StateHelper.newUnknownStateException(aStateName);
    }

}


/**
 * This utility class contains state names.
 */
final class StateNames {

    static final String UNINITIALIZED_STRING = "uninitialized";
    static final String INITIALIZATION_STRING = "initialization";
    static final String INITIALIZED_STRING = "initialized";
    static final String STARTING_STRING = "starting";
    static final String RUNNING_STRING = "running";
    static final String STOPPING_STRING = "stopping";
    static final String STOPPED_STRING = "stopped";
    static final String RECOVERABLE_ERROR_STRING = "recoverable error";
    static final String FATAL_ERROR_STRING = "fatal error";

    /**
     * The default constructor.
     */
    private StateNames() {

        throw new UnsupportedOperationException();
    }

}
