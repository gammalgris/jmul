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


import java.util.regex.Pattern;

import jmul.misc.exceptions.EmptyArrayParameterException;
import jmul.misc.exceptions.EmptyStringParameterException;
import jmul.misc.exceptions.NullArrayParameterException;
import jmul.misc.exceptions.NullParameterException;

import static jmul.string.Constants.EMPTY_STRING;


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
     * The default constructor.
     *
     * @param aMessage
     */
    public Message(String aMessage) {

        checkParameter(aMessage);

        unresolvedMessage = aMessage;
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

            throw new NullParameterException();
        }

        if (aMessage.trim().isEmpty()) {

            throw new EmptyStringParameterException();
        }

        if (!containsPlaceholders(aMessage)) {

            StringConcatenator message =
                new StringConcatenator("A message withouth placeholder (", aMessage, ") has been specified!");
            throw new IllegalArgumentException(String.valueOf(message));
        }
    }

    /**
     * The method resolves the specified placeholders within this message.
     *
     * @param someItems
     *        key-value pairs of placeholders and their replacements
     *
     * @return a message
     */
    public String resolvePlaceholder(String... someItems) {

        checkParameter(someItems);

        String resolvedMessage = unresolvedMessage;

        int pairs = someItems.length / 2;

        for (int a = 1; a <= pairs; a++) {

            int index = (a - 1) * 2;
            String placeholder = someItems[index];
            checkPlaceholder(placeholder);

            String value = someItems[index + 1];

            if (!unresolvedMessage.contains(placeholder)) {

                StringConcatenator message =
                    new StringConcatenator("An unknown placeholder (", placeholder, ") is being processed (",
                                           unresolvedMessage, ")!");
                throw new UnknownPlaceholderException(String.valueOf(message));
            }

            resolvedMessage = resolvedMessage.replace(placeholder, value);
        }


        if (containsPlaceholders(resolvedMessage)) {

            StringConcatenator message =
                new StringConcatenator("There still exist some placeholders (", resolvedMessage, ")!");
            throw new UnresolvedPlaceholderException(String.valueOf(message));
        }


        return resolvedMessage;
    }

    /**
     * Checks if the specified message contains placeholders.
     *
     * @param aMessage
     *
     * @return <code>true</code> if the specified message contains placeholders,
     *         else <code>false</code>
     */
    private static boolean containsPlaceholders(String aMessage) {

        String removedPlaceholders = aMessage.replaceAll(PLACEHOLDER_REGEX, EMPTY_STRING);
        return !aMessage.equals(removedPlaceholders);
    }

    /**
     * Checks the specified parameter.
     *
     * @param someParameters
     *
     * @thows IllegalArgumentException
     *        is thrown if the specified parameter is invalid
     */
    private static void checkParameter(String... someParameters) {

        if (someParameters == null) {

            throw new NullArrayParameterException();
        }

        if (someParameters.length == 0) {

            throw new EmptyArrayParameterException();
        }

        if ((someParameters.length % 2) == 1) {

            String message =
                "An odd number of parameters has been specified where an even number (i.e. key-value pairs) was expected!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified parameter.
     *
     * @param aPlaceholder
     *
     * @thows IllegalArgumentException
     *        is thrown if the specified parameter is invalid
     */
    private static void checkPlaceholder(String aPlaceholder) {

        if (aPlaceholder == null) {

            throw new NullParameterException();
        }

        if (!Pattern.matches(PLACEHOLDER_REGEX, aPlaceholder)) {

            StringConcatenator message =
                new StringConcatenator("The speicifed palceholder \"", aPlaceholder,
                                       "\" doesn't match the epxected pattern \"", PLACEHOLDER_REGEX, "\"!");
            throw new IllegalArgumentException(String.valueOf(message));
        }
    }

    /**
     * The method returns the actual (unresolved) message.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return unresolvedMessage;
    }

}
