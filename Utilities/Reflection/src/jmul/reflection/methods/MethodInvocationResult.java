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


/**
 * An implementation of a method invocation result.
 *
 * @author Kristian Kutin
 */
class MethodInvocationResult implements InvocationResult {

    /**
     * The class member contains the cause for an invocation failure.
     */
    private Exception failureCause;

    /**
     * A constructor.
     */
    MethodInvocationResult() {

        failureCause = null;
    }

    /**
     * A constructor.
     *
     * @param aFailure
     *        the cause of an invocation failure
     */
    MethodInvocationResult(Exception aFailure) {

        failureCause = aFailure;
    }

    /**
     * The method determines if the method invocation has failed.
     *
     * @return true, if the method invocation has failed, else false
     */
    @Override
    public boolean hasFailed() {

        return failureCause != null;
    }

    /**
     * The method returns the cause of the invocation failure.
     *
     * @return the cause of the invocation failure or <code>null</code>
     */
    @Override
    public Exception getCauseOfFailure() {

        return failureCause;
    }

    /**
     * The method determines if the invoked method doesn't return a result (i.e.
     * the method's return type is defined as void).
     *
     * @return true
     */
    @Override
    public boolean isVoid() {

        return true;
    }

    /**
     * The method returns the result of the method inocation.
     *
     * @return <code>null</code>
     */
    @Override
    public Object getResult() {

        return null;
    }

}
