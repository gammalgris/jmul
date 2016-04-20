/*
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

package jmul.transformation.creation.creators;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import jmul.string.StringConcatenator;


/**
 * An implementation of a creation rule.
 *
 * @author Kristian Kutin
 */
public class DateCreator implements ObjectCreator {

    /**
     * The default constructor.
     */
    public DateCreator() {
    }

    /**
     * Instantiates and initializes a {@link java.util.Date}.
     *
     * @param anInitialValue
     *        a string which contains an initial value
     * @param aPattern
     *        a pattern which tells how to parse the string which contains the
     *        inital value
     *
     * @return a new object
     */
    @Override
    public Object createObject(String anInitialValue, String aPattern) {


        // Check the specified parameters.

        if (anInitialValue == null) {

            String message = "No initial value has been specified!";
            throw new IllegalArgumentException(message);
        }

        if (aPattern == null) {

            String message = "No pattern was specified but a pattern is required!";
            throw new IllegalArgumentException(message);
        }


        // Create a new instance.

        SimpleDateFormat dateFormat = null;

        try {

            dateFormat = new SimpleDateFormat(aPattern);

        } catch (IllegalArgumentException e) {

            StringConcatenator message =
                new StringConcatenator("An invalid pattern has been specified (", aPattern, ")!");
            throw new IllegalArgumentException(message.toString());
        }

        Date date = null;

        try {

            date = dateFormat.parse(anInitialValue);

        } catch (ParseException e) {

            StringConcatenator message =
                new StringConcatenator("The specified initial value \"", anInitialValue,
                                       "\" doesn't match the specified pattern \"", aPattern, "\"!");
            throw new IllegalArgumentException(message.toString());
        }

        return date;
    }

}
