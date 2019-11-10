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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.test.gui;


import java.awt.Component;
import java.awt.KeyboardFocusManager;

import static jmul.test.gui.logging.TestLogger.getLogger;


/**
 * A utility class for determining the focus of the GUI.
 *
 * @author Kristian Kutin
 */
public final class GuiFocusHelper {

    /**
     * The default constructor.
     */
    private GuiFocusHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Tries to identify a GUI component with the current keyboard focus and returns it.
     *
     * @return a GUI element
     */
    public static Component getComponentWithFocus() {

        return KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
    }

    /**
     * Logs the name of the GUI component with the current keyboard focus.
     */
    public static void logElementWithFocus() {

        Component component = getComponentWithFocus();

        if (component == null) {

        } else {

            String name = component.getName();
            String simpleName = component.getClass().getSimpleName();

            getLogger().logDebug("has focus: " + "(" + simpleName + "):" + name);
        }
    }

}
