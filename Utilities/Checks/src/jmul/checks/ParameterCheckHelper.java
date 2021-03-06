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

package jmul.checks;


import java.io.File;

import java.nio.charset.Charset;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import jmul.checks.exceptions.EmptyArrayParameterException;
import jmul.checks.exceptions.EmptyStringParameterException;
import jmul.checks.exceptions.InvalidRegularExpressionParameterException;
import jmul.checks.exceptions.NullArrayParameterException;
import jmul.checks.exceptions.NullClassParameterException;
import jmul.checks.exceptions.NullDirectoryParameterException;
import jmul.checks.exceptions.NullFileNameParameterException;
import jmul.checks.exceptions.NullFileParameterException;
import jmul.checks.exceptions.NullListParameterException;
import jmul.checks.exceptions.NullListenerParameterException;
import jmul.checks.exceptions.NullParameterException;
import jmul.checks.exceptions.UnexpectedSizeException;


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
     *        a class parameter
     *
     * @return the specified class
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static Class checkClassParameter(Class aClass) {

        if (aClass == null) {

            throw new NullParameterException();
        }

        return aClass;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aMessage
     *        a string parameter
     *
     * @return the specified message
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static String checkExceptionMessage(String aMessage) {

        return checkStringParameter(aMessage);
    }

    /**
     * Checks the specified parameter.
     *
     * @param aString
     *        a string parameter
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

        String aTrimmedString = aString.trim();
        if (aTrimmedString.isEmpty()) {

            throw new EmptyStringParameterException();
        }

        return aString;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aString
     *        a string parameter
     *
     * @return the specified string
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static String checkBufferParameter(String aString) {

        if (aString == null) {

            throw new NullParameterException();
        }

        return aString;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aCause
     *        an exception parameter
     *
     * @return the specified exception cause
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
     *        several exception parameters
     *
     * @return the specified exception causes
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
     * @param someCauses
     *        several exception parameters
     * @param aMinimumSize
     *        a minimum size
     *
     * @return the specified exception causes
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static Throwable[] checkExceptionCauses(Throwable[] someCauses, int aMinimumSize) {

        if (someCauses == null) {

            throw new NullArrayParameterException();
        }

        if (aMinimumSize < 0) {

            String message = "A negative size (" + aMinimumSize + ") has been specified!";
            throw new IllegalArgumentException(message);
        }

        int actualSize = someCauses.length;
        if (actualSize == 0) {

            throw new EmptyArrayParameterException();
        }

        if (actualSize < aMinimumSize) {

            String message =
                "The exception array is smaller than expected (actual size: " + actualSize +
                "; expected minimum size:" + aMinimumSize + ")!";
            throw new IllegalArgumentException(message);
        }

        return someCauses;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aFile
     *        a file object parameter
     *
     * @return the specified file object
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
     * @param aPath
     *        a relative or absolute path to a file or directory
     *
     * @return the specified path
     *
     * @throws IllegalArgumentException
     *         if the specified parameter is invalid
     */
    public static String checkPathParameter(String aPath) {

        if (aPath == null) {

            throw new NullParameterException();
        }

        return aPath;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aPath
     *        a relative or absolute path to a file or directory
     *
     * @return the specified path
     *
     * @throws IllegalArgumentException
     *         if the specified parameter is invalid
     */
    public static File checkPathParameter(File aPath) {

        if (aPath == null) {

            throw new NullParameterException();
        }

        return aPath;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aFileName
     *        a string parameter
     *
     * @return the specified file name
     *
     * @throws IllegalArgumentException
     *         if the specified parameter is invalid
     */
    public static String checkFileNameParameter(String aFileName) {

        if (aFileName == null) {

            throw new NullFileNameParameterException();
        }

        String aTrimmedFileName = aFileName.trim();
        if (aTrimmedFileName.isEmpty()) {

            String message = "No file name (empty string) has been specified!";
            throw new IllegalArgumentException(message);
        }

        if (!aFileName.equals(aTrimmedFileName)) {

            String message = "The file name (\"" + aFileName + "\") contains leading or trailing spaces!";
            throw new IllegalArgumentException(message);
        }

        return aFileName;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aDirectory
     *        a file object parameter
     *
     * @return the specified directory object
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static File checkDirectoryParameter(File aDirectory) {

        if (aDirectory == null) {

            throw new NullDirectoryParameterException();
        }

        return aDirectory;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aDirectoryName
     *        a string parameter
     *
     * @return the specified directory name
     *
     * @throws IllegalArgumentException
     *         if the specified parameter is invalid
     */
    public static String checkDirectoryNameParameter(String aDirectoryName) {

        if (aDirectoryName == null) {

            throw new NullFileNameParameterException();
        }

        String aTrimmedDirectoryName = aDirectoryName.trim();
        if (aTrimmedDirectoryName.isEmpty()) {

            String message = "No directory name (empty string) has been specified!";
            throw new IllegalArgumentException(message);
        }

        if (!aDirectoryName.equals(aTrimmedDirectoryName)) {

            String message = "The directory name (\"" + aDirectoryName + "\") contains leading or trailing spaces!";
            throw new IllegalArgumentException(message);
        }

        return aDirectoryName;
    }

    /**
     * Checks the specified listener.
     *
     * @param aListener
     *        a listener object parameter
     *
     * @return the specified listener
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
     *        the expected listener type
     * @param aListener
     *        a listener object parameter
     *
     * @return the specified listener
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
     * @param aRegex
     *        a string parameter
     *
     * @return the specified regular expression
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

    /**
     * Checks the specified parameter.
     *
     * @param aMinValue
     *        the expected min value
     * @param anIndex
     *        a number parameter
     * @param aMaxValue
     *        the expected max value
     *
     * @return the specified index
     */
    public static int checkIndex(int aMinValue, int anIndex, int aMaxValue) {

        if (anIndex < aMinValue) {

            String message =
                "The specified index is smaller than the allowed minimum value (min value = " + aMinValue +
                "; index = " + anIndex + ")!";
            throw new IndexOutOfBoundsException(message);
        }

        if (anIndex > aMaxValue) {

            String message =
                "The specified index is greater than the allowed maximum value (max value = " + aMaxValue +
                "; index = " + anIndex + ")!";
            throw new IndexOutOfBoundsException(message);
        }

        return anIndex;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aList
     *        a list parameter
     *
     * @return the specified list
     */
    public static List checkList(List aList) {

        if (aList == null) {

            throw new NullListParameterException();
        }

        return aList;
    }

    /**
     * Checks the specified paramter.
     *
     * @param aList
     *        a list parameter
     * @param anExpectedSize
     *        the expected list size
     *
     * @return the specified list
     */
    public static List checkList(List aList, int anExpectedSize) {

        checkList(aList);

        if (anExpectedSize < 0) {

            String message = "A negative list size (" + anExpectedSize + ") was specified!";
            throw new IllegalArgumentException(message);
        }

        if (anExpectedSize != aList.size()) {

            throw new UnexpectedSizeException(anExpectedSize, aList.size());
        }

        return aList;
    }

    /**
     * Checks the specified Parameter.
     *
     * @param aParameter
     *        a string builder parameter
     *
     * @throws IllegalArgumentException
     *         is thrown if the parameter has an invalid value
     *
     * @return the parameter
     */
    public static StringBuilder checkStringBuilderParameter(StringBuilder aParameter) {

        if (aParameter == null) {

            throw new NullParameterException();
        }

        return aParameter;
    }

    /**
     * Checks the specified Parameter.
     *
     * @param aParameter
     *        a parameter array
     *
     * @return the parameter
     *
     * @throws IllegalArgumentException
     *         is thrown if the parameter has an invalid value
     */
    public static Object[] checkObjectArrayParameter(Object[] aParameter) {

        if (aParameter == null) {

            throw new NullParameterException();
        }

        return aParameter;
    }

    /**
     * Checks the specified Parameter.
     *
     * @param aParameter
     *        a parameter array
     *
     * @return the parameter
     *
     * @throws IllegalArgumentException
     *         is thrown if the parameter has an invalid value
     */
    public static String[] checkStringArrayParameter(String[] aParameter) {

        if (aParameter == null) {

            throw new NullParameterException();
        }

        return aParameter;
    }

    /**
     * Checks the specified Parameter.
     *
     * @param aParameter
     *        a parameter array
     * @param aMinimumSize
     *        a minimum size
     *
     * @return the parameter
     *
     * @throws IllegalArgumentException
     *         is thrown if the parameter has an invalid value
     */
    public static String[] checkStringArrayParameter(String[] aParameter, int aMinimumSize) {

        checkStringArrayParameter(aParameter);

        if (aMinimumSize < 0) {

            String message = "A negative minimum size (" + aMinimumSize + ") was specified!";
            throw new IllegalArgumentException(message);
        }

        int actualSize = aParameter.length;
        if (actualSize < aMinimumSize) {

            String message =
                "The array is smaller than expected (actual size: " + actualSize + "; expected minimum size:" +
                aMinimumSize + ")!";
            throw new IllegalArgumentException(message);
        }

        return aParameter;
    }

    /**
     * Checks the specified Parameter.
     *
     * @param aParameter
     *        a parameter array
     *
     * @return the parameter
     *
     * @throws IllegalArgumentException
     *         is thrown if the parameter has an invalid value
     */
    public static List<String> checkStringListParameter(List<String> aParameter) {

        if (aParameter == null) {

            throw new NullListParameterException();
        }

        return aParameter;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aParameter
     *        a charset parameter
     *
     * @return the parameter
     *
     * @throws IllegalArgumentException
     *         is thrown if the parameter has an invalid value
     */
    public static Charset checkCharsetParameter(Charset aParameter) {

        if (aParameter == null) {

            throw new NullParameterException();
        }

        return aParameter;
    }

    /**
     * Checks the specified parameter.
     *
     * @param aString
     *        a string parameter
     *
     * @return the specified string
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static String checkLineSeparatorParameter(String aString) {

        if (aString == null) {

            throw new NullParameterException();
        }

        if (aString.isEmpty()) {

            throw new EmptyStringParameterException();
        }

        return aString;
    }

    /**
     * Checks the specified parameter.
     *
     * @param anObject
     *        an object paramter
     *
     * @return the specified object
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static Object checkObjectParameter(Object anObject) {

        if (anObject == null) {

            throw new NullParameterException();
        }

        return anObject;
    }

    /**
     * Checks the specified parameter (i.e. must be 0 or greater).
     *
     * @param anInteger
     *        an integer
     *
     * @return the specified integer
     *
     * @throws IllegalArgumentException
     *         is thrown if the specified parameter is invalid
     */
    public static int checkPositiveIntegerParameter(int anInteger) {

        if (anInteger < 0) {

            String message = "A negtaive integer was specified but a positive integer is expected!";
            throw new IllegalArgumentException(message);
        }

        return anInteger;
    }

}
