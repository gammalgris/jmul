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


import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * An implementation of a token.
 *
 * @author Kristian Kutin
 */
public class TokenImpl implements Token {

    /**
     * Contains all traits associated with this token.
     */
    private final List<TokenTrait> traits;

    /**
     * The actual token.
     */
    private final String string;

    /**
     * Creates a new instane according to the specified parameters.
     *
     * @param aCharSequence
     *        a character sequence representing the token
     */
    public TokenImpl(CharSequence aCharSequence) {

        this(aCharSequence.toString(), new TokenTrait[] { });
    }

    /**
     * Creates a new instane according to the specified parameters.
     *
     * @param aString
     *        a string representing the token
     */
    public TokenImpl(String aString) {

        this(aString, new TokenTrait[] { });
    }

    /**
     * Creates a new instane according to the specified parameters.
     *
     * @param aCharSequence
     *        a character sequence representing the token
     * @param someTraits
     *        traits of the token
     */
    public TokenImpl(CharSequence aCharSequence, TokenTrait... someTraits) {

        this(aCharSequence.toString(), someTraits);
    }

    /**
     * Creates a new instane according to the specified parameters.
     *
     * @param aString
     *        a string representing the token
     * @param someTraits
     *        traits of the token
     */
    public TokenImpl(String aString, TokenTrait... someTraits) {

        super();

        traits = Collections.unmodifiableList(Arrays.asList(TokenTraitsHelper.validate(someTraits)));
        string = aString;
    }

    /**
     * Returns the length of this token.
     *
     * @return a length
     */
    @Override
    public int length() {

        return string.length();
    }

    /**
     * Returns the character at the specified index.
     *
     * @param index
     *        an index, i.e. a number equals to or greater than zero and
     *        lesser than the token length
     *
     * @return a character
     */
    @Override
    public char charAt(int index) {

        return string.charAt(index);
    }

    /**
     * Returns a substring according to the specified parameters.
     *
     * @param start
     *        a start index, i.e. a number equals to or greater than zero and
     *        lesser than the end index
     * @param end
     *        an end index, i.e. a number greater than the start index and lesser
     *        than the token length
     *
     * @return a substring
     */
    @Override
    public CharSequence subSequence(int start, int end) {

        return string.subSequence(start, end);
    }

    /**
     * Returns all traits which are attributed to the entity.
     *
     * @return a list of traits
     */
    @Override
    public List<TokenTrait> getTraits() {

        return traits;
    }

    /**
     * Checks if the entity has the specified trait.
     *
     * @param aTrait
     *        a trait
     *
     * @return <code>true</code> if the entity has the specifiedtrait, else <code>false</code>
     */
    @Override
    public boolean hasTrait(TokenTrait aTrait) {

        return traits.contains(aTrait);
    }

    /**
     * Returns a string representation of this token.
     *
     * @return a string represetnation
     */
    @Override
    public String toString() {

        return string;
    }

}
