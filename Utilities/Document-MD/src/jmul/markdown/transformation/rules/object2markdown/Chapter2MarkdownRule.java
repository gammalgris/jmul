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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.markdown.transformation.rules.object2markdown;


import jmul.document.markdown.content.chapter.Chapter;
import jmul.document.markdown.content.paragraph.Paragraph;

import jmul.markdown.transformation.rules.MarkdownHelper;

import static jmul.string.Constants.NEW_LINE;
import static jmul.string.Constants.SPACE;

import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationResources;
import jmul.transformation.TransformationRuleBase;


/**
 * A transformation rule to create a string representation of a document element.
 *
 * @author Kristian Kutin
 */
public class Chapter2MarkdownRule extends TransformationRuleBase {

    /**
     * Constructs a transformation rule.
     *
     * @param anOrigin
     *        a description of the transformation origin
     * @param aDestination
     *        a description of the transformation destination
     * @param aPriority
     *        a rule priority
     */
    public Chapter2MarkdownRule(String anOrigin, String aDestination, int aPriority) {

        super(anOrigin, aDestination, aPriority);
    }

    /**
     * The method determines if this rule can be applied to the specified
     * object.
     *
     * @param someParameters
     *        some transformation parameters, including the object which is to
     *        be transformed
     *
     * @return <code>true</code> if the rule is applicable, else
     *         <code>false</code>
     */
    @Override
    public boolean isApplicable(TransformationParameters someParameters) {

        Object object = someParameters.getObject();

        return (object != null) && (object instanceof Chapter);
    }

    /**
     * The method performs the actual transformation.
     *
     * @param someParameters
     *        some transformation parameters, including the object which is to
     *        be transformed
     *
     * @return the transformed object
     */
    @Override
    public Object transform(TransformationParameters someParameters) {

        Object object = someParameters.getObject();
        Chapter chapter = (Chapter) object;

        StringBuilder buffer = new StringBuilder();


        if (chapter.hasHeadline()) {

            buffer.append(MarkdownHelper.determineHeaderPrefix(chapter));
            buffer.append(SPACE);
            buffer.append(chapter.getHeadline());
        }


        for (int a = 0; a < chapter.paragraphs(); a++) {

            Paragraph paragraph = chapter.getParagraph(a);

            TransformationParameters newParameters = ParameterHelper.newTransformationParameters(paragraph);
            Object result = TransformationResources.getTransformationFactory().transform(newParameters);

            if (a > 0) {

                buffer.append(NEW_LINE);
            }

            buffer.append(result);
        }


        for (int a = 0; a < chapter.subchapters(); a++) {

            Chapter subchapter = chapter.getSubchapter(a);

            TransformationParameters newParameters = ParameterHelper.newTransformationParameters(subchapter);
            Object result = TransformationResources.getTransformationFactory().transform(newParameters);

            if (a > 0) {

                buffer.append(NEW_LINE);
            }

            buffer.append(result);
        }


        return buffer.toString();
    }

}
