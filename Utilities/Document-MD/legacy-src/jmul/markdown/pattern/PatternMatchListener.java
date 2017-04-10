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

package jmul.markdown.pattern;


import java.util.List;


/**
 * This interface describes an listener that is informed if a matching
 * pattern has been identified.
 *
 * @author Kristian Kutin
 */
public interface PatternMatchListener {

    /**
     * Informs this listener if a matching pattern was encountered.
     *
     * @param aMatcher
     * @param someActualMatches
     */
    void informOnMatch(PatternMatcher aMatcher, List<String> someActualMatches);

}
