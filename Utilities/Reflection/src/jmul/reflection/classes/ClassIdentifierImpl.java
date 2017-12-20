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

package jmul.reflection.classes;


import jmul.string.TextHelper;


class ClassIdentifierImpl implements ClassIdentifier {

    /**
     * The class member contains athe class name.
     */
    private final String classname;

    /**
     * The class member may contain an alternate class path.
     */
    private final String alternateClasspath;

    /**
     * The default constructor.
     *
     * @param aClassname
     *        the name of a class
     */
    ClassIdentifierImpl(String aClassname) {

        this(aClassname, null);
    }

    /**
     * A constructor.
     *
     * @param aClassname
     *        the name of a class
     * @param aClasspath
     *        the classpath if this class is not found on the default classpath
     */
    ClassIdentifierImpl(String aClassname, String aClasspath) {

        classname = aClassname;
        alternateClasspath = aClasspath;
    }

    /**
     * The method returns the classname.
     *
     * @return a classname
     */
    @Override
    public String getClassname() {

        return classname;
    }

    /**
     * The method determines if this class is located on the default classpath.
     *
     * @return true, if this class is located on the default classpath, else
     *         false
     */
    @Override
    public boolean isLocatedOnDefaultClasspath() {

        return alternateClasspath == null;
    }

    /**
     * The method determines if this class is located on an alternate classpath.
     *
     * @return true, if this class is located on an alternate classpath, else
     *         false
     */
    @Override
    public boolean isLocatedOnAlternateClasspath() {

        return !isLocatedOnDefaultClasspath();
    }

    /**
     * The method returns the alternate classpath where this class is located.
     *
     * @return an alternative classpath or <code>null</code>
     */
    @Override
    public String getAlternateClasspath() {

        return alternateClasspath;
    }

    /**
     * The method checks if this object equals the specified object.
     *
     * @param o
     *        an object
     *
     * @return true, if this object equals the specified object, else false
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;

        } else if (o == this) {

            return true;

        } else if (o instanceof ClassIdentifier) {

            ClassIdentifier other = (ClassIdentifier) o;

            boolean sameNames = this.getClassname().equals(other.getClassname());
            boolean defaultClasspath = this.isLocatedOnDefaultClasspath() && other.isLocatedOnDefaultClasspath();
            boolean sameAlternateClasspath =
                this.isLocatedOnAlternateClasspath() && other.isLocatedOnAlternateClasspath() &&
                this.getAlternateClasspath().equals(other.getAlternateClasspath());

            return sameNames && (defaultClasspath || sameAlternateClasspath);
        }

        return false;
    }

    /**
     * The method calculates a hash code.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        int hash = 7;
        int tmp;

        hash = 23 * hash + getClassname().hashCode();

        if (getAlternateClasspath() != null) {

            tmp = getAlternateClasspath().hashCode();

        } else {

            tmp = 0;
        }

        hash = 23 * hash + tmp;

        return hash;
    }

    /**
     * The method returns a string representation of this entity.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder representation = new StringBuilder(getClassname());

        if (isLocatedOnAlternateClasspath()) {

            TextHelper.append2StringBuilder(representation, " (", getAlternateClasspath(), ")");
        }

        return representation.toString();
    }

}
