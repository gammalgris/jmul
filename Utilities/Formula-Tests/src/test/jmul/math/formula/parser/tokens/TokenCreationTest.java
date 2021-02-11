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

package test.jmul.math.formula.parser.tokens;


import java.util.List;

import jmul.math.formula.parser.tokens.Token;
import jmul.math.formula.parser.tokens.TokenImpl;
import jmul.math.formula.parser.tokens.TokenTrait;
import jmul.math.formula.parser.tokens.TokenTraits;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This class contains tests to create (i.e. instantiate and initialize) tokens.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class TokenCreationTest {

    /**
     * Tests creating a token without traits.
     */
    @Test
    public void testCreation01() {

        String value = "Hello World!";

        Token token = new TokenImpl(value);
        assertEquals(value, token.toString());

        List<TokenTrait> traits = token.getTraits();
        assertNotNull(traits);
        assertEquals(0, traits.size());
    }

    /**
     * Tests creating a token with a trait.
     */
    @Test
    public void testCreation02() {

        String value = "Hello World!";
        TokenTrait trait = TokenTraits.VARIABLE;

        Token token = new TokenImpl(value, trait);
        assertEquals(value, token.toString());

        List<TokenTrait> traits = token.getTraits();
        assertNotNull(traits);
        assertEquals(1, traits.size());
        assertTrue(token.hasTrait(trait));
    }

    /**
     * Tests creating a token without a trait (i.e. <code>null</code>).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreation03() {

        String value = "Hello World!";
        TokenTrait trait = null;

        new TokenImpl(value, trait);
    }

}
