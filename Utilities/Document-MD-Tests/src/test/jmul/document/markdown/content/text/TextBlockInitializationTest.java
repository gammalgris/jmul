/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2020  Kristian Kutin
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

package test.jmul.document.markdown.content.text;


import jmul.document.markdown.content.text.TextBlock;
import jmul.document.markdown.content.text.TextBlockImpl;

import static jmul.string.Constants.NEW_LINE;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests the initialization of a text block.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class TextBlockInitializationTest {

    /**
     * The actual text block.
     */
    private TextBlock textBlock;

    /**
     * Prepares everything for a test.
     */
    @Before
    public void setUp() {

        textBlock = new TextBlockImpl();
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        textBlock = null;
    }

    /**
     * Tests adding a single line to a text block.
     */
    @Test
    public void testAddLine() {

        assertNotNull(textBlock);

        assertEquals(0, textBlock.lines());
        assertEquals(0, textBlock.getContent().size());
        assertEquals("", textBlock.getContentAsString());

        String s;
        String expectedContent;
        String actualContent;

        s = "line #1";
        textBlock.addLine(s);
        assertEquals(1, textBlock.lines());
        assertEquals(1, textBlock.getContent().size());
        assertEquals(s, textBlock.getContent().get(0));

        expectedContent = s + NEW_LINE;
        actualContent = textBlock.getContentAsString();
        assertEquals(expectedContent.length(), actualContent.length());
        assertEquals(expectedContent, actualContent);
    }

    /**
     * Tests adding two lines to a text block.
     */
    @Test
    public void testAddTwoLines() {

        assertNotNull(textBlock);

        assertEquals(0, textBlock.lines());
        assertEquals(0, textBlock.getContent().size());
        assertEquals("", textBlock.getContentAsString());

        String s;
        String expectedContent;
        String actualContent;

        s = "line #1";
        textBlock.addLine(s);
        assertEquals(1, textBlock.lines());
        assertEquals(1, textBlock.getContent().size());
        assertEquals(s, textBlock.getContent().get(0));

        expectedContent = s + NEW_LINE;
        actualContent = textBlock.getContentAsString();
        assertEquals(expectedContent.length(), actualContent.length());
        assertEquals(expectedContent, actualContent);

        s = "line #2";
        textBlock.addLine(s);
        assertEquals(2, textBlock.lines());
        assertEquals(2, textBlock.getContent().size());
        assertEquals(s, textBlock.getContent().get(1));

        expectedContent = expectedContent + s + NEW_LINE;
        actualContent = textBlock.getContentAsString();
        assertEquals(expectedContent.length(), actualContent.length());
        assertEquals(expectedContent, actualContent);
    }

    /**
     * Tests adding and removing a line.
     */
    @Test
    public void testAddAndRemoveLine() {

        assertNotNull(textBlock);

        assertEquals(0, textBlock.lines());
        assertEquals(0, textBlock.getContent().size());
        assertEquals("", textBlock.getContentAsString());

        String s;
        String t;
        String expectedContent;
        String actualContent;

        s = "line #1";
        textBlock.addLine(s);
        assertEquals(1, textBlock.lines());
        assertEquals(1, textBlock.getContent().size());
        assertEquals(s, textBlock.getContent().get(0));

        expectedContent = s + NEW_LINE;
        actualContent = textBlock.getContentAsString();
        assertEquals(expectedContent.length(), actualContent.length());
        assertEquals(expectedContent, actualContent);

        t = textBlock.removeLine(0);
        assertEquals(s, t);
        assertEquals(0, textBlock.lines());
        assertEquals(0, textBlock.getContent().size());

        expectedContent = "";
        actualContent = textBlock.getContentAsString();
        assertEquals(expectedContent.length(), actualContent.length());
        assertEquals(expectedContent, actualContent);
    }

    /**
     * Tests adding two lines to a text block.
     */
    @Test
    public void testAddTwoLinesAndRemovingFirstLine() {

        assertNotNull(textBlock);

        assertEquals(0, textBlock.lines());
        assertEquals(0, textBlock.getContent().size());
        assertEquals("", textBlock.getContentAsString());

        String s;
        String t;
        String expectedContent;
        String actualContent;

        s = "line #1";
        textBlock.addLine(s);
        assertEquals(1, textBlock.lines());
        assertEquals(1, textBlock.getContent().size());
        assertEquals(s, textBlock.getContent().get(0));

        expectedContent = s + NEW_LINE;
        actualContent = textBlock.getContentAsString();
        assertEquals(expectedContent.length(), actualContent.length());
        assertEquals(expectedContent, actualContent);

        s = "line #2";
        textBlock.addLine(s);
        assertEquals(2, textBlock.lines());
        assertEquals(2, textBlock.getContent().size());
        assertEquals(s, textBlock.getContent().get(1));

        expectedContent = expectedContent + s + NEW_LINE;
        actualContent = textBlock.getContentAsString();
        assertEquals(expectedContent.length(), actualContent.length());
        assertEquals(expectedContent, actualContent);

        t = textBlock.removeLine(0);
        assertEquals(1, textBlock.lines());
        assertEquals(1, textBlock.getContent().size());
        assertEquals(s, textBlock.getContent().get(0));

        expectedContent = s + NEW_LINE;
        actualContent = textBlock.getContentAsString();
        assertEquals(expectedContent.length(), actualContent.length());
        assertEquals(expectedContent, actualContent);
    }

}
