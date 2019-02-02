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

package jmul.test.gui.logging.rules;


import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

import jmul.test.gui.GuiTreeHelper;
import jmul.test.gui.logging.GuiLoggerHelper;
import jmul.test.gui.logging.OutputRule;
import jmul.test.gui.logging.TestLogger;
import jmul.test.gui.types.FrameComponentEntity;


/**
 * An implementation of an output rule for a custom data structure.
 *
 * @author Kristian Kutin
 */
public class FrameComponentEntityOutputRule implements OutputRule {

    /**
     * The default constructor.
     */
    public FrameComponentEntityOutputRule() {

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

        if (aComponent == null) {

            return;
        }

        FrameComponentEntity gui = (FrameComponentEntity) aComponent;

        if (gui.getComponent() instanceof JComboBox) {

            GuiLoggerHelper.log(JComboBox.class, aLabel, gui.getComponent());

        } else {

            GuiLoggerHelper.log(JComponent.class, aLabel, gui.getComponent());
        }

        TestLogger.getLogger().logDebug(GuiTreeHelper.getParentRelationsAsString(gui.getComponent()));
        GuiLoggerHelper.log(JFrame.class, aLabel, gui.getFrame());
    }

}
