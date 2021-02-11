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


import java.util.List;

import jmul.math.formula.parser.tokens.Token;

import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationParametersImpl;
import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationResources;


/**
 * A helper class for transformation parameters.
 *
 * @author Kristian Kutin
 */
public final class TransformationHelper {

    /**
     * A transformation path.
     */
    private static final TransformationPath TRANSFORMATION_PATH;

    /*
     * The default constructor.
     */
    static {

        TRANSFORMATION_PATH = new TransformationPath("sequence", "tree");
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
     * @param aSequence
     *        a token sequence
     *
     * @return transformation parameters
     */
    public static TransformationParameters createTranformationParameters(List<Token> aSequence) {

        return new TransformationParametersImpl(TRANSFORMATION_PATH, aSequence);
    }

    /**
     * Creates a new transformation parameter which wraps the specified object.
     *
     * @param someInput
     *        an input string
     *
     * @return transformation parameters
     */
    public static TransformationParameters createTransformationParameters(String someInput) {

        return new TransformationParametersImpl(TRANSFORMATION_PATH, someInput);
    }

    /**
     * Extracts a wrapped transformation object.
     *
     * @param someParameters
     *        transformation parameters
     *
     * @return the object to be transformed
     */
    public static List<Token> object2TokenSequence(TransformationParameters someParameters) {

        return (List<Token>) someParameters.getObject();
    }

    /**
     * Extracts a wrapped transformation object.
     *
     * @param someParameters
     *        transformation parameters
     *
     * @return the object to be transformed
     */
    public static String object2String(TransformationParameters someParameters) {

        return (String) someParameters.getObject();
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

}
