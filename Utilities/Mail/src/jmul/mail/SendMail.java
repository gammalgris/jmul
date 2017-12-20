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


import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * This class implements a mechanism to send e-mails via SMTP.<br />
 * <br />
 * <i>Note:<br />
 * The code has been taken from
 * <a href="http://www.tutorialspoint.com/java/java_sending_email.htm">Tutorialpoint</a>
 * and was modified.</i><br />
 * <br />
 * <i>Security Note:<br />
 * Passwords are handled as strings which is bad practice (due to special treatment of
 * strings by the java virtual machine). For internal use this is somewhat acceptable,
 * though. In the long run the credentials should be encrypted within the .properties
 * file and the authenticator class should accepts passwords which are provided as
 * character array. Additionally it should be checked that the authentication mechanism
 * doesn't send the password as plain text (i.e. check if additional properties have to
 * be set).</i>
 *
 * @author Kristian Kutin
 */
public class SendMail {

    /**
     * The default constructor.
     */
    private SendMail() {

        throw new UnsupportedOperationException();
    }

    /**
     * The main method is provided as a means to send mails via console
     * invocation.
     *
     * @param args
     *        command line parmaters
     */
    public static void main(String[] args) {

        String recipientAddress = extractRecipient(args);
        String subject = extractSubject(args);
        String text = extractText(args);

        sendMail(recipientAddress, subject, text);

        System.out.println("Mail sent successfully...");
    }

    /**
     * Checks the specified parameters for validity.
     *
     * @param args
     */
    private static void checkParameters(String[] args) {

        if (args == null) {

            String message = "No parameters were specified (null)!";
            throw new IllegalArgumentException(message);
        }

        if (args.length == 0) {

            String message = "No parameters were specified (empty array)!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks the specified parameters for the e-mail address of a recipient.
     *
     * @param args
     *        all command line parameters
     *
     * @return the e-mail address of a recipient
     */
    private static String extractRecipient(String[] args) {

        checkParameters(args);

        String recipient = args[0];

        if (recipient.trim().isEmpty()) {

            String message = "No parameters were specified (empty string)!";
            throw new IllegalArgumentException(message);
        }

        return recipient;
    }

    /**
     * Checks the specified parameters for a mail subject.
     *
     * @param args
     *        all command line parameters
     *
     * @return a mail subject
     */
    private static String extractSubject(String[] args) {

        checkParameters(args);

        if (args.length < 2) {

            String message = "No subject was specified!";
            throw new IllegalArgumentException(message);
        }

        String subject = args[1];

        if (subject.trim().isEmpty()) {

            String message = "No subject was specified (empty string)!";
            throw new IllegalArgumentException(message);
        }

        return subject;
    }

    /**
     * Checks the specified parameters for a mail text.
     *
     * @param args
     *        all command line parameters
     *
     * @return a mail text
     */
    private static String extractText(String[] args) {

        checkParameters(args);

        if (args.length < 3) {

            String message = "No text was specified!";
            throw new IllegalArgumentException(message);
        }

        String text = args[2];

        if (text.trim().isEmpty()) {

            String message = "No text was specified (empty string)!";
            throw new IllegalArgumentException(message);
        }

        return text;
    }

    /**
     * Send an e-mail to the specified recipient with the specified content (i.e.
     * subect and text). Default sender details are provided in a configuration file.
     *
     * @param aRecipientAddress
     * @param aSubject
     * @param aText
     */
    public static void sendMail(String aRecipientAddress, String aSubject, String aText) {

        sendMail(ConfigurationReader.getMailSmtpHost(), ConfigurationReader.getSenderAddress(),
                 ConfigurationReader.getSenderLogin(), ConfigurationReader.getSenderCredentials(), aRecipientAddress,
                 aSubject, aText);
    }

    /**
     * Send an e-mail to the specified recipient with the specified content (i.e.
     * subect and text). Sender and technical details are also provided as parameters.
     *
     * @param aHostname
     * @param aSenderAddress
     * @param aSenderLogin
     * @param someSenderCredentials
     * @param aRecipientAddress
     * @param aSubject
     * @param aText
     */
    public static void sendMail(String aHostname, String aSenderAddress, String aSenderLogin,
                                String someSenderCredentials, String aRecipientAddress, String aSubject, String aText) {

        SmtpConfiguration configuration = new SmtpConfiguration(aHostname);

        Session session =
            Session.getDefaultInstance(configuration, new SmtpAuthentication(aSenderLogin, someSenderCredentials));

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(aSenderAddress));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(aRecipientAddress));
            message.setSubject(aSubject);
            message.setText(aText);

            Transport.send(message);

        } catch (MessagingException e) {

            throw new MailingException(e);
        }
    }

}


/**
 * This class implements the access to a configuration file.
 *
 * @author Kristian Kutin
 */
final class ConfigurationReader {

    /**
     * The name of a resource bundle.
     */
    private static final String BUNDLE_NAME = SendMail.class.getName();

    /**
     * A property key.
     */
    private static final String SENDER_LOGIN_KEY = "sender.login";

    /**
     * A property key.
     */
    private static final String SENDER_ADDRESS_KEY = "sender.address";

    /**
     * A property key.
     */
    private static final String SENDER_CREDENTIALS_KEY = "mail.credentials";

    /**
     * A property key.
     */
    private static final String MAIL_SMTP_HOST_KEY = "mail.smtp.host";

    /**
     * The default constructor.
     */
    private ConfigurationReader() {

        throw new UnsupportedOperationException();
    }

    /**
     * A getter method.
     *
     * @return a resource bundle
     */
    private static ResourceBundle getResourceBundle() {

        return ResourceBundle.getBundle(BUNDLE_NAME);
    }

    /**
     * A getter method.
     *
     * @return a mail server's host name/ ip address
     */
    public static String getMailSmtpHost() {

        return getResourceBundle().getString(MAIL_SMTP_HOST_KEY);
    }

    /**
     * A getter method.
     *
     * @return the sender's e-mail address
     */
    public static String getSenderAddress() {

        return getResourceBundle().getString(SENDER_ADDRESS_KEY);
    }

    /**
     * A getter method.
     *
     * @return the sender's credentials
     */
    public static String getSenderCredentials() {

        return getResourceBundle().getString(SENDER_CREDENTIALS_KEY);
    }

    /**
     * A getter method.
     *
     * @return the sender's login name
     */
    public static String getSenderLogin() {

        return getResourceBundle().getString(SENDER_LOGIN_KEY);
    }

}
