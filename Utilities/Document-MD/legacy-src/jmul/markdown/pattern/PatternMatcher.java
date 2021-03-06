/*
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.markdown.pattern;


import jmul.markdown.buffer.BufferChangeListener;


/**
 * This interface describes an entity which alerts a listener if a
 * specific pattern is identified.
 *
 * @author Kristian Kutin
 */
public interface PatternMatcher extends BufferChangeListener {

    /**
     * Returns the actual pattern.
     *
     * @return a pattern
     */
    String getPattern();

    /**
     * Adds the specified listener.
     *
     * @param aListener
     */
    void addPatternMatchListener(PatternMatchListener aListener);

    /**
     * Removes the specified listener.
     *
     * @param aListener
     */
    void removePatternMatchListener(PatternMatchListener aListener);

}
