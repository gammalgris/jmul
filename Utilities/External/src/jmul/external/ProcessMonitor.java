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

package jmul.external;


/**
 * This class monitors an external process.
 *
 * @author Kristian Kutin
 */
public class ProcessMonitor {

    /**
     * A reference to an external process.
     */
    private Process process;

    /**
     * A monitor for the standard output of the process.
     */
    private InputStreamMonitor outputStreamMonitor;

    /**
     * A monitor for the error output of the process.
     */
    private InputStreamMonitor errorStreamMonitor;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aProcess
     *        an external process
     */
    ProcessMonitor(Process aProcess) {

        process = aProcess;

        outputStreamMonitor = new InputStreamMonitor(process.getInputStream());
        errorStreamMonitor = new InputStreamMonitor(process.getErrorStream());


        Thread t = null;

        t = new Thread(outputStreamMonitor);
        t.start();
        t = new Thread(errorStreamMonitor);
        t.start();
    }

    /**
     * Returns the standard output of the external process.
     *
     * @return the standard output
     */
    public String getOutputMessage() {

        return outputStreamMonitor.getStreamContent();
    }

    /**
     *  Returns the error output of the external process.
     *
     * @return the error output
     */
    public String getErrorMessage() {

        return errorStreamMonitor.getStreamContent();
    }

}
