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

package test.jmul.transformation.rules;


import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationRuleBase;


/**
 * A transformation rule for testing purposes.
 *
 * @author Kristian Kutin
 */
public class DummyRule1 extends TransformationRuleBase {

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
    public DummyRule1(String anOrigin, String aDestination, int aPriority) {

        super(anOrigin, aDestination, aPriority);
    }

    /**
     * The method determines if this rule can be applied on the specified
     * object.
     *
     * @param someParameters
     *        some transformation parameters, including the object which is to
     *        be transformed
     *
     * @return <code>true</code> if the rule is applicable, else
     *         <code>false</code>
     */
    @Override
    public boolean isApplicable(TransformationParameters someParameters) {

        return true;
    }

    /**
     * The method performs a transformation.
     *
     * @param someParameters
     *        some transformation parameters, including the object which is to
     *        be transformed
     *
     * @return the object itself, without any changes
     */
    @Override
    public Object transform(TransformationParameters someParameters) {

        return someParameters.getObject();
    }

}
