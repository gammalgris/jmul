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


import static jmul.component.ComponentStates.INITIALIZATION;
import static jmul.component.ComponentStates.STARTING;
import static jmul.component.ComponentStates.STOPPING;

import jmul.misc.state.State;


/**
 * A base implementation of a component.
 *
 * @author Kristian Kutin
 */
public abstract class ComponentBase implements Component {

    /**
     * The component state.
     */
    private State componentState;

    /**
     * An error.
     */
    private Throwable error;

    /**
     * The default constructor.
     */
    protected ComponentBase() {

        super();

        componentState = ComponentStates.UNINITIALIZED;
    }

    /**
     * Returns the current state of the component.
     *
     * @return a state
     */
    @Override
    public State getComponentState() {

        synchronized (this) {

            return componentState;
        }
    }

    /**
     * The component state is set to the specified new state.
     *
     * @param newState
     *        the component's new state
     */
    protected void transitionTo(State newState) {

        synchronized (this) {

            componentState = componentState.transitionTo(newState);
        }
    }

    /**
     * Initializes the component (i.e. the status is set accordingly but the
     * initialization is delegated to another method).
     */
    protected void initializeComponent() {

        transitionTo(INITIALIZATION);

        init();
    }

    /**
     * This method does the actual initialization and must be overridden
     * by an implementing class.
     */
    protected abstract void init();

    /**
     * Starts the component.
     */
    @Override
    public void startComponent() {

        initializeComponent();

        transitionTo(STARTING);

        start();
    }

    /**
     * This method takes care of starting teh resource (e.g. allocating resources,
     * etc.).
     */
    protected abstract void start();

    /**
     * Restarts the component after a recoverable error occurred.
     */
    @Override
    public void restartComponent() {

        startComponent();
    }

    /**
     * Stops the component.
     */
    @Override
    public void stopComponent() {

        transitionTo(STOPPING);

        stop();
    }

    /**
     * This method takes care of stopping the component (e.g. closing resources,
     * etc.).
     */
    protected abstract void stop();

    /**
     * Returns an error or exception if it has occurred.
     *
     * @return an error/ exception or <code>null</code> if none has occurred
     */
    @Override
    public Throwable getError() {

        synchronized (this) {

            return error;
        }
    }

    /**
     * Sets the specified error as the occuring error.
     *
     * @param anError
     */
    protected void setError(Throwable anError) {

        synchronized (this) {

            error = anError;
        }
    }

}
