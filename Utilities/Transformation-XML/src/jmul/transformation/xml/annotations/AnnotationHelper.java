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

package jmul.transformation.xml.annotations;


import java.lang.annotation.Annotation;


/**
 * A utility class which provides useful operations concerning annotations.
 *
 * @author Kristian Kutin
 */
public final class AnnotationHelper {

    /**
     * The default constructor.
     */
    private AnnotationHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * The method checks if a class possesses a specific annotation. Optionally
     * the parent classes can be examined as well.
     *
     * @param theExaminedClass
     *        the class which is to be examined
     * @param theExpectedAnnotation
     *        the expected annotation type
     *
     * @return <code>true</code> if the annotation exists, else
     *         <code>false</code>
     */
    public static boolean isAnnotationPresent(Class theExaminedClass, Class theExpectedAnnotation) {

        return isAnnotationPresent(theExaminedClass, theExpectedAnnotation, false);
    }

    /**
     * The method checks if a class possesses a specific annotation. Optionally
     * the parent classes can be examined as well.
     *
     * @param theExaminedClass
     *        the class which is to be examined
     * @param theExpectedAnnotation
     *        the expected annotation type
     * @param recurse
     *        the flag indicates if the parent classes should be examined
     *
     * @return <code>true</code> if the annotation exists, else
     *         <code>false</code>
     */
    public static boolean isAnnotationPresent(Class theExaminedClass, Class theExpectedAnnotation, boolean recurse) {

        Class probedType = theExaminedClass;
        boolean result = false;

        while (recurse && (probedType != null)) {

            result = probedType.isAnnotationPresent(theExpectedAnnotation);

            if (result) {

                break;

            } else {

                probedType = probedType.getSuperclass();
            }
        }

        return result;
    }

    /**
     * The method returns the expected annotation.
     *
     * @param theExaminedClass
     *        the class which is to be examined
     * @param theExpectedAnnotation
     *        the expected annotation type
     *
     * @return the specified annotation or <code>null</code> if no such
     *         annotation exists
     */
    public static Annotation getAnnotation(Class theExaminedClass, Class theExpectedAnnotation) {

        return getAnnotation(theExaminedClass, theExpectedAnnotation, false);
    }

    /**
     * The method returns the expected annotation. Optionally the parent classes
     * can be examined as well.
     *
     * @param theExaminedClass
     *        the class which is to be examined
     * @param theExpectedAnnotation
     *        the expected annotation type
     * @param recurse
     *        the flag indicates if the parent classes should be examined
     *
     * @return the specified annotation or <code>null</code> if no such
     *         annotation exists
     */
    public static Annotation getAnnotation(Class theExaminedClass, Class theExpectedAnnotation, boolean recurse) {

        Class probedType = theExaminedClass;

        while (recurse && (probedType != null)) {

            if (probedType.isAnnotationPresent(theExpectedAnnotation)) {

                return probedType.getAnnotation(theExpectedAnnotation);

            } else {

                probedType = probedType.getSuperclass();
            }
        }

        return null;
    }

}
