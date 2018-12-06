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
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JPanel;

import static jmul.test.gui.TestLogger.getLogger;


/**
 * This helper class contains several convenience methods for logging GUI
 * automation.
 *
 * @author Kristian Kutin
 */
public final class GuiLoggerHelper {

    /**
     * The default constructor.
     */
    private GuiLoggerHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * The method logs some general information about the speficied GUI component.
     *
     * @param component
     *        a GUI component
     */
    public static void logComponent(Component component) {

        logComponent(null, component);
    }

    /**
     * The method logs some general information about the speficied GUI component.
     *
     * @param label
     *        a label specified by the invoker
     * @param component
     *        a GUI component
     */
    public static void logComponent(String label, Component component) {

        String prefix;

        if (label == null) {

            prefix = "";

        } else if (label.trim().isEmpty()) {

            prefix = "";

        } else {

            prefix = label + " ";
        }


        if (component == null) {

            getLogger().logDebug(prefix + "(?):?");

        } else {

            String name = component.getName();
            String simpleName = component.getClass().getSimpleName();

            getLogger().logDebug(prefix + "(" + simpleName + "):" + name);
        }
    }

    /**
     * The method logs some general information about the speficied dialog.
     *
     * @param dialog
     *        a dialog
     */
    public static void logDialog(JDialog dialog) {

        logDialog(null, dialog);
    }

    /**
     * The method logs some general information about the speficied dialog.
     *
     * @param label
     *        a label specified by the invoker
     * @param dialog
     *        a dialog
     */
    public static void logDialog(String label, JDialog dialog) {

        String prefix;

        if (label == null) {

            prefix = "";

        } else if (label.trim().isEmpty()) {

            prefix = "";

        } else {

            prefix = label + " ";
        }


        if (dialog == null) {

            getLogger().logDebug(prefix + "(?|title=?;name=?)");

        } else {

            String title = dialog.getTitle();
            String name = dialog.getName();
            String simpleName = dialog.getClass().getSimpleName();
            Rectangle bounds = dialog.getBounds();

            getLogger()
                .logDebug(prefix + "(" + simpleName + "|title=\"" + title + "\"; name=\"" + name + "\"; bounds=" +
                          bounds + ")");
        }
    }

    /**
     * The method logs some general information about the speficied panel.
     *
     * @param panel
     *        a panel
     */
    public static void logPanel(JPanel panel) {

        logPanel(null, panel);
    }

    /**
     * The method logs some general information about the speficied panel.
     *
     * @param label
     *        a label specified by the invoker
     * @param panel
     *        a panel
     */
    public static void logPanel(String label, JPanel panel) {

        String prefix;

        if (label == null) {

            prefix = "";

        } else if (label.trim().isEmpty()) {

            prefix = "";

        } else {

            prefix = label + " ";
        }


        if (panel == null) {

            getLogger().logDebug(prefix + "(?|title=?;name=?)");

        } else {

            String name = panel.getName();
            String simpleName = panel.getClass().getSimpleName();
            String uiClassId = panel.getUIClassID();
            Rectangle bounds = panel.getBounds();

            getLogger()
                .logDebug(prefix + "(" + simpleName + "|name=\"" + name + "\"; UI-Class-ID=\"" + uiClassId +
                          "\"; bounds=" + bounds + ")");
        }
    }

}
