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

package test.jmul.persistence.loadtest;


import java.awt.GridBagLayout;
import java.awt.event.KeyListener;

import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import jmul.persistence.PersistenceContainer;
import jmul.persistence.id.IDGenerator;

import test.jmul.datatypes.department.DepartmentGenderDetails;


/**
 * The frame implements the test control center.<br>
 * <br>
 * <i>Implementation details:<br>
 * <ul>
 *   <li><a href="http://stackoverflow.com/questions/1333377/how-to-update-a-gui-from-another-thread-in-java">Updating gui elements</a></li>
 *   <li><a href="http://www.java2s.com/Code/Java/Swing-JFC/JTextFieldAlignmentSample.htm">Aligning text in a text field</a></li>
 * </ul></i>
 *
 * @author Kristian Kutin
 */
public class TestControlFrame extends JFrame {

    /**
     * A constant.
     */
    private static final long MINIMUM_SLEEP_TIME = 50L;

    /**
     * A constant.
     */
    private static final int INITIAL_THREADS = 0;

    /**
     * A constant.
     */
    private static final long INITIAL_SLEEP_TIME = 1000L;

    /**
     * A constant.
     */
    private static final int DEFAULT_WIDTH = 400;

    /**
     * A constant.
     */
    private static final int DEFAULT_HEIGHT = 160;

    /**
     * The number of threads which will be started after the next sleep period.
     */
    private int threads;

    /**
     * The duration of the sleep period.
     */
    private long sleepTime;

    /**
     * A thread randomizer.
     */
    private ThreadRandomizer threadRandomizer;

    /**
     * A persistence manager.
     */
    private PersistenceContainer<DepartmentGenderDetails> persistenceManager;

    /**
     * An id generator to create IDs.
     */
    private IDGenerator idGenerator;

    /**
     * The weights for randomly creating threads.
     */
    private int[] threadWeights;

    /**
     * Monitor for the progress bar.
     */
    private ProgressBarMonitor monitor;

    /**
     * Constructs the test control frame.
     *
     * @param aPersistenceManager
     *        a reference to a persistence manager
     * @param anIdGenerator
     *        an ID generator
     */
    public TestControlFrame(PersistenceContainer<DepartmentGenderDetails> aPersistenceManager,
                            IDGenerator anIdGenerator) {

        persistenceManager = aPersistenceManager;
        idGenerator = anIdGenerator;

        threads = INITIAL_THREADS;
        sleepTime = INITIAL_SLEEP_TIME;


        setTitle(getClass().getSimpleName());
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);


        initComponents();
        initProgressBar();
        initThreadRandomization();


        addWindowListener(new CustomWindowListener());
    }

    /**
     * Initializes all gui components.
     */
    private final void initComponents() {

        KeyListener keyListener = new CustomKeyListener(this);

        GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);

        for (GUIComponent component : GUIComponent.values()) {

            gridbag.setConstraints(component.getComponent(), component.getLayoutInformations());
            add(component.getComponent());

            if (JTextField.class.isInstance(component.getComponent())) {

                JTextField textField = (JTextField) component.getComponent();
                textField.addKeyListener(keyListener);
            }
        }


        JTextField textField = null;

        textField = (JTextField) GUIComponent.SleepTimeField.getComponent();
        textField.setText(String.valueOf(getSleepTime()));

        textField = (JTextField) GUIComponent.ThreadsField.getComponent();
        textField.setText(String.valueOf(getThreads()));
    }

    /**
     * Initializes the progress bar.
     */
    private final void initProgressBar() {

        JProgressBar progressBar = (JProgressBar) GUIComponent.SleepMonitor.getComponent();
        progressBar.setMaximum((int) sleepTime);

        monitor = new ProgressBarMonitor();
        (new Thread(monitor)).start();
    }

    /**
     * Initializes the thread randomizer.
     */
    private final void initThreadRandomization() {

        threadRandomizer = new ThreadRandomizer();

        JTextField textField = null;
        JLabel label = null;

        Class[] emptyParameterSignature = { };
        Object[] emptyParameterList = { };


        // Store Operatiopn #1

        Method createRandomOrganisationDetails = null;

        try {

            createRandomOrganisationDetails =
                RandomizationHelper.class.getMethod("createRandomOrganisationDetails", emptyParameterSignature);

        } catch (NoSuchMethodException e) {

            throw new RuntimeException(e);
        }

        FieldInitializer storedObjectInitializer =
            new DynamicFieldInitializer("object", null, createRandomOrganisationDetails, emptyParameterList);
        FieldInitializer persistenceManagerInitializer =
            new StaticFieldInitializer("persistenceManager", persistenceManager);

        ThreadInitializer storeOperation =
            new ThreadInitializer("mabs.test.persistence.threads.StoreObjectThread", storedObjectInitializer,
                                  persistenceManagerInitializer);


        // Get Operation #1

        Method createRandomValidID = null;

        try {

            createRandomValidID = IDGenerator.class.getMethod("generateID", emptyParameterSignature);

        } catch (NoSuchMethodException e) {

            throw new RuntimeException(e);
        }

        FieldInitializer validObjectIDInitializer =
            new DynamicFieldInitializer("id", idGenerator, createRandomValidID, emptyParameterList);

        ThreadInitializer getOperation1 =
            new ThreadInitializer("mabs.test.persistence.threads.GetObjectThread", validObjectIDInitializer,
                                  persistenceManagerInitializer);


        // Get Operation #2

        Method createRandomInvalidID = null;

        try {

            createRandomInvalidID =
                RandomizationHelper.class.getMethod("createRandomInvalidID", emptyParameterSignature);

        } catch (NoSuchMethodException e) {

            throw new RuntimeException(e);
        }

        FieldInitializer invalidObjectIDInitializer =
            new DynamicFieldInitializer("id", null, createRandomInvalidID, emptyParameterList);

        ThreadInitializer getOperation2 =
            new ThreadInitializer("mabs.test.persistence.threads.GetObjectThread", invalidObjectIDInitializer,
                                  persistenceManagerInitializer);


        threadRandomizer.setThreads(storeOperation, getOperation1, getOperation2);

        threadWeights = new int[] { 1, 1, 1 };

        label = (JLabel) GUIComponent.ThreadTypeLabel.getComponent();
        label.setText("Store Object / Get Object (valid ID) / Get Object (invalid ID)");

        textField = (JTextField) GUIComponent.ThreadTypeField.getComponent();
        textField.setText(String.valueOf(threadWeights[0] + "," + threadWeights[1] + "," + threadWeights[2]));
        textField.setHorizontalAlignment(JTextField.CENTER);
    }

    /**
     * Cleans up after this window has been closed.
     */
    @Override
    public void dispose() {

        super.dispose();

        monitor.stop();
        persistenceManager.shutdown();
    }

    /**
     * A setter method.
     *
     * @param newSleepTime
     *        the new sleep time
     */
    public void setSleepTime(long newSleepTime) {

        synchronized (this) {

            sleepTime = newSleepTime;
        }

        SwingUtilities.invokeLater(new MaxValueUpdater((int) sleepTime));
    }

    /**
     * A getter method.
     *
     * @return the sleep time
     */
    public long getSleepTime() {

        long result = 0L;

        synchronized (this) {

            result = sleepTime;
        }

        return result;
    }

    /**
     * A setter method.
     *
     * @param newThreadNumber
     *        the new number of threads which are started
     */
    public void setThreads(int newThreadNumber) {

        synchronized (this) {

            threads = newThreadNumber;
        }
    }

    /**
     * A getter method.
     *
     * @return the number of threads which are started
     */
    public int getThreads() {

        int result = 0;

        synchronized (this) {

            result = threads;
        }

        return result;
    }

    /**
     * A setter method.
     *
     * @param weights
     *        the weight for randomly starting threads
     */
    public void setThreadDistribution(int... weights) {

        synchronized (this) {

            threadWeights = weights;
            threadRandomizer.setWeights(weights);
        }
    }

    /**
     * A getter method.
     *
     * @return the weight for randomly starting threads
     */
    public int[] getThreadDistribution() {

        int[] results = null;

        synchronized (this) {

            results = threadWeights;
        }

        return results;
    }

    /**
     * This inner class monitors the progress bar and initiates cerin actions.
     */
    class ProgressBarMonitor implements Runnable {

        /**
         * A flag which indicates that the monitors will stop its execution.
         */
        private boolean stop;

        /**
         * The default constructor.
         */
        public ProgressBarMonitor() {

            stop = false;
        }

        /**
         * Stops the execution of this thread.
         */
        public void stop() {

            stop = true;
        }

        /**
         * Monitors the progress bar and starts related events.
         */
        public void run() {

            while (!stop) {

                int oldValue = 0;
                int newValue = 0;
                int maxValue = 0;

                JProgressBar progressBar = (JProgressBar) GUIComponent.SleepMonitor.getComponent();


                synchronized (progressBar) {

                    oldValue = progressBar.getValue();
                    maxValue = progressBar.getMaximum();
                }


                if (oldValue >= maxValue) {

                    for (int a = 0; a < threads; a++) {

                        (new Thread(threadRandomizer.newThread())).start();
                    }

                    SwingUtilities.invokeLater(new CurrentValueUpdater(0));

                } else {

                    newValue = oldValue + (int) MINIMUM_SLEEP_TIME;
                    SwingUtilities.invokeLater(new CurrentValueUpdater(newValue));
                }


                try {

                    Thread.currentThread().sleep(MINIMUM_SLEEP_TIME);

                } catch (InterruptedException e) {

                    // Ignore this exception.
                }
            }
        }

    }

}
