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

package jmul.gui;


import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;


/**
 * A utility class for handling AWT and Swing GUIs.
 *
 * @author Kristian Kutin
 */
public final class GuiHelper {

    /**
     * The default constructor.
     */
    private GuiHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Moves up the component tree to the parent frame and returns the parent frame.
     *
     * @param aComponent
     *        a component in the component tree
     *
     * @return the parent frame
     */
    public static Component getParentFrame(Component aComponent) {

        if (aComponent == null) {

            return null;
        }

        if (aComponent instanceof JPopupMenu) {

            JPopupMenu popupMenu = (JPopupMenu) aComponent;

            return getParentFrame(popupMenu.getInvoker());
        }

        if (aComponent instanceof JFrame) {

            return aComponent;
        }

        if (aComponent instanceof JDialog) {

            return aComponent;
        }

        return getParentFrame(aComponent.getParent());
    }

}
