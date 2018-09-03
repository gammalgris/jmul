/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

package test.jmul.xml;


import java.io.IOException;

import jmul.test.classification.ManualTest;

import jmul.xml.reader.XmlDocumentReader;
import jmul.xml.reader.XmlDocumentReaderImpl;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;


/**
 * A manual test to provoke parser exceptions.
 *
 * @author Kristian Kutin
 */
@ManualTest
public class ParserErrorTest {

    public static void main(String[] args) {

        XmlDocumentReader reader = new XmlDocumentReaderImpl();
        //String fileName = "testdata-xml\\empty.xml";
        //String fileName = "testdata-xml\\header-only.xml";
        //String fileName = "testdata-xml\\invalid-root.xml";
        //String fileName = "testdata-xml\\invalid-root2.xml";
        //String fileName = "testdata-xml\\invalid-root3.xml";
        String fileName = "testdata-xml\\multi-root.xml";

        try {

            Document document = reader.readFrom(fileName);

        } catch (IOException e) {

            e.printStackTrace();

        } catch (SAXException e) {

            e.printStackTrace();
        }
    }

}
