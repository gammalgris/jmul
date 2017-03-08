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

package test.jmul.persistence.legacy.loadtest;


import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;
import jmul.reflection.Initializer;

import jmul.concurrent.threads.ObservableThread;


/**
 * A utility class that specifies how a thread is initialized at runtime.
 *
 * @author Kristian Kutin
 */
public class ThreadInitializer {

    /**
     * The type of thread which is to be initialized.
     */
    private ClassDefinition type;

    /**
     * Informations about how to calculate the informations which the thread
     * requires.
     */
    private FieldInitializer[] fieldInitializers;

    /**
     * Constructs a thread initialization object.
     *
     * @param aType
     *        the type of the thread
     * @param someFieldInitializers
     *        informations on how to calculate additional informations which are
     *        required by the thread
     */
    public ThreadInitializer(String aType, FieldInitializer... someFieldInitializers) {

        try {

            type = ClassHelper.getClass(aType);

        } catch (ClassNotFoundException e) {

            String message = "Unknown class: " + aType;
            throw new RuntimeException(message, e);
        }

        fieldInitializers = someFieldInitializers;
    }

    /**
     * Constructs a thread initialization object.
     *
     * @param aType
     *        the type of the thread
     * @param someFieldInitializers
     *        informations on how to calculate additional informations which are
     *        required by the thread
     */
    public ThreadInitializer(ClassDefinition aType, FieldInitializer... someFieldInitializers) {

        type = aType;
        fieldInitializers = someFieldInitializers;
    }

    /**
     * Creates a new thread according to the specified informations.
     *
     * @return a new thread
     */
    public ObservableThread newThread() {

        Initializer initializer = new Initializer(type);

        for (FieldInitializer fieldInitializer : fieldInitializers) {

            initializer.setField(fieldInitializer.getFieldName(), fieldInitializer.getFieldValue());
        }

        ObservableThread thread = (ObservableThread) initializer.newInitializedInstance();
        return thread;
    }

}
