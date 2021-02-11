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

package jmul.math.formula.parser.transformation.tokentree2componenttree;


import java.util.List;

import jmul.math.formula.parser.components.Variable;
import jmul.math.formula.parser.tokens.Token;
import jmul.math.graph.Node;
import jmul.math.graph.Tree;

import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationParametersImpl;
import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationResources;


/**
 * A helper class for transformation parameters.
 *
 * @author Kristian Kutin
 */
public class TransformationHelper {

    /**
     * A transformation prerequisite
     */
    public static final String VARIABLE_REFERENCE_LIST;

    /**
     * A transformation path.
     */
    private static final TransformationPath TRANSFORMATION_PATH;

    /**
     * A list of prerequisites which are required by the transformation.
     */
    private static final String[] PREREQUISITE_NAMES;

    /*
     * The default constructor.
     */
    static {

        TRANSFORMATION_PATH = new TransformationPath("token", "component");

        VARIABLE_REFERENCE_LIST = "variable reference list";

        PREREQUISITE_NAMES = new String[] { VARIABLE_REFERENCE_LIST };
    }

    /**
     * The default constructor.
     */
    private TransformationHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new transformation parameter which wraps the specified object.
     *
     * @param aTree
     *        a token tree
     *
     * @return transformation parameters
     */
    public static TransformationParameters createTransformationParameters(Tree<Node<Token>> aTree) {

        return new TransformationParametersImpl(TRANSFORMATION_PATH, aTree);
    }

    /**
     * Creates a new transformation parameter which wraps the specified object.
     *
     * @param aNode
     *        a token node
     *
     * @return transformation parameters
     */
    public static TransformationParameters createTransformationParameters(Node<Token> aNode) {

        return new TransformationParametersImpl(TRANSFORMATION_PATH, aNode);
    }

    /**
     * Extracts a wrapped transformation object.
     *
     * @param someParameters
     *        transformation parameters
     *
     * @return the object to be transformed
     */
    public static Tree<Node<Token>> object2Tree(TransformationParameters someParameters) {

        return (Tree<Node<Token>>) someParameters.getObject();
    }

    /**
     * Extracts a wrapped transformation object.
     *
     * @param someParameters
     *        transformation parameters
     *
     * @return the object to be transformed
     */
    public static Node<Token> object2Node(TransformationParameters someParameters) {

        return (Node<Token>) someParameters.getObject();
    }

    /**
     * Calls the trnasformation framework. Calling the transformation framwork from within a
     * transformation rule will result in a recursion.
     *
     * @param someParameters
     *        transformation parameters
     *
     * @return a transformation result
     */
    public static Object transform(TransformationParameters someParameters) {

        return TransformationResources.getTransformationFactory().transform(someParameters);
    }

    /**
     * Transfers all transformation prerequisites from the specified transformation parametrs <code>a</code>
     * to the specified transformation paramters <code>b</code>.
     *
     * @param a
     *        some transformation parameters
     * @param b
     *        some transformation parameters
     */
    public static void transferPrerequisites(TransformationParameters a, TransformationParameters b) {

        for (String prerequisiteName : PREREQUISITE_NAMES) {

            Object prerequisite = a.getPrerequisite(prerequisiteName);
            b.addPrerequisite(prerequisiteName, prerequisite);
        }
    }

    /**
     * Extracts the variable reference list from the specified transformation parameters.
     *
     * @param someParameters
     *        some transformation parameters
     *
     * @return a variable reference list
     */
    public static List<Variable> extractVariableReferenceList(TransformationParameters someParameters) {

        return (List<Variable>) someParameters.getPrerequisite(VARIABLE_REFERENCE_LIST);
    }

}
