/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package jmul.io.text;


/**
 * This utility class contains the buffer which was read.
 *
 * @author Kristian Kutin
 */
public final class ReadBuffer {

    /**
     * The content which was read until a line separator or the end of
     * file was encountered.
     */
    private final String line;

    /**
     * A flag indicating that the end of file has been reached.
     */
    private final boolean endOfFile;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aCharSequence
     */
    public ReadBuffer(CharSequence aCharSequence) {

        this(aCharSequence, false);
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aCharSequence
     * @param anEndOfFileFlag
     */
    public ReadBuffer(CharSequence aCharSequence, boolean anEndOfFileFlag) {

        line = aCharSequence.toString();
        endOfFile = anEndOfFileFlag;
    }

    /**
     * Returns the line which was read.
     *
     * @return a line
     */
    public String getLine() {

        return line;
    }

    /**
     * Returns a boolean value indicating that the end of file has been reached
     * while reading from a file.
     *
     * @return <code>true</code> if the end of file has been reached,
     *         else <code>false</code>
     */
    public boolean isEndOfFile() {

        return endOfFile;
    }

    /**
     * Returns a boolean value indicating that the buffer is empty.
     *
     * @return <code>true</code> if the buffer is empty,
     *         else <code>false</code>
     */
    public boolean isEmpty() {

        return (line == null) || (line.isEmpty());
    }

}
