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

package test.jmul.test.gui.dummy.control.actions;


import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;


/**
 * A base implementation for a GUI action (the action which results from interacting with a GUI component).
 * The actual action is implemented in the {@link java.lang.Runnable#run} method.
 *
 * @author Kristian Kutin
 */
abstract class ActionBase implements Action {

    /**
     * A reference to the action event. The {@link test.jmul.test.gui.dummy.control.actions.Action#performAction}
     * method initializes this field.
     */
    protected ActionEvent actionEvent;

    /**
     * The default constructor.
     */
    protected ActionBase() {

        super();
    }

    /**
     * Initializes the field {@link #actionEvent} and starts the action asynchronously (see
     * {@link java.lang.Runnable#run} method).
     *
     * @param e
     *        an action event
     */
    @Override
    public void performAction(ActionEvent e) {

        actionEvent = e;

        SwingUtilities.invokeLater(this);
    }

}
