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


import java.util.ArrayList;
import java.util.List;

import jmul.document.markdown.content.internal.ContentNode;
import jmul.document.markdown.content.internal.ContentNodeImpl;
import jmul.document.markdown.content.text.TextBlock;

import static jmul.string.Constants.SPACE;


/**
 * An implementation of a text block container.
 *
 * @author Kristian Kutin
 */
public class TextBlocksImpl extends ContentNodeImpl implements TextBlocks {

    /**
     * Contains all text blocks.
     */
    private List<TextBlock> textBlocks;

    /**
     * The default constructor.
     */
    public TextBlocksImpl() {

        super();

        textBlocks = new ArrayList<TextBlock>();
    }

    /**
     * Returns the current text block count.
     *
     * @return a text block count
     */
    @Override
    public int textBlocks() {

        return textBlocks.size();
    }

    /**
     * Adds (i.e. appends) the specified text block to this paragraph.
     *
     * @param aTextBlock
     */
    @Override
    public void addTextBlock(TextBlock aTextBlock) {

        textBlocks.add(aTextBlock);

        ContentNode contentNode = (ContentNode) aTextBlock;
        contentNode.setParent(this);
    }

    /**
     * Returns the text block at the specified index.
     *
     * @param anIndex
     *
     * @return a text block
     */
    @Override
    public TextBlock getTextBlock(int anIndex) {

        return textBlocks.get(anIndex);
    }

    /**
     * Removes the text block at the specified index.
     *
     * @param anIndex
     */
    @Override
    public void removeTextBlock(int anIndex) {

        TextBlock textBlock = getTextBlock(anIndex);
        ContentNode contentNode = (ContentNode) textBlock;

        contentNode.setParent(null);
        textBlocks.remove(anIndex);
    }

    /**
     * A setter method.
     *
     * @param someTextBlocks
     */
    public void setTextBlocks(List<TextBlock> someTextBlocks) {

        textBlocks = someTextBlocks;
    }

    /**
     * A getter method.
     *
     * @return a list with all text blocks
     */
    public List<TextBlock> getTextBlocks() {

        return textBlocks;
    }

    /**
     * Returns a string representation for this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        for (int a = 0; a < textBlocks(); a++) {

            TextBlock textBlock = getTextBlock(a);

            buffer.append(SPACE);
            buffer.append(textBlock);
        }

        return buffer.toString();
    }

}
