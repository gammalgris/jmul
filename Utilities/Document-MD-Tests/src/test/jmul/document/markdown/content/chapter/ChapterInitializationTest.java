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

package test.jmul.document.markdown.content.chapter;


import jmul.document.markdown.content.chapter.Chapter;
import jmul.document.markdown.content.chapter.ChapterImpl;
import jmul.document.markdown.content.chapter.Headline;
import jmul.document.markdown.content.chapter.HeadlineImpl;
import jmul.document.markdown.content.node.ContentNode;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * Tests the initialization of a chapter.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ChapterInitializationTest {

    /**
     * Tests adding a headline to a chapter.
     */
    @Test
    public void testInitializeHeadline() {

        Chapter chapter = new ChapterImpl();
        assertNotNull(chapter);

        assertNull(chapter.getHeadline());
        assertFalse(chapter.hasHeadline());
        assertEquals(0, chapter.subchapters());
        assertEquals(0, chapter.paragraphs());

        ContentNode node = (ContentNode) chapter;
        assertNull(node.getParent());


        Headline headline = new HeadlineImpl("Test");
        assertNotNull(headline);

        assertEquals(0, headline.getHeadlineType());
        assertEquals(4, headline.length());
        assertEquals("Test", headline.toString());


        Headline previousHeadline = chapter.setHeadline(headline);
        assertNull(previousHeadline);

        assertNotNull(chapter.getHeadline());
        assertTrue(chapter.hasHeadline());
        assertEquals(0, chapter.subchapters());
        assertEquals(0, chapter.paragraphs());

        assertEquals(1, headline.getHeadlineType());
        assertEquals(4, headline.length());
        assertEquals("Test", headline.toString());
    }

    /**
     * Tests adding a headline to a chapter and removing it.
     */
    @Test
    public void testRemoveHeadline() {

        Chapter chapter = new ChapterImpl();
        assertNotNull(chapter);

        assertNull(chapter.getHeadline());
        assertFalse(chapter.hasHeadline());
        assertEquals(0, chapter.subchapters());
        assertEquals(0, chapter.paragraphs());

        ContentNode node = (ContentNode) chapter;
        assertNull(node.getParent());


        Headline headline = new HeadlineImpl("Test");
        assertNotNull(headline);

        assertEquals(0, headline.getHeadlineType());
        assertEquals(4, headline.length());
        assertEquals("Test", headline.toString());


        Headline previousHeadline = chapter.setHeadline(headline);
        assertNull(previousHeadline);

        assertNotNull(chapter.getHeadline());
        assertTrue(chapter.hasHeadline());
        assertEquals(0, chapter.subchapters());
        assertEquals(0, chapter.paragraphs());

        assertEquals(1, headline.getHeadlineType());
        assertEquals(4, headline.length());
        assertEquals("Test", headline.toString());


        previousHeadline = chapter.setHeadline(null);
        assertNotNull(previousHeadline);

        assertNull(chapter.getHeadline());
        assertFalse(chapter.hasHeadline());
        assertEquals(0, chapter.subchapters());
        assertEquals(0, chapter.paragraphs());

        assertEquals(0, previousHeadline.getHeadlineType());
        assertEquals(4, previousHeadline.length());
        assertEquals("Test", previousHeadline.toString());
    }

}
