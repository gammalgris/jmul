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

package test.jmul.persistence.legacy.loadtest;


import test.jmul.datatypes.department.DepartmentGenderDetails;
import test.jmul.datatypes.department.DepartmentGenderDetailsImpl2;
import test.jmul.datatypes.employee.Employee;
import test.jmul.datatypes.employee.EmployeeImpl6;

import jmul.persistence.id.ID;
import jmul.persistence.id.StringID;

import jmul.string.StringConcatenator;


/**
 * A utility class which generates randomized data.
 *
 * @author Kristian Kutin
 */
public class RandomizationHelper {

    /**
     * Default length for random strings.
     */
    private static final int DEFAULT_RANDOM_STRING_MAX_LENGTH = 10;

    /**
     * Default length for random strings.
     */
    private static final int DEFAULT_RANDOM_STRING_MIN_LENGTH = 4;

    /**
     * Contains all allowed characters.
     */
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Contains all allowed digits.
     */
    private static final String ALLOWED_DIGITS = "0123456789";

    /**
     * Contains all allowed genders.
     */
    private static final String[] ALLOWED_GENDERS = { "male", "female" };

    /**
     * The default constructor.
     */
    private RandomizationHelper() {
    }

    /**
     * Returns a random character.
     *
     * @param someAllowedCharacters
     * the set of cahracters to choose from
     *
     * @return a character
     */
    private static char getRandomCharacter(String someAllowedCharacters) {

        int randomIndex = (int) (someAllowedCharacters.length() * Math.random());
        char randomCharacter = someAllowedCharacters.charAt(randomIndex);

        return randomCharacter;
    }

    /**
     * Returns a random character.
     *
     * @return a character
     */
    public static char getRandomCharacter() {

        return getRandomCharacter(ALLOWED_CHARACTERS);
    }

    /**
     * Returns a random digit.
     *
     * @return a character
     */
    public static char getRandomDigit() {

        return getRandomCharacter(ALLOWED_DIGITS);
    }

    /**
     * Returns a random gender.
     *
     * @return a gender
     */
    public static String getRandomGender() {

        int randomIndex = (int) (ALLOWED_GENDERS.length * Math.random());
        String randomGender = ALLOWED_GENDERS[randomIndex];

        return randomGender;
    }

    /**
     * Returns a random string.
     *
     * @return a string
     */
    public static String createRandomString() {

        return createRandomString(DEFAULT_RANDOM_STRING_MIN_LENGTH, DEFAULT_RANDOM_STRING_MAX_LENGTH);
    }

    /**
     * Returns a random string.
     *
     * @param aMinLength
     * a minimum string length
     * @param aMaxLength
     * a maximum string length
     *
     * @return a string
     */
    public static String createRandomString(int aMinLength, int aMaxLength) {

        int size = aMinLength + (int) (Math.random() * aMaxLength);
        StringConcatenator randomString = new StringConcatenator();

        for (int a = 0; a < size; a++) {

            randomString.append(getRandomCharacter());
        }

        return randomString.toString();
    }

    /**
     * Creates a new employee with random values.
     *
     * @return a new employee
     */
    public static Employee createRandomEmployee() {

        return new EmployeeImpl6(createRandomString(), createRandomString(), createRandomString(), getRandomGender(),
                                 (float) (Math.random() * 2000.00f), createRandomString());
    }

    /**
     * Creates random organisation details.
     *
     * @return organisation details
     */
    public static DepartmentGenderDetails createRandomOrganisationDetails() {

        DepartmentGenderDetails details = new DepartmentGenderDetailsImpl2();

        int employees = (int) (Math.random() * 50);

        for (int a = 0; a < employees; a++) {

            details.addEmployee(createRandomEmployee());
        }

        return details;
    }

    /**
     * Creates a random id.
     *
     * @return an id
     */
    public static ID createRandomInvalidID() {

        return new StringID(createRandomString());
    }

}
