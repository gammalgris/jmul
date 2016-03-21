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

package jmul.transformation;


/**
 * This interface describes an entity which manages transformation rules and
 * performs transformations.
 *
 * @author Kristian Kutin
 */
public interface TransformationFactory {

    /**
     * Adds a transformation rule.
     *
     * @param aRule
     *        a new transformation rule
     */
    void addTransformationRule(TransformationRule aRule);

    /**
     * Performs a transformation. The specified parameters provides all
     * necessary informations.
     *
     * @param someParameters
     *        all transformation parameters, including the object which is to be
     *        transformed
     *
     * @return the result of the transformation
     */
    Object transform(TransformationParameters someParameters);

}
