/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package jmul.math.notation;


import java.util.regex.Pattern;

import jmul.checks.ParameterCheckHelper;

import static jmul.math.notation.NotationTypes.FLOATING_POINT;
import static jmul.math.notation.NotationTypes.INTEGER;
import static jmul.math.notation.NotationTypes.SCIENTIFIC_NOTATION;
import static jmul.math.notation.Signs.NEGATIVE;
import static jmul.math.notation.Signs.POSITIVE;
import jmul.math.notation.evaluation.ExpressionEvaluator;
import jmul.math.notation.evaluation.FloatingPointFloatingPointEvaluator;
import jmul.math.notation.evaluation.IntegerIntegerEvaluator;
import jmul.math.notation.evaluation.ScientificNotationScientificNotationEvaluator;
import jmul.math.notation.parser.FloatingPointParser;
import jmul.math.notation.parser.IntegerParser;
import jmul.math.notation.parser.NumberParser;
import jmul.math.notation.precision.IntegerPrecisionChecker;
import jmul.math.notation.precision.PrecisionChecker;

import static jmul.string.Constants.COMMA;
import static jmul.string.Constants.POINT;


/**
 * This helper class provides operations to check a number string and return
 * structural informations.
 *
 * @author Kristian Kutin
 */
public final class NotationHelper {

    /**
     * A character.
     */
    private static final char ZERO_CHARACTER = '0';

    /**
     * No decimal separator exists.
     */
    private static final String NO_DECIMAL_SEPARATOR = "";

    /**
     * The decimal separator is a point.
     */
    private static final String POINT_AS_DECIMAL_SEPARATOR = ".";

    /**
     * The decimal separator is a comma.
     */
    private static final String COMMA_AS_DECIMAL_SEPARATOR = ",";

    /**
     * A pattern to identify integer numbers.
     */
    private static final String POSITIVE_NUMBER_REGEX = "^[+]?[\\d]+$";

    /**
     * A pattern to identify integer numbers.
     */
    private static final String NEGATIVE_NUMBER_REGEX = "^[-][\\d]+$";

    /**
     * A pattern to identify floating point numbers (point as decimal separator).
     */
    private static final String POSITIVE_FLOATING_POINT_NUMBER_REGEX = "^[+]?[\\d]+[.][\\d]+$";

    /**
     * A pattern to identify floating point numbers (point as decimal separator).
     */
    private static final String NEGATIVE_FLOATING_POINT_NUMBER_REGEX = "^[-][\\d]+[.][\\d]+$";

    /**
     * A pattern to identify floating point numbers (comma as decimal separator).
     */
    private static final String POSITIVE_FLOATING_POINT_NUMBER_2_REGEX = "^[+]?[\\d]+[,][\\d]+$";

    /**
     * A pattern to identify floating point numbers (comma as decimal separator).
     */
    private static final String NEGATIVE_FLOATING_POINT_NUMBER_2_REGEX = "^[-][\\d]+[,][\\d]+$";

    /**
     * A pattern to identify numbers specified with the scientific notation (point as decimal separator).
     */
    private static final String POSITIVE_SCIENTIFIC_NOTATION_REGEX = "^[+]?[\\d][.][\\d]*E[+-]?[\\d]+$";

    /**
     * A pattern to identify numbers specified with the scientific notation (point as decimal separator).
     */
    private static final String NEGATIVE_SCIENTIFIC_NOTATION_REGEX = "^[-][\\d][.][\\d]*E[+-]?[\\d]+$";

    /**
     * A pattern to identify numbers specified with the scientific notation (comma as decimal separator).
     */
    private static final String POSITIVE_SCIENTIFIC_NOTATION_2_REGEX = "^[+]?[\\d][,][\\d]*E[+-]?[\\d]+$";

    /**
     * A pattern to identify numbers specified with the scientific notation (comma as decimal separator).
     */
    private static final String NEGATIVE_SCIENTIFIC_NOTATION_2_REGEX = "^[-][\\d][,][\\d]*E[+-]?[\\d]+$";

    /**
     * A pattern to identify a positive exponent.
     */
    private static final String POSITIVE_EXPONENT_REGEX = "^.+[E][+]?[\\d].*$";

    /**
     * A pattern to identify a negative exponent.
     */
    private static final String NEGATIVE_EXPONENT_REGEX = "^.+[E][-][\\d].*$";

    /**
     * A pattern to identify a signed number.
     */
    private static final String SIGNED_NUMBER_REGEX = "^[+-].+$";

    /**
     * An expression evaluator.
     */
    private static final ExpressionEvaluator INTEGER_INTEGER_EVALUATOR = new IntegerIntegerEvaluator();

    /**
     * An expression evaluator.
     */
    private static final ExpressionEvaluator FLOATING_POINT_FLOATING_POINT_EVALUATOR =
        new FloatingPointFloatingPointEvaluator();

    /**
     * An expression evaluator.
     */
    private static final ExpressionEvaluator SCIENTIFIC_NOTATION_SCIENTIFIC_NOTATION_EVALUATOR =
        new ScientificNotationScientificNotationEvaluator();

    /**
     * A precision checker.
     */
    private static final PrecisionChecker INTEGER_PRECISION_CHECKER = new IntegerPrecisionChecker();

    /**
     * A number parser.
     */
    private static final NumberParser INTEGER_PARSER = new IntegerParser();

    /**
     * A number parser.
     */
    private static final NumberParser FLOATING_POINT_PARSER = new FloatingPointParser();

    /**
     * The default constructor.
     */
    private NotationHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Tries to determine the sign of the exponent in the specified string.
     *
     * @param aString
     *        a number string
     *
     * @return a sign
     */
    private static Signs determineExponentSign(String aString) {

        if (Pattern.matches(POSITIVE_EXPONENT_REGEX, aString)) {

            return POSITIVE;

        } else if (Pattern.matches(NEGATIVE_EXPONENT_REGEX, aString)) {

            return NEGATIVE;

        } else {

            throw new UnsupportedOperationException();
        }
    }

    /**
     * Checks the specified number string and returns the identified properties.
     *
     * @param aString
     *        a number string
     *
     * @return the identified properties for the number string
     */
    public static NotationProperties checkString(String aString) {

        ParameterCheckHelper.checkStringParameter(aString);

        if (Pattern.matches(POSITIVE_NUMBER_REGEX, aString)) {

            return new NotationPropertiesImpl(NO_DECIMAL_SEPARATOR, INTEGER, POSITIVE);

        } else if (Pattern.matches(NEGATIVE_NUMBER_REGEX, aString)) {

            return new NotationPropertiesImpl(NO_DECIMAL_SEPARATOR, INTEGER, NEGATIVE);

        } else if (Pattern.matches(POSITIVE_FLOATING_POINT_NUMBER_REGEX, aString)) {

            return new NotationPropertiesImpl(POINT_AS_DECIMAL_SEPARATOR, FLOATING_POINT, POSITIVE);

        } else if (Pattern.matches(NEGATIVE_FLOATING_POINT_NUMBER_REGEX, aString)) {

            return new NotationPropertiesImpl(POINT_AS_DECIMAL_SEPARATOR, FLOATING_POINT, NEGATIVE);

        } else if (Pattern.matches(POSITIVE_FLOATING_POINT_NUMBER_2_REGEX, aString)) {

            return new NotationPropertiesImpl(COMMA_AS_DECIMAL_SEPARATOR, FLOATING_POINT, POSITIVE);

        } else if (Pattern.matches(NEGATIVE_FLOATING_POINT_NUMBER_2_REGEX, aString)) {

            return new NotationPropertiesImpl(COMMA_AS_DECIMAL_SEPARATOR, FLOATING_POINT, NEGATIVE);

        } else if (Pattern.matches(POSITIVE_SCIENTIFIC_NOTATION_REGEX, aString)) {

            return new NotationPropertiesImpl(POINT_AS_DECIMAL_SEPARATOR, SCIENTIFIC_NOTATION, POSITIVE,
                                              determineExponentSign(aString));

        } else if (Pattern.matches(NEGATIVE_SCIENTIFIC_NOTATION_REGEX, aString)) {

            return new NotationPropertiesImpl(POINT_AS_DECIMAL_SEPARATOR, SCIENTIFIC_NOTATION, NEGATIVE,
                                              determineExponentSign(aString));

        } else if (Pattern.matches(POSITIVE_SCIENTIFIC_NOTATION_2_REGEX, aString)) {

            return new NotationPropertiesImpl(COMMA_AS_DECIMAL_SEPARATOR, SCIENTIFIC_NOTATION, POSITIVE,
                                              determineExponentSign(aString));

        } else if (Pattern.matches(NEGATIVE_SCIENTIFIC_NOTATION_2_REGEX, aString)) {

            return new NotationPropertiesImpl(COMMA_AS_DECIMAL_SEPARATOR, SCIENTIFIC_NOTATION, NEGATIVE,
                                              determineExponentSign(aString));

        } else {

            String message = "The specified string (\"" + aString + "\") couldn't be interpreted as a number!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Normalize the specified number string (i.e. replace the decimal separator).
     *
     * @param aString
     *        a string containing a number
     *
     * @return the normalized number string
     */
    public static String normalizeString(String aString) {

        NotationProperties properties = checkString(aString);

        if (properties.getDecimalSeparator().equals(COMMA)) {

            return aString.replace(COMMA, POINT);

        } else {

            return aString;
        }
    }

    /**
     * Checks the specified number string and returns a corresponding precision type (i.e.
     * the lowest possible precision type to store the number).
     *
     * @param aString
     *        a string containing a number
     *
     * @return a corresponding precision type
     */
    public static PrecisionTypes checkPrecision(String aString) {

        NotationProperties properties = checkString(aString);
        NotationTypes notationType = properties.getNotationType();

        PrecisionChecker checker;

        switch (notationType) {

        case INTEGER:
            checker = INTEGER_PRECISION_CHECKER;
            break;
        case FLOATING_POINT:
            throw new UnsupportedOperationException("Not implemented yet!");
        case SCIENTIFIC_NOTATION:
            throw new UnsupportedOperationException("Not implemented yet!");
        default:
            String message = "An unknown notation type (\"" + notationType + "\") was encountered!";
            throw new IllegalArgumentException(message);
        }

        return checker.checkPrecision(aString, properties);
    }

    /**
     * Evaluates the specified number strings according to the specified comparator.
     *
     * @param firstNumber
     *        the first number as string
     * @param aComparator
     *        a comparator for both numbers
     * @param secondNumber
     *        the second number as string
     *
     * @return <code>true</code> if the statement is true, else <code>false</code>
     */
    public static boolean evaluate(String firstNumber, Comparators aComparator, String secondNumber) {

        NotationProperties firstProperties = checkString(firstNumber);
        NotationProperties secondProperties = checkString(secondNumber);

        return evaluate(firstNumber, firstProperties, aComparator, secondNumber, secondProperties);
    }

    /**
     * Evaluates the specified number strings according to the specified comparator.
     *
     * @param firstNumber
     *        the first number as string
     * @param firstProperties
     *        the properties of the first number
     * @param aComparator
     *        a comparator for both numbers
     * @param secondNumber
     *        the second number as string
     * @param secondProperties
     *        the properties of the second number
     *
     * @return <code>true</code> if the statement is true, else <code>false</code>
     */
    private static boolean evaluate(String firstNumber, NotationProperties firstProperties, Comparators aComparator,
                                    String secondNumber, NotationProperties secondProperties) {

        NotationTypes firstNotation = firstProperties.getNotationType();
        NotationTypes secondNotation = secondProperties.getNotationType();

        ParameterCheckHelper.checkObjectParameter(aComparator);

        if (firstNotation == secondNotation) {

            ExpressionEvaluator evaluator;

            switch (firstNotation) {
            case INTEGER:
                evaluator = INTEGER_INTEGER_EVALUATOR;
                break;
            case FLOATING_POINT:
                evaluator = FLOATING_POINT_FLOATING_POINT_EVALUATOR;
                break;
            case SCIENTIFIC_NOTATION:
                evaluator = SCIENTIFIC_NOTATION_SCIENTIFIC_NOTATION_EVALUATOR;
                break;
            default:
                String message = "An unknown notation was specified (" + firstNotation + ")!";
                throw new UnsupportedOperationException(message);
            }

            return evaluator.evaluate(firstNumber, firstProperties, aComparator, secondNumber, secondProperties);

        } else {

            String message =
                "Cannot evaluate numbers with different notations (" + firstNotation + "/" + secondNotation + ")!";
            throw new UnsupportedOperationException(message);
        }
    }

    /**
     * Expands the specified number string if it is provided in the scientific notation.
     *
     * @param aString
     *        a number string
     *
     * @return an expanded number string
     */
    public static String expandScientificNotation(String aString) {

        NotationProperties properties = checkString(aString);

        return expandScientificNotation(aString, properties);
    }

    /**
     * Expands a scientific notation with a positive exponent.
     *
     * @param aString
     *        a number string
     * @param aDecimalSeparator
     *        a decimal separator
     *
     * @return an expanded number string
     */
    private static String handleNumberWithPositiveExponent(String aString, String aDecimalSeparator) {

        String number = getNumber(aString);
        int exponent = getExponent(aString);

        for (int a = 0; a < exponent; a++) {

            number = shiftDecimalSeparator2Right(number, aDecimalSeparator);
        }

        return number;
    }

    /**
     * Expands a scientific notation with a positive exponent.
     *
     * @param aString
     *        a number string
     * @param aDecimalSeparator
     *        a decimal separator
     *
     * @return an expanded number string
     */
    private static String handleNumberWithNegativeExponent(String aString, String aDecimalSeparator) {

        String number = getNumber(aString);
        int exponent = getExponent(aString);

        for (int a = 0; a < exponent; a++) {

            number = shiftDecimalSeparator2Left(number, aDecimalSeparator);
        }

        return number;
    }

    /**
     * Expands the specified number string if it is provided in the scientific notation.
     *
     * @param aString
     *        a number string
     * @param someProperties
     *        notation properties of the number string
     *
     * @return an expanded number string
     */
    public static String expandScientificNotation(String aString, NotationProperties someProperties) {

        NotationTypes notation = someProperties.getNotationType();
        if (SCIENTIFIC_NOTATION != notation) {

            String message = "The specified number has the wrong notation (" + notation + ")!";
            throw new IllegalArgumentException(message);
        }

        String number;

        String decimalSeparator = someProperties.getDecimalSeparator();
        Signs exponentSign = someProperties.getExponentSign();
        switch (exponentSign) {
        case POSITIVE:
            number = handleNumberWithPositiveExponent(aString, decimalSeparator);
            break;
        case NEGATIVE:
            number = handleNumberWithNegativeExponent(aString, decimalSeparator);
            break;
        default:
            String message = "An unknown exponent sign (" + exponentSign + ") was specified!";
            throw new UnsupportedOperationException(message);
        }

        return number;
    }

    /**
     * Returns the signed base inside a number with scientific notation.
     *
     * @param aString
     *        a number string
     *
     * @return a signed base
     */
    private static String getNumber(String aString) {

        String[] substrings = aString.split("E");

        return substrings[0];
    }

    /**
     * Returns the unsigned exponent inside a number with scientific notation.
     *
     * @param aString
     *        a number string
     *
     * @return an unsigned exponent
     */
    private static int getExponent(String aString) {

        String[] substrings = aString.split("E");
        String exponent = substrings[1].replaceAll("[+-]", "");

        return Integer.parseInt(exponent);
    }

    /**
     * Trim the specified number string.
     *
     * @param aString
     *        a number string
     *
     * @return a trimmed string
     */
    private static String trim(CharSequence aString, String aDecimalSeparator) {

        StringBuilder buffer = new StringBuilder(aString);

        int length = buffer.length();
        int index = length - 1;

        while (true) {

            char c = buffer.charAt(index);

            if (c == ZERO_CHARACTER) {

                buffer.deleteCharAt(index);
                index--;

            } else {

                break;
            }
        }

        char actualEnd = buffer.charAt(index);
        char expectedEnd = aDecimalSeparator.charAt(0);
        if (actualEnd == expectedEnd) {

            buffer.append(ZERO_CHARACTER);
        }

        return String.valueOf(buffer);
    }

    /**
     * Shifts the decimal separator one position to the left (i.e. the equivalent of dividing
     * by 10).
     *
     * @param aString
     *        a number string
     * @param aDecimalSeparator
     *        a decimal separator
     *
     * @return a number string
     */
    private static String shiftDecimalSeparator2Left(String aString, String aDecimalSeparator) {

        StringBuilder buffer = new StringBuilder(aString);


        boolean signed;
        char sign;
        if (Pattern.matches(SIGNED_NUMBER_REGEX, buffer)) {

            signed = true;
            sign = buffer.charAt(0);
            buffer.deleteCharAt(0);

        } else {

            sign = '+';
            signed = false;
        }


        int index = buffer.indexOf(aDecimalSeparator);

        if (index < 0) {

            String message = "No decimal separator was found (" + aString + ")!";
            throw new IllegalArgumentException(message);
        }

        int index2 = index - 1;

        if (index2 < 0) {

            buffer.setCharAt(0, ZERO_CHARACTER);
            buffer.insert(0, aDecimalSeparator);
            buffer.insert(0, ZERO_CHARACTER);

        } else {

            char a = buffer.charAt(index);
            char b = buffer.charAt(index2);

            buffer.setCharAt(index, b);
            buffer.setCharAt(index2, a);

            if (index2 == 0) {

                buffer.insert(0, ZERO_CHARACTER);
            }
        }


        if (signed) {

            buffer.insert(0, sign);
        }

        return trim(buffer, aDecimalSeparator);
    }

    /**
     * Shifts the decimal separator one position to the right (i.e. the equivalent of multiplying
     * by 10).
     *
     * @param aString
     *        a number string
     * @param aDecimalSeparator
     *        a decimal separator
     *
     * @return a number string
     */
    private static String shiftDecimalSeparator2Right(String aString, String aDecimalSeparator) {

        StringBuilder buffer = new StringBuilder(aString);

        int index = buffer.indexOf(aDecimalSeparator);

        if (index < 0) {

            String message = "No decimal separator was found (" + aString + ")!";
            throw new IllegalArgumentException(message);
        }

        int index2 = index + 1;
        int length = buffer.length();

        if (index2 < length) {

            char a = buffer.charAt(index);
            char b = buffer.charAt(index2);

            buffer.setCharAt(index, b);
            buffer.setCharAt(index2, a);

            if (index2 == length - 1) {

                buffer.append(ZERO_CHARACTER);
            }

        } else {

            buffer.setCharAt(index, ZERO_CHARACTER);
            buffer.append(aDecimalSeparator);
            buffer.append(ZERO_CHARACTER);
        }

        return trim(buffer, aDecimalSeparator);
    }

    /**
     * Parses the specified string according to the specified precision.
     *
     * @param aPrecisionType
     *        a precision type
     * @param aString
     *        a number string
     *
     * @return a number
     */
    public static Number parseString(PrecisionTypes aPrecisionType, String aString) {

        ParameterCheckHelper.checkObjectParameter(aPrecisionType);

        NotationProperties properties = NotationHelper.checkString(aString);
        NotationTypes notationType = properties.getNotationType();

        switch (notationType) {
        case INTEGER:
            return INTEGER_PARSER.parseString(aPrecisionType, aString);
        case FLOATING_POINT:
            return FLOATING_POINT_PARSER.parseString(aPrecisionType, aString);
        case SCIENTIFIC_NOTATION:
            return FLOATING_POINT_PARSER.parseString(aPrecisionType, aString);
        default:
            String message = "An unknown precision was specified (" + aPrecisionType + ")!";
            throw new UnsupportedOperationException(message);
        }
    }

}
