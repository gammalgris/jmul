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

package jmul.math.formula.parser.transformation.tokentree2componenttree;


import java.util.List;

import jmul.math.formula.parser.components.Variable;
import jmul.math.formula.parser.tokens.Token;
import jmul.math.formula.parser.tokens.TokenTraits;
import jmul.math.graph.Node;

import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationRuleBase;


/**
 * This rule translates a token node into a component.
 *
 * @author Kristian Kutin
 */
public class Token2VariableRule extends TransformationRuleBase {

    /**
     * Creates a new transformation rule according to the specified parameters.
     *
     * @param anOrigin
     * @param aDestination
     * @param aPriority
     */
    public Token2VariableRule(String anOrigin, String aDestination, int aPriority) {

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

        if (object instanceof Node) {

            Node<Token> node = (Node<Token>) object;
            Token token = node.getContent();

            return token.hasTrait(TokenTraits.VARIABLE);
        }

        return false;
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

        Node<Token> node = TransformationHelper.object2Node(someParameters);
        Token token = node.getContent();

        Variable variable = new Variable(token);
        List<Variable> variableReferenceList = TransformationHelper.extractVariableReferenceList(someParameters);
        variableReferenceList.add(variable);

        return variable;
    }

}
