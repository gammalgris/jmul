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

package jmul.measures.definitions.reader;


import java.util.List;


/**
 * A Helper class to store informations which is read from a definition file.
 *
 * @author Kristian Kutin
 */
public final class Definition {

    /**
     * The class member contains a category.
     */
    public final String category;

    /**
     * The class member contains a set of unit definitions.
     */
    private final List<Unit> units;

    /**
     * The default constructor.
     *
     * @param aCategory
     *        a category
     * @param someUnits
     *        a set of unit definitions
     */
    protected Definition(String aCategory, List<Unit> someUnits) {

        category = aCategory;
        units = someUnits;
    }

    /**
     * The method returns the unit at a given index.
     *
     * @param anIndex
     *        the index for an entry
     *
     * @return a unit at the given index
     */
    public Unit getUnit(int anIndex) {

        return units.get(anIndex);
    }

    /**
     * The method returns the number of entries for units.
     *
     * @return number of entries
     */
    public int units() {

        return units.size();
    }

}
