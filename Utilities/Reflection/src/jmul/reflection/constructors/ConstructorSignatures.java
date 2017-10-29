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

package jmul.reflection.constructors;


import java.io.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This class contains signatures for various constructors.
 *
 * @author Kristian Kutin
 */
public final class ConstructorSignatures {

    /**
     * A signature of a constructor.
     */
    private static final List<Class> DEFAULT_CONSTRUCTOR;

    /**
     * A signature of a constructor.
     */
    public static final List<Class> MESSAGE_CONSTRUCTOR;

    /**
     * A signature of a constructor.
     */
    public static final List<Class> CAUSE_CONSTRUCTOR;

    /**
     * A signature of a constructor.
     */
    public static final List<Class> MESSAGE_CAUSE_CONSTRUCTOR;

    /**
     * A signature of a constructor.
     */
    public static final List<Class> MESSAGE_FILE_CONSTRUCTOR;

    /**
     * A signature of a constructor.
     */
    public static final List<Class> CLASS_PARAMETER_CONSTRUCTOR;

    /**
     * A signature of a constructor.
     */
    public static final List<Class> SIZES_PARAMETERS_CONSTRUCTOR;

    /*
     * The static initializer.
     */
    static {

        List<Class> tmp;

        tmp = new ArrayList<>();
        DEFAULT_CONSTRUCTOR = Collections.unmodifiableList(tmp);

        tmp = new ArrayList<>();
        tmp.add(String.class);
        MESSAGE_CONSTRUCTOR = Collections.unmodifiableList(tmp);

        tmp = new ArrayList<>();
        tmp.add(Throwable.class);
        CAUSE_CONSTRUCTOR = Collections.unmodifiableList(tmp);

        tmp = new ArrayList<>();
        tmp.add(String.class);
        tmp.add(Throwable.class);
        MESSAGE_CAUSE_CONSTRUCTOR = Collections.unmodifiableList(tmp);

        tmp = new ArrayList<>();
        tmp.add(String.class);
        tmp.add(File.class);
        MESSAGE_FILE_CONSTRUCTOR = Collections.unmodifiableList(tmp);

        tmp = new ArrayList<>();
        tmp.add(Class.class);
        CLASS_PARAMETER_CONSTRUCTOR = Collections.unmodifiableList(tmp);

        tmp = new ArrayList<>();
        tmp.add(Integer.TYPE);
        tmp.add(Integer.TYPE);
        SIZES_PARAMETERS_CONSTRUCTOR = Collections.unmodifiableList(tmp);
    }

    /**
     * The default constructor.
     */
    private ConstructorSignatures() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns a constructor signature.
     *
     * @return a constructor signature
     */
    public static Class[] getDefaultConstructorSignature() {

        return DEFAULT_CONSTRUCTOR.toArray(new Class[] { });
    }

    /**
     * Returns a constructor signature.
     *
     * @return a constructor signature
     */
    public static Class[] getMessageConstructorSignature() {

        return MESSAGE_CONSTRUCTOR.toArray(new Class[] { });
    }

    /**
     * Returns a constructor signature.
     *
     * @return a constructor signature
     */
    public static Class[] getFileNameConstructorSignature() {

        return getMessageConstructorSignature();
    }

    /**
     * Returns a constructor signature.
     *
     * @return a constructor signature
     */
    public static Class[] getCauseConstructorSignature() {

        return CAUSE_CONSTRUCTOR.toArray(new Class[] { });
    }

    /**
     * Returns a constructor signature.
     *
     * @return a constructor signature
     */
    public static Class[] getMessageCauseConstructorSignature() {

        return MESSAGE_CAUSE_CONSTRUCTOR.toArray(new Class[] { });
    }

    /**
     * Returns a constructor signature.
     *
     * @return a constructor signature
     */
    public static Class[] getMessageFileConstructorSignature() {

        return MESSAGE_FILE_CONSTRUCTOR.toArray(new Class[] { });
    }

    /**
     * Returns a constructor signature.
     *
     * @return a constructor signature
     */
    public static Class[] getClassParameterConstructor() {

        return CLASS_PARAMETER_CONSTRUCTOR.toArray(new Class[] { });
    }

    /**
     * Returns a constructor signature.
     *
     * @return a constructor signature
     */
    public static Class[] getSizesParametersConstructor() {

        return SIZES_PARAMETERS_CONSTRUCTOR.toArray(new Class[] { });
    }

}
