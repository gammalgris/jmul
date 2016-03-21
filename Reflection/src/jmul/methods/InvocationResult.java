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


/**
 * This interface describes an entity which represents the result of a method
 * invocation.
 *
 * @author Kristian Kutin
 */
public interface InvocationResult {

    /**
     * The method determines if the method invocation has failed.
     *
     * @return true, if the method invocation has failed, else false
     */
    boolean hasFailed();

    /**
     * The method returns the cause of the invocation failure.
     *
     * @return the cause of the invocation failure or <code>null</code>
     */
    Exception getCauseOfFailure();

    /**
     * The method determines if the invoked method doesn't return a result (i.e.
     * the method's return type is defined as void).
     *
     * @return true, if the method doesn't return a result, else false
     */
    boolean isVoid();

    /**
     * The method returns the result of the method inocation.
     *
     * @return the result of the method invocation or <code>null</code>
     */
    Object getResult();

}
