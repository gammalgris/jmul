/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.misc.text;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jmul.checks.ParameterCheckHelper;


/**
 * An implementation of a modifable text container.
 *
 * @author Kristian Kutin
 */
public class ModifiableTextImpl extends TextBase implements ModifiableText {

    /**
     * The default constructor.
     */
    public ModifiableTextImpl() {

        this(new ArrayList<String>());
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param allLines
     *        the whole text
     */
    public ModifiableTextImpl(String... allLines) {

        this(Arrays.asList(ParameterCheckHelper.checkStringArrayParameter(allLines)));
    }

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param allLines
     *        the whole text
     */
    public ModifiableTextImpl(List<String> allLines) {

        super(ParameterCheckHelper.checkStringListParameter(allLines), READ_AND_WRITE_ACCESS);
    }

    /**
     * Replaces the line at the specified line index with the specified
     * line.
     *
     * @param aLineIndex
     *        a line index
     * @param aReplacementLine
     *        the new line
     *
     * @return the old line
     */
    @Override
    public String replaceLine(int aLineIndex, String aReplacementLine) {

        return getContent().set(aLineIndex, aReplacementLine);
    }

    /**
     * Appends a new line at the end of the text.
     *
     * @param aNewLine
     *        the new line
     */
    @Override
    public void addLine(String aNewLine) {

        getContent().add(aNewLine);
    }

    /**
     * Inserts the specified line at the specified index.
     *
     * @param aLineIndex
     *        a line index
     * @param aNewLine
     *        the new line
     */
    @Override
    public void insertLine(int aLineIndex, String aNewLine) {

        getContent().add(aLineIndex, aNewLine);
    }

    /**
     * Removes the line at the specified index.
     *
     * @param aLineIndex
     *        a line index
     *
     * @return the old line
     */
    @Override
    public String removeLine(int aLineIndex) {

        return getContent().remove(aLineIndex);
    }

}
