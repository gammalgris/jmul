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

package jmul.math.formula.parser.tokens;


/**
 * A utility class for handling token traits.
 *
 * @author Kristian Kutin
 */
public final class TokenTraitsHelper {

    /**
     * The default constructor.
     */
    private TokenTraitsHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Validates the specified parameter.
     *
     * @param someTraits
     *        an array of traits
     *
     * @return the specified parameter
     */
    public static TokenTrait[] validate(TokenTrait... someTraits) {

        if (someTraits == null) {

            String message = "No traits have been specified (null)!";
            throw new IllegalArgumentException(message);
        }

        for (TokenTrait trait : someTraits) {

            if (trait == null) {

                String message = "An invalid trait has been specified (null)!";
                throw new IllegalArgumentException(message);
            }
        }

        return someTraits;
    }

}
