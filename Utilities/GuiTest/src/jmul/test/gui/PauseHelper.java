/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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


import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JRootPane;

import jmul.concurrent.threads.ThreadHelper;
import static jmul.concurrent.threads.ThreadHelper.sleep;

import static jmul.test.gui.logging.TestLogger.getLogger;
import jmul.test.gui.types.FrameComponentEntity;


/**
 * A utility class for pausing GUI tests.
 *
 * @author Kristian Kutin
 */
public final class PauseHelper {

    /**
     * A sleep time.
     */
    private static final long DEFAULT_SLEEP_DURATION = 500L;

    /**
     * The default constructor.
     */
    private PauseHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Waits for the specified amount of time.
     *
     * @param seconds
     *        a sleep time (in seconds)
     */
    public static void sleepSeconds(int seconds) {

        getLogger().logDebug("sleeping: " + seconds + "s");
        sleep(seconds * 1000L);
    }

    /**
     * Waits until a window or dialog appears and a GUI component can be identified which
     * has the focus for keyboard inputs.
     *
     * @return the amount of time waited (in milliseconds)
     */
    public static long waitForComponentWithFocus() {

        long before = System.currentTimeMillis();

        while (true) {

            ThreadHelper.sleep(DEFAULT_SLEEP_DURATION);

            Component component = GuiFocusHelper.getComponentWithFocus();

            if (component != null) {

                break;
            }
        }

        long after = System.currentTimeMillis();

        return after - before;
    }

    /**
     * Waits until the GUI is responsive again (i.e. the mouse pointer symbol is taken as an
     * indicator of GUI responsiveness).
     *
     * @return
     */
    public static long waitIfBusy() {

        long before = System.currentTimeMillis();

        ThreadHelper.sleep(DEFAULT_SLEEP_DURATION);

        while (true) {

            ThreadHelper.sleep(DEFAULT_SLEEP_DURATION);

            FrameComponentEntity gui = FrameComponentEntity.newInstanceWithGuiFocus();
            JRootPane rootPane = gui.getFrame().getRootPane();

            int actualType = rootPane.getCursor().getType();
            int expectedType = Cursor.WAIT_CURSOR;

            if (actualType != expectedType) {

                break;
            }
        }

        long after = System.currentTimeMillis();

        return after - before;
    }

}
