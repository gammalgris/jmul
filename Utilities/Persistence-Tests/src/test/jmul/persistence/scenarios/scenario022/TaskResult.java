/*
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

package test.jmul.persistence.scenarios.scenario022;


import jmul.misc.id.ID;


/**
 * The result of a single task.
 *
 * @author Kristian Kutin
 */
public class TaskResult {

    /**
     * The ID of a processed object.
     */
    private final ID id;

    /**
     * A short summary of the actual processing.
     */
    private final String summary;

    /**
     * A flag indicating success or failue of the processing.
     */
    private final boolean success;

    /**
     * The actual duration of a task.
     */
    private final long duration;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param anID
     * @param aSummary
     * @param aSuccess
     * @param aDuration
     */
    private TaskResult(ID anID, String aSummary, boolean aSuccess, long aDuration) {

        id = anID;
        summary = aSummary;
        success = aSuccess;
        duration = aDuration;
    }

    /**
     * A getter method.
     *
     * @return ID of a processed object
     */
    public ID getId() {

        return id;
    }

    /**
     * A getter method.
     *
     * @return short summary of the actual processing
     */
    public String getSummary() {

        return summary;
    }

    /**
     * A getter method.
     *
     * @return <code>true</code> if the processing was successful, else
     *         <code>false</code>
     */
    public boolean isSuccess() {

        return success;
    }

    /**
     * A getter method.
     *
     * @return
     */
    public long getDuration() {

        return duration;
    }

    /**
     * Creates a new task result for a successful task.
     *
     * @param anID
     * @param aSummary
     * @param aDuration
     *
     * @return a new task result
     */
    public static TaskResult createSuccessfulTaskReport(ID anID, String aSummary, long aDuration) {

        return new TaskResult(anID, aSummary, true, aDuration);
    }

    /**
     * Creates a new task result for a failed task.
     *
     * @param anID
     * @param aSummary
     * qparam aDuration
     *
     * @return a new task result
     */
    public static TaskResult createFailedTaskReport(ID anID, String aSummary, long aDuration) {

        return new TaskResult(anID, aSummary, false, aDuration);
    }

}
