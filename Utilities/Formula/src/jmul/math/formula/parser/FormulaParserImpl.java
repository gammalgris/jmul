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


import java.util.ArrayList;
import java.util.List;

import jmul.math.formula.Formula;
import jmul.math.formula.FormulaImpl;
import jmul.math.formula.VariableManager;
import jmul.math.formula.VariableManagerImpl;
import jmul.math.formula.parser.components.Component;
import jmul.math.formula.parser.components.Variable;
import jmul.math.formula.parser.tokens.Token;
import jmul.math.formula.parser.transformation.tokentree2componenttree.TransformationHelper;
import jmul.math.graph.Node;
import jmul.math.graph.Tree;

import jmul.transformation.TransformationParameters;


/**
 * An implementation of a formula parser.
 *
 * @author Kristian Kutin
 */
public class FormulaParserImpl implements FormulaParser {

    /**
     * A token parser.
     */
    private final TokenParser tokenParser;

    /**
     * The default constructor.
     */
    public FormulaParserImpl() {

        super();

        tokenParser = new TokenParserImpl();
    }

    /**
     * The method will parse a string and return a formula.
     *
     * @param aString
     *        a string to be parsed
     *
     * @return a formula
     */
    @Override
    public Formula parseString(String aString) {

        Tree<Node<Token>> tokenTree = tokenParser.parseString(aString);

        List<Variable> variableReferenceList = new ArrayList<>();
        TransformationParameters parameters = TransformationHelper.createTransformationParameters(tokenTree);
        parameters.addPrerequisite(TransformationHelper.VARIABLE_REFERENCE_LIST, variableReferenceList);

        Tree<Component> componentTree = (Tree<Component>) TransformationHelper.transform(parameters);
        VariableManager variableManager = new VariableManagerImpl(variableReferenceList);

        Formula formula = new FormulaImpl(aString, componentTree, variableManager);

        return formula;
    }

}
