/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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

package jmul.genetic;


/**
 * This interface describes an entity which calculates a score for objects.
 *
 * @param <T>
 *        the allowed object type
 */
public interface Evaluator<T> {

    /**
     * Examines the specified object and calculates a score. The score is a positive integer
     * including 0. The higher the number the better the result. An implementation should specify
     * the actual range of the score and the meaning of individual numbers.
     *
     * @param anObject
     *        the object which is to be evaluated
     *
     * @return an evaluation score
     */
    int calculateScore(T anObject);

}
