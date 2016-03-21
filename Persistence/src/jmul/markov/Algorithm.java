/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package jmul.markov;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/**
 * The class implements a markov algorithm.<br>
 * <br>
 * The implementation is very simplistic. All rules are, according to their
 * order, applied on a string. If one rule has been applied successfully (i.e.
 * the string was changed) then the string manipulation stops. To resume the
 * string manipulation the rules must be applied again on the previous
 * result.<br>
 * <br>
 * Terminating rules are not yet implemented.
 *
 * @author Kristian Kutin
 */
public class Algorithm {

    /**
     * The class member contains the line separator for this operating system.
     */
    private static String LINE_SEPARATOR =
        System.getProperty("line.separator");

    /**
     * The class member contains all rules which define this specific markov
     * algorithm. The order of the rules is important.
     */
    private final Collection<Rule> rules;

    /**
     * The default constructor.
     *
     * @param someRules
     *        some rules which define this algorithm
     */
    public Algorithm(Rule... someRules) {

        Collection<Rule> tmp = new ArrayList<Rule>();

        for (Rule rule : someRules) {

            tmp.add(rule);
        }

        rules = Collections.unmodifiableCollection(tmp);
    }

    /**
     * The method applies the rules of this specific algorithm on a string.
     *
     * @param aString
     *        the string on which the rules are to be applied
     *
     * @return a modified string
     */
    public String applyRules(String aString) {

        String result = aString;

        for (Rule rule : rules) {

            result = rule.applyRule(result);

            if (!result.equals(aString)) {

                break;
            }
        }

        return result;
    }

    /**
     * The method returns a string representation of this entity.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        boolean first = true;
        for (Rule rule : rules) {

            if (first) {

                first = false;

            } else {

                buffer.append(LINE_SEPARATOR);
            }

            buffer.append(rule.toString());
        }

        return buffer.toString();
    }

}
