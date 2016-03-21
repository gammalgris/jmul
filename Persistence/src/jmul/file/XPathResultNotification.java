/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.file;

import java.io.File;

import jmul.threads.ObservableThread;
import jmul.threads.ThreadEventBase;


/**
 * An implementation of a thread event.
 *
 * @author Kristian Kutin
 */
public class XPathResultNotification extends ThreadEventBase {

    /**
     * A result list.
     */
    private final File[] files;

    /**
     * Constructs a thread event.
     *
     * @param aCause
     *        the event that is the cause for this event
     * @param someResults
     *        the results of a thread
     */
    public XPathResultNotification(ObservableThread aCause,
                                   File[] someResults) {

        super(aCause);

        files = someResults;
    }

    /**
     * Returns the thread result.
     *
     * @return a result.
     */
    public File[] getFiles() {

        return files;
    }

}
