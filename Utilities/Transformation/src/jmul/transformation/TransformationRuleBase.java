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

package jmul.transformation;


/**
 * A base implementation of a transformation rule.
 *
 * @author Kristian Kutin
 */
public abstract class TransformationRuleBase implements TransformationRule {

    /**
     * The transformation path.
     */
    private final TransformationPath transformationPath;

    /**
     * The rule priority;
     */
    private final int priority;

    /**
     * Constructs a transformation rule.
     *
     * @param anOrigin
     *        a description of the transformation origin
     * @param aDestination
     *        a description of the transformation destination
     * @param aPriority
     *        a rule priority
     */
    public TransformationRuleBase(String anOrigin, String aDestination, int aPriority) {

        transformationPath = new TransformationPath(anOrigin, aDestination);
        priority = aPriority;
    }

    /**
     * The method returns the transformation path which was specified for this
     * rule.
     *
     * @return a transformation path
     */
    @Override
    public TransformationPath getTransformationPath() {

        return transformationPath;
    }

    /**
     * The method returns the priority of this rule. If two or more rules can
     * be applied on the same object the priority helps decide which rule is
     * more appropriate.<br>
     * The priority is represented by a numeric value where the lower value
     * indicates a higher priority.
     *
     * @return a rule priority
     */
    @Override
    public int getPriority() {

        return priority;
    }

}
