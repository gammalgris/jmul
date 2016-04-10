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

package jmul.cache.transformation;


/**
 * A cache entry.
 *
 * @author Kristian Kutin
 */
public class CacheEntry {

    /**
     * An object.
     */
    private final Object object;

    /**
     * The declared type of the object.
     */
    private final Class declaredType;

    /**
     * Constructs a cache entry.
     *
     * @param anObject
     *        an object
     * @param aDeclaredType
     *        the declared type of the object
     */
    public CacheEntry(Object anObject, Class aDeclaredType) {

        object = anObject;
        declaredType = aDeclaredType;
    }

    /**
     * Returns the object.
     *
     * @return the object
     */
    public Object getObject() {

        return object;
    }

    /**
     * Returns the declared type of the object.
     *
     * @return the declared type of the object
     */
    public Class getDeclaredType() {

        return declaredType;
    }

    /**
     * The method checks if this cache entry equals the specified object.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if the cache entry equals the specified object,
     *         else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (o == this) {

            return true;
        }

        if (o instanceof CacheEntry) {

            CacheEntry other = (CacheEntry)o;

            // This method will compare two cache entries by comparing the
            // type and content of the cached object.

            if (this.getDeclaredType().equals(other.getDeclaredType())) {

                // Since the types are already determined to be equal only the
                // contents remain to be compared.
                //
                // This cache is used to keep track of objects which have been
                // already serialized during the serialization process. It is
                // important that this cache contains only unique objects (in
                // order to be able to serialize object graphs which contain
                // circular object references).
                //
                // For some objects this comparison can simply be done by using
                // their equals method. Others might need some special
                // treatment.

                if (Iterable.class.isAssignableFrom(this.getDeclaredType())) {

                    // Collections and similar containers need a special check,
                    // since their equals method will declare two empty
                    // collections as equal. This will cause data to not be
                    // properly serialized which will only be noticed after
                    // deserialization.

                    return (this.getObject() == other.getObject());
                }

                return this.getObject().equals(other.getObject());
            }
        }

        return false;
    }

    /**
     * The method calculates a hash value.
     *
     * @return a hash value
     */
    @Override
    public int hashCode() {

        return getObject().hashCode();
    }

    /**
     * The method returns a strin representation for this cache entry.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getObject().toString();
    }

}
