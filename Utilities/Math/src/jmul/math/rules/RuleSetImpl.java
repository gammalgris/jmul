/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2015  Kristian Kutin
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

package jmul.math.rules;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;


/**
 * An implementation of a rule set.
 *
 * @author Kristian Kutin
 */
public class RuleSetImpl implements RuleSet {

    /**
     * All rules of this rule set.
     */
    private final Collection<Rule> rules;

    /**
     * Creates a new rule set.
     *
     * @param someRules
     */
    public RuleSetImpl(Rule... someRules) {

        rules = Collections.unmodifiableCollection(Arrays.asList(someRules));
    }

    /**
     * Checks if the specified numeric value adheres to this rule set.
     *
     * @param aNumericValue
     *
     * @return <code>true</code> the numeric value adheres to this rule set, else <code>false</code>
     */
    @Override
    public boolean checkNumericValue(int aNumericValue) {

        for (Rule rule : this) {

            boolean result = rule.checkNumericValue(aNumericValue);

            if (!result) {

                return false;
            }
        }

        return true;
    }

    /**
     * A getter method.
     *
     * @return all rules
     */
    private Collection<Rule> getRules() {

        return rules;
    }

    /**
     * Returns an iterator for this rule set.
     *
     * @return an iterator
     */
    @Override
    public Iterator<Rule> iterator() {

        return getRules().iterator();
    }

    /**
     * Returns a string representation of this rule.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("RuleSet[");

        boolean isFirstElement = true;
        for (Rule rule : this) {

            if (isFirstElement) {

                isFirstElement = false;

            } else {

                buffer.append(", ");
            }

            buffer.append(rule);
        }

        buffer.append("]");

        return String.valueOf(buffer);
    }

}
