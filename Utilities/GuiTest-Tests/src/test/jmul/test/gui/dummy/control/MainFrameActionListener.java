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


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Collection;

import test.jmul.test.gui.dummy.control.actions.Action;
import test.jmul.test.gui.dummy.control.actions.ExitAction;


/**
 * A custom implementatin of an action listener.
 *
 * @author Kristian Kutin
 */
public class MainFrameActionListener implements ActionListener {

    /**
     * A collection with all rules for handling action events.
     */
    private Collection<Action> actions;

    /**
     * The default constructor.
     */
    public MainFrameActionListener() {

        super();

        actions = new ArrayList<>();
        actions.add(new ExitAction());
    }

    /**
     * Logs the specified action event.
     *
     * @param e
     *        an action event
     */
    private void logEvent(ActionEvent e) {

        System.out.println("DEBUG::" + e.toString());
    }

    /**
     * Handles the specified action.
     *
     * @param e
     *        an action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        logEvent(e);

        for (Action action : actions) {

            if (action.matchesAction(e)) {

                action.performAction(e);
            }
        }
    }

}
