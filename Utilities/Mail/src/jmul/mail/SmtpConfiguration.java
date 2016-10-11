/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2015  Kristian Kutin
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

package jmul.mail;


import java.util.Properties;


/**
 * This class contains the configuration which is required to communicate
 * with a mail server.
 *
 * @author Kristian Kutin
 */
class SmtpConfiguration extends Properties {

    /**
     * A property key.
     */
    private static final String HOST_KEY = "mail.smtp.host";

    /**
     * A property key.
     */
    private static final String AUTHENTICATION_KEY = "mail.smtp.auth";

    /**
     * Creates a new SMTP configuration.
     *
     * @param aHost
     */
    public SmtpConfiguration(String aHost) {

        super(new Properties(System.getProperties()));

        this.setProperty(HOST_KEY, aHost);
        this.setProperty(AUTHENTICATION_KEY, "true");
    }

}