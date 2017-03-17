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

package jmul.document;


import java.io.IOException;

import jmul.document.content.Structure;


/**
 * An implementation of a generic document.
 *
 * @author Kristian Kutin
 */
public class GenericDocumentImpl extends DocumentBase<GenericStructure> {

    /**
     * Details about the document's structure.
     */
    private final GenericStructure structure;

    /**
     * Creates a new generic document according to the specified parameters.
     *
     * @param aFileName
     *
     * @throws IOException
     */
    public GenericDocumentImpl(String aFileName) throws IOException {

        super(aFileName);

        structure = new GenericStructure();
    }

    /**
     * Returns a document's structure.
     *
     * @return a document structure
     */
    @Override
    public GenericStructure getStructure() {

        return structure;
    }

}


/**
 * An implementation of a generic document structure.
 *
 * @author Kristian Kutin
 */
class GenericStructure implements Structure {

    /**
     * A description text.
     */
    private static final String STRUCTURE_DESCRIPTION =
        "No details about the document's internal structure are available.";

    /**
     * Returns a string representation for this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return STRUCTURE_DESCRIPTION;
    }

}
