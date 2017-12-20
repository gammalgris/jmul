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

package jmul.document.markdown.content.chapter;


import jmul.document.markdown.content.paragraph.Paragraph;
import jmul.document.markdown.content.text.Headline;


/**
 * This interface describes a chapter within the markdown document.
 *
 * @author Kristian Kutin
 */
public interface Chapter {

    /**
     * Adds the headline for this chapter.
     *
     * @param aHeadline
     *        the chapter's new headline
     */
    void setHeadline(Headline aHeadline);

    /**
     * Removes the headline for this chapter.
     */
    void removeHeadline();

    /**
     * Returns the current subchapter count.
     *
     * @return a subchapter count
     */
    int subchapters();

    /**
     * Adds (i.e. appends) the specified subchapter to this chapter.
     *
     * @param aChapter
     *        the subchapter which is to be added
     */
    void addSubchapter(Chapter aChapter);

    /**
     * Returns the subchapter at the specified index.
     *
     * @param anIndex
     *        the index of the requested subchapter
     *
     * @return a subchapter
     */
    Chapter getSubchapter(int anIndex);

    /**
     * Removes the subchapter at the specified index.
     *
     * @param anIndex
     *        the index of the subchapter which is to be removed
     */
    void removeSubchapter(int anIndex);

    /**
     * Returns the current paragraph count.
     *
     * @return a paragraph count
     */
    int paragraphs();

    /**
     * Adds (i.e. appends) the specified paragraph to this chapter.
     *
     * @param aParagraph
     *        the paragraph which is to be added
     */
    void addParagraph(Paragraph aParagraph);

    /**
     * Returns the paragraph at the specified index.
     *
     * @param anIndex
     *        the index of the requested paragraph
     *
     * @return a paragraph
     */
    Paragraph getParagraph(int anIndex);

    /**
     * Removes the paragraph at the specified index.
     *
     * @param anIndex
     *        the index of the pragraph which is to be removed
     */
    void removeParagraph(int anIndex);

}
