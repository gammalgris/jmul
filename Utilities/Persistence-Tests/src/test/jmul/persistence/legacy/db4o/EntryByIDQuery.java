/*
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

package test.jmul.persistence.legacy.db4o;


import com.db4o.query.Predicate;

import jmul.misc.id.ID;


/**
 * A custom native query for db4o.
 *
 * @author Kristian Kutin
 */
public class EntryByIDQuery extends Predicate<DB4OEntry> {

    /**
     * The ID which is looked for.
     */
    private ID id;

    /**
     * Constructs this native query.
     *
     * @param anID
     * the id which is looked for
     */
    public EntryByIDQuery(ID anID) {

        id = anID;
    }

    /**
     * Checks if the specified entry has the required ID.
     *
     * @param anEntry
     * an entry
     *
     * @return <code>true</code> if the specified entry has the required ID,
     * else <code>false</code>
     */
    public boolean match(DB4OEntry anEntry) {

        return anEntry.getId().equals(id);
    }

}
