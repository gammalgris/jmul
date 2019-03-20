/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
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

package jmul.concurrent.threads;


import java.util.Map;


/**
 * This helper class provides various utility methods for threads. Some methods
 * handle checked exceptions and may rethrow unchecked exceptions.
 *
 * @author Kristian Kutin
 */
public final class ThreadHelper {

    /**
     * A constant value for looking up the invoking method in a stacktrace.
     */
    private static final int INVOKING_METHOD_INDEX = 3;

    /**
     * The default constructor.
     */
    private ThreadHelper() {

        throw new UnsupportedOperationException();
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

            currentThread.interrupt();
        }
    }

    /**
     * Determines the name of the invoking method.
     *
     * @return a simple method name
     */
    public static String getInvokingMethodName() {

        return getInvokingMethodName(NameTypes.SIMPLE_NAME, false);
    }

    /**
     * Determines the name of the invoking method.
     *
     * @param aNameType
     *        specifies what kind of method name is returned
     *
     * @return a canonical or simple method name
     */
    public static String getInvokingMethodName(NameTypes aNameType) {

        return getInvokingMethodName(aNameType, false);
    }

    /**
     * Determines the name of the invoking method.<br>
     * <br>
     * <i><u>Note:</u><br>
     * The method looks for the method name within the current thread's stack trace. The
     * current implementation looks for the name at a fixed position.</i>
     *
     * @param aNameType
     *        specifies what kind of method name is returned
     * @param sideThread
     *        indicates that the invocation occurs within a side thread and not the main thread. The
     *        stacktrace is different.
     *
     * @return a canonical or simple method name
     */
    public static String getInvokingMethodName(NameTypes aNameType, boolean sideThread) {

        Thread currentThread = Thread.currentThread();
        StackTraceElement[] stackTrace = currentThread.getStackTrace();

        int elementCount = stackTrace.length;
        int index;

        if (sideThread) {

            index = INVOKING_METHOD_INDEX - 1;

        } else {

            index = INVOKING_METHOD_INDEX;
        }

        if (INVOKING_METHOD_INDEX < elementCount) {

            StackTraceElement element = stackTrace[index];

            String methodName = removeLineInformation(element.toString());

            if (NameTypes.SIMPLE_NAME == aNameType) {

                return removePath(methodName);

            } else if (NameTypes.CANONICAL_NAME == aNameType) {

                return methodName;

            } else {

                throw new UnsupportedOperationException();
            }
        }

        throw new UnsupportedOperationException();
    }

    /**
     * Removes the line informations from the stacktrace element string.
     *
     * @param aString
     *        a string representing a stacktrace element
     *
     * @return a canonical method name
     */
    private static String removeLineInformation(String aString) {

        String[] substrings = aString.split("\\(");

        return substrings[0];
    }

    /**
     * Removes the path and class information from the specified method name string.
     *
     * @param aString
     *        a string containing a canonical method name
     *
     * @return a simple method name
     */
    private static String removePath(String aString) {

        String[] substrings = aString.split("\\.");
        int lastIndex = substrings.length - 1;

        return substrings[lastIndex];
    }

    /**
     * Prints the current thread's details to the console.
     *
     * @deprecated The method is going to be removed.
     */
    @Deprecated
    public static void printCurrentThreadDetails() {

        Thread currentThread = Thread.currentThread();

        StackTraceElement[] stackTrace = currentThread.getStackTrace();

        for (StackTraceElement element : stackTrace) {

            System.out.println("\t" + element);
        }
    }

    /**
     * Returns a string containing the current stack trace.
     *
     * @return a string
     */
    public static String getCurrentStackTrace() {

        StringBuilder buffer = new StringBuilder();

        Thread currentThread = Thread.currentThread();

        StackTraceElement[] stackTrace = currentThread.getStackTrace();

        for (StackTraceElement element : stackTrace) {

            buffer.append("\t");
            buffer.append(element);
            buffer.append("\r\n");
        }

        return buffer.toString();
    }

    /**
     * Find a thread with the specified name. If no such thread exists then
     * an exception is thrown.
     *
     * @param aName
     *        a name
     *
     * @return a thread
     */
    public static Thread getThreadByName(String aName) {

        Map<Thread, StackTraceElement[]> allThreads = Thread.getAllStackTraces();
        for (Thread thread : allThreads.keySet()) {

            String actualName = thread.getName();

            if (actualName.equals(aName)) {

                return thread;
            }
        }

        String message = "No thread with the name \"" + aName + "\" exists!";
        throw new IllegalArgumentException(message);
    }

    /**
     * The current thread waits for the specified thread to end.
     *
     * @param t
     *        a thread
     */
    public static void waitForThreadToEnd(Thread t) {

        Thread currentThread = Thread.currentThread();

        try {

            t.join();

        } catch (InterruptedException e) {

            currentThread.interrupt();
        }
    }

}
