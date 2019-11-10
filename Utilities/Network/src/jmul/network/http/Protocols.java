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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.network.http;


/**
 * This enumeration contains entries for various protocols.
 *
 * @author Kristian Kutin
 */
public enum Protocols {


    HTTP("http"),
    HTTPS("https"), ;


    /**
     * Contains a protocol name.
     */
    private final String protocolName;


    /**
     * Creates a new enumeration element.
     *
     * @param aProtocolName
     *        the name of a protocol
     */
    private Protocols(String aProtocolName) {

        protocolName = aProtocolName;
    }

    /**
     * A getter method.
     *
     * @return a protocol name
     */
    public String getProtocolName() {

        return protocolName;
    }

    /**
     * Checks if the specified name equals this protocol name.
     *
     * @param aName
     *        the name of a protocol
     *
     * @return <code>true</code> if the specified name equals this protocol name,
     *         else <code>false</code>
     */
    public boolean equalsProtocolName(String aName) {

        return getProtocolName().equals(aName);
    }

    /**
     * Returns a string representation for this enumeration element.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getProtocolName();
    }

}
