/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package test.jmul.persistence;


import java.io.File;
import java.io.IOException;

import jmul.concurrent.threads.ThreadHelper;

import jmul.io.FileHelper;

import static jmul.math.Constants.SECOND;

import jmul.persistence.file.FileManager;
import jmul.persistence.file.FileManagerImpl;
import jmul.persistence.id.ID;
import jmul.persistence.id.IDGenerator;
import jmul.persistence.id.StringIDGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * A base implementation for tests with file managers.
 *
 * @author Kristian Kutin
 */
abstract class FileManagerTestBase {

    /**
     * Initializes the specified base directory (i.e. cleans all the content).
     *
     * @param aBaseDirectory
     */
    protected void initBaseDirectory(String aBaseDirectory) {

        ThreadHelper.sleep(SECOND);

        File baseDirectory = new File(aBaseDirectory);

        if (baseDirectory.exists()) {

            FileHelper.delete(baseDirectory);
        }

        baseDirectory.mkdirs();
    }

    /**
     * Initializes a file manager according to the specified parameters.
     *
     * @param aBaseDirectory
     *
     * @return a file manager
     */
    protected FileManager initFileManager(String aBaseDirectory) {

        return new FileManagerImpl(aBaseDirectory);
    }

    /**
     * Initializes an ID generator according to the specified parmaeters.
     *
     * @param anIdFile
     *
     * @return an ID generator
     */
    protected IDGenerator initIDGenerator(String anIdFile) {

        return StringIDGenerator.getAlternativeGenerator(anIdFile);
    }

    /**
     * Create the specified number of files using a file manager and the ID generator.
     *
     * @param aFileManager
     * @param aGenerator
     * @param aFilesThreshold
     */
    protected void createTestFiles(FileManager aFileManager, IDGenerator aGenerator, int aFilesThreshold) {

        for (int a = 0; a < aFilesThreshold; a++) {

            ID id = aGenerator.generateID();
            File newFile = aFileManager.newFile(id.toString());

            try {

                newFile.createNewFile();

            } catch (IOException e) {

                fail(e.getMessage());
            }
        }
    }

    /**
     * Checks the existence of a file according to the expectations and the actual search result.
     *
     * @param anIdentifier
     * @param theExpectedResult
     * @param theActualResult
     */
    protected void checkExistence(String anIdentifier, boolean theExpectedResult, boolean theActualResult) {

        String message = anIdentifier;

        if (theExpectedResult && theActualResult) {

            message = message + " - should exist and was found (OK).";

        } else if (theExpectedResult && !theActualResult) {

            message = message + " - should exist and was not found (ERROR)!";

        } else if (!theExpectedResult && theActualResult) {

            message = message + " - should not exist but was found (ERROR)!";

        } else {

            message = message + " - should not exist and was not found (OK).";
        }

        assertEquals(message, theExpectedResult, theActualResult);
    }

}
