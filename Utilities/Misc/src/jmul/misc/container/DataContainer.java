/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.misc.container;


/**
 * This interface describes a simple data container. This entity is similar to a
 * map but provides only a small fraction of the functionality.<br />
 * <br />
 * <i>Note:<br />
 * There are several cases where a struct is required, but only for a short and
 * limited use. Instead of creating a class each time which contains either
 * public members or getter- and setter-methods this entity can be used.</i>
 *
 * @author Kristian Kutin
 */
public interface DataContainer<K, V> {

    /**
     * Retrieves the value which is associated with the specified key. If the
     * key is unknown then an exception is thrown.
     *
     * @param key
     *
     * @return the value which is associated with the specified key
     */
    V getValue(K key);

    /**
     * Adds a new key-value pair or updates an existing key-value par.
     *
     * @param key
     * @param value
     */
    void setValue(K key, V value);

}
