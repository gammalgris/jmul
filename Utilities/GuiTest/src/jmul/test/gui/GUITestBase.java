/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

package jmul.test.gui;


import java.awt.AWTException;
import java.awt.Robot;

import java.util.ResourceBundle;

import jmul.test.exceptions.SetUpException;
import static jmul.test.gui.TestLogger.getLogger;

import org.junit.After;
import org.junit.Before;


/**
 * A base class for GUI tests.
 *
 * @author Kristian Kutin
 */
public abstract class GUITestBase {

    /**
     * A key to read the corresponding configuration from a property file.
     */
    private static final String DEFAULT_AUTO_DELAY_KEY = "autoDelay";

    /**
     * The default delay between GUI events (in milliseconds).
     */
    private static final int DEFAULT_AUTO_DELAY;

    /*
     * The static initializer.
     */
    static {

        ResourceBundle bundle = ResourceBundle.getBundle(GUITestBase.class.getName());

        String value = bundle.getString(DEFAULT_AUTO_DELAY_KEY);
        DEFAULT_AUTO_DELAY = Integer.parseInt(value);
    }

    /**
     * The entity that is used to automate GUI actions.
     */
    private Robot robot;

    /**
     * The entity that starts the GUI based application.
     */
    private final GuiApplicationStarter starter;

    /**
     * Creates a new GUI test according to the specified parameters.
     *
     * @param aStarter
     *        an entity that starts the GUI based application
     */
    protected GUITestBase(GuiApplicationStarter aStarter) {

        starter = aStarter;
    }

    /**
     * Sets up the test environment.
     */
    @Before
    public void setUp() {

        startApplication();
        initializeRobot();
    }

    /**
     * Start the application.
     */
    private final void startApplication() {

        getLogger().logDebug("init client ...");
        starter.start();
        getLogger().logDebug("done.");
    }

    /**
     * Initialize the robot.
     */
    private final void initializeRobot() {

        try {

            getLogger().logDebug("init robot ...");
            robot = new Robot();
            robot.setAutoDelay(DEFAULT_AUTO_DELAY);
            getLogger().logDebug("done.");

        } catch (AWTException e) {

            throw new SetUpException(e);
        }
    }

    /**
     * Cleans up after the test.
     */
    @After
    public void tearDown() {

        robot = null;
    }

    /**
     * A getter method.
     *
     * @return a robot
     */
    public Robot getRobot() {

        return robot;
    }

}
