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

package jmul.reflection.classes.signature;


import java.util.List;

import jmul.reflection.classes.ClassDefinition;


/**
 * This interface describes an entity which checks if two signatures are
 * equivalent (see strategy pattern).
 *
 * @author Kristian Kutin
 */
public interface SignatureComparator {

    /**
     * The method checks if the specified signatures are equivalent.
     *
     * @param expectedSignature
     *        a parameter signature
     * @param foundSignature
     *        a parameter signature
     *
     * @return <code>true</code>, if both signatures are considered equal,
     *         else <code>false</code>
     */
    boolean compareSignatures(List<ClassDefinition> expectedSignature, List<ClassDefinition> foundSignature);

}
