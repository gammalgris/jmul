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
import java.awt.KeyboardFocusManager;

import javax.swing.JDialog;
import javax.swing.JPanel;

import jmul.concurrent.threads.ThreadHelper;


/**
 * This helper class contains utility functions regarding GUI environments.
 *
 * @author Kristian Kutin
 */
public final class GuiHelper {

    /**
     * A sleep time.
     */
    private static final long DEFAULT_SLEEP_DURATION = 500L;

    /**
     * A utility class which looks for a specific parent.
     */
    private final static ComponentParentFinder<JDialog> parentDialogFinder;

    /**
     * A utility class which looks for a specific parent.
     */
    private final static ComponentParentFinder<JPanel> parentPanelFinder;

    /*
     * The static initializer.
     */
    static {

        parentDialogFinder = new ComponentParentFinder<JDialog>(JDialog.class);
        parentPanelFinder = new ComponentParentFinder<JPanel>(JPanel.class);
    }

    /**
     * The default constructor.
     */
    private GuiHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns the GUI component which currently has the focus for keyboard inputs.
     *
     * @return a component or <code>null</code> if no component has the focus
     */
    public static Component getComponentWithFocus() {

        return KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
    }

    /**
     * Returns the owning dialog of a GUI component.
     *
     * @param component
     *        a GUI component
     *
     * @return a dialog or <code>null</code> if no dialog can be found
     */
    public static JDialog getOwningDialog(Component component) {

        return parentDialogFinder.findParent(component);
    }

    /**
     * Returns the owning panel of a GUI component.
     *
     * @param component
     *        a GUI component
     *
     * @return a panel or <code>null</code> if no panel can be found
     */
    public static JPanel getOwningPanel(Component component) {

        return parentPanelFinder.findParent(component);
    }

    /**
     * Creates and returns a string which contains all parent relations for the
     * specified GUI component. When analyzing a GUI this string will show how
     * and where the GUI component is embedded.
     *
     * @param component
     *        a GUI component
     *
     * @return a string whith all parent relations
     */
    public static String getParentRelationsAsString(Component component) {

        if (component == null) {

            return "";
        }

        String name = component.getName();
        String simpleName = component.getClass().getSimpleName();

        String s = simpleName + "(name:\"" + name + "\")";
        Component parent = component.getParent();

        return "" + getParentRelationsAsString(parent) + "->" + s;
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

            Component component = getComponentWithFocus();

            if (component != null) {

                break;
            }
        }

        long after = System.currentTimeMillis();

        return after - before;
    }

}

