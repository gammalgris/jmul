/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.io.archives;


import java.util.ArrayList;
import java.util.Collection;

import jmul.io.archives.ArchiveEntry;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Tests scanning archives with valid parameters (see {@link jmul.io.archives.ArchiveEntry#scanArchive} ).
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ArchiveScanValidParametersTest {

    /**
     * The name of an archive.
     */
    private String archiveName;

    /**
     * The number of expected entries.
     */
    private int expectedEntriesCount;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param anArchiveName
     * @param anExpectedEntriesCount
     */
    public ArchiveScanValidParametersTest(String anArchiveName, int anExpectedEntriesCount) {

        archiveName = anArchiveName;
        expectedEntriesCount = anExpectedEntriesCount;
    }

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

        Collection<ArchiveEntry> results = ArchiveEntry.scanArchive(archiveName);
        assertEquals(expectedEntriesCount, results.size());
    }

    /**
     * Returns a matrix of input data.
     *
     * @return a matrix of input data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "testdata-io\\archive1.zip", 4 });
        parameters.add(new Object[] { "testdata-io\\archive2.zip", 1 });
        parameters.add(new Object[] { "testdata-io\\archive2.zip", 1 });
        parameters.add(new Object[] { "testdata-io\\archive2.zip", 1 });

        return parameters;
    }

}
