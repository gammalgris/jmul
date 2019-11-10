/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.persistence.file;


import java.io.File;
import java.io.FileFilter;

import jmul.concurrent.threads.ObservableThreadBase;
import jmul.concurrent.threads.ThreadExecutionStatus;

import jmul.xml.query.XPathQuery;


/**
 * This thread evaluates an XPath expression and returns
 */
public class XPathEvaluatorThread extends ObservableThreadBase {

    /**
     * The directory which is to be scanned.
     */
    private File directory;

    /**
     * Some XPath queries.
     */
    private XPathQuery[] queries;

    /**
     * The execution status for all related threads.
     */
    private ThreadExecutionStatus executionStatus;

    /**
     * Constructs a thread.
     *
     * @param anExecutionStatus
     *        the execution status (indicates that the execution is obsolete)
     * @param aDirectory
     *        the directory which is to be scanned
     * @param someQueries
     *        all queries which will be performed on all files
     */
    public XPathEvaluatorThread(ThreadExecutionStatus anExecutionStatus, File aDirectory, XPathQuery... someQueries) {

        executionStatus = anExecutionStatus;
        directory = aDirectory;
        queries = someQueries;
    }

    /**
     * The actual XPath evaluation is defined in this method.
     */
    @Override
    protected void performAction() {

        if (!executionStatus.isObsoleteExecution()) {

            FileFilter filter = new XPathFilter(queries);
            File[] result = directory.listFiles(filter);

            if ((result != null) && (result.length > 0)) {

                XPathResultNotification event = new XPathResultNotification(this, result);
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
