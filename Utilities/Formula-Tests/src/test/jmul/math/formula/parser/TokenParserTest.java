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

package test.jmul.math.formula.parser;


import jmul.math.formula.parser.FormulaParsingException;
import jmul.math.formula.parser.TokenParser;
import jmul.math.formula.parser.TokenParserImpl;
import jmul.math.formula.parser.tokens.Token;
import jmul.math.formula.parser.tokens.TokenTraits;
import jmul.math.graph.Node;
import jmul.math.graph.Tree;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * This class cotnains tests to translate a string into tokens.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class TokenParserTest {

    /**
     * A parser.
     */
    private TokenParser tokenParser;

    /**
     * Prepares everything for a test.
     */
    @Before
    public void setUp() {

        tokenParser = new TokenParserImpl();
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        tokenParser = null;
    }

    /**
     * Tests parsing an empty string.
     */
    @Test
    public void testParsing001() {

        String input = "";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);
        assertNull(tree.getRoot());
    }

    /**
     * Tests parsing a single token sequence (i.e. a number).
     */
    @Test
    public void testParsing002() {

        String input = "1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERAND));
        assertTrue(token.hasTrait(TokenTraits.NUMBER));
        assertEquals(input, token.toString());
    }

    /**
     * Tests parsing a single token sequence (i.e. a variable).
     */
    @Test
    public void testParsing003() {

        String input = "a";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERAND));
        assertTrue(token.hasTrait(TokenTraits.VARIABLE));
        assertEquals(input, token.toString());
    }

    /**
     * Tests parsing a single token sequence (i.e. a plus symbol).
     */
    @Test(expected = FormulaParsingException.class)
    public void testParsing004() {

        String input = "+";

        tokenParser.parseString(input);
    }

    /**
     * Tests parsing a single token sequence (i.e. a minus symbol).
     */
    @Test(expected = FormulaParsingException.class)
    public void testParsing005() {

        String input = "-";

        tokenParser.parseString(input);
    }

    /**
     * Tests parsing a single token sequence (i.e. a division symbol).
     */
    @Test(expected = FormulaParsingException.class)
    public void testParsing006() {

        String input = "/";

        tokenParser.parseString(input);
    }

    /**
     * Tests parsing a single token sequence (i.e. a modulo symbol).
     */
    @Test(expected = FormulaParsingException.class)
    public void testParsing007() {

        String input = "%";

        tokenParser.parseString(input);
    }

    /**
     * Tests parsing a single token sequence (i.e. a multiplication symbol).
     */
    @Test(expected = FormulaParsingException.class)
    public void testParsing008() {

        String input = "*";

        tokenParser.parseString(input);
    }

    /**
     * Tests parsing a single token sequence (i.e. a dice operator symbol).
     */
    @Test(expected = FormulaParsingException.class)
    public void testParsing009() {

        String input = "d";

        tokenParser.parseString(input);
    }

    /**
     * Tests parsing a single token sequence (i.e. opening parenthesis).
     */
    @Test(expected = FormulaParsingException.class)
    public void testParsing010() {

        String input = "(";

        tokenParser.parseString(input);
    }

    /**
     * Tests parsing a single token sequence (i.e. closing parenthesis).
     */
    @Test(expected = FormulaParsingException.class)
    public void testParsing011() {

        String input = ")";

        tokenParser.parseString(input);
    }

    /**
     * Tests parsing opening and closing parenthesis.
     */
    @Test
    public void testParsing012() {

        String input = "()";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);
        assertNull(tree.getRoot());
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing013() {

        String input = "1+1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.PLUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing014() {

        String input = "1+a";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.PLUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing015() {

        String input = "a+1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.PLUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.VARIABLE));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing016() {

        String input = "1-1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MINUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing017() {

        String input = "1-a";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MINUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing018() {

        String input = "a-1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MINUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.VARIABLE));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing019() {

        String input = "1*1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MULTIPLICATION));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing020() {

        String input = "1*a";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MULTIPLICATION));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing021() {

        String input = "a*1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MULTIPLICATION));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.VARIABLE));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing022() {

        String input = "1/1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.DIVISION));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing023() {

        String input = "1/a";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.DIVISION));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing024() {

        String input = "a/1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.DIVISION));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.VARIABLE));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing025() {

        String input = "1%1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MODULO));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing026() {

        String input = "1%a";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MODULO));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing027() {

        String input = "a%1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MODULO));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.VARIABLE));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a three token sequence (i.e. operand, operator and operand).
     */
    @Test
    public void testParsing028() {

        String input = "1d1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.DICE_OPERATOR));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a two token sequence (i.e. operand and operand).
     */
    @Test(expected = FormulaParsingException.class)
    public void testParsing029() {

        String input = "1a";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.DICE_OPERATOR));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a single token sequence (i.e. operand).
     */
    @Test
    public void testParsing030() {

        String input = "a1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERAND));
        assertTrue(token.hasTrait(TokenTraits.VARIABLE));

        assertEquals(0, root.children());
    }

    /**
     * Tests parsing a two token sequence (i.e. operator and operand).
     */
    @Test
    public void testParsing031() {

        String input = "+1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.PLUS));

        assertEquals(1, root.children());

        Node<Token> rightNode = root.getChild(0);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a two token sequence (i.e. operator and operand).
     */
    @Test
    public void testParsing032() {

        String input = "-1";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MINUS));

        assertEquals(1, root.children());

        Node<Token> rightNode = root.getChild(0);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a two token sequence (i.e. operator and operand).
     */
    @Test
    public void testParsing033() {

        String input = "+a";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.PLUS));

        assertEquals(1, root.children());

        Node<Token> rightNode = root.getChild(0);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a two token sequence (i.e. operator and operand).
     */
    @Test
    public void testParsing034() {

        String input = "-a";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MINUS));

        assertEquals(1, root.children());

        Node<Token> rightNode = root.getChild(0);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing035() {

        String input = "(1+1)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.PLUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing036() {

        String input = "(1+a)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.PLUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing037() {

        String input = "(a+1)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.PLUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.VARIABLE));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing038() {

        String input = "(1-1)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MINUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing039() {

        String input = "(1-a)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MINUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing040() {

        String input = "(a-1)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MINUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.VARIABLE));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing041() {

        String input = "(1*1)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MULTIPLICATION));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing042() {

        String input = "(1*a)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MULTIPLICATION));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing043() {

        String input = "(a*1)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MULTIPLICATION));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.VARIABLE));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing044() {

        String input = "(1/1)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.DIVISION));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing045() {

        String input = "(1/a)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.DIVISION));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing046() {

        String input = "(a/1)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.DIVISION));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.VARIABLE));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing047() {

        String input = "(1%1)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MODULO));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing048() {

        String input = "(1%a)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MODULO));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.VARIABLE));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing049() {

        String input = "(a%1)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MODULO));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.VARIABLE));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a five token sequence (i.e. opening parenthesis, operand,
     * operator, operand and closing parenthesis).
     */
    @Test
    public void testParsing050() {

        String input = "(1d1)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.DICE_OPERATOR));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));
    }

    /**
     * Tests parsing a five token sequence (i.e. operand, operator, operand, operator
     * and operand).
     */
    @Test
    public void testParsing051() {

        String input = "1+2*3";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.PLUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, leftNode.children());

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERATOR));
        assertTrue(rightToken.hasTrait(TokenTraits.MULTIPLICATION));

        assertEquals(2, rightNode.children());

        Node<Token> leftRightNode = rightNode.getChild(0);
        Token leftRightToken = leftRightNode.getContent();
        assertNotNull(leftRightToken);
        assertTrue(leftRightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftRightToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, leftRightNode.children());

        Node<Token> rightRightNode = rightNode.getChild(1);
        Token rightRightToken = rightRightNode.getContent();
        assertNotNull(rightRightToken);
        assertTrue(rightRightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightRightToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, rightRightNode.children());
    }

    /**
     * Tests parsing a five token sequence (i.e. operand, operator, operand, operator
     * and operand).
     */
    @Test
    public void testParsing052() {

        String input = "1/2-3";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.MINUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERATOR));
        assertTrue(leftToken.hasTrait(TokenTraits.DIVISION));

        assertEquals(2, leftNode.children());

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, rightNode.children());

        Node<Token> leftLeftNode = leftNode.getChild(0);
        Token leftLeftToken = leftLeftNode.getContent();
        assertNotNull(leftLeftToken);
        assertTrue(leftLeftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftLeftToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, leftLeftNode.children());

        Node<Token> rightLeftNode = leftNode.getChild(1);
        Token rightLeftToken = rightLeftNode.getContent();
        assertNotNull(rightLeftToken);
        assertTrue(rightLeftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightLeftToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, rightLeftNode.children());
    }

    /**
     * Tests parsing a five token sequence (i.e. operand, operator, operand, operator
     * and operand).
     */
    @Test
    public void testParsing053() {

        String input = "1d2+3";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.PLUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERATOR));
        assertTrue(leftToken.hasTrait(TokenTraits.DICE_OPERATOR));

        assertEquals(2, leftNode.children());

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, rightNode.children());

        Node<Token> leftLeftNode = leftNode.getChild(0);
        Token leftLeftToken = leftLeftNode.getContent();
        assertNotNull(leftLeftToken);
        assertTrue(leftLeftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftLeftToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, leftLeftNode.children());

        Node<Token> rightLeftNode = leftNode.getChild(1);
        Token rightLeftToken = rightLeftNode.getContent();
        assertNotNull(rightLeftToken);
        assertTrue(rightLeftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightLeftToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, rightLeftNode.children());
    }

    /**
     * Tests parsing a five token sequence (i.e. operand, operator, operand, operator
     * and operand).
     */
    @Test
    public void testParsing054() {

        String input = "(1+2)+(3+3)";

        Tree<Node<Token>> tree = tokenParser.parseString(input);
        assertNotNull("No tree has been returned (null)!", tree);

        Node<Token> root = tree.getRoot();
        assertNotNull(root);

        Token token = root.getContent();
        assertNotNull(token);
        assertTrue(token.hasTrait(TokenTraits.OPERATOR));
        assertTrue(token.hasTrait(TokenTraits.PLUS));

        assertEquals(2, root.children());

        Node<Token> leftNode = root.getChild(0);
        Token leftToken = leftNode.getContent();
        assertNotNull(leftToken);
        assertTrue(leftToken.hasTrait(TokenTraits.OPERATOR));
        assertTrue(leftToken.hasTrait(TokenTraits.PLUS));

        assertEquals(2, leftNode.children());

        Node<Token> rightNode = root.getChild(1);
        Token rightToken = rightNode.getContent();
        assertNotNull(rightToken);
        assertTrue(rightToken.hasTrait(TokenTraits.OPERATOR));
        assertTrue(rightToken.hasTrait(TokenTraits.PLUS));

        assertEquals(2, rightNode.children());

        Node<Token> leftLeftNode = leftNode.getChild(0);
        Token leftLeftToken = leftLeftNode.getContent();
        assertNotNull(leftLeftToken);
        assertTrue(leftLeftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftLeftToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, leftLeftNode.children());

        Node<Token> rightLeftNode = leftNode.getChild(1);
        Token rightLeftToken = rightLeftNode.getContent();
        assertNotNull(rightLeftToken);
        assertTrue(rightLeftToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightLeftToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, rightLeftNode.children());

        Node<Token> leftRightNode = rightNode.getChild(0);
        Token leftRightToken = leftRightNode.getContent();
        assertNotNull(leftRightToken);
        assertTrue(leftRightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(leftRightToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, leftRightNode.children());

        Node<Token> rightRightNode = rightNode.getChild(1);
        Token rightRightToken = rightRightNode.getContent();
        assertNotNull(rightRightToken);
        assertTrue(rightRightToken.hasTrait(TokenTraits.OPERAND));
        assertTrue(rightRightToken.hasTrait(TokenTraits.NUMBER));

        assertEquals(0, rightRightNode.children());
    }

}
