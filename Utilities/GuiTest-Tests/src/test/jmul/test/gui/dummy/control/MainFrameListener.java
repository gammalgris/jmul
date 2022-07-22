/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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

package test.jmul.test.gui.dummy.control;


import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


/**
 * A custom implementation for a window listener.
 *
 * @author Kristian Kutin
 */
public class MainFrameListener implements WindowListener {

    /**
     * The default constructor.
     */
    public MainFrameListener() {

        super();
    }

    private void logEvent(WindowEvent e) {

        System.out.println("DEBUG::" + e.toString());
    }

    @Override
    public void windowOpened(WindowEvent e) {

        logEvent(e);
        // TODO Implement this method
    }

    @Override
    public void windowClosing(WindowEvent e) {

        logEvent(e);
        // TODO Implement this method
    }

    @Override
    public void windowClosed(WindowEvent e) {

        logEvent(e);
        // TODO Implement this method
    }

    @Override
    public void windowIconified(WindowEvent e) {

        logEvent(e);
        // TODO Implement this method
    }

    @Override
    public void windowDeiconified(WindowEvent e) {

        logEvent(e);
        // TODO Implement this method
    }

    @Override
    public void windowActivated(WindowEvent e) {

        logEvent(e);
        // TODO Implement this method
    }

    @Override
    public void windowDeactivated(WindowEvent e) {

        logEvent(e);

        Window window = (Window) e.getSource();
        window.dispose();
    }

}
