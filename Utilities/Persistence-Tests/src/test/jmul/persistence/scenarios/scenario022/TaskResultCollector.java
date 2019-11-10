/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package test.jmul.persistence.scenarios.scenario022;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * This class collects all task results.
 *
 * @author Kristian Kutin
 */
public class TaskResultCollector implements Iterable<TaskResult> {

    /**
     * All results by collection date.
     */
    private Map<Date, TaskResult> results;

    public TaskResultCollector() {

        results = new TreeMap<Date, TaskResult>();
    }

    /**
     * Adds the specified task result.
     *
     * @param aResult
     */
    public void addResult(TaskResult aResult) {

        Date currentDate = new Date();

        synchronized (this) {

            results.put(currentDate, aResult);
        }
    }

    /**
     * Returns an iterator to iterate through all task results.
     *
     * @return an iterator
     */
    @Override
    public Iterator<TaskResult> iterator() {

        List<TaskResult> values;

        synchronized (this) {

            values = Collections.unmodifiableList(new ArrayList<TaskResult>(results.values()));
        }

        return values.iterator();
    }

}
