/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package test.jmul.io;


import java.io.FileWriter;
import java.io.IOException;

import jmul.io.FileDeletionException;
import jmul.io.FileDeletionHelper;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.SetUpException;
import jmul.test.exceptions.TearDownException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * The class contains tests for the deletion of files which are still in use.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class Deletion2Test {

    /**
     * A path for a file.
     */
    private String filePath = "testdata-io/deletion/withhandle.txt";

    /**
     * An entity for writing into the file.
     */
    private FileWriter writer;

    /**
     * Steps to prepare the test.
     */
    @Before
    public void setUp() {

        try {

            writer = new FileWriter(filePath);
            writer.append("hello world");
            writer.flush();

        } catch (IOException e) {

            String message = "Unable to open the file \"" + filePath + "\"!";
            throw new SetUpException(message, e);
        }
    }

    /**
     * Steps to clean up after the test.
     */
    @After
    public void tearDown() {

        try {

            writer.close();

        } catch (IOException e) {

            String message = "Unable to close the file \"" + filePath + "\"!";
            throw new TearDownException(message, e);
        }

        writer = null;
    }

    /**
     * Tries to delete a file which is in use.
     */
    @Test(expected = FileDeletionException.class)
    public void testDeleteWithHandle() {

        FileDeletionHelper.delete(filePath);
    }

}
