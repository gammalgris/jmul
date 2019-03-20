/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package jmul.math.notation;


/**
 * This enumeration class contains entries for various numerical precisions.<br>
 * <br>
 * <i>Note:<br>
 * The enumeration currently contains only basic entries. If more precision types
 * are needed then these should be added.</i>
 *
 * @author Kristian Kutin
 */
public enum PrecisionTypes {

    /*
     * Corresponds with the primitive type int.
     */
    SIGNED_INTEGER_32_BIT,

    /*
     * Corresponds with the primitive type long.
     */
    SIGNED_INTEGER_64_BIT,

    /*
     * Corresponds with the primitive type float.
     */
    SIGNED_FLOATING_POINT_32_BIT,

    /*
     * Corresponds with the primitive type double.
     */
    SIGNED_FLOATING_POINT_64_BIT;

}
