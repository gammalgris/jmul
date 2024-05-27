/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package jmul.io;


import java.util.Properties;

import static jmul.string.Constants.NEW_LINE;


/**
 * This class collects various path informations.
 *
 * @author Kristian Kutin
 */
public final class SystemPaths {

    /**
     * A property key.
     */
    private static final String USER_DIR_KEY;

    /**
     * A property key.
     */
    private static final String INSTALLATION_DIR_KEY;

    /**
     * A property key.
     */
    private static final String LOG_FILE_KEY;

    /*
     * The static initializer.
     */
    static {

        USER_DIR_KEY = "user.dir";
        INSTALLATION_DIR_KEY = "installation.dir";
        LOG_FILE_KEY = "log.file";
    }

    /**
     * The installation directory of this application.
     */
    public final String installationDirectory;

    /**
     * The user directory on this machine.
     */
    public final String userDirectory;

    /**
     * The log file for console logging.
     */
    public final String logFile;

    /**
     * The working directory on this machine.
     */
    public final String workingDirectory;

    /**
     * The default constructor.
     */
    public SystemPaths() {

        super();

        String value;
        Properties properties = System.getProperties();

        existsSystemProperty(properties, INSTALLATION_DIR_KEY);
        value = properties.getProperty(INSTALLATION_DIR_KEY);
        this.installationDirectory = normalizePath(value);

        existsSystemProperty(properties, USER_DIR_KEY);
        value = properties.getProperty(USER_DIR_KEY);
        this.userDirectory = normalizePath(value);

        existsSystemProperty(properties, LOG_FILE_KEY);
        value = properties.getProperty(LOG_FILE_KEY);
        this.logFile = normalizePath(value);

        value = ".";
        this.workingDirectory = normalizePath(value);
    }

    /**
     * Checks if the specified system propertists.
     *
     * @param properties
     *        all system properties
     * @param key
     *        the key for a system property
     **/
    private static void existsSystemProperty(Properties properties, String key) {

        if (!properties.containsKey(key)) {

            throw new MissingSystemPropertyException(key);
        }
    }

    /**
     * Normalizes the specified path.
     *
     * @param path
     *        a path to a file or directory
     *
     * @return the normalized path
     */
    private static String normalizePath(String path) {

        PathDetails pathDetails = new PathDetails(path);
        return pathDetails.normalizedPath;
    }

    /**
     * Returns a string representation for this instance.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("user dir=\"");
        buffer.append(userDirectory);
        buffer.append("\";installation dir=\"");
        buffer.append(installationDirectory);
        buffer.append("\";log file=\"");
        buffer.append(logFile);
        buffer.append("\";working dir=\"");
        buffer.append(workingDirectory);
        buffer.append("\"");
        buffer.append(NEW_LINE);

        return buffer.toString();
    }

}
