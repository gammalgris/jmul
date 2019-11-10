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

package jmul.test.gui.logging.rules;


import java.awt.Rectangle;

import javax.swing.JDialog;

import jmul.test.gui.logging.OutputRule;
import static jmul.test.gui.logging.TestLogger.getLogger;


/**
 * An implementation of an output rule for a dialog.
 *
 * @author Kristian Kutin
 */
public class DialogOutputRule implements OutputRule {

    /**
     * The default constructor.
     */
    public DialogOutputRule() {

        super();
    }

    /**
     * The method logs some general information about the speficied GUI component.
     *
     * @param aLabel
     *        a label specified by the invoker
     * @param aComponent
     *        a GUI component
     */
    @Override
    public void log(String aLabel, Object aComponent) {

        String prefix;

        if (aLabel == null) {

            prefix = "";

        } else if (aLabel.trim().isEmpty()) {

            prefix = "";

        } else {

            prefix = aLabel + " ";
        }


        if (aComponent == null) {

            getLogger().logDebug(prefix + "(?|title=?;name=?)");

        } else {

            JDialog dialog = (JDialog) aComponent;

            String title = dialog.getTitle();
            String name = dialog.getName();
            String simpleName = dialog.getClass().getSimpleName();
            Rectangle bounds = dialog.getBounds();

            getLogger()
                .logDebug(prefix + "(" + simpleName + "|title=\"" + title + "\"; name=\"" + name + "\"; bounds=" +
                          bounds + ")");
        }
    }

}
