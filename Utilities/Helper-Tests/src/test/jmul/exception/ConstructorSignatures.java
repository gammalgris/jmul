/*
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

package test.jmul.exception;


/**
 * This class contains signatures for various constructors.
 *
 * @author Kristian Kutin
 */
public final class ConstructorSignatures {

    /**
     * A signature of a constructor.
     */
    public static final Class[] EXCEPTION_CONSTRUCTOR1 = new Class[] { String.class };

    /**
     * A signature of a constructor.
     */
    public static final Class[] EXCEPTION_CONSTRUCTOR2 = new Class[] { Throwable.class };

    /**
     * A signature of a constructor.
     */
    public static final Class[] EXCEPTION_CONSTRUCTOR3 = new Class[] { String.class, Throwable.class };

    /**
     * The default constructor.
     */
    private ConstructorSignatures() {

        throw new UnsupportedOperationException();
    }

}
