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

package jmul.test.gui.types;


import java.awt.Component;

import javax.swing.JPanel;

import jmul.test.gui.GuiFocusHelper;
import jmul.test.gui.GuiTreeHelper;


/**
 * This class represents a data structure which contains a GUI component and
 * it's parent panel.
 *
 * @author Kristian Kutin
 */
public class PanelComponentEntity extends AbstractComponentEntity {

    /**
     * A parent panel.
     */
    private final JPanel panel;

    /**
     * Creates a new instance of this data structure according to the specified parameters.
     *
     * @param aComponent
     *        a GUI component
     */
    public PanelComponentEntity(Component aComponent) {

        super(aComponent);

        panel = GuiTreeHelper.getOwningPanel(aComponent);
    }

    /**
     * A getter method.
     *
     * @return the parent panel or <code>null</code> if none exists
     */
    public JPanel getPanel() {

        return panel;
    }

    /**
     * Returns the name of the parent panel.
     *
     * @return a name or <code>null</code> if no parent panel exists or no name was specified
     */
    public String getPanelName() {

        if (getPanel() == null) {

            return null;

        } else {

            return getPanel().getName();
        }
    }

    /**
     * Returns a new instance of this data type with the GUI component that has the current focus on
     * keyboard inputs.
     *
     * @return a new instance
     */
    public static PanelComponentEntity newInstanceWithGuiFocus() {

        return new PanelComponentEntity(GuiFocusHelper.getComponentWithFocus());
    }

}
