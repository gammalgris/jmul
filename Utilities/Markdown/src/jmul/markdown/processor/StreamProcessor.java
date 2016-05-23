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

package jmul.markdown.processor;


import jmul.io.CoupledStreams;

import jmul.markdown.pattern.PatternMatchListener;


/**
 * This interface describes an entity which process an input stream and
 * writes the result to an output stream.
 *
 * @author Kristian Kutin
 */
public interface StreamProcessor extends PatternMatchListener {

    /**
     * Processes the specified input stream and writes the output to
     * the specified output stream.
     *
     * @param someCoupledStream
     */
    void processFile(CoupledStreams someCoupledStream);

}
