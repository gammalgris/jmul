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

package jmul.string;


/**
 * This interface describes a message entity which is configurable (i.e.
 * placeholders have to be provided).
 *
 * @author Kristian Kutin
 */
public interface ConfigurableMessage {

    /**
     * The method resolves the specified placeholders.
     *
     * @param someItems
     *        key-value pairs of placeholders and their replacements
     */
    void resolvePlaceholder(String... someItems);

    /**
     * The method determines if there are still some unresolved palceholders.
     *
     * @return <code>true</code> if there are some unresolved placeholders,
     *         else <code>false</code>
     */
    boolean existUnresolvedPlaceholders();

}
