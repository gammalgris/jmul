/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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
package test.jmul.persistence.scenarios.scenario022;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import jmul.misc.generators.Generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmul.math.random.Dice;
import jmul.math.random.DiceImpl;
import jmul.math.random.DieImpl;
import static jmul.math.random.StandardDice.D6;

import static jmul.string.Constants.POINT;


/**
 * A utlity class.
 *
 * @author Kristian Kutin
 */
public final class RandomizationHelper {

    /**
     * All intrnally used generators.
     */
    private static Map<Generators, Generator<String>> generators;

    /**
     * The static initializer.
     */
    static {

        Map<Generators, Generator<String>> tmp = new HashMap<Generators, Generator<String>>();
        tmp.put(Generators.FIRST_NAME_GENERATOR, new FirstNameGenerator());
        tmp.put(Generators.LAST_NAME_GENERATOR, new LastNameGenerator());
        tmp.put(Generators.GENDER_GENERATOR, new GenderGenerator());
        tmp.put(Generators.BIRTHDAY_GENERATOR, new BirthdayGenerator());

        generators = Collections.unmodifiableMap(tmp);
    }

    /**
     * The default constrcutor.
     */
    private RandomizationHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns a randomly generated first name.
     *
     * @return a first name
     */
    public static String randomFirstName() {

        Generator<String> generator = generators.get(Generators.FIRST_NAME_GENERATOR);
        return generator.createValue();
    }

    /**
     * Returns a randomly generated last name.
     *
     * @return a last name
     */
    public static String randomLastName() {

        Generator<String> generator = generators.get(Generators.LAST_NAME_GENERATOR);
        return generator.createValue();
    }

    /**
     * Returns a randomly determined gender.
     *
     * @return a gender
     */
    public static String randomGender() {

        Generator<String> generator = generators.get(Generators.GENDER_GENERATOR);
        return generator.createValue();
    }

    /**
     * Returns a randomly generated birthday.
     *
     * @return a birthday
     */
    public static String randomBirthday() {

        Generator<String> generator = generators.get(Generators.BIRTHDAY_GENERATOR);
        return generator.createValue();
    }

}


/**
 * The enumeration contains all existing generators.
 *
 * @author Kristian Kutin
 */
enum Generators {

    FIRST_NAME_GENERATOR,
    LAST_NAME_GENERATOR,
    GENDER_GENERATOR,
    BIRTHDAY_GENERATOR, ;

}


/**
 * A base implementation for a string generator.
 *
 * @author Kristian Kutin
 */
abstract class GeneratorBase implements Generator<String> {

    /**
     * The default constructor.
     */
    protected GeneratorBase() {

        super();
    }

}


/**
 * An implementation of a generator for first names.
 */
class FirstNameGenerator extends GeneratorBase {

    /**
     * All characters in the latin alphabet.
     */
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * A set of virtual dice to determine the length of the first name.
     */
    private Dice randomLength;

    /**
     * A set of virtual dice to determine a character from the first name.
     */
    private Dice randomCharacter;

    /**
     * Creates a new generator.
     */
    public FirstNameGenerator() {

        randomLength = new DiceImpl(D6, D6);
        randomCharacter = new DiceImpl(new DieImpl(CHARACTERS.length()));
    }

    /**
     * Returns a randomly created first name.
     *
     * @return a first name
     */
    @Override
    public String createValue() {

        StringBuffer buffer = new StringBuffer();
        boolean first = true;
        int length = randomLength.roll();

        for (int a = 1; a <= length; a++) {

            int index = randomCharacter.roll();
            char c = CHARACTERS.charAt(index - 1);

            if (first) {

                buffer.append(Character.toUpperCase(c));
                first = false;

            } else {

                buffer.append(c);
            }
        }

        return buffer.toString();
    }

}


/**
 * An implementation of a generator for first names.
 */
class LastNameGenerator extends GeneratorBase {

    /**
     * All characters in the latin alphabet.
     */
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * A set of virtual dice to determine the length of the first name.
     */
    private Dice randomLength;

    /**
     * A set of virtual dice to determine a character from the first name.
     */
    private Dice randomCharacter;

    /**
     * Creates a new generator.
     */
    public LastNameGenerator() {

        randomLength = new DiceImpl(D6, D6, D6);
        randomCharacter = new DiceImpl(new DieImpl(CHARACTERS.length()));
    }

    /**
     * Returns a randomly created first name.
     *
     * @return a first name
     */
    @Override
    public String createValue() {

        StringBuffer buffer = new StringBuffer();
        boolean first = true;
        int length = randomLength.roll();

        for (int a = 1; a <= length; a++) {

            int index = randomCharacter.roll();
            char c = CHARACTERS.charAt(index - 1);

            if (first) {

                buffer.append(Character.toUpperCase(c));
                first = false;

            } else {

                buffer.append(c);
            }
        }

        return buffer.toString();
    }

}


/**
 * An implementation of a generator for first names.
 */
class GenderGenerator extends GeneratorBase {

    /**
     * All defined genders.
     */
    private static final List<String> GENDERS;

    /*
     * The static initializer.
     */
    static {

        List<String> tmp = new ArrayList<String>();
        tmp.add("male");
        tmp.add("female");

        GENDERS = Collections.unmodifiableList(tmp);
    }

    /**
     * A set of virtual dice to determine the gender.
     */
    private Dice randomGender;

    /**
     * Creates a new generator.
     */
    public GenderGenerator() {

        randomGender = new DiceImpl(new DieImpl(GENDERS.size()));
    }

    /**
     * Returns a randomly created first name.
     *
     * @return a first name
     */
    @Override
    public String createValue() {

        int index = randomGender.roll();

        return GENDERS.get(index - 1);
    }

}


/**
 * An implementation of a generator for first names.
 */
class BirthdayGenerator extends GeneratorBase {

    /**
     * A date pattern.
     */
    private final String DATE_PATTERN = "d.M.yyyy";

    /**
     * The maximum number of days in a month.
     */
    private final int MAX_DAYS = 31;

    /**
     * The maximum number of months in a year.
     */
    private final int MAX_MONTHS = 12;

    /**
     * The maximum number of years since the minimum year threshold.
     */
    private final int MAX_YEARS = 100;

    /**
     * The minimum year threshold.
     */
    private final int BASE_YEAR = 1900;

    /**
     * A set of virtual dice to determine a day randomly.
     */
    private Dice randomDay;

    /**
     * A set of virtual dice to determine a month randomly.
     */
    private Dice randomMonth;

    /**
     * A set of virtual dice to determine a year randomly.
     */
    private Dice randomYear;

    /**
     * Creates a new generator.
     */
    public BirthdayGenerator() {

        randomDay = new DiceImpl(new DieImpl(MAX_DAYS));
        randomMonth = new DiceImpl(new DieImpl(MAX_MONTHS));
        randomYear = new DiceImpl(new DieImpl(MAX_YEARS));
    }

    /**
     * Returns a randomly created first name.
     *
     * @return a first name
     */
    @Override
    public String createValue() {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

        while (true) {

            int day = randomDay.roll();
            int month = randomMonth.roll();
            int year = BASE_YEAR + randomYear.roll();

            String dateString = day + POINT + month + POINT + year;

            try {

                sdf.parse(dateString);
                return dateString;

            } catch (ParseException e) {

                continue;
            }
        }
    }

}
