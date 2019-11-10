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

package jmul.document.markdown.content.chapter;


import jmul.document.markdown.content.paragraph.Paragraph;


/**
 * This interface describes a chapter within the markdown document.
 *
 * @author Kristian Kutin
 */
public interface Chapter {

    /**
     * Returns the headline for this chapter.
     *
     * @return the chapter's headline
     */
    Headline getHeadline();

    /**
     * Adds the headline for this chapter.
     *
     * @param aHeadline
     *        the chapter's new headline
     *
     * @return the previously set headline or <code>null</code> if none was
     *         set.
     */
    Headline setHeadline(Headline aHeadline);

    /**
     * Removes the headline for this chapter.
     *
     * @return the removed headline
     */
    Headline removeHeadline();

    /**
     * Checks if this chapter has a headline or not.
     *
     * @return <code>true</code> if this chapter has a headline, else <code>false</code>
     */
    boolean hasHeadline();

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
     * Inserts the specifies subchapter at the specified index.
     *
     * @param aChapter
     *        the subchapter which is to be inserted
     * @param anIndex
     *        the index of the inserted subchapter
     */
    void insertSubchapter(Chapter aChapter, int anIndex);

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
     *
     * @return the removed subchapter
     */
    Chapter removeSubchapter(int anIndex);

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
     * Inserts the specified paragraph at the specified index. Existing
     * paragraphs (i.e. following paragraphs) will be indexed accordingly.
     *
     * @param aParagraph
     *        the paragraph which is to be inserted
     * @param anIndex
     *        the index of the inserted paragraph
     */
    void insertParagraph(Paragraph aParagraph, int anIndex);

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
     *
     * @return the removed paragraph
     */
    Paragraph removeParagraph(int anIndex);

}
