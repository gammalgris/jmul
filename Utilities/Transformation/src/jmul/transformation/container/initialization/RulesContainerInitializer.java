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

package jmul.transformation.container.initialization;

import jmul.transformation.container.RulesContainer;


/**
 * This interface describes an entity that initializes a container
 * for transformation rules. The exact steps for a successful
 * initialization are specified within an implementation.
 *
 * @author Kristian Kutin
 */
public interface RulesContainerInitializer {

    /**
     * Initializs a container for transformation rules.
     *
     * @return a container for transformation rules
     */
    RulesContainer newContainer();

}
