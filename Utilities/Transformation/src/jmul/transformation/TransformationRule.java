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

package jmul.transformation;


/**
 * This interface describes a transformation rule.
 *
 * @author Kristian Kutin
 */
public interface TransformationRule {

    /**
     * The method returns the transformation path which was specified for this
     * rule.
     *
     * @return a transformation path
     */
    TransformationPath getTransformationPath();

    /**
     * The method returns the priority of this rule. If two or more rules can
     * be applied on the same object the priority helps decide which rule is
     * more appropriate.<br>
     * The priority is represented by a numeric value where the lower value
     * indicates a higher priority.
     *
     * @return a rule priority
     */
    int getPriority();

    /**
     * The method determines if this rule can be applied to the specified
     * object.
     *
     * @param someParameters
     *        some transformation parameters, including the object which is to
     *        be transformed
     *
     * @return <code>true</code> if the rule is applicable, else
     *         <code>false</code>
     */
    boolean isApplicable(TransformationParameters someParameters);

    /**
     * The method performs the actual transformation.
     *
     * @param someParameters
     *        some transformation parameters, including the object which is to
     *        be transformed
     *
     * @return the transformed object
     */
    Object transform(TransformationParameters someParameters);

}
