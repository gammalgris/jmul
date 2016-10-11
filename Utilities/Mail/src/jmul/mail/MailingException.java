/*
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

package jmul.mail;


/**
 * The exception is thrown if an error occurs while trying to send e-mails.
 *
 * @author Kristian Kutin
 */
public class MailingException extends RuntimeException {

    /**
     * The default constructor.
     */
    public MailingException() {

        super();
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aMessage
     */
    public MailingException(String aMessage) {

        super(aMessage);
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aCause
     */
    public MailingException(Throwable aCause) {

        super(aCause);
    }

    /**
     * Creates a new exception according to the specified parameters.
     *
     * @param aMessage
     * @param aCause
     */
    public MailingException(String aMessage, Throwable aCause) {

        super(aMessage, aCause);
    }

}
