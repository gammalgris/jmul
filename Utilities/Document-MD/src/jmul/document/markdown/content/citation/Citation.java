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


import jmul.document.markdown.content.paragraph.Paragraph;


/**
 * This interface describes a paragraph which represents a literal citation.
 *
 * @author Kristian Kutin
 */
public interface Citation extends Paragraph {

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
