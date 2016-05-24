/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package jmul.markdown.pattern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jmul.misc.checks.ParameterCheckHelper.checkListenerParameter;


/**
 * An implementation of {@link jmul.markdown.pattern.PatternMatcher}.
 *
 * @author Kristian Kutin
 */
public enum RegexPatternMatchers implements PatternMatcher {


    //TODO enumeration elements are missing
    A("");


    /**
     * A regular expression.
     */
    private final String regex;

    /**
     * The compiled pattern.
     */
    private final Pattern pattern;

    /**
     * All listeners.
     */
    private final Collection<PatternMatchListener> listeners;

    /**
     * Creates a new enumeration element according to the specified parameters.
     *
     * @param aRegex
     */
    private RegexPatternMatchers(String aMatcherName) {

        regex = ConfigurationReader.readRegex(aMatcherName);
        pattern = Pattern.compile(regex);

        listeners = new ArrayList<PatternMatchListener>();
    }

    /**
     * Adds the specified listener.
     *
     * @param aListener
     */
    @Override
    public void addPatternMatchListener(PatternMatchListener aListener) {

        checkListenerParameter(aListener);

        listeners.add(aListener);
    }

    /**
     * Removes the specified listener.
     *
     * @param aListener
     */
    @Override
    public void removePatternMatchListener(PatternMatchListener aListener) {

        checkListenerParameter(aListener);

        listeners.remove(aListener);
    }

    /**
     * The listener is informed if a buffer is changed.
     *
     * @param aBuffer
     *        the buffer's actual content (including the change)
     */
    @Override
    public void informOnChange(String aBuffer) {

        if (containsPattern(aBuffer)) {

            String match = getMatch(aBuffer);
            informListeners(match);
        }
    }

    /**
     * Returns the actual pattern.
     *
     * @return a pattern
     */
    @Override
    public String getPattern() {

        return regex;
    }

    /**
     * Checks if the specified string contains this matcher's pattern.
     *
     * @param aBuffer
     *
     * @return <code>true</code> if the specified string contains this
     *         matcher's pattern, else <code>false</code>
     */
    protected boolean containsPattern(String aBuffer) {

        Matcher m = pattern.matcher(aBuffer);
        return m.matches();
    }

    /**
     * Returns the actual match.
     *
     * @param aBuffer
     *
     * @return the actual match or <code>null</code> if no such match
     *         exists
     */
    protected String getMatch(String aBuffer) {

        Matcher m = pattern.matcher(aBuffer);

        String match = aBuffer.substring(m.start(), m.end());
        return match;
    }

    /**
     * Informs all listeners that the buffer has changed.
     *
     * @param aMatch
     */
    private void informListeners(String aMatch) {

        for (PatternMatchListener listener : listeners) {

            listener.informOnMatch(this, aMatch);
        }
    }

}


/**
 * This utility class reads specific entries from a configuration file.
 *
 * @author Kristian Kutin
 */
class ConfigurationReader {

    /**
     * A suffix for a resource key.
     */
    private static final String REGEX_SUFFIX = ".regex";

    /**
     * Reads a regex which has been associated with the specified name.
     *
     * @param aMatcherName
     *
     * @return a regex or <code>null</code> if an unknown name was specified
     */
    public static String readRegex(String aMatcherName) {

        ResourceBundle bundle = ResourceBundle.getBundle(RegexPatternMatchers.class.getName());

        String key = aMatcherName + REGEX_SUFFIX;
        String regex = bundle.getString(key);
        return regex;
    }

}
