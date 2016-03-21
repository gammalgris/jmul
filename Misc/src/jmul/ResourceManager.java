/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul;


import jmul.logging.ApplicationLogger;
import jmul.xml.reader.XmlDocumentReader;


/**
 * The Interface ResourceManager describes the behaviour of an entity which
 * manages various resources. Access to these resources is centralized and
 * can be adressed via the ResourceManager.
 * Examples of resources are loggers, factories, database connections, etc.
 *
 * @author Kristian Kutin
 */
@Deprecated
public interface ResourceManager {

    /**
     * The method getApplicationLogger grants access to the currently used
     * logger instance.
     *
     * @return a reference on the currently used logger
     */
    ApplicationLogger getApplicationLogger();

    /**
     * The method getXMLDocumentReader grants acces to the currently used
     * xml document reader.
     *
     * @return a reference on a xml document reader
     */
    XmlDocumentReader getXMLDocumentReader();

}
