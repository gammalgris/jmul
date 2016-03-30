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

package jmul.string;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class implements a message entity which contains placeholders. The
 * placeholders can be provided later.<br>
 * <br>
 * This is used for descriptions of exceptions and errors where not all
 * informations are known at the time when the exception occurs. Only by going
 * through the whole exeption handling process the description can be completed.
 *
 * @author Kristian Kutin
 */
public class Message implements ConfigurableMessage {

    /**
     * The pattern to identify placeholders.
     */
    private static final String PLACEHOLDER_REGEX = "<([a-zA-Z]){0,}>";

    /**
     * The class member contains the actual message with placeholders.
     */
    private final String unresolvedMessage;

    /**
     * This class member contains the placeholders which have already been
     * resolved.
     */
    private Map<String, String> placeholders;

    /**
     * The default constructor.
     *
     * @param aMessage
     */
    public Message(String aMessage) {

        checkParameter(aMessage);


        unresolvedMessage = aMessage;


        placeholders = new HashMap<String, String>();
        String[] foundPlaceholders = identifyMatches(aMessage);

        for (int a = 0; a < foundPlaceholders.length; a++) {

            placeholders.put(foundPlaceholders[a], null);
        }
    }

    /**
     * Checks the specified parameter.
     *
     * @param aMessage
     *
     * @thows IllegalArgumentException
     *        is thrown if the specified parameter is invalid
     */
    private static void checkParameter(String aMessage) {

        if (aMessage == null) {

            String message = "No parameter has been specified!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * The method resolves the specified placeholders.
     *
     * @param someItems
     *        key-value pairs of placeholders and their replacements
     */
    public void resolvePlaceholder(String... someItems) {

        int length = someItems.length;

        if (length == 0) {

            String exceptionMessage = "No parameters were provided!";
            throw new IllegalArgumentException(exceptionMessage);
        }


        int pairs = length / 2;
        int remainder = length % 2;
        if (remainder != 0) {

            String exceptionMessage =
                "An odd number of parameters has been specified where an even number (i.e. key-value pairs) was expected!";
            throw new IllegalArgumentException(exceptionMessage);
        }


        for (int a = 1; a <= pairs; a++) {

            int index = (a - 1) * 2;
            String key = someItems[index];
            String value = someItems[index + 1];


            // Check if the provided key is one of the expected placeholders.

            boolean validPlaceholder = placeholders.containsKey(key);
            if (!validPlaceholder) {

                StringConcatenator exceptionMessage =
                    new StringConcatenator("The specified placeholder (\"", key,
                                           "\") is not expected within this message (");

                boolean first = true;
                for (String expectedPlaceholder : placeholders.keySet()) {

                    if (first) {

                        first = false;

                    } else {

                        exceptionMessage.append(",");
                    }

                    exceptionMessage.append("\"", expectedPlaceholder, "\"");
                }

                exceptionMessage.append(")!");

                throw new IllegalArgumentException(exceptionMessage.toString());
            }

            placeholders.put(key, value);
        }
    }

    /**
     * The method determines if there are still some unresolved palceholders.
     *
     * @return <code>true</code> if there are some unresolved placeholders,
     *         else <code>false</code>
     */
    public boolean existUnresolvedPlaceholders() {

        for (String placeholder : placeholders.keySet()) {

            String value = placeholders.get(placeholder);

            if (value == null) {

                return true;
            }
        }

        return false;
    }

    /**
     * The method returns a string representation of this entity.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        String resolvedMessage = unresolvedMessage;

        for (String placeholder : placeholders.keySet()) {

            String value = placeholders.get(placeholder);

            if (value != null) {

                resolvedMessage = resolvedMessage.replace(placeholder, value);
            }
        }


        if (existUnresolvedPlaceholders()) {

            StringConcatenator exceptionMessage =
                new StringConcatenator("The message still contains unresolved placeholders(\"", resolvedMessage,
                                       "\")!");
            throw new UnresolvedPlaceholderException(exceptionMessage.toString());
        }


        return resolvedMessage;
    }

    /**
     * The method returns the placeholders which have been identified in the
     * specified target string.
     *
     * @param aTarget
     *        a target string
     *
     * @return all identified placeholders
     */
    private static String[] identifyMatches(String aTarget) {

        Collection<String> matches = new ArrayList<String>();
        Matcher matcher = Pattern.compile(PLACEHOLDER_REGEX).matcher(aTarget);

        while (matcher.find()) {

            String match = matcher.group();
            matches.add(match);
        }

        String[] result = new String[matches.size()];
        result = matches.toArray(result);

        return result;
    }

}
