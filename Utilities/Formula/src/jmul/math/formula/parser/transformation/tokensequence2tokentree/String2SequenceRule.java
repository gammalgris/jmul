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

package jmul.math.formula.parser.transformation.tokensequence2tokentree;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import jmul.math.formula.parser.ParserHelper;
import jmul.math.formula.parser.patterns.TokenPattern;
import jmul.math.formula.parser.patterns.TokenPatternHelper;
import jmul.math.formula.parser.tokens.Token;
import jmul.math.graph.Node;
import jmul.math.graph.Tree;
import jmul.math.graph.TreeImpl;

import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationRuleBase;


/**
 * This rule translates a string into a token tree.
 *
 * @author Kristian Kutin
 */
public class String2SequenceRule extends TransformationRuleBase {

    /**
     * All patterns for identifying tokens.
     */
    private static final Map<TokenPattern, Pattern> PATTERNS;

    /*
     * The static initializer.
     */
    static {

        PATTERNS = Collections.unmodifiableMap(TokenPatternHelper.toMap());
    }

    /**
     * Creates a new transformation rule according to the specified parameters.
     *
     * @param anOrigin
     * @param aDestination
     * @param aPriority
     */
    public String2SequenceRule(String anOrigin, String aDestination, int aPriority) {

        super(anOrigin, aDestination, aPriority);
    }

    /**
     * Checks if this rule is applicable on the specified parameter.
     *
     * @param someParameters
     *
     * @return <code>true</code> if this rule is applicable, else <code>false</code>
     */
    @Override
    public boolean isApplicable(TransformationParameters someParameters) {

        Object object = someParameters.getObject();

        return object instanceof String;
    }

    /**
     * The actual transformation of the specified parameters.
     *
     * @param someParameters
     *
     * @return a token tree
     */
    @Override
    public Object transform(TransformationParameters someParameters) {

        String input = TransformationHelper.object2String(someParameters);

        String normalizedInput = ParserHelper.trimString(input);
        List<Token> tokenSequence = ParserHelper.splitString(PATTERNS, normalizedInput);

        TransformationParameters newParameters = TransformationHelper.createTranformationParameters(tokenSequence);

        Node<Token> result = (Node<Token>) TransformationHelper.transform(newParameters);
        Tree<Node<Token>> tree = new TreeImpl<Node<Token>>(result);

        return tree;
    }

}
