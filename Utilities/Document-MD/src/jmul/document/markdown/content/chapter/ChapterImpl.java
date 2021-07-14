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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.document.markdown.content.chapter;


import java.util.ArrayList;
import java.util.List;

import jmul.document.markdown.content.node.ContentNode;
import jmul.document.markdown.content.node.ContentNodeImpl;
import jmul.document.markdown.content.paragraph.Paragraph;


/**
 * An implementation of a chapter.
 *
 * @author Kristian Kutin
 */
public class ChapterImpl extends ContentNodeImpl implements Chapter {

    /**
     * This chapter's headline.
     */
    private Headline headline;

    /**
     * This chapter's subchapters.
     */
    private List<Chapter> allSubchapters;

    /**
     * This chapter's paragraphs.
     */
    private List<Paragraph> allParagraphs;

    /**
     * The default constructor.
     */
    public ChapterImpl() {

        super();

        headline = null;

        allSubchapters = new ArrayList<>();
        allParagraphs = new ArrayList<>();
    }

    /**
     * Returns the headline for this chapter.
     *
     * @return the chapter's headline
     */
    @Override
    public Headline getHeadline() {

        return headline;
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

        Headline previousHeadline = headline;
        ContentNode previousNode = (ContentNode) headline;
        if (previousNode != null) {

            previousNode.setParent(null);
        }

        headline = aHeadline;
        ContentNode newNode = (ContentNode) aHeadline;
        if (newNode != null) {

            newNode.setParent(this);
        }

        return previousHeadline;
    }

    /**
     * Removes the headline for this chapter.
     *
     * @return the removed headline
     */
    @Override
    public Headline removeHeadline() {

        return setHeadline(null);
    }

    /**
     * Checks if this chapter has a headline or not.
     *
     * @return <code>true</code> if this chapter has a headline, else <code>false</code>
     */
    @Override
    public boolean hasHeadline() {

        return headline != null;
    }

    /**
     * Returns the current subchapter count.
     *
     * @return a subchapter count
     */
    @Override
    public int subchapters() {

        return allSubchapters.size();
    }

    /**
     * Adds (i.e. appends) the specified subchapter to this chapter.
     *
     * @param aChapter
     *        the subchapter which is to be added
     */
    @Override
    public void addSubchapter(Chapter aChapter) {

        ContentNode newNode = (ContentNode) aChapter;
        if (newNode != null) {

            newNode.setParent(this);
        }

        allSubchapters.add(aChapter);
    }

    /**
     * Inserts the specifies subchapter at the specified index.
     *
     * @param aChapter
     *        the subchapter which is to be inserted
     * @param anIndex
     *        the index of the inserted subchapter
     */
    @Override
    public void insertSubchapter(Chapter aChapter, int anIndex) {

        ContentNode newNode = (ContentNode) aChapter;
        if (newNode != null) {

            newNode.setParent(this);
        }

        allSubchapters.add(anIndex, aChapter);
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

        return allSubchapters.get(anIndex);
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

        Chapter removedChapter = allSubchapters.remove(anIndex);
        ContentNode removedNode = (ContentNode) removedChapter;
        if (removedNode != null) {

            removedNode.setParent(null);
        }

        return removedChapter;
    }

    /**
     * Returns the current paragraph count.
     *
     * @return a paragraph count
     */
    @Override
    public int paragraphs() {

        return allParagraphs.size();
    }

    /**
     * Adds (i.e. appends) the specified paragraph to this chapter.
     *
     * @param aParagraph
     *        the paragraph which is to be added
     */
    @Override
    public void addParagraph(Paragraph aParagraph) {

        ContentNode newNode = (ContentNode) aParagraph;
        if (newNode != null) {

            newNode.setParent(this);
        }

        allParagraphs.add(aParagraph);
    }

    /**
     * Inserts the specified paragraph at the specified index. Existing
     * paragraphs (i.e. following paragraphs) will be indexed accordingly.
     *
     * @param aParagraph
     *        the paragraph which is to be inserted
     * @param anIndex
     *        the index of the inserted paragraph
     */
    @Override
    public void insertParagraph(Paragraph aParagraph, int anIndex) {

        ContentNode newNode = (ContentNode) aParagraph;
        if (newNode != null) {

            newNode.setParent(this);
        }

        allParagraphs.add(anIndex, aParagraph);
    }

    /**
     * Returns the paragraph at the specified index.
     *
     * @param anIndex
     *        the index of the requested paragraph
     *
     * @return a paragraph
     */
    @Override
    public Paragraph getParagraph(int anIndex) {

        return allParagraphs.get(anIndex);
    }

    /**
     * Removes the paragraph at the specified index.
     *
     * @param anIndex
     *        the index of the pragraph which is to be removed
     *
     * @return the removed paragraph
     */
    @Override
    public Paragraph removeParagraph(int anIndex) {

        Paragraph removedParagraph = allParagraphs.remove(anIndex);
        ContentNode removedNode = (ContentNode) removedParagraph;
        if (removedNode != null) {

            removedNode.setParent(null);
        }

        return removedParagraph;
    }

}
