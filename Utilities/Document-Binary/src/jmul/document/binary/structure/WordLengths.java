/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package jmul.document.binary.structure;


/**
 * This enumeration class defines several word lengths.
 *
 * @author Kristian Kutin
 */
public enum WordLengths {


    WORD_8_BIT(1),
    WORD_16_BIT(2),
    WORD_32_BIT(4),
    WORD_64_BIT(8), ;


    /**
     * The correspondig buffer size for byte arrays.
     */
    private final int bufferSize;

    /**
     * Creates a new enumeration element according to the specified
     * parameters.
     *
     * @param aBufferSize
     */
    private WordLengths(int aBufferSize) {

        bufferSize = aBufferSize;
    }

    /**
     * Returns a buffer size for byte arrays which corresponds to this
     * word length.
     *
     * @return a buffer size
     */
    public int getBufferSize() {

        return bufferSize;
    }

}
