/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.transformation.xml.id;


import jmul.misc.id.ID;
import jmul.misc.id.IDGenerator;
import jmul.misc.id.IntegerID;


/**
 * This interface describes an entity that generates an ID (see strategy
 * pattern). An implementation must provide rules to compute an id.
 *
 * @author Kristian Kutin
 */
public class IntegerIDGenerator implements IDGenerator {

    /**
     * The next available id.
     */
    private int nextID;

    /**
     * The default constructor.
     */
    public IntegerIDGenerator() {

        nextID = 0;
    }

    /**
     * The method creates an object which contains an id.
     *
     * @return an object containing an id
     */
    @Override
    public ID generateID() {

        ID id = new IntegerID(nextID);
        nextID++;

        return id;
    }

}
