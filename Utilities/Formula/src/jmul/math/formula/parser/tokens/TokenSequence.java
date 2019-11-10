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

package jmul.math.formula.parser.tokens;


import java.util.ArrayList;
import java.util.List;


/**
 * A helper class for better readability.
 *
 * @author Kristian Kutin
 */
public class TokenSequence extends ArrayList<Token> implements List<Token> {

    /**
     * The default constructor.
     */
    public TokenSequence() {

        super();
    }

    /**
     * The method checks whether a sequence of tokens is ambigous or not.
     *
     * @return true, if the sequence is ambigous, else false
     */
    public boolean containsAmbigousToken() {

        for (Token token : this) {

            if (token.isAmbigous()) {

                return true;
            }
        }

        return false;
    }

    /**
     * The method checks whether a sequence of tokens contains undefined tokens
     * or not.
     *
     * @return true, if the sequence contains undefined tokens, else false
     */
    public boolean containsUndefinedToken() {

        for (Token token : this) {

            if (token.isUndefined()) {
                return true;
            }
        }

        return false;
    }

    /**
     * The method will split this sequence into two parts. One part left of the
     * token with the given index and one part right of the token with the given
     * index.
     *
     * @param anIndex
     *        an index
     *
     * @return two sequences without the dividing token
     */
    public TokenSequence[] splitSequence(int anIndex) {

        TokenSequence leftSequence = new TokenSequence();
        TokenSequence rightSequence = new TokenSequence();

        int length = size();
        if ((anIndex < 0) || (anIndex >= length)) {

            throw new IndexOutOfBoundsException();
        }

        for (int a = 0; a < anIndex; a++) {

            Token token = get(a);
            leftSequence.add(token);
        }

        for (int a = anIndex + 1; a < length; a++) {

            Token token = get(a);
            rightSequence.add(token);
        }


        return new TokenSequence[] { leftSequence, rightSequence };
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("[");
        boolean first = true;

        for (Token token : this) {

            if (first) {

                first = !first;

            } else {

                buffer.append(",");
            }

            buffer.append("'");
            buffer.append(token);
            buffer.append("'");
        }

        buffer.append("]");

        return buffer.toString();
    }

}
