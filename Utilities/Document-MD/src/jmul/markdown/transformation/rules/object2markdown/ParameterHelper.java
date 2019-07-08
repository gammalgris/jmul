/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package jmul.markdown.transformation.rules.object2markdown;


import jmul.document.markdown.content.chapter.Chapter;
import jmul.document.markdown.content.paragraph.Paragraph;

import static jmul.markdown.transformation.Constants.OBJECT_TO_MARKDOWN;

import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationParametersImpl;


/**
 * The class contains utility functions to build transformation parameters and
 * extract informations from transformation parameters.
 *
 * @author Kristian Kutin
 */
public final class ParameterHelper {

    /**
     * The default constructor.
     */
    private ParameterHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates new transformation parameters according to the specified parameters.
     *
     * @param aChapter
     *        a chapter
     *
     * @return transformation parameters
     */
    public static TransformationParameters newTransformationParameters(Chapter aChapter) {

        TransformationParameters newParameters = new TransformationParametersImpl(OBJECT_TO_MARKDOWN, aChapter);

        return newParameters;
    }

    /**
     * Creates new transformation parameters according to the specified parameters.
     *
     * @param aParagraph
     *        a paragraph
     *
     * @return transformation parameters
     */
    public static TransformationParameters newTransformationParameters(Paragraph aParagraph) {

        TransformationParameters newParameters = new TransformationParametersImpl(OBJECT_TO_MARKDOWN, aParagraph);

        return newParameters;
    }

}
