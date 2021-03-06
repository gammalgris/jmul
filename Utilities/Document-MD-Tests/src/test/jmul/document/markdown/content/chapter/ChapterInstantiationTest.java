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
import jmul.document.markdown.content.node.ContentNode;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;


/**
 * This class tests the instantiation of a chapter.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ChapterInstantiationTest {

    /**
     * Tests the instantiation of a chapter implementation.
     */
    @Test
    public void testInstantiationImplementation1() {

        Chapter chapter = new ChapterImpl();
        assertNotNull(chapter);

        assertNull(chapter.getHeadline());
        assertFalse(chapter.hasHeadline());
        assertEquals(0, chapter.subchapters());
        assertEquals(0, chapter.paragraphs());

        ContentNode node = (ContentNode) chapter;
        assertNull(node.getParent());
    }

}
