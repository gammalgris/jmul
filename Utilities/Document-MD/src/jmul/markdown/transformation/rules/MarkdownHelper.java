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

package jmul.markdown.transformation.rules;


import jmul.document.markdown.content.chapter.Chapter;
import jmul.document.markdown.content.citation.Citation;
import jmul.document.markdown.content.node.ContentNode;


/**
 * This class contains utility functions for markdown documents.
 *
 * @author Kristian Kutin
 */
public final class MarkdownHelper {

    /**
     * A character used to identify header lines.
     */
    private static final String HEADER_CHARACTER = "#";

    /**
     * A character used to identify citations.
     */
    private static final String CITATION_CHARACTER = ">";

    /**
     * The default constructor.
     */
    private MarkdownHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Determines the header prefix for the specified chapter.
     *
     * @param aChapter
     *        a chapter
     *
     * @return a prefix
     */
    public static String determineHeaderPrefix(Chapter aChapter) {

        ContentNode aNode = (ContentNode) aChapter;

        return determineHeaderPrefix(aNode);
    }

    /**
     * Determines the header prefix for the specified content node.
     *
     * @param aNode
     *        a content node
     *
     * @return a prefix
     */
    private static String determineHeaderPrefix(ContentNode aNode) {

        StringBuffer buffer = new StringBuffer();

        if (aNode == null) {

            return buffer.toString();
        }

        ContentNode aParent = aNode.getParent();
        buffer.append(determineHeaderPrefix(aParent));
        buffer.append(HEADER_CHARACTER);

        return buffer.toString();
    }

    /**
     * Determines a prefix for the specified citation.
     *
     * @param aCitation
     *        a citation
     *
     * @return a prefix
     */
    public static String determineCitationPrefix(Citation aCitation) {

        ContentNode aNode = (ContentNode) aCitation;

        return determineCitationPrefix(aNode);
    }

    /**
     * Determines the citation prefix for the specified content node.
     *
     * @param aNode
     *        a content node
     *
     * @return a prefix
     */
    private static String determineCitationPrefix(ContentNode aNode) {

        StringBuffer buffer = new StringBuffer();

        if (aNode == null) {

            return buffer.toString();
        }

        ContentNode aParent = aNode.getParent();
        buffer.append(determineCitationPrefix(aParent));
        buffer.append(CITATION_CHARACTER);

        return buffer.toString();
    }

    /* *
     * Determines the numbering for the specified chapter.
     *
     * @param aChapter
     *
     * @return
     * /
    private static String getChapterNumbering(Chapter aChapter) {

        StringBuffer buffer = new StringBuffer();


        if (aChapter == null) {

            return buffer.toString();
        }

        buffer.append(POINT);


        ChapterNode chapterNode = (ChapterNode) aChapter;

        int chapter = 1;
        while (true) {

            ChapterNode predecessor = chapterNode.getPredecessor();

            if (predecessor == null) {

                break;
            }

            chapter++;
        }

        buffer.append(chapter);


        ContentNode contentNode = (ContentNode) aChapter;
        ContentNode parentNode = contentNode.getParent();

        buffer.insert(0, getChapterNumbering((Chapter) parentNode));


        return buffer.toString();
    }*/

}
