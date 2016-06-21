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
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jmul.misc.checks.ParameterCheckHelper.checkBufferParameter;
import static jmul.misc.checks.ParameterCheckHelper.checkListenerParameter;
import static jmul.misc.checks.ParameterCheckHelper.checkRegexParameter;


/**
 * An implementation of {@link jmul.markdown.pattern.PatternMatcher}. The
 * order of the enumeration items represents their priority (first =
 * higher priority).<br />
 * For the sake of an easier handling the patterns don't span multiple
 * lines.
 *
 * @author Kristian Kutin
 */
public enum RegexPatternMatchers implements PatternMatcher {


    HEADING("heading"),
    UNORDERED_LIST("unordered-list"),
    ORDERED_LIST("ordered-list"),
    QUOTE("quote"),
    LINE("line"),
    EMPTY_LINE("empty-line"),

    IMAGE("image"),
    LINK("link"), ;


    /**
     * A regular expression.
     */
    private final String regex;

    /**
     * A pattern.
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

        regex = checkRegexParameter(ConfigurationReader.readRegex(aMatcherName));
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

        checkBufferParameter(aBuffer);

        if (containsPattern(aBuffer)) {

            informListeners(getGroups(aBuffer));
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

        checkBufferParameter(aBuffer);

        Matcher m = pattern.matcher(aBuffer);
        return m.find();
    }

    /**
     * Returns the actual match and splits the match into all identified
     * capturing groups. Group 0 contaisn the whole match, though.
     *
     * @param aBuffer
     *
     * @return the actual match (split down into all identified capturing groups)
     *         or an empty list if no such match exists
     */
    protected List<String> getGroups(String aBuffer) {

        checkBufferParameter(aBuffer);

        List<String> groups = new ArrayList<String>();

        Matcher m = pattern.matcher(aBuffer);
        if (m.find()) {

            int index = 0;
            while (true) {

                String group = null;

                try {

                    group = m.group(index);

                } catch (IndexOutOfBoundsException e) {

                    break;
                }

                groups.add(group);
                index++;
            }

        }

        if (groups.isEmpty()) {

            throw new IllegalArgumentException("No match exists!");

        } else {

            return Collections.unmodifiableList(groups);
        }
    }

    /**
     * Informs all listeners that the buffer has changed.
     *
     * @param aMatch
     */
    private void informListeners(List<String> aMatch) {

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
     * Returns a resource bundle.
     *
     * @return a resource bundle
     */
    private static ResourceBundle getBundle() {

        return ResourceBundle.getBundle(RegexPatternMatchers.class.getName());
    }

    /**
     * Reads a regex which has been associated with the specified name.
     *
     * @param aMatcherName
     *
     * @return a regex or <code>null</code> if an unknown name was specified
     */
    public static String readRegex(String aMatcherName) {

        ResourceBundle bundle = getBundle();

        String key = aMatcherName + REGEX_SUFFIX;
        String regex = bundle.getString(key);

        return regex;
    }

}
