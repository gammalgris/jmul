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

package jmul.markdown.buffer;


/**
 * This interface describes an entity that buffers text.
 *
 * @author Kristian Kutin
 */
public interface TextBuffer {

    /**
     * Checks if the end of the input stream (e.g. a file) has been
     * reached.
     *
     * @return <code>true</code> if the end of the input stream has benn
     *         reached, else <code>false</code>
     */
    boolean hasReachedEOS();

    /**
     * Reads the next line from the input stream.
     */
    void readNextLine();

    /**
     * Adds the specified listener.
     *
     * @param aListener
     */
    void addBufferChangeListener(BufferChangeListener aListener);

    /**
     * Removes the specified listener.
     *
     * @param aListener
     */
    void removeBufferChangeListener(BufferChangeListener aListener);

}
