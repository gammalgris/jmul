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

package jmul.persistence.file;


import java.io.File;
import java.io.FileFilter;

import jmul.concurrent.threads.ObservableThreadBase;
import jmul.concurrent.threads.ThreadExecutionStatus;

import jmul.io.filters.FilenameFilter;


/**
 * This thread scans a directory (i.e. looks for a specific file).
 *
 * @author Kristian Kutin
 */
public class DirectoryScannerThread extends ObservableThreadBase {

    /**
     * The directory which is to be scanned.
     */
    private File directory;

    /**
     * The file which is looked for.
     */
    private String filename;

    /**
     * The execution status for all related threads.
     */
    private ThreadExecutionStatus executionStatus;

    /**
     * Constructs a thread.
     *
     * @param aDirectory
     *        the directory which is to be scanned
     * @param aFilename
     *        the file which is looked for
     */
    public DirectoryScannerThread(ThreadExecutionStatus anExecutionStatus, File aDirectory, String aFilename) {

        directory = aDirectory;
        filename = aFilename;
        executionStatus = anExecutionStatus;
    }

    /**
     * The actual lookup is defined in this method.
     */
    @Override
    protected void performAction() {

        if (!executionStatus.isObsoleteExecution()) {

            FileFilter filter = new FilenameFilter(filename);
            File[] result = directory.listFiles(filter);

            // Either no file is found or one file was found.
            if ((result != null) && (result.length == 1)) {

                FileFoundNotification event = new FileFoundNotification(this, result[0]);
                informListeners(event);
            }
        }
    }

    /**
     * Returns the execution status for this thread and all related threads.
     *
     * @return a thread execution status
     */
    public ThreadExecutionStatus getThreadExecutionStatus() {

        return executionStatus;
    }

}
