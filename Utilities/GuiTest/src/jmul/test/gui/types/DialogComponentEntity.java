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

import javax.swing.JDialog;

import jmul.test.gui.GuiFocusHelper;
import jmul.test.gui.GuiTreeHelper;


/**
 * This class represents a data structure which contains a GUI component and
 * it's parent dialog.
 *
 * @author Kristian Kutin
 */
public class DialogComponentEntity extends AbstractComponentEntity {

    /**
     * A parent dialog.
     */
    private final JDialog dialog;

    /**
     * Creates a new instance of this data structure according to the specified parameters.
     *
     * @param aComponent
     *        a GUI component
     */
    public DialogComponentEntity(Component aComponent) {

        super(aComponent);

        dialog = GuiTreeHelper.getOwningDialog(aComponent);
    }

    /**
     * A getter method.
     *
     * @return the parent dialog or <code>null</code> if none exists
     */
    public JDialog getDialog() {

        return dialog;
    }

    /**
     * Returns the name of the parent dialog.
     *
     * @return a name or <code>null</code> if no parent dialog exists or no name was specified
     */
    public String getDialogName() {

        if (getDialog() == null) {

            return null;

        } else {

            return getDialog().getName();
        }
    }

    /**
     * Returns the title of the parent dialog.
     *
     * @return a title or <code>null</code> if no parent dialog exists or no title was specified
     */
    public String getDialogTitle() {

        if (getDialog() == null) {

            return null;

        } else {

            return getDialog().getTitle();
        }
    }

    /**
     * Returns a new instance of this data type with the GUI component that has the current focus on
     * keyboard inputs.
     *
     * @return a new instance
     */
    public static DialogComponentEntity newInstanceWithGuiFocus() {

        return new DialogComponentEntity(GuiFocusHelper.getComponentWithFocus());
    }

}
