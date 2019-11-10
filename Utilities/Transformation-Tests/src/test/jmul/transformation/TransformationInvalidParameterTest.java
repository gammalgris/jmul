/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package test.jmul.transformation;


import jmul.test.classification.ModuleTest;

import jmul.transformation.TransformationFactory;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationParametersImpl;
import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationResources;

import org.junit.Test;


/**
 * This class contains tests to check the processing of invalid transformation
 * parameters.
 *
 * @author Kristian Kutin
 */
@ModuleTest
public class TransformationInvalidParameterTest {

    /**
     * Tests the instantiation of transformation parameters with an invalid transformation path
     * and a <code>null</code> object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullObjectOnInvalidPath() {

        Object o = null;
        new TransformationParametersImpl(new TransformationPath("a", "b"), o);
    }

    /**
     * Tests the instantiation of transformation parameters with an invalid transformation path
     * and a non-<code>null</code> object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testObjectOnInvalidPath() {

        Object o = new Object();
        TransformationParameters parameters = new TransformationParametersImpl(new TransformationPath("a", "b"), o);
        TransformationFactory factory = TransformationResources.getTransformationFactory();
        factory.transform(parameters);
    }

}
