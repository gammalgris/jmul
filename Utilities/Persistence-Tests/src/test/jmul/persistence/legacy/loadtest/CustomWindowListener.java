/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package test.jmul.persistence.legacy.loadtest;


import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


/**
 * A custom implementation of a windowl istener.<br>
 * <br>
 * <i>Note:<br>
 * The method descriptions are taken from the interface.</i>
 *
 * @author Kristian Kutin
 */
class CustomWindowListener implements WindowListener {

    /**
     * The default constructor.
     */
    public CustomWindowListener() {
    }

    /**
     * Invoked the first time a window is made visible.
     */
    public void windowOpened(WindowEvent e) {
    }

    /**
     * Invoked when the user attempts to close the window
     * from the window's system menu.
     */
    public void windowClosing(WindowEvent e) {

        Window parentWindow = e.getWindow();
        parentWindow.dispose();
    }

    /**
     * Invoked when a window has been closed as the result
     * of calling dispose on the window.
     */
    public void windowClosed(WindowEvent e) {
    }

    /**
     * Invoked when a window is changed from a normal to a
     * minimized state. For many platforms, a minimized window
     * is displayed as the icon specified in the window's
     * iconImage property.
     */
    public void windowIconified(WindowEvent e) {
    }

    /**
     * Invoked when a window is changed from a minimized
     * to a normal state.
     */
    public void windowDeiconified(WindowEvent e) {
    }

    /**
     * Invoked when the Window is set to be the active Window. Only a Frame or
     * a Dialog can be the active Window. The native windowing system may
     * denote the active Window or its children with special decorations, such
     * as a highlighted title bar. The active Window is always either the
     * focused Window, or the first Frame or Dialog that is an owner of the
     * focused Window.
     */
    public void windowActivated(WindowEvent e) {
    }

    /**
     * Invoked when a Window is no longer the active Window. Only a Frame or a
     * Dialog can be the active Window. The native windowing system may denote
     * the active Window or its children with special decorations, such as a
     * highlighted title bar. The active Window is always either the focused
     * Window, or the first Frame or Dialog that is an owner of the focused
     * Window.
     */
    public void windowDeactivated(WindowEvent e) {
    }

}
