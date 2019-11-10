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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.persistence.legacy.db4o;


import java.io.File;

import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jmul.io.FileHelper;

import jmul.misc.id.IDGenerator;

import jmul.persistence.PersistenceContainer;
import jmul.persistence.id.StringIDGenerator;

import test.jmul.datatypes.legacy.department.DepartmentGenderDetails;
import test.jmul.persistence.legacy.SerializationBase;
import test.jmul.persistence.legacy.loadtest.TestControlFrame;


/**
 * An implementation of a persistence load test.<br>
 * <br>
 * A setup window allows to specify the number of threads and the kind of
 * threads (storing objects, retrieving objects) which are started by the test
 * manager. The system's load can then be monitored with jconsole and perfmon.
 *
 * @author Kristian Kutin
 */
public class DB4OTestPersistenceLoad {

    /**
     * The name of a resource bundle.
     */
    private static final String RESOURCE_BUNDLE_NAME = SerializationBase.class.getName();

    /**
     * A property key.
     */
    private static final String DRIVE_KEY = "drive";

    /**
     * A property key.
     */
    private static final String BASEDIR_KEY = "basedir";

    /**
     * The file separator which is used under this operating system.
     */
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * A persistence manager.
     */
    private PersistenceContainer<DepartmentGenderDetails> persistenceManager;

    /**
     * An id generator to create IDs.
     */
    private IDGenerator idGenerator;

    /**
     * The default constructor.
     */
    public DB4OTestPersistenceLoad() {

        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME);

        String drive = "C:"; /*bundle.getString(DRIVE_KEY);*/
        String basedir = bundle.getString(BASEDIR_KEY);


        File testdir =
            new File(drive + FILE_SEPARATOR + basedir + FILE_SEPARATOR + "Concurrent" + FILE_SEPARATOR + "Persistence");

        if (testdir.exists()) {

            FileHelper.delete(testdir);
        }


        persistenceManager =
            new DB4OPersistenceManagerImpl<DepartmentGenderDetails>(DepartmentGenderDetails.class,
                                                                    testdir.getAbsolutePath());


        File idfile = new File(drive + FILE_SEPARATOR + basedir + FILE_SEPARATOR + "randomIDstore");

        if (idfile.exists()) {

            FileHelper.delete(idfile);
        }

        idGenerator = StringIDGenerator.getAlternativeGenerator(idfile.getAbsolutePath());
    }

    /**
     * Starts the actual test.
     */
    public void startTest() {

        JFrame frame = new TestControlFrame(persistenceManager, idGenerator);
        frame.setVisible(true);
    }

    /**
     * The main method is required for manual tests.
     *
     * @param args
     *        command line arguments
     */
    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Click button to start test");

        DB4OTestPersistenceLoad test = new DB4OTestPersistenceLoad();
        test.startTest();
    }

}
