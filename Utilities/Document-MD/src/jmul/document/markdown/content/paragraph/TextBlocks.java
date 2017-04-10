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

package jmul.document.markdown.content.paragraph;


import jmul.document.markdown.content.text.TextBlock;


/**
 * This interface describes a parapgraph within a markdown document.
 *
 * @author Kristian Kutin
 */
public interface TextBlocks extends Paragraph {

    /**
     * Returns the current text block count.
     *
     * @return a text block count
     */
    int textBlocks();

    /**
     * Adds (i.e. appends) the specified text block to this paragraph.
     *
     * @param aTextBlock
     */
    void addTextBlock(TextBlock aTextBlock);

    /**
     * Returns the text block at the specified index.
     *
     * @param anIndex
     *
     * @return a text block
     */
    TextBlock getTextBlock(int anIndex);

    /**
     * Removes the text block at the specified index.
     *
     * @param anIndex
     */
    void removeTextBlock(int anIndex);

}
