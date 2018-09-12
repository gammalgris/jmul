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

package jmul.web;


import static jmul.checks.ParameterCheckHelper.checkObjectParameter;


/**
 * A handler for sigint (Ctrl+C) events.
 *
 * @author Kristian Kutin
 */
class SigintHandler extends Thread {

    /**
     * A reference to a server instance
     */
    private final WebServer server;

    /**
     * The default constructor.
     */

    /**
     * Creates a new sigint handler according to the specified parameters.
     *
     * @param aServer
     *        a reference to a server instance
     */
    public SigintHandler(WebServer aServer) {

        super();

        checkObjectParameter(aServer);

        server = aServer;

        String name = aServer.getClass().getSimpleName() + "#" + this.getClass().getSimpleName();
        setName(name);
    }

    /**
     * Initiates the shutdown of this web server instance.
     */
    @Override
    public void run() {

        server.stopServer();
    }

}
