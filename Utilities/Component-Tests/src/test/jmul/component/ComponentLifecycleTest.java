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

package test.jmul.component;


import jmul.component.Component;
import jmul.component.ComponentBase;
import static jmul.component.ComponentStates.FATAL_ERROR;
import static jmul.component.ComponentStates.INITIALIZED;
import static jmul.component.ComponentStates.RECOVERABLE_ERROR;
import static jmul.component.ComponentStates.RUNNING;
import static jmul.component.ComponentStates.STOPPED;
import static jmul.component.ComponentStates.UNINITIALIZED;
import jmul.component.PassiveComponent;

import jmul.concurrent.threads.ThreadHelper;

import static jmul.math.Constants.SECOND;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;


/**
 * The class contains tests for various lifecycle scenarios.
 *
 * @author Kristian Kutin
 */
public class ComponentLifecycleTest {

    /**
     * Lifecycle Scenario 1:<br />
     * Everything works without error.
     */
    @Test
    public void testScenario1() {

        Component component = new DummyComponent1();
        assertEquals(UNINITIALIZED, component.getComponentState());

        component.startComponent();
        assertEquals(RUNNING, component.getComponentState());

        component.stopComponent();
        assertEquals(STOPPED, component.getComponentState());
    }

    /**
     * Lifecycle Scenario 2:<br />
     * The initialization fails (recoverable error).
     */
    @Test
    public void testScenario2() {

        Component component = new DummyComponent2();
        assertEquals(UNINITIALIZED, component.getComponentState());

        try {

            component.startComponent();
            fail("An exception was expected but none was thrown!");

        } catch (RuntimeException e) {

            assertEquals(RECOVERABLE_ERROR, component.getComponentState());
        }
    }

    /**
     * Lifecycle Scenario 3:<br />
     * The initialization fails (fatal error).
     */
    @Test
    public void testScenario3() {

        Component component = new DummyComponent3();
        assertEquals(UNINITIALIZED, component.getComponentState());

        try {

            component.startComponent();
            fail("An exception was expected but none was thrown!");

        } catch (RuntimeException e) {

            assertEquals(FATAL_ERROR, component.getComponentState());
        }
    }

    /**
     * Lifecycle Scenario 4:<br />
     * Starting the component fails (recoverable error).
     */
    @Test
    public void testScenario4() {

        Component component = new DummyComponent4();
        assertEquals(UNINITIALIZED, component.getComponentState());

        try {

            component.startComponent();
            fail("An exception was expected but none was thrown!");

        } catch (RuntimeException e) {

            assertEquals(RECOVERABLE_ERROR, component.getComponentState());
        }
    }

    /**
     * Lifecycle Scenario 5:<br />
     * Starting the component fails (fatal error).
     */
    @Test
    public void testScenario5() {

        Component component = new DummyComponent5();
        assertEquals(UNINITIALIZED, component.getComponentState());

        try {

            component.startComponent();
            fail("An exception was expected but none was thrown!");

        } catch (RuntimeException e) {

            assertEquals(FATAL_ERROR, component.getComponentState());
        }
    }

    /**
     * Lifecycle Scenario 5:<br />
     * Stopping the component fails (recoverable error).
     */
    @Test
    public void testScenario6() {

        Component component = new DummyComponent6();
        assertEquals(UNINITIALIZED, component.getComponentState());

        component.startComponent();
        assertEquals(RUNNING, component.getComponentState());

        try {

            component.stopComponent();
            fail("An exception was expected but none was thrown!");

        } catch (RuntimeException e) {

            assertEquals(RECOVERABLE_ERROR, component.getComponentState());
        }
    }

    /**
     * Lifecycle Scenario 6:<br />
     * Stopping the component fails (fatal error).
     */
    @Test
    public void testScenario7() {

        Component component = new DummyComponent7();
        assertEquals(UNINITIALIZED, component.getComponentState());

        component.startComponent();
        assertEquals(RUNNING, component.getComponentState());

        try {

            component.stopComponent();
            fail("An exception was expected but none was thrown!");

        } catch (RuntimeException e) {

            assertEquals(FATAL_ERROR, component.getComponentState());
        }
    }

}


/**
 * A dummy component to test a lifecycle without errors.
 */
class DummyComponent1 extends ComponentBase implements PassiveComponent {

    /**
     * A onstant value.
     */
    private final long DEFAULT_SLEEP_TIME = SECOND * 3L;

    /**
     * The default constructor.
     */
    public DummyComponent1() {

        super();
    }

    /**
     * Performs the actual initialization, including state transition.
     */
    @Override
    protected void init() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(INITIALIZED);
    }

    /**
     * Performs the actual start up, including state transition.
     */
    @Override
    protected void start() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(RUNNING);
    }

    /**
     * Performs the actual shutdown, including state transition.
     */
    @Override
    protected void stop() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(STOPPED);
    }

}


/**
 * A dummy component to test a lifecycle without errors.
 */
class DummyComponent2 extends ComponentBase implements PassiveComponent {

    /**
     * A onstant value.
     */
    private final long DEFAULT_SLEEP_TIME = SECOND * 3L;

    /**
     * The default constructor.
     */
    public DummyComponent2() {

        super();
    }

    /**
     * Performs the actual initialization, including state transition.
     */
    @Override
    protected void init() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(RECOVERABLE_ERROR);
        throw new RuntimeException();
    }

    /**
     * Performs the actual start up, including state transition.
     */
    @Override
    protected void start() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(RUNNING);
    }

    /**
     * Performs the actual shutdown, including state transition.
     */
    @Override
    protected void stop() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(STOPPED);
    }

}


/**
 * A dummy component to test a lifecycle without errors.
 */
class DummyComponent3 extends ComponentBase implements PassiveComponent {

    /**
     * A onstant value.
     */
    private final long DEFAULT_SLEEP_TIME = SECOND * 3L;

    /**
     * The default constructor.
     */
    public DummyComponent3() {

        super();
    }

    /**
     * Performs the actual initialization, including state transition.
     */
    @Override
    protected void init() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(FATAL_ERROR);
        throw new RuntimeException();
    }

    /**
     * Performs the actual start up, including state transition.
     */
    @Override
    protected void start() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(RUNNING);
    }

    /**
     * Performs the actual shutdown, including state transition.
     */
    @Override
    protected void stop() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(STOPPED);
    }

}


/**
 * A dummy component to test a lifecycle without errors.
 */
class DummyComponent4 extends ComponentBase implements PassiveComponent {

    /**
     * A onstant value.
     */
    private final long DEFAULT_SLEEP_TIME = SECOND * 3L;

    /**
     * The default constructor.
     */
    public DummyComponent4() {

        super();
    }

    /**
     * Performs the actual initialization, including state transition.
     */
    @Override
    protected void init() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(INITIALIZED);
    }

    /**
     * Performs the actual start up, including state transition.
     */
    @Override
    protected void start() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(RECOVERABLE_ERROR);
        throw new RuntimeException();
    }

    /**
     * Performs the actual shutdown, including state transition.
     */
    @Override
    protected void stop() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(STOPPED);
    }

}


/**
 * A dummy component to test a lifecycle without errors.
 */
class DummyComponent5 extends ComponentBase implements PassiveComponent {

    /**
     * A onstant value.
     */
    private final long DEFAULT_SLEEP_TIME = SECOND * 3L;

    /**
     * The default constructor.
     */
    public DummyComponent5() {

        super();
    }

    /**
     * Performs the actual initialization, including state transition.
     */
    @Override
    protected void init() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(INITIALIZED);
    }

    /**
     * Performs the actual start up, including state transition.
     */
    @Override
    protected void start() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(FATAL_ERROR);
        throw new RuntimeException();
    }

    /**
     * Performs the actual shutdown, including state transition.
     */
    @Override
    protected void stop() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(STOPPED);
    }

}


/**
 * A dummy component to test a lifecycle without errors.
 */
class DummyComponent6 extends ComponentBase implements PassiveComponent {

    /**
     * A onstant value.
     */
    private final long DEFAULT_SLEEP_TIME = SECOND * 3L;

    /**
     * The default constructor.
     */
    public DummyComponent6() {

        super();
    }

    /**
     * Performs the actual initialization, including state transition.
     */
    @Override
    protected void init() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(INITIALIZED);
    }

    /**
     * Performs the actual start up, including state transition.
     */
    @Override
    protected void start() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(RUNNING);
    }

    /**
     * Performs the actual shutdown, including state transition.
     */
    @Override
    protected void stop() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(RECOVERABLE_ERROR);
        throw new RuntimeException();
    }

}


/**
 * A dummy component to test a lifecycle without errors.
 */
class DummyComponent7 extends ComponentBase implements PassiveComponent {

    /**
     * A onstant value.
     */
    private final long DEFAULT_SLEEP_TIME = SECOND * 3L;

    /**
     * The default constructor.
     */
    public DummyComponent7() {

        super();
    }

    /**
     * Performs the actual initialization, including state transition.
     */
    @Override
    protected void init() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(INITIALIZED);
    }

    /**
     * Performs the actual start up, including state transition.
     */
    @Override
    protected void start() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(RUNNING);
    }

    /**
     * Performs the actual shutdown, including state transition.
     */
    @Override
    protected void stop() {

        ThreadHelper.sleep(DEFAULT_SLEEP_TIME);

        transitionTo(FATAL_ERROR);
        throw new RuntimeException();
    }

}
