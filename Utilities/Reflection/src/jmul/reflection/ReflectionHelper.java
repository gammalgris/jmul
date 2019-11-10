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

package jmul.reflection;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jmul.checks.ParameterCheckHelper;

import jmul.misc.error.ErrorStatus;
import jmul.misc.error.SingleErrorStatus;

import jmul.reflection.classes.AccessorHelper;
import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;
import jmul.reflection.methods.AlternativeFunctionInvocation;
import jmul.reflection.methods.AlternativeMethodInvocation;
import jmul.reflection.methods.InvocationResult;
import jmul.reflection.methods.OperationInvoker;
import jmul.reflection.methods.StandardFunctionInvocation;
import jmul.reflection.methods.StandardMethodInvocation;

import jmul.string.TextHelper;


/**
 * A util class for reflection mechanisms.
 *
 * TODO
 * The two public methods are almost identical (except for the parameter
 * signature). Examine if redundant code can be minimized.
 *
 * @author Kristian Kutin
 */
public final class ReflectionHelper {

    /**
     * The class member can be used to invoke a method.
     */
    private static OperationInvoker standardMethodInvoker = new StandardMethodInvocation();

    /**
     * The class member can be used to invoke a method.
     */
    private static OperationInvoker alternativeMethodInvoker = new AlternativeMethodInvocation();

    /**
     * The class member can be used to invoke a function.
     */
    private static OperationInvoker standardFunctionInvoker = new StandardFunctionInvocation();

    /**
     * The class member can be used to invoke a function.
     */
    private static OperationInvoker alternativeFunctionInvoker = new AlternativeFunctionInvocation();

    /**
     * The default constructor.
     */
    private ReflectionHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Identfies a class definition according to the specified class.
     *
     * @param aClass
     *        a class
     *
     * @return a class definition
     */
    private static ClassDefinition identifyClassDefinition(Class aClass) {

        try {

            return ClassHelper.getClass(aClass);

        } catch (ClassNotFoundException f) {

            String message =
                TextHelper.concatenateStrings("The target's class couldn't be determined (", aClass.getName(), ")!");
            throw new IllegalArgumentException(message, f);
        }
    }

    /**
     * Evaluates the specified error status and may throw a corresponding exception.
     *
     * @param anErrorStatus
     *        an error status
     *
     * @throws NoSuchMethodException
     *         is thrown if no method can be identified
     * @throws IllegalAccessException
     *         is thrown if the method has restricted access
     * @throws InvocationTargetException
     *         is thrown if an error occurs while the method is executed
     */
    private static void evaluateError(ErrorStatus anErrorStatus) throws NoSuchMethodException, IllegalAccessException,
                                                                        InvocationTargetException {

        Throwable cause = anErrorStatus.getError();

        if (cause instanceof NoSuchMethodException) {

            throw (NoSuchMethodException) cause;

        } else if (cause instanceof IllegalAccessException) {

            throw (IllegalAccessException) cause;

        } else if (cause instanceof InvocationTargetException) {

            throw (InvocationTargetException) cause;
        }
    }

    /**
     * The method calls a setter method to set a class member.
     *
     * @param target
     *        the target object
     * @param fieldName
     *        the field name
     * @param fieldValue
     *        the new value of the field
     *
     * @throws NoSuchMethodException
     *         is thrown if no setter method can be identified
     * @throws IllegalAccessException
     *         is thrown if the setter method has restricted access
     * @throws InvocationTargetException
     *         is thrown if an error occurs while the setter is executed
     */
    public static void invokeSetter(Object target, String fieldName, Object fieldValue) throws NoSuchMethodException,
                                                                                               IllegalAccessException,
                                                                                               InvocationTargetException {

        ParameterCheckHelper.checkObjectParameter(target);
        ParameterCheckHelper.checkStringParameter(fieldName);


        // Prepare the method invocation.

        ErrorStatus status = new SingleErrorStatus();


        Object[] parameters = { fieldValue };
        Class[] parameterSignature = { fieldValue.getClass() };

        Class probedClass = target.getClass();
        ClassDefinition probedClassDefinition = identifyClassDefinition(probedClass);


        // Use the regular way first to identify the setter method. This is the
        // fastest way.

        try {

            String methodname = AccessorHelper.determineAccesorName(AccessorHelper.SETTER_PREFIX, fieldName);
            Method setter = probedClass.getMethod(methodname, parameterSignature);
            InvocationResult result = standardMethodInvoker.invoke(target, setter, parameters);

            if (result.hasFailed()) {

                status.reportError(result.getCauseOfFailure());

            } else {

                return;
            }

        } catch (NoSuchMethodException e) {

            status.reportError(e);
        }


        // It didn't work the regular way. Now it is necessary to look into
        // the class itself. This is the slow way.

        try {

            Method setter = probedClassDefinition.getAccessor(AccessorHelper.SETTER_PREFIX, fieldName, true);
            InvocationResult result = standardMethodInvoker.invoke(target, setter, parameters);

            if (result.hasFailed()) {

                status.reportError(result.getCauseOfFailure());

            } else {

                return;
            }

        } catch (NoSuchMethodException e) {

            status.reportError(e);

        }


        // Try the same again, but this time invoke the setter in a different
        // way.

        try {

            Method setter = probedClassDefinition.getAccessor(AccessorHelper.SETTER_PREFIX, fieldName, true);
            InvocationResult result = alternativeMethodInvoker.invoke(target, setter, parameters);

            if (result.hasFailed()) {

                status.reportError(result.getCauseOfFailure());

            } else {

                return;
            }

        } catch (NoSuchMethodException e) {

            status.reportError(e);
        }


        // Now throw the exception!
        evaluateError(status);
    }

    /**
     * The method calls a getter method to get a class member.
     *
     * @param target
     *        the target object
     * @param fieldName
     *        the field name
     *
     * @return the field value
     *
     * @throws NoSuchMethodException
     *         is thrown if no getter method can be identified
     * @throws IllegalAccessException
     *         is thrown if the getter method has restricted access
     * @throws InvocationTargetException
     *         is thrown if an error occurs while the getter is executed
     */
    public static Object invokeGetter(Object target, String fieldName) throws NoSuchMethodException,
                                                                              IllegalAccessException,
                                                                              InvocationTargetException {

        ParameterCheckHelper.checkObjectParameter(target);
        ParameterCheckHelper.checkStringParameter(fieldName);


        // Prepare the method invocation.

        ErrorStatus status = new SingleErrorStatus();
        InvocationResult result;
        Method getter;

        Object[] parameters = { };
        Class[] parameterSignature = { };

        Class probedClass = target.getClass();
        ClassDefinition probedClassDefinition = identifyClassDefinition(probedClass);


        // Use the regular way first to identify the getter method. This is the
        // fastest way.

        try {

            String methodname = AccessorHelper.determineAccesorName(AccessorHelper.GETTER_PREFIX, fieldName);
            getter = probedClass.getMethod(methodname, parameterSignature);

        } catch (NoSuchMethodException e) {

            status.reportError(e);
            getter = null;
        }


        if (getter != null) {

            result = standardFunctionInvoker.invoke(target, getter, parameters);

            if (result.hasFailed()) {

                status.reportError(result.getCauseOfFailure());
            }

        } else {

            result = null;
        }


        if ((result != null) && !result.hasFailed()) {

            // If no error occurred then we're done.
            return result.getResult();
        }


        // It didn't work the regular way. Now it is necessary to look into
        // the class itself. This is the slow way.

        try {

            getter = probedClassDefinition.getAccessor(AccessorHelper.GETTER_PREFIX, fieldName, true);
            result = standardFunctionInvoker.invoke(target, getter, parameters);

            if (result.hasFailed()) {

                status.reportError(result.getCauseOfFailure());
            }

        } catch (NoSuchMethodException e) {

            status.reportError(e);
        }


        if ((result != null) && !result.hasFailed()) {

            // If no error occurred then we're done.
            return result.getResult();
        }


        // Try the same again, but this time invoke the setter in a different
        // way.

        //TODO
        // This last attempt is superfluous. Remove it if no better
        // implementation for the alternative invocation can be found.

        if (getter != null) {

            result = alternativeFunctionInvoker.invoke(target, getter, parameters);

            if (result.hasFailed()) {

                status.reportError(result.getCauseOfFailure());
            }
        }


        if ((result != null) && !result.hasFailed()) {

            // If no error occurred then we're done.
            return result.getResult();
        }


        // Now throw the exception!
        evaluateError(status);

        String message = "The getter method didn't return anything!";
        throw new InvalidGetterException(message);
    }

}
