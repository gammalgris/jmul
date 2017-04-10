/*
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

package jmul.document.markdown.content;

import jmul.document.markdown.content.chapter.Chapter;


/**
 * This interface describes the content of a markdown file.
 *
 * @author Kristian Kutin
 */
public interface MarkdownContent {

    /**
     * Returns the current chapter count.
     *
     * @return a chapter count
     */
    int chapters();

    /**
     * Adds (i.e. appends) the specified chapter to this markdown document.
     *
     * @param aChapter
     */
    void addChapter(Chapter aChapter);

    /**
     * Returns the chapter at the specified index.
     *
     * @param anIndex
     *
     * @return a chapter
     */
    Chapter getChapter(int anIndex);

    /**
     * Removes the chapter at the specified index.
     *
     * @param anIndex
     */
    void removeChapter(int anIndex);

}
