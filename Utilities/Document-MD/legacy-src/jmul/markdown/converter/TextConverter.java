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

package jmul.markdown.converter;


import java.io.File;


/**
 * This interface describes an entity which converts the content of a text file.
 *
 * @author Kristian Kutin
 */
public interface TextConverter {

    /**
     * The text content of the specified source file is converted and written
     * to the specified output file.
     *
     * @param aSourceFileName
     * @param anOutputFileName
     */
    void convert(String aSourceFileName, String anOutputFileName);

    /**
     * The text content of the specified source file is converted and written
     * to the specified output file.
     *
     * @param aSourceFile
     * @param anOutputFile
     */
    void convert(File aSourceFile, File anOutputFile);

}
