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

package test.jmul.persistence;


import java.io.File;

import jmul.concurrent.threads.ThreadHelper;

import jmul.io.FileDeletionHelper;
import jmul.io.FileHelper;

import static jmul.math.Constants.SECOND;


/**
 * A base implementation for tests.
 *
 * @author Kristian Kutin
 */
abstract class TestBase {

    /**
     * A root directory for files and directories which are created during test execution.
     */
    public static final String ROOT_DIRECTORY = ".\\testdata-persistence\\";

    /**
     * Initializes the specified base directory (i.e. cleans all the content).
     *
     * @param aBaseDirectory
     */
    protected static void initBaseDirectory(String aBaseDirectory) {

        ThreadHelper.sleep(SECOND);

        File baseDirectory = new File(aBaseDirectory);

        if (baseDirectory.exists()) {

            FileDeletionHelper.delete(baseDirectory, true);
        }

        baseDirectory.mkdirs();
    }

}
