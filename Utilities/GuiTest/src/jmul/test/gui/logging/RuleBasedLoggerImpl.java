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

package jmul.test.gui.logging;


import java.util.HashMap;
import java.util.Map;


/**
 * An implementation of a rule based logger.
 *
 * @author Kristian Kutin
 */
final class RuleBasedLoggerImpl implements RuleBasedLogger {

    /**
     * The map contains all known rules.
     */
    private final Map<Class, OutputRule> rules;

    /**
     * The default construcor.
     */
    public RuleBasedLoggerImpl() {

        super();

        rules = new HashMap<>();
    }

    /**
     * Creates a new logger according to the specified parameters.
     *
     * @param someRules
     *        some logging rules
     */
    public RuleBasedLoggerImpl(Map<Class, OutputRule> someRules) {

        super();

        rules = new HashMap<>(someRules);
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
    @Override
    public void addRule(Class aType, OutputRule aRule) {

        rules.put(aType, aRule);
    }

    /**
     * Logs the specified GUI component.
     *
     * @param aType
     *        a GUI component type
     * @param aComponent
     *        a GUI component
     */
    @Override
    public void log(Class aType, Object aComponent) {

        log(aType, null, aComponent);
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
    @Override
    public void log(Class aType, String aLabel, Object aComponent) {

        OutputRule rule = rules.get(aType);

        if (rule == null) {

            String message = "There is no rule for " + aType.getName() + "!";
            throw new IllegalArgumentException(message);
        }

        rule.log(aLabel, aComponent);
    }

}
