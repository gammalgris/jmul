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

package test.jmul.web;

import jmul.test.classification.ManualTest;

import jmul.web.WebServer;
import jmul.web.WebServerImpl;


/**
 * This class contains tests regarding running a web server instance.
 *
 * @author Kristian Kutin
 */
@ManualTest
public class WebServerTest {

    /**
     * Starts the web server. The specified command line arguments are not processed.
     *
     * @param args
     *        some command line arguments
     */
    public static void main(String[] args) {

        WebServer webServer = new WebServerImpl(WebServer.class.getName());
        webServer.startServer();
    }

}