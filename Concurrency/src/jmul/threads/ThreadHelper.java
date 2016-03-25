/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2014  Kristian Kutin
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

package jmul.threads;


/**
 * This utility class provides various methods for threads.
 *
 * @author Kristian Kutin
 */
public final class ThreadHelper {

    /**
     * The default constructor.
     */
    private ThreadHelper() {
    }

    /**
     * Puts the current thread to sleep for the specified amount of time.
     *
     * @param sleepTime
     *        in milliseconds
     */
    public static void sleep(long sleepTime) {

        Thread currentThread = Thread.currentThread();

        try {

            Thread.sleep(sleepTime);

        } catch (InterruptedException e) {

            e.printStackTrace();
            currentThread.interrupt();
        }
    }

    /**
     * Determines the name of the invoking method.<br />
     * <br />
     * <i><u>Note:</u><br />
     * The method looks for the method name within the current thread's stack trace. The
     * current implementation looks for the name at a fixed position.</i>
     *
     * @return a method name
     */
    public static String getMethodName() {

        Thread currentThread = Thread.currentThread();
        StackTraceElement[] stackTrace = currentThread.getStackTrace();

        int elements = stackTrace.length;
        int expectedIndex = 3;

        if (expectedIndex < elements) {

            StackTraceElement element = stackTrace[expectedIndex];

            String[] substrings = element.toString().split("\\(");
            String name = substrings[0];

            return name;
        }

        return "unknown method";
    }

    /**
     * Prints the current thread's details to the console.
     */
    public static void printCurrentThreadDetails() {

        Thread currentThread = Thread.currentThread();

        StackTraceElement[] stackTrace = currentThread.getStackTrace();

        for (StackTraceElement element : stackTrace) {

            System.out.println("\t" + element);
        }
    }

}
