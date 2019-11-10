/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package jmul.math;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * This utility class is a central place for numeric constants.
 *
 * @author Kristian Kutin
 */
public final class Constants {

    /**
     * A second in milliseconds.
     */
    public static final long SECOND = 1000L;

    /**
     * A minute in milliseconds.
     */
    public static final long MINUTE = 60L * SECOND;

    /**
     * An hour in milliseconds.
     */
    public static final long HOUR = 60L * MINUTE;

    /**
     * An arbitrary small limit for different number types.
     */
    private static final Map<Class, Number> EPSILONS;

    /**
     * Zero for different number types.
     */
    private static final Map<Class, Number> ZEROS;

    /**
     * The static initializer.
     */
    static {

        byte b;
        short s;
        int i;
        long l;
        float f;
        double d;


        Map<Class, Number> tmp;


        tmp = new HashMap<>();

        f = 0.001F;
        tmp.put(Float.TYPE, f);
        tmp.put(Float.class, Float.valueOf(f));

        d = 0.001D;
        tmp.put(Double.TYPE, d);
        tmp.put(Double.class, Double.valueOf(d));

        EPSILONS = Collections.unmodifiableMap(tmp);


        tmp = new HashMap<>();

        b = 0;
        tmp.put(Byte.TYPE, b);
        tmp.put(Byte.class, Byte.valueOf(b));

        s = 0;
        tmp.put(Short.TYPE, s);
        tmp.put(Short.class, Short.valueOf(s));

        i = 0;
        tmp.put(Integer.TYPE, i);
        tmp.put(Integer.class, Integer.valueOf(i));

        l = 0L;
        tmp.put(Long.TYPE, l);
        tmp.put(Long.class, Long.valueOf(l));

        f = 0.0F;
        tmp.put(Float.TYPE, f);
        tmp.put(Float.class, Float.valueOf(f));

        d = 0.0D;
        tmp.put(Double.TYPE, d);
        tmp.put(Double.class, Double.valueOf(d));

        ZEROS = Collections.unmodifiableMap(tmp);
    }

    /**
     * The default constructor.
     */
    private Constants() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns the epsilon for the specified number type.
     *
     * @param aType
     *        a number type
     *
     * @return epsilon (an arbitrary small limit)
     */
    public static Number getEpsilon(Class aType) {

        if (aType == null) {

            throw new IllegalArgumentException("No value (null) was specified!");
        }

        Number value = EPSILONS.get(aType);

        if (value == null) {

            String message = "For the type " + aType.getName() + " no epsilon was defined!";
            throw new IllegalArgumentException(message);
        }

        return value;
    }

    /**
     * Returns zero for the specified number type.
     *
     * @param aType
     *        a number type
     *
     * @return zero
     */
    public static Number getZero(Class aType) {

        if (aType == null) {

            throw new IllegalArgumentException("No value (null) was specified!");
        }

        Number value = ZEROS.get(aType);

        if (value == null) {

            String message = "For the type " + aType.getName() + " no zero was defined!";
            throw new IllegalArgumentException(message);
        }

        return value;
    }

}
