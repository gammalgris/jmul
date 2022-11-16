/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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

package jmul.transformation.creation.creators;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;


/**
 * This utility class provides normalization functions.
 *
 * @author Kristian Kutin
 */
public final class NormalizationHelper {

    /**
     * All normalizers for real numbers.
     */
    private static List<Normalizer> REAL_NUMBER_NORMALIZERS;

    /*
     * The static initializer.
     */
    static {

        List<Normalizer> tmp = new ArrayList<>();

        tmp.add(new RealNumberWithDecimalPointNormalizer());
        tmp.add(new RealNumberWithDecimalCommaNormalizer());

        REAL_NUMBER_NORMALIZERS = Collections.unmodifiableList(tmp);
    }

    /**
     * The default constructor.
     */
    private NormalizationHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Normalizes a string containing a real number (i.e. checks which notation is used
     * and normalizes the decimal separator).
     *
     * @param s
     *        a string
     *
     * @return a nomralized string
     */
    public static String normalizeRealNumber(String s) {

        if (s == null) {

            throw new IllegalArgumentException("No string (null) has been specified!");
        }

        for (Normalizer normalizer : REAL_NUMBER_NORMALIZERS) {

            if (normalizer.isApplicable(s)) {

                return normalizer.normalize(s);
            }
        }

        throw new IllegalArgumentException(String.format("The specified string (\"%s\") contains an unsupported real number notation and cannot be normalized!",
                                                         s));
    }

}


/**
 * This interface describes an entity which normalized strings.
 *
 * @author Kristian Kutin
 */
interface Normalizer {

    /**
     * Checks if the specified string can be normalized by this entity.
     *
     * @param s
     *        a string
     *
     * @return <code>true</code> is this entity can normalize the specified string, else <code>false</code>
     */
    boolean isApplicable(String s);

    /**
     * Returns a nomralized copy of the specified string.
     *
     * @param s
     *        a string
     *
     * @return a normalized string
     */
    String normalize(String s);

}


/**
 * An implementation for a normalizer which normalizes a real number notation with a decimal point.
 *
 * @author Kristian Kutin
 */
class RealNumberWithDecimalPointNormalizer implements Normalizer {

    /**
     * A regular expression which contains a real number notation with a decimal point.
     */
    private static final String REGEX;

    /*
     * The static initializer.
     */
    static {

        REGEX = "^[+-]?[0-9]+([.][0-9]+)?$";
    }

    /**
     * The default constructor.
     */
    public RealNumberWithDecimalPointNormalizer() {

        super();
    }

    /**
     * Checks if the specified string can be normalized by this entity.
     *
     * @param s
     *        a string
     *
     * @return <code>true</code> is this entity can normalize the specified string, else <code>false</code>
     */
    @Override
    public boolean isApplicable(String s) {

        return Pattern.matches(REGEX, s);
    }

    /**
     * Returns a nomralized copy of the specified string.
     *
     * @param s
     *        a string
     *
     * @return a normalized string
     */
    @Override
    public String normalize(String s) {

        return s;
    }

}


/**
 * An implementation for a normalizer which normalizes a real number notation with a decimal comma.
 *
 * @author Kristian Kutin
 */
class RealNumberWithDecimalCommaNormalizer implements Normalizer {

    /**
     * A regular expression which contains a real number notation with a decimal comma.
     */
    private static final String REGEX;

    /**
     * A constant.
     */
    private static final String COMMA;

    /**
     * A constant.
     */
    private static final String POINT;

    /*
     * The static initializer.
     */
    static {

        REGEX = "^[+-]?[0-9]+([,][0-9]+)?$";
        COMMA = ",";
        POINT = ".";
    }

    /**
     * The default constructor.
     */
    public RealNumberWithDecimalCommaNormalizer() {

        super();
    }

    /**
     * Checks if the specified string can be normalized by this entity.
     *
     * @param s
     *        a string
     *
     * @return <code>true</code> is this entity can normalize the specified string, else <code>false</code>
     */
    @Override
    public boolean isApplicable(String s) {

        return Pattern.matches(REGEX, s);
    }

    /**
     * Returns a nomralized copy of the specified string (i.e. replaces the decimal comma with a point).
     *
     * @param s
     *        a string
     *
     * @return a normalized string
     */
    @Override
    public String normalize(String s) {

        return s.replace(COMMA, POINT);
    }

}
