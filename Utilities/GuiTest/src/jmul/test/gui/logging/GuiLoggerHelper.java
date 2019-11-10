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

package jmul.test.gui.logging;


import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jmul.test.gui.logging.rules.ComboBoxOutputRule;
import jmul.test.gui.logging.rules.ComponentOutputRule;
import jmul.test.gui.logging.rules.DialogComponentEntityOutputRule;
import jmul.test.gui.logging.rules.DialogOutputRule;
import jmul.test.gui.logging.rules.FrameComponentEntityOutputRule;
import jmul.test.gui.logging.rules.FrameOutputRule;
import jmul.test.gui.logging.rules.PanelComponentEntityOutputRule;
import jmul.test.gui.logging.rules.PanelOutputRule;
import jmul.test.gui.types.DialogComponentEntity;
import jmul.test.gui.types.FrameComponentEntity;
import jmul.test.gui.types.PanelComponentEntity;


/**
 * This helper class contains several convenience methods for logging GUI
 * automation.
 *
 * @author Kristian Kutin
 */
public final class GuiLoggerHelper {

    /**
     * A logger singleton.
     */
    private static RuleBasedLogger logger;

    /*
     * The static initializer.
     */
    static {

        logger = null;
    }

    /**
     * The default constructor.
     */
    private GuiLoggerHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new logger with a default ruleset.
     *
     * @return a new logger
     */
    private static RuleBasedLogger newLoggerInstance() {

        RuleBasedLogger newLogger = new RuleBasedLoggerImpl();

        newLogger.addRule(JComponent.class, new ComponentOutputRule());
        newLogger.addRule(JComboBox.class, new ComboBoxOutputRule());
        newLogger.addRule(JDialog.class, new DialogOutputRule());
        newLogger.addRule(JPanel.class, new PanelOutputRule());
        newLogger.addRule(JFrame.class, new FrameOutputRule());
        newLogger.addRule(DialogComponentEntity.class, new DialogComponentEntityOutputRule());
        newLogger.addRule(PanelComponentEntity.class, new PanelComponentEntityOutputRule());
        newLogger.addRule(FrameComponentEntity.class, new FrameComponentEntityOutputRule());

        return newLogger;
    }

    /**
     * Returns the current logger instance.
     *
     * @return a logger
     */
    private static RuleBasedLogger getLogger() {

        if (logger == null) {

            logger = newLoggerInstance();
        }

        return logger;
    }

    /**
     * Resets the logger to a default ruleset.
     */
    public static void resetLogger() {

        logger = newLoggerInstance();
    }

    /**
     * Adds the specified rule for the specified type. Adding a rule for a known
     * GUI component type will simply replace the rule.
     *
     * @param aType
     *        a GUI component type
     * @param aRule
     *        a rule to log such GUI components
     */
    public static void addRule(Class aType, OutputRule aRule) {

        getLogger().addRule(aType, aRule);
    }

    /**
     * Logs the specified GUI component.
     *
     * @param aType
     *        a GUI component type
     * @param aComponent
     *        a GUI component
     */
    public static void log(Class aType, Object aComponent) {

        getLogger().log(aType, aComponent);
    }

    /**
     * Logs the specified GUI component.
     *
     * @param aType
     *        a GUI component type
     * @param aLabel
     *        a label specified by the invoker
     * @param aComponent
     *        a GUI component
     */
    public static void log(Class aType, String aLabel, Object aComponent) {

        getLogger().log(aType, aLabel, aComponent);
    }

    /**
     * Logs the specified object.
     *
     * @param object
     *        an object
     */
    public static void log(Object object) {

        if (object == null) {

            throw new IllegalArgumentException("No parameter was specified (null)!");
        }

        log(object.getClass(), object);
    }

}
