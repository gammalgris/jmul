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

package jmul.misc.container;

import java.util.HashMap;
import java.util.Map;


/**
 * An implementation of a data container.
 *
 * @author Kristian Kutin
 */
public class DataContainerImpl<K, V> implements DataContainer<K, V> {

    /**
     * The actual data container.
     */
    private Map<K, V> container;

    /**
     * The default constructor.
     */
    public DataContainerImpl() {

        container = new HashMap<>();
    }

    /**
     * Retrieves the value which is associated with the specified key. If the
     * key is unknown then an exception is thrown.
     *
     * @param key
     *        the key for the requested value object
     *
     * @return the value which is associated with the specified key
     */
    @Override
    public V getValue(K key) {

        boolean unknownKey = !container.containsKey(key);

        if (unknownKey) {

            String message = "No entry exists for the specified key \"" + key + "\"!";
            throw new IllegalArgumentException(message);
        }

        return container.get(key);
    }

    /**
     * Adds a new key-value pair or updates an existing key-value par.
     *
     * @param key
     *        the key for the requested value object
     * @param value
     *        the value object
     */
    @Override
    public void putValue(K key, V value) {

        container.put(key, value);
    }

}
