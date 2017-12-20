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

package test.jmul.persistence.scenarios.scenario022;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import jmul.misc.state.State;
import jmul.misc.state.StateHelper;


/**
 * This enumeration class describes all possible states of a task.
 *
 * @author Kristian Kutin
 */
public enum LifecycleStates implements State {


    /**
     * The lifecycle is uninitialized.
     */
    UNINITIALIZED(StateNames.UNINITIALIZED_STRING, StateNames.INITIALIZING_FIRST_TASK_STRING),

    /**
     * The lifecycle is being initialized.
     */
    INITIALIZING_FIRST_TASK(StateNames.INITIALIZING_FIRST_TASK_STRING, StateNames.INITIALIZED_FIRST_TASK_STRING,
                            StateNames.ERROR_STRING),

    /**
     * The initialization of the lifefycle is finished.
     */
    INITIALIZED_FIRST_TASK(StateNames.INITIALIZED_FIRST_TASK_STRING, StateNames.STARTING_FIRST_TASK_STRING),

    /**
     * The first task of the lifecycle is started.
     */
    STARTING_FIRST_TASK(StateNames.STARTING_FIRST_TASK_STRING, StateNames.EXECUTING_FIRST_TASK_STRING,
                        StateNames.ERROR_STRING),

    /**
     * The first task of the lifecycle is being executed.
     */
    EXECUTING_FIRST_TASK(StateNames.EXECUTING_FIRST_TASK_STRING, StateNames.FINISHED_FIRST_TASK_STRING,
                         StateNames.ERROR_STRING),

    /**
     * The first task of the lifecycle has finished.
     */
    FINISHED_FIRST_TASK(StateNames.FINISHED_FIRST_TASK_STRING, StateNames.INITIALIZING_FOLLOWUP_TASKS_STRING),

    /**
     * The followup tasks are initialized.
     */
    INITIALIZING_FOLLOWUP_TASKS(StateNames.INITIALIZING_FOLLOWUP_TASKS_STRING,
                                StateNames.INITIALIZED_FOLLOWUP_TASKS_STRING, StateNames.ERROR_STRING),

    /**
     * The followup tasks have been initialized.
     */
    INITIALIZED_FOLLOWUP_TASKS(StateNames.INITIALIZED_FOLLOWUP_TASKS_STRING, StateNames.STARTING_FOLLOWUP_TASKS_STRING),

    /**
     * The followup tasks are started.
     */
    STARTING_FOLLOWUP_TASKS(StateNames.STARTING_FOLLOWUP_TASKS_STRING, StateNames.EXECUTING_FOLLOWUP_TASKS_STRING,
                            StateNames.ERROR_STRING),

    /**
     * The followup tasks are executed.
     */
    EXECUTING_FOLLOWUP_TASKS(StateNames.EXECUTING_FOLLOWUP_TASKS_STRING, StateNames.FINISHED_FOLLOWUP_TASKS_STRING,
                             StateNames.ERROR_STRING),

    /**
     * The followup tasks have been finished.
     */
    FINISHED_FOLLOWUP_TASKS(StateNames.FINISHED_FOLLOWUP_TASKS_STRING),

    /**
     * An unrecoverable error occurred.
     */
    ERROR(StateNames.ERROR_STRING), ;


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
    private LifecycleStates(String aStateName, String... someDestinationStates) {

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

        if (newState instanceof LifecycleStates) {

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
     *        the new state
     *
     * @throws IllegalStateTransitionException
     *         is thrown if the current state doesn't allow a transition to the
     *         specified state
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
    static final String INITIALIZING_FIRST_TASK_STRING = "initializing first task";
    static final String INITIALIZED_FIRST_TASK_STRING = "initialized first task";
    static final String STARTING_FIRST_TASK_STRING = "starting first task";
    static final String EXECUTING_FIRST_TASK_STRING = "executing first task";
    static final String FINISHED_FIRST_TASK_STRING = "finished first task";
    static final String INITIALIZING_FOLLOWUP_TASKS_STRING = "initializing followup tasks";
    static final String INITIALIZED_FOLLOWUP_TASKS_STRING = "initialized followup tasks";
    static final String STARTING_FOLLOWUP_TASKS_STRING = "starting followup tasks";
    static final String EXECUTING_FOLLOWUP_TASKS_STRING = "executing followup tasks";
    static final String FINISHED_FOLLOWUP_TASKS_STRING = "finished followup tasks";
    static final String ERROR_STRING = "error";

}
