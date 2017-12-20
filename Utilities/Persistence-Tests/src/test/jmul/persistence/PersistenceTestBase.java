/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package test.jmul.persistence;


import java.util.ResourceBundle;

import jmul.concurrent.threads.ThreadHelper;

import static jmul.math.Constants.SECOND;

import jmul.persistence.cache.ExpirationMonitor;


/**
 * A base implementation for tests.
 *
 * @author Kristian Kutin
 */
public class PersistenceTestBase extends TestBase {

    /**
     * A property key.
     */
    private static final String EXPIRATION_INTERVAL_KEY = "expirationInterval";

    /**
     * A property key.
     */
    private static final String SLEEP_TIME_KEY = "sleepTime";

    /**
     * Returns the expiration interval (in milliseconds).
     *
     * @return an expiration interval
     */
    protected static long getExpirationInterval() {

        ResourceBundle bundle = ResourceBundle.getBundle(ExpirationMonitor.class.getName());

        String value = bundle.getString(EXPIRATION_INTERVAL_KEY);
        long expirationInterval = Long.parseLong(value) * SECOND;

        return expirationInterval;
    }

    /**
     * Returns the sleept time (in milliseconds).
     *
     * @return a sleep time
     */
    protected static long getSleepTime() {

        ResourceBundle bundle = ResourceBundle.getBundle(ExpirationMonitor.class.getName());

        String value = bundle.getString(SLEEP_TIME_KEY);
        long expirationInterval = Long.parseLong(value);

        return expirationInterval;
    }

    /**
     * Pauses the current thread until the cache is cleaned.
     */
    protected static void waitForEmptyCache() {

        long sleepTime = getExpirationInterval() + getSleepTime() + SECOND * 2;

        ThreadHelper.sleep(sleepTime);
    }

}
