/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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


import jmul.document.markdown.content.node.ContentNodeImpl;
import jmul.document.markdown.content.paragraph.Paragraph;


public class ChapterImpl extends ContentNodeImpl implements Chapter {

    public ChapterImpl() {

        super();
    }

    /**
     * Adds the headline for this chapter.
     *
     * @param aHeadline
     *        the chapter's new headline
     *
     * @return the previously set headline or <code>null</code> if none was
     *         set.
     */
    @Override
    public Headline setHeadline(Headline aHeadline) {

        //TODO
        return null;
    }

    /**
     * Removes the headline for this chapter.
     *
     * @return the removed headline
     */
    @Override
    public Headline removeHeadline() {

        //TODO
        return null;
    }

    /**
     * Returns the current subchapter count.
     *
     * @return a subchapter count
     */
    @Override
    public int subchapters() {

        //TODO
        return 0;
    }

    /**
     * Adds (i.e. appends) the specified subchapter to this chapter.
     *
     * @param aChapter
     *        the subchapter which is to be added
     */
    @Override
    public void addSubchapter(Chapter aChapter) {

        //TODO
    }

    /**
     * Returns the subchapter at the specified index.
     *
     * @param anIndex
     *        the index of the requested subchapter
     *
     * @return a subchapter
     */
    @Override
    public Chapter getSubchapter(int anIndex) {

        //TODO
        return null;
    }

    /**
     * Removes the subchapter at the specified index.
     *
     * @param anIndex
     *        the index of the subchapter which is to be removed
     *
     * @return the removed subchapter
     */
    @Override
    public Chapter removeSubchapter(int anIndex) {

        //TODO
        return null;
    }

    /**
     * Returns the current paragraph count.
     *
     * @return a paragraph count
     */
    @Override
    public int paragraphs() {

        //TODO
        return 0;
    }

    /**
     * Adds (i.e. appends) the specified paragraph to this chapter.
     *
     * @param aParagraph
     *        the paragraph which is to be added
     */
    public void addParagraph(Paragraph aParagraph) {

        //TODO
    }

    /**
     * Returns the paragraph at the specified index.
     *
     * @param anIndex
     *        the index of the requested paragraph
     *
     * @return a paragraph
     */
    public Paragraph getParagraph(int anIndex) {

        //TODO
        return null;
    }

    /**
     * Removes the paragraph at the specified index.
     *
     * @param anIndex
     *        the index of the pragraph which is to be removed
     *
     * @return the removed paragraph
     */
    public Paragraph removeParagraph(int anIndex) {

        //TODO
        return null;
    }

}
