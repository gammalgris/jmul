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

package jmul.document.markdown.content.citation;


import java.util.ArrayList;
import java.util.List;

import jmul.document.markdown.content.node.ContentNodeImpl;
import jmul.document.markdown.content.paragraph.Paragraph;


/**
 * An implementation of a citation.
 *
 * @author Kristian Kutin
 */
public class CitationImpl extends ContentNodeImpl implements Citation {

    /**
     * This chapter's paragraphs.
     */
    private List<Paragraph> allParagraphs;

    /**
     * The default constructor.
     */
    private CitationImpl() {

        super();

        allParagraphs = new ArrayList<>();
    }

    /**
     * Creates a new citation block according to the specified parameters.
     *
     * @param aParagraph
     *        the quoted text
     */
    public CitationImpl(Paragraph aParagraph) {

        this();

        addParagraph(aParagraph);
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

        return allParagraphs.remove(anIndex);
    }

}
