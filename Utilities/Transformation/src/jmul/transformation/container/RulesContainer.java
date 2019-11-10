/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package jmul.transformation.container;


import java.util.Collection;

import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationRule;


/**
 * This interface describes a container for transformation rules.
 *
 * @author Kristian Kutin
 */
public interface RulesContainer extends ContainerInformations {

    /**
     * Returns all transformation rules for the specified transformation
     * path.
     *
     * @param aTransformationPath
     *        a transformation path
     *
     * @return a container with transformation rules
     */
    Collection<TransformationRule> getRules(TransformationPath aTransformationPath);

    /**
     * Checks if the container contains transformation rules for the specified
     * transformation path.
     *
     * @param aTransformationPath
     *        a transformation path
     *
     * @return <code>true</code> if rules exist, else <code>false</code>
     */
    boolean existsPath(TransformationPath aTransformationPath);

}
