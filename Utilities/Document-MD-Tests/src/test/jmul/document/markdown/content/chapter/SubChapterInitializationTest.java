/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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


import jmul.document.markdown.content.ContentFactory;
import jmul.document.markdown.content.chapter.Chapter;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests the initialization of a subchapter.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class SubChapterInitializationTest {

    /**
     * A chapter.
     */
    private Chapter chapter;

    /**
     * Prepares everything before a test.
     */
    @Before
    public void setUp() {

        chapter = ContentFactory.createChapter("Test");
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        chapter = null;
    }

    /**
     * Tests adding a subchapter.
     */
    @Test
    public void testAddSubchapter() {

        String headline1 = "Test2";

        assertNotNull(chapter);
        assertEquals(0, chapter.subchapters());

        Chapter subchapter = ContentFactory.createChapter(headline1);
        assertNotNull(subchapter);
        assertNotNull(subchapter.getHeadline());
        assertEquals(1, subchapter.getHeadline().getHeadlineType());
        assertEquals(headline1.length(), subchapter.getHeadline().length());
        assertEquals(headline1, subchapter.getHeadline().toString());

        chapter.addSubchapter(subchapter);
        assertEquals(1, chapter.subchapters());
        assertEquals(2, subchapter.getHeadline().getHeadlineType());
        assertEquals(headline1.length(), subchapter.getHeadline().length());
        assertEquals(headline1, subchapter.getHeadline().toString());
    }

    /**
     * Tests adding two subchapters.
     */
    @Test
    public void testAddTwoSubchapters() {

        String headline1 = "Test2";
        String headline2 = "Test3";

        assertNotNull(chapter);
        assertEquals(0, chapter.subchapters());

        Chapter subchapter1 = ContentFactory.createChapter(headline1);
        assertNotNull(subchapter1);
        assertNotNull(subchapter1.getHeadline());
        assertEquals(1, subchapter1.getHeadline().getHeadlineType());
        assertEquals(headline1.length(), subchapter1.getHeadline().length());
        assertEquals(headline1, subchapter1.getHeadline().toString());

        chapter.addSubchapter(subchapter1);
        assertEquals(1, chapter.subchapters());
        assertEquals(2, subchapter1.getHeadline().getHeadlineType());
        assertEquals(headline1.length(), subchapter1.getHeadline().length());
        assertEquals(headline1, subchapter1.getHeadline().toString());

        Chapter subchapter2 = ContentFactory.createChapter(headline2);
        assertNotNull(subchapter2);
        assertNotNull(subchapter2.getHeadline());
        assertEquals(1, subchapter2.getHeadline().getHeadlineType());
        assertEquals(headline2.length(), subchapter2.getHeadline().length());
        assertEquals(headline2, subchapter2.getHeadline().toString());

        chapter.addSubchapter(subchapter2);
        assertEquals(2, chapter.subchapters());
        assertEquals(2, subchapter2.getHeadline().getHeadlineType());
        assertEquals(headline2.length(), subchapter2.getHeadline().length());
        assertEquals(headline2, subchapter2.getHeadline().toString());
    }

    /**
     * Tests removing a subchapter.
     */
    @Test
    public void testRemovingSubchapter() {

        String headline1 = "Test2";

        assertNotNull(chapter);
        assertEquals(0, chapter.subchapters());

        Chapter subchapter = ContentFactory.createChapter(headline1);
        assertNotNull(subchapter);
        assertNotNull(subchapter.getHeadline());
        assertEquals(1, subchapter.getHeadline().getHeadlineType());
        assertEquals(headline1.length(), subchapter.getHeadline().length());
        assertEquals(headline1, subchapter.getHeadline().toString());

        chapter.addSubchapter(subchapter);
        assertEquals(1, chapter.subchapters());
        assertEquals(2, subchapter.getHeadline().getHeadlineType());
        assertEquals(headline1.length(), subchapter.getHeadline().length());
        assertEquals(headline1, subchapter.getHeadline().toString());

        Chapter removedChapter = chapter.removeSubchapter(0);
        assertEquals(0, chapter.subchapters());
        assertEquals(1, removedChapter.getHeadline().getHeadlineType());
        assertEquals(headline1.length(), removedChapter.getHeadline().length());
        assertEquals(headline1, removedChapter.getHeadline().toString());
    }

}
