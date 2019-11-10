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

package jmul.markdown.buffer;


import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

import static jmul.misc.checks.ParameterCheckHelper.checkListenerParameter;


/**
 * An implementation of {@link jmul.markdown.buffer.TextBuffer}.
 *
 * @author Kristian Kutin
 */
public class TextBufferImpl implements TextBuffer {

    /**
     * All listeners.
     */
    private Collection<BufferChangeListener> listeners;

    /**
     * The actual text buffer.
     */
    private StringBuilder buffer;

    /**
     * The actual stream that is being read.
     */
    private BufferedReader reader;

    /**
     * The line which been read most recently.
     */
    private String currentLine;

    /**
     * A flag indicating that the end of the stream has been reached.
     */
    boolean eos;

    /**
     * Creates a new instance according to the specified parameters
     *
     * @param aReader
     */
    public TextBufferImpl(BufferedReader aReader) {

        buffer = new StringBuilder();

        listeners = new ArrayList<BufferChangeListener>();

        reader = aReader;
        currentLine = null;
        eos = false;
    }

    /**
     * Checks if the end of the input stream (e.g. a file) has been
     * reached.
     *
     * @return <code>true</code> if the end of the input stream has benn
     *         reached, else <code>false</code>
     */
    @Override
    public boolean hasReachedEOS() {

        return eos;
    }

    /**
     * Reads the next line from the input stream.
     *
     * @throws IOException
     *         is thrown if an error occurs while trying to read from a stream
     */
    @Override
    public void readNextLine() throws IOException {

        currentLine = reader.readLine();

        if (currentLine == null) {

            eos = true;

        } else {

            buffer.append(currentLine);
            informListeners();
        }
    }

    /**
     * Adds the specified listener.
     *
     * @param aListener
     */
    @Override
    public void addBufferChangeListener(BufferChangeListener aListener) {

        checkListenerParameter(aListener);

        listeners.add(aListener);
    }

    /**
     * Removes the specified listener.
     *
     * @param aListener
     */
    @Override
    public void removeBufferChangeListener(BufferChangeListener aListener) {

        checkListenerParameter(aListener);

        listeners.remove(aListener);
    }

    /**
     * Informs all listeners that the buffer has changed.
     */
    private void informListeners() {

        String text = toString();

        for (BufferChangeListener listener : listeners) {

            listener.informOnChange(text);
        }
    }

    /**
     * Returns the buffer's current text content.
     *
     * @return the current text content
     */
    @Override
    public String toString() {

        return buffer.toString();
    }

}
