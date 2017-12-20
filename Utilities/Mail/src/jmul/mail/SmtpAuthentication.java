/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


/**
 * This class implements authentication for SMTP.
 *
 * @author Kristian Kutin
 */
class SmtpAuthentication extends Authenticator {

    /**
     * The actual authentication entity.
     */
    private final PasswordAuthentication authentication;

    /**
     * Creates a new instance of an authenticator.<br />
     * <br />
     * <i>Note:<br />
     * The credentials are passed as string which from a security view is a bad
     * decission (see internal java handling of strings). On the other hand the
     * class that takes care of the actual authentication requires the credentials
     * to be provided as string. As long as the constructor signature isn't changed
     * a transition to char[] isn't possible.</i>
     *
     * @param aLogin
     * @param someCredentials
     */
    public SmtpAuthentication(String aLogin, String someCredentials) {

        authentication = new PasswordAuthentication(aLogin, someCredentials);
    }

    /**
     * A getter method.
     *
     * @return an authenticator entity
     */
    @Override
    public PasswordAuthentication getPasswordAuthentication() {

        return authentication;
    }

}
