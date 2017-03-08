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


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * A utility class that specifies how to get a field's value at runtime by using
 * reflection mechanisms.
 *
 * @author Kristian Kutin
 */
public class DynamicFieldInitializer extends FieldInitializerBase {

    /**
     * A reference to the object which is used to calculate the field's value.
     */
    private Object object;

    /**
     * A reference to the object's method which is used to calculate the field's
     * value.
     */
    private Method method;

    /**
     * The parameters which are required to invoke the method.
     */
    private Object[] parameters;

    /**
     * Constructs a field initialization object.
     *
     * @param aFieldName
     * a field name
     * @param anObject
     * a reference to the object which is used to calculate the field's
     * value
     * @param aMethod
     * a reference to the object's method which is used to calculate the
     * field's value
     * @param someParameters
     *
     */
    public DynamicFieldInitializer(String aFieldName, Object anObject, Method aMethod, Object[] someParameters) {

        super(aFieldName);
        object = anObject;
        method = aMethod;
        parameters = someParameters;
    }

    /**
     * Calculates the field's value according to the specified informations.
     *
     * @return a field's value
     */
    public Object getFieldValue() {

        Object result = null;

        try {

            result = method.invoke(object, parameters);

        } catch (IllegalAccessException e) {

            String message = "Invocation failed!";
            throw new RuntimeException(message, e);

        } catch (InvocationTargetException e) {

            String message = "Invocation failed!";
            throw new RuntimeException(message, e);
        }

        return result;
    }

}
