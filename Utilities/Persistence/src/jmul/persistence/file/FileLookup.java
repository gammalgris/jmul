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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import jmul.concurrent.threads.ThreadEvent;
import jmul.concurrent.threads.ThreadExecutionStatus;
import jmul.concurrent.threads.ThreadFinishedEvent;
import jmul.concurrent.threads.ThreadHelper;
import jmul.concurrent.threads.ThreadListener;
import jmul.concurrent.threads.ThreadPool;

import jmul.reflection.Initializer;
import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import jmul.string.StringConcatenator;

import jmul.xml.query.XPathQuery;


/**
 * This class looks for a specific file within all subfolders.
 *
 * @author Kristian Kutin
 */
public class FileLookup implements ThreadListener {

    /**
     * A property key.
     */
    private static final String MAX_SCANNER_THREADS_KEY = "maxScannerThreads";

    /**
     * A property key.
     */
    private static final String MAX_QUEUE_SIZE_KEY = "maxQueueSize";

    /**
     * A property key.
     */
    private static final String THREAD_POOL_TYPE_KEY = "threadPoolType";

    /**
     * A sleep time.
     */
    private static final long SLEEP_TIME = 30L;

    /**
     * Manages the threads which will independently look for the specified
     * file.
     */
    private static ThreadPool threadPoolSingleton;

    /**
     * A lock to safeguard that the thread pool is instantiated and initialized
     * properly.
     */
    private static Lock threadPoolLock = new ReentrantLock();

    /**
     * The method creates a new threadpool which manages parallel file lookups.
     */
    private static void newThreadPoolSingleton() {

        synchronized (threadPoolLock) {

            if (threadPoolSingleton == null) {

                ResourceBundle bundle = ResourceBundle.getBundle(FileLookup.class.getName());
                int maxScannerThreads = Integer.parseInt(bundle.getString(MAX_SCANNER_THREADS_KEY));
                int maxQueueSize = Integer.parseInt(bundle.getString(MAX_QUEUE_SIZE_KEY));
                String threadPoolType = bundle.getString(THREAD_POOL_TYPE_KEY);


                ClassDefinition definition = null;

                try {

                    definition = ClassHelper.getClass(threadPoolType);

                } catch (ClassNotFoundException e) {

                    StringConcatenator message =
                        new StringConcatenator("The specified thread pool (", threadPoolType, ") is invalid!");
                    throw new RuntimeException(message.toString(), e);
                }


                Initializer initializer = new Initializer(definition);
                threadPoolSingleton = (ThreadPool) initializer.newInitializedInstance();

                threadPoolSingleton.setMaximumThreads(maxScannerThreads);
                threadPoolSingleton.setMaximumQueueSize(maxQueueSize);
            }
        }
    }

    /**
     * The method stops a running threadpool.
     */
    private static void stopThreadPoolSingleton() {

        synchronized (threadPoolLock) {

            if (threadPoolSingleton != null) {

                threadPoolSingleton.stop();
                threadPoolSingleton = null;
            }
        }
    }

    /**
     * Checks if the threadpool is curretnly active.
     *
     * @return <code>true</code> if the threadpool is currently aczive, else
     *         <code>false</code>
     */
    private static boolean isActiveThreadPool() {

        boolean result;

        synchronized (threadPoolLock) {

            result = threadPoolSingleton != null;
        }

        return result;
    }

    /**
     * Returns a reference to the currently used thread pool.
     *
     * @return a reference to the currently used thread pool
     */
    private static ThreadPool getThreadPool() {

        ThreadPool pool;

        synchronized (threadPoolLock) {

            pool = threadPoolSingleton;
        }

        return pool;
    }

    /**
     * The results of a lookup.
     */
    private Collection<File> results;

    /**
     * A count of active scans.
     */
    private int activeScans;

    /**
     * The default constructor.<br>
     * <br>
     * <i>Implementation detail:<br>
     * The constructor will only initialize this object. To avoid having too
     * many IO operations blocking the computer all file lookup entities share
     * the same thread pool which executes IO operations.<br>
     * This shared thread pool must be explicitely activated (see
     * {@link #activateLookupMechanism}) or deactivated (see
     * {@link #deactivateLookupMechanism}).</i>
     */
    FileLookup() {

        super();

        results = new ArrayList<File>();
        activeScans = 0;
    }

    /**
     * The method searches the specified file within the specified folders.
     *
     * @param someSearchFolders
     *        the folders which are searched
     * @param aFilename
     *        the file which is searched
     *
     * @return <code>true</code> if the specified file can be found within one
     *         of the folders, else <code>false</code>
     */
    public boolean existsFile(Collection<File> someSearchFolders, String aFilename) {

        ThreadExecutionStatus status = new ThreadExecutionStatus();

        for (File folder : someSearchFolders) {

            DirectoryScannerThread thread = new DirectoryScannerThread(status, folder, aFilename);
            thread.addListener(this);

            synchronized (this) {

                activeScans++;
            }

            getThreadPool().addThread(thread);
        }


        while ((activeScans > 0) && (results.isEmpty())) {

            ThreadHelper.sleep(SLEEP_TIME);
        }


        boolean result;

        synchronized (this) {

            result = !results.isEmpty();
        }

        return result;
    }

    /**
     * Returns the file with the specified filename. All specified folders are
     * searched to identify the file's location.
     *
     * @param someSearchFolders
     *        the folders which are searched
     * @param aFilename
     *        the file which is searched
     *
     * @return a file or <code>null</code> if no such file exists
     */
    public File getFile(Collection<File> someSearchFolders, String aFilename) {

        ThreadExecutionStatus status = new ThreadExecutionStatus();

        for (File folder : someSearchFolders) {

            DirectoryScannerThread thread = new DirectoryScannerThread(status, folder, aFilename);
            thread.addListener(this);

            synchronized (this) {

                activeScans++;
            }

            getThreadPool().addThread(thread);
        }


        while (activeScans > 0) {

            ThreadHelper.sleep(SLEEP_TIME);
        }


        int matches;
        File result = null;

        synchronized (this) {

            matches = results.size();

            if (matches == 1) {

                result = results.iterator().next();
            }
        }


        if (matches > 1) {

            StringConcatenator message =
                new StringConcatenator("Too many files with the name ", aFilename, " have been found!");
            throw new IllegalArgumentException(message.toString());
        }

        return result;
    }

    /**
     * The listener is informed about an event.
     *
     * @param anEvent
     *        a thread event
     */
    @Override
    public void informOnEvent(ThreadEvent anEvent) {

        if (anEvent instanceof FileFoundNotification) {

            FileFoundNotification notification = (FileFoundNotification) anEvent;

            DirectoryScannerThread cause = (DirectoryScannerThread) anEvent.getObservableThread();
            cause.getThreadExecutionStatus().setObsoleteExecution(true);

            synchronized (this) {

                results.add(notification.getFile());
            }

        } else if (anEvent instanceof XPathResultNotification) {

            XPathResultNotification notification = (XPathResultNotification) anEvent;

            synchronized (this) {

                for (File file : notification.getFiles()) {

                    results.add(file);
                }
            }

        } else if (anEvent instanceof ThreadFinishedEvent) {

            synchronized (this) {

                activeScans--;
            }
        }
    }

    /**
     * Start the lookup mechanism which is managed by a shared thread pool.
     */
    public static void activateLookupMechanism() {

        newThreadPoolSingleton();
    }

    /**
     * Stops the lookup mechanism which is managed by a shared thread pool.
     */
    public static void deactivateLookupMechanism() {

        stopThreadPoolSingleton();
    }

    /**
     * The method returns all files which meet the specified criteria.
     *
     * @param someSearchFolders
     * @param someQueries
     *
     * @return a list of files which meet the specified criteria
     */
    public Collection<File> findFiles(Collection<File> someSearchFolders, XPathQuery... someQueries) {

        ThreadExecutionStatus status = new ThreadExecutionStatus();

        for (File folder : someSearchFolders) {

            XPathEvaluatorThread thread = new XPathEvaluatorThread(status, folder, someQueries);
            thread.addListener(this);

            synchronized (this) {

                activeScans++;
            }

            getThreadPool().addThread(thread);
        }


        while (activeScans > 0) {

            ThreadHelper.sleep(SLEEP_TIME);
        }


        Collection<File> unmodifuableResults;

        synchronized (this) {

            unmodifuableResults = Collections.unmodifiableCollection(results);
        }

        return unmodifuableResults;
    }

}
