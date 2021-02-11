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

package jmul.math.formula.parser;


import jmul.math.formula.parser.tokens.Token;
import jmul.math.formula.parser.transformation.tokensequence2tokentree.TransformationHelper;
import jmul.math.graph.Node;
import jmul.math.graph.Tree;

import jmul.transformation.TransformationParameters;


/**
 * An implementation of a token parser.
 *
 * @author Kristian Kutin
 */
public class TokenParserImpl implements TokenParser {

    /**
     * The default constructor.
     */
    public TokenParserImpl() {

        super();
    }

    /**
     * The method will parse a string and return a token tree.
     *
     * @param aString
     *        a string to be parsed
     *
     * @return a token tree
     */
    @Override
    public Tree<Node<Token>> parseString(String aString) {

        TransformationParameters parameters = TransformationHelper.createTransformationParameters(aString);
        Tree<Node<Token>> tree = (Tree<Node<Token>>) TransformationHelper.transform(parameters);

        return tree;
    }

}
