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

package test.jmul.persistence.legacy;


import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.Border;

import jmul.concurrent.threads.ObservableThread;

import jmul.misc.id.ID;
import jmul.misc.id.IDGenerator;
import jmul.misc.id.StringID;

import jmul.persistence.PersistenceContainer;
import jmul.persistence.PersistenceContainerImpl;
import jmul.persistence.id.StringIDGenerator;

import jmul.string.StringConcatenator;

import test.jmul.datatypes.legacy.department.DepartmentGenderDetails;
import test.jmul.datatypes.legacy.department.DepartmentGenderDetailsImpl2;
import test.jmul.datatypes.legacy.employee.Employee;
import test.jmul.datatypes.legacy.employee.EmployeeImpl6;
import test.jmul.persistence.legacy.threads.GetObjectThread;
import test.jmul.persistence.legacy.threads.StoreObjectThread;


public class TestPersistenceLoad {

    /**
     * A base directory for tests.
     */
    private static final String BASEDIR = SerializationBase.DRIVE + "\\Test\\Concurrent\\Persistence";

    /**
     * A persistence manager.
     */
    private PersistenceContainer<DepartmentGenderDetails> persistenceManager;

    /**
     * An id generator to create IDs.
     */
    private IDGenerator idGenerator;

    private int creationCount;

    public TestPersistenceLoad() {

        File directory = new File(BASEDIR);

        if (directory.exists()) {

            directory.delete();
        }


        persistenceManager =
            new PersistenceContainerImpl<DepartmentGenderDetails>(DepartmentGenderDetails.class, BASEDIR);


        String filename = null;
        File file = null;

        filename = SerializationBase.DRIVE + "\\randomIDstore";
        file = new File(filename);

        if (file.exists()) {

            file.delete();
        }

        idGenerator = StringIDGenerator.getAlternativeGenerator(filename);


        creationCount = 0;
    }

    public PersistenceContainer<DepartmentGenderDetails> getPersistenceManager() {

        return persistenceManager;
    }

    public IDGenerator getIDGenerator() {

        return idGenerator;
    }

    /**
     * Returns a random character.
     *
     * @return a character
     */
    public char getRandomCharacter() {

        String allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int randomIndex = (int) (allowedCharacters.length() * Math.random());
        char randomCharacter = allowedCharacters.charAt(randomIndex);

        return randomCharacter;
    }

    /**
     * Returns a random string.
     *
     * @return a string
     */
    public String createRandomString() {

        int size = 4 + (int) (Math.random() * 10);
        StringConcatenator randomString = new StringConcatenator();

        for (int a = 0; a < size; a++) {

            randomString.append(getRandomCharacter());
        }

        return randomString.toString();
    }

    /**
     * Creates a new employee with random values.
     *
     * @return a new employee
     */
    public Employee createRandomEmployee() {

        return new EmployeeImpl6(createRandomString(), createRandomString(), createRandomString(), createRandomString(),
                                 (float) (Math.random() * 2000.00f), createRandomString());
    }

    /**
     * Creates random organisation details.
     *
     * @return organisation details
     */
    public DepartmentGenderDetails createRandomOrganisationDetails() {

        DepartmentGenderDetails details = new DepartmentGenderDetailsImpl2();

        int employees = (int) (Math.random() * 50);

        for (int a = 0; a < employees; a++) {

            details.addEmployee(createRandomEmployee());
        }

        return details;
    }

    /**
     * Creates a random id.
     *
     * @return an id
     */
    public ID createRandomInvalidID() {

        return new StringID(createRandomString());
    }

    /**
     * Creates a random id.
     *
     * @return an id
     */
    public ID createRandomValidID() {

        return idGenerator.generateID();
    }

    /**
     * Creates a random persistence operation thread.
     *
     * @return a persistence operation thread
     */
    public ObservableThread createRandomThread() {

        int operationType = 0;

        if (creationCount < 1000) {

            creationCount++;

        } else {

            operationType = (int) (Math.random() * 3);
            creationCount++;
        }

        System.out.print("(#" + creationCount + ") " + operationType);

        ObservableThread operation = null;

        switch (operationType) {

        case 0:
            System.out.println(" -> store operation...");
            operation = new StoreObjectThread(persistenceManager, createRandomOrganisationDetails());
            break;
        case 1:
            System.out.println(" -> get operation...");
            operation = new GetObjectThread(persistenceManager, createRandomInvalidID());
            break;
        default:
            System.out.println(" -> get operation...");
            operation = new GetObjectThread(persistenceManager, createRandomValidID());
        }

        return operation;
    }

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Click button to start test");

        TestPersistenceLoad test = new TestPersistenceLoad();

        Frame frame = new ConcurrencyAdjuster(test);
        frame.setVisible(true);
    }
}


class ConcurrencyAdjuster extends JFrame {

    private static final long MINIMUM_SLEEP_TIME = 50L;

    private static final int INITIAL_THREADS = 0;

    private static final long INITIAL_SLEEP_TIME = 1000L;

    private static final int DEFAULT_WIDTH = 400;

    private static final int DEFAULT_HEIGHT = 160;

    private static final int DEFAULT_COLUMNS = 20;

    static final String THREADS_LABEL = "threads to be started:";

    static final String SLEEP_TIME_LABEL = "sleep time (in ms):";

    private JLabel threadsLabel;

    private JTextField threadsField;

    private JLabel sleepTimeLabel;

    private JTextField sleepTimeField;

    private JProgressBar progressBar;

    private int threads;

    private long sleepTime;

    private ThreadInvoker threadInvoker;

    private long fraction = INITIAL_SLEEP_TIME / MINIMUM_SLEEP_TIME;

    /**
     * The default constructor.
     */
    public ConcurrencyAdjuster(TestPersistenceLoad test) {

        threads = INITIAL_THREADS;
        sleepTime = INITIAL_SLEEP_TIME;


        setTitle(getClass().getSimpleName());
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);


        WindowListener windowListener = new CustomWindowListener(test.getPersistenceManager());
        KeyListener keyListener = new CustomKeyListener(this);
        addWindowListener(windowListener);


        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraints = null;
        setLayout(gridbag);


        constraints = new GridBagConstraints();
        constraints.weightx = 1.0;
        threadsLabel = new JLabel(THREADS_LABEL);
        gridbag.setConstraints(threadsLabel, constraints);
        add(threadsLabel);


        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        threadsField = new JTextField(String.valueOf(threads), DEFAULT_COLUMNS);
        threadsField.setName(THREADS_LABEL);
        gridbag.setConstraints(threadsField, constraints);
        add(threadsField);
        threadsField.addKeyListener(keyListener);


        constraints = new GridBagConstraints();
        constraints.weightx = 1.0;
        sleepTimeLabel = new JLabel(SLEEP_TIME_LABEL);
        gridbag.setConstraints(sleepTimeLabel, constraints);
        add(sleepTimeLabel);


        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        sleepTimeField = new JTextField(String.valueOf(sleepTime), DEFAULT_COLUMNS);
        sleepTimeField.setName(SLEEP_TIME_LABEL);
        gridbag.setConstraints(sleepTimeField, constraints);
        add(sleepTimeField);
        sleepTimeField.addKeyListener(keyListener);


        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        progressBar = new JProgressBar();
        progressBar.setValue(0);
        progressBar.setMaximum((int) sleepTime);
        Border border = BorderFactory.createTitledBorder("Next interval...");
        progressBar.setBorder(border);
        gridbag.setConstraints(progressBar, constraints);
        add(progressBar);

        threadInvoker = new ThreadInvoker(test);
        (new Thread(threadInvoker)).start();
    }

    public ThreadInvoker getThreadInvoker() {

        return threadInvoker;
    }

    public void setThreads(int threads) {

        synchronized (this) {

            this.threads = threads;
        }
    }

    public void setSleepTime(long sleepTime) {

        synchronized (this) {

            this.sleepTime = sleepTime;
            progressBar.setMaximum((int) sleepTime);
            fraction = sleepTime / MINIMUM_SLEEP_TIME;
            threadInvoker.setSleepTime(sleepTime / fraction);
        }
    }

    class ThreadInvoker implements Runnable {

        private TestPersistenceLoad test;

        private long invocatorSleepTime;

        private boolean stop;

        public ThreadInvoker(TestPersistenceLoad test) {

            this.test = test;
            invocatorSleepTime = sleepTime / fraction;
            stop = false;
        }

        public void setSleepTime(long sleepTime) {

            invocatorSleepTime = sleepTime;
        }

        public void run() {

            while (!stop) {

                int oldValue = 0;
                int newValue = 0;
                int maxValue = 0;

                synchronized (ConcurrencyAdjuster.this) {

                    oldValue = progressBar.getValue();
                    maxValue = progressBar.getMaximum();

                    if (oldValue >= maxValue) {

                        for (int a = 0; a < threads; a++) {

                            Runnable r = test.createRandomThread();
                            (new Thread(r)).start();
                        }

                        progressBar.setValue(0);

                    } else {

                        newValue = oldValue + (int) invocatorSleepTime;
                        progressBar.setValue(newValue);
                    }
                }


                try {

                    Thread.currentThread().sleep(invocatorSleepTime);

                } catch (InterruptedException e) {

                    // Ignore this exception.
                }
            }
        }

        public void stop() {

            stop = true;
        }
    }
}


class CustomWindowListener implements WindowListener {

    private PersistenceContainer<DepartmentGenderDetails> persistenceManager;

    public CustomWindowListener(PersistenceContainer<DepartmentGenderDetails> persistenceManager) {

        this.persistenceManager = persistenceManager;
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {

        Window parentWindow = e.getWindow();

        if (parentWindow instanceof ConcurrencyAdjuster) {

            ConcurrencyAdjuster frame = (ConcurrencyAdjuster) parentWindow;
            frame.getThreadInvoker().stop();
        }

        persistenceManager.shutdown();

        parentWindow.dispose();
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

}


class CustomKeyListener implements KeyListener {

    private ConcurrencyAdjuster parentFrame;

    public CustomKeyListener(ConcurrencyAdjuster parentFrame) {

        this.parentFrame = parentFrame;
    }

    public void keyTyped(KeyEvent e) {

        // Ignore this event.
    }

    public void keyPressed(KeyEvent e) {

        // Ignore this event.
    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            Component component = e.getComponent();

            if (component instanceof JTextField) {

                JTextField textField = (JTextField) component;

                String message = textField.getName() + " " + textField.getText();
                System.out.println(message);

                if (textField.getName().equals(ConcurrencyAdjuster.SLEEP_TIME_LABEL)) {

                    long newValue = Long.parseLong(textField.getText());
                    parentFrame.setSleepTime(newValue);

                } else if (textField.getName().equals(ConcurrencyAdjuster.THREADS_LABEL)) {

                    int newValue = Integer.parseInt(textField.getText());
                    parentFrame.setThreads(newValue);
                }
            }
        }
    }

}
