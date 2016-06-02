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

package jmul.misc.checks;


import java.io.File;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import jmul.misc.exceptions.EmptyArrayParameterException;
import jmul.misc.exceptions.EmptyStringParameterException;
import jmul.misc.exceptions.InvalidRegularExpressionParameterException;
import jmul.misc.exceptions.NullArrayParameterException;
import jmul.misc.exceptions.NullClassParameterException;
import jmul.misc.exceptions.NullFileNameParameterException;
import jmul.misc.exceptions.NullFileParameterException;
import jmul.misc.exceptions.NullListenerParameterException;
import jmul.misc.exceptions.NullParameterException;


/**
 * A helper class for checking parameters.
 *
 * @author Kristian Kutin
 */
public final class ParameterCheckHelper {

    /**
     * The default constructor.
     */
    private ParameterCheckHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks the specified parameter.
     *
     * @param aClass
     *
     * @return the specified class
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static Class checkClass(Class aClass) {

        if (aClass == null) {

            throw new NullParameterException();
        }

        return aClass;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aMessage
     *
     * @return the specified message
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static String checkExceptionMessage(String aMessage) {

        if (aMessage == null) {

            throw new NullParameterException();
        }

        if (aMessage.trim().isEmpty()) {

            throw new EmptyStringParameterException();
        }

        return aMessage;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aString
     *
     * @return the specified string
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static String checkStringParameter(String aString) {

        if (aString == null) {

            throw new NullParameterException();
        }

        if (aString.trim().isEmpty()) {

            throw new EmptyStringParameterException();
        }

        return aString;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aCause
     *
     * @return the specified cause
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static Throwable checkExceptionCause(Throwable aCause) {

        if (aCause == null) {

            throw new NullParameterException();
        }

        return aCause;
    }

    /**
     * Checks the specified parameter.
     *
     * @param someCauses
     *
     * @return the specified causes
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static Throwable[] checkExceptionCauses(Throwable[] someCauses) {

        if (someCauses == null) {

            throw new NullArrayParameterException();
        }

        if (someCauses.length == 0) {

            throw new EmptyArrayParameterException();
        }

        return someCauses;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aFile
     *
     * @return the specified file
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static File checkFileParameter(File aFile) {

        if (aFile == null) {

            throw new NullFileParameterException();
        }

        return aFile;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aFileName
     *
     * @return the specified parameter
     *
     * @throws IllegalArgumentException
     *         if the specified parameter is invalid
     */
    public static String checkFileNameParameter(String aFileName) {

        if (aFileName == null) {

            throw new NullFileNameParameterException();
        }

        if (aFileName.trim().isEmpty()) {

            String message = "No file name (empty string) has been specified!";
            throw new IllegalArgumentException(message);
        }

        if (!aFileName.equals(aFileName.trim())) {

            String message = "The file name (\"" + aFileName + "\") contains leading or trailing spaces!";
            throw new IllegalArgumentException(message);
        }

        return aFileName;
    }

    /**
     * Checks the specified listener.
     *
     * @param aListener
     *
     * @return the specified parameter
     *
     * @throws IllegalArgumentException
     *         if the specified listener is invalid
     */
    public static Object checkListenerParameter(Object aListener) {

        if (aListener == null) {

            throw new NullListenerParameterException();
        }

        return aListener;
    }

    /**
     * Checks the specified listener.
     *
     * @param aListenerClass
     * @param aListener
     *
     * @return the specified parameter
     *
     * @throws IllegalArgumentException
     *         if the specified listener is invalid
     */
    public static Object checkListenerParameter(Class aListenerClass, Object aListener) {

        if (aListenerClass == null) {

            throw new NullClassParameterException();
        }

        if (aListener == null) {

            throw new NullListenerParameterException();
        }

        Class actualClass = aListener.getClass();
        if (!aListenerClass.isAssignableFrom(actualClass)) {

            String message =
                "The specified listener (actual type: " + actualClass.getCanonicalName() +
                ") is of an invalid type (expected type: " + aListenerClass.getCanonicalName() + ")!";
            throw new IllegalArgumentException(message);
        }

        return aListener;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aString
     *
     * @return the specified string
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static String checkRegexParameter(String aRegex) {

        if (aRegex == null) {

            throw new InvalidRegularExpressionParameterException();
        }

        if (aRegex.trim().isEmpty()) {

            throw new InvalidRegularExpressionParameterException(aRegex);
        }

        try {

            Pattern.compile(aRegex);

        } catch (PatternSyntaxException e) {

            throw new InvalidRegularExpressionParameterException(aRegex, e);
        }

        return aRegex;
    }

}
