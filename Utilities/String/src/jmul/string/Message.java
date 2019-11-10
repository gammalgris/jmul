/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.string;


import java.util.regex.Pattern;

import static jmul.checks.ParameterCheckHelper.checkStringArrayParameter;
import static jmul.checks.ParameterCheckHelper.checkStringParameter;
import jmul.checks.exceptions.EmptyArrayParameterException;
import jmul.checks.exceptions.NullParameterException;

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
     *        the message text
     */
    public Message(String aMessage) {

        checkParameter(aMessage);

        unresolvedMessage = aMessage;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aMessage
     *        the message text
     *
     * @thows IllegalArgumentException
     *        is thrown if the specified parameter is invalid
     */
    private static void checkParameter(String aMessage) {

        checkStringParameter(aMessage);

        if (!containsPlaceholders(aMessage)) {

            String message =
                TextHelper.concatenateStrings("A message withouth placeholder (", aMessage, ") has been specified!");
            throw new IllegalArgumentException(message);
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
    @Override
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

                String message =
                    TextHelper.concatenateStrings("An unknown placeholder (", placeholder, ") is being processed (",
                                                  unresolvedMessage, ")!");
                throw new UnknownPlaceholderException(message);
            }

            resolvedMessage = resolvedMessage.replace(placeholder, value);
        }


        if (containsPlaceholders(resolvedMessage)) {

            String message =
                TextHelper.concatenateStrings("There still exist some placeholders (", resolvedMessage, ")!");
            throw new UnresolvedPlaceholderException(message);
        }


        return resolvedMessage;
    }

    /**
     * Checks if the specified message contains placeholders.
     *
     * @param aMessage
     *        the message text
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
     *        all placeholders and their replacement strings
     *
     * @thows IllegalArgumentException
     *        is thrown if the specified parameter is invalid
     */
    private static void checkParameter(String... someParameters) {

        checkStringArrayParameter(someParameters);

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
     *        a placeholder string
     *
     * @thows IllegalArgumentException
     *        is thrown if the specified parameter is invalid
     */
    private static void checkPlaceholder(String aPlaceholder) {

        if (aPlaceholder == null) {

            throw new NullParameterException();
        }

        if (!Pattern.matches(PLACEHOLDER_REGEX, aPlaceholder)) {

            String message =
                TextHelper.concatenateStrings("The speicifed palceholder \"", aPlaceholder,
                                              "\" doesn't match the epxected pattern \"", PLACEHOLDER_REGEX, "\"!");
            throw new IllegalArgumentException(message);
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
