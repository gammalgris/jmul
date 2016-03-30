/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.methods;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * An implementation of an operation invoker.<br>
 * <br>
 * This implementation should be used with care, because it overrides the real
 * accessibility of the method!
 *
 * @author Kristian Kutin
 */
public class AlternativeFunctionInvocation implements OperationInvoker {

    /**
     * The default constructor.
     */
    public AlternativeFunctionInvocation() {
    }

    /**
     * The method invokes the specified function on the specified target. A
     * result or exception is provided by an invocation result.
     *
     * @param invocationTarget
     *        the target on which to call the specified method
     * @param operation
     *        a function
     * @param parameters
     *        the parameters which are required to invoke the method
     *
     * @return the result of the invocation
     */
    public InvocationResult invoke(Object invocationTarget, Method operation,
                                   Object[] parameters) {

        InvocationResult result = null;
        boolean accessible = operation.isAccessible();

        try {

            if (!accessible) {

                operation.setAccessible(true);
                Object functionResult =
                    operation.invoke(invocationTarget, parameters);
                result = new FunctionInvocationResult(functionResult);
                operation.setAccessible(accessible);

            } else {

                String message =
                    "The prerequisites for the alternative method invocation don't apply!";
                Exception cause = new IllegalArgumentException(message);
                result = new FunctionInvocationResult(cause);
            }

        } catch (IllegalAccessException e) {

            result = new FunctionInvocationResult(e);

        } catch (InvocationTargetException e) {

            result = new FunctionInvocationResult(e);

        } finally {

            operation.setAccessible(accessible);
        }

        return result;
    }


}
