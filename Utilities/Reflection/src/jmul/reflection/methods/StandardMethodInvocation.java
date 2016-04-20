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

package jmul.reflection.methods;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * An implementation of an operation invoker.
 *
 * @author Kristian Kutin
 */
public class StandardMethodInvocation implements OperationInvoker {

    /**
     * The default constructor.
     */
    public StandardMethodInvocation() {
    }

    /**
     * The method invokes the specified method on the specified target. A
     * result or exception is provided by an invocation result.
     *
     * @param invocationTarget
     *        the target on which to call the specified method
     * @param operation
     *        a method
     * @param parameters
     *        the parameters which are required to invoke the method
     *
     * @return the result of the invocation
     */
    @Override
    public InvocationResult invoke(Object invocationTarget, Method operation, Object[] parameters) {

        InvocationResult result = null;

        try {

            operation.invoke(invocationTarget, parameters);
            result = new MethodInvocationResult();

        } catch (IllegalAccessException e) {

            result = new MethodInvocationResult(e);

        } catch (InvocationTargetException e) {

            result = new MethodInvocationResult(e);
        }

        return result;
    }

}
