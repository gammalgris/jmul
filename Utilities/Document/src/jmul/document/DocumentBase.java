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

package jmul.document;


import jmul.document.structure.Structure;
import jmul.document.type.DocumentType;


/**
 * A base implementation of a document.
 *
 * @author Kristian Kutin
 */
public abstract class DocumentBase<T extends Structure> implements Document<T> {

    /**
     * The document type.
     */
    private final DocumentType documentType;

    /**
     * Creates a new document object according to the specified parameters.
     *
     * @param aDocumentType
     */
    protected DocumentBase(DocumentType aDocumentType) {

        super();

        documentType = aDocumentType;
    }

    /**
     * Returns the document type of the document.
     *
     * @return a document type
     */
    @Override
    public DocumentType getDocumentType() {

        return documentType;
    }

}
