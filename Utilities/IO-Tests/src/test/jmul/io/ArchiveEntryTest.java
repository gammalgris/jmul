/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package test.jmul.io;


import java.util.Collection;

import jmul.io.ArchiveEntry;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests scanning archives with valid parameters (see {@link jmul.io.ArchiveEntry#scanArchive}).
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ArchiveEntryTest {

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Tests scanning for a specific archive entry.
     */
    @Test
    public void testScan() {

        Collection<ArchiveEntry> results = ArchiveEntry.scanArchive("testdata-io\\archive2.zip");
        assertEquals(1, results.size());

        ArchiveEntry entry = results.iterator().next();
        assertTrue(entry.isFile());
        assertEquals("testdata-io/config1.properties", entry.getName());
        assertEquals("config1.properties", entry.getShortName());
    }

    /**
     * Tests scanning for a specific archive entry.
     */
    @Test
    public void testScan2() {

        Collection<ArchiveEntry> results = ArchiveEntry.scanArchive("testdata-io\\archive4.zip");
        assertEquals(1, results.size());

        ArchiveEntry entry = results.iterator().next();
        assertTrue(entry.isDirectory());
        assertEquals("testdata-io/folder3/", entry.getName());
        assertEquals("folder3", entry.getShortName());
    }

}
