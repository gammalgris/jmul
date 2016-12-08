/*
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

package jmul.web;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jmul.misc.state.State;
import jmul.misc.state.StateHelper;


/**
 * This enumeration class describes all possible states of a web server.
 *
 * @author Kristian Kutin
 */
public enum WebServerState implements State {

    /**
     * The web server instance is uninitialized.
     */
    UNINITIALIZED(Constants.UNINITIALIZED_STRING, Constants.INITIALIZATION_STRING),

    /**
     * The web server instance is being initialized.
     */
    INITIALIZATION(Constants.INITIALIZATION_STRING, Constants.INITIALIZED_STRING, Constants.ERROR_STRING),

    /**
     * The initialization of the web server instacne is finished.
     */
    INITIALIZED(Constants.INITIALIZED_STRING, Constants.STARTING_STRING),

    /**
     * The web server instance is being started.
     */
    STARTING(Constants.STARTING_STRING, Constants.RUNNING_STRING, Constants.ERROR_STRING),

    /**
     * The web server instance is up and running.
     */
    RUNNING(Constants.RUNNING_STRING, Constants.STOPPING_STRING, Constants.ERROR_STRING),

    /**
     * The web server insatnce is being shut down.
     */
    STOPPING(Constants.STOPPING_STRING, Constants.STOPPED_STRING, Constants.ERROR_STRING),

    /**
     * The web server instance is stopped.
     */
    STOPPED(Constants.STOPPED_STRING),

    /**
     * An unrecoverable error occurred.
     */
    ERROR(Constants.ERROR_STRING), ;


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
    private WebServerState(String aStateName, String... someDestinationStates) {

        stateName = aStateName;


        Set<String> tmp = new HashSet<String>();

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

        if (newState instanceof WebServerState) {

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

        Set<State> states = new HashSet<State>();

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
     *
     * @throws IllegalStateTransitionException
     *
     * @return the new state
     */
    @Override
    public State transitionTo(State newState) {

        if (isAllowedTransition(newState)) {

            return newState;
        }

        throw StateHelper.newIllegalTransitionException(this, newState);
    }

    /**
     * Returns the specified state.
     *
     * @param aStateName
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
 * This utility class contains specific string constants.
 */
final class Constants {

    static final String UNINITIALIZED_STRING = "uninitialized";
    static final String INITIALIZATION_STRING = "initialization";
    static final String INITIALIZED_STRING = "initialized";
    static final String STARTING_STRING = "starting";
    static final String RUNNING_STRING = "running";
    static final String STOPPING_STRING = "stopping";
    static final String STOPPED_STRING = "stopped";
    static final String ERROR_STRING = "error";

}
