/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2013  Kristian Kutin
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

package jmul.transformation.configuration;


import java.io.IOException;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;

import jmul.transformation.TransformationRule;


/**
 * This interface describes an entity which parses rule configuration files.
 *
 * @author Kristian Kutin
 */
public interface ConfigurationReader {

    /**
     * Parses the configuration of a transformation rule.
     *
     * @param aFilename
     *        the filename of the transformation rule configuration
     *
     * @return a transformation rule
     *
     * @throws SAXException
     *         This exception is thrown when the configuration file is invalid
     * @throws IOException
     *         This exception is thrown when the configuration file couldn't be
     *         read
     */
    TransformationRule parseConfiguration(String aFilename) throws SAXException,
                                                                   IOException;

    /**
     * Parses the configuration of a transformation rule.
     *
     * @param aDocument
     *        an xml document
     *
     * @return a transformation rule
     *
     * @throws SAXException
     *         This exception is thrown when the configuration file is invalid
     * @throws IOException
     *         This exception is thrown when the configuration file couldn't be
     *         read
     */
    TransformationRule parseConfiguration(Document aDocument) throws SAXException,
                                                                     IOException;

}
