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

package test.jmul.document;


import java.io.IOException;

import jmul.document.Document;
import jmul.document.GenericDocumentImpl;

import jmul.test.classification.ManualTest;


/**
 * A test to check the general properties of a document.
 *
 * @author Kristian Kutin
 *
 * @deprecated delete before checking in
 */
@Deprecated
@ManualTest
public class Test {

    public static void main(String[] args) {

        try {

            GenericDocumentImpl gdocument = new GenericDocumentImpl("D:\\readme.txt");
            Document document = gdocument;

            System.out.println(gdocument.getStructure());

            System.out.println(gdocument.getMetaData());
            System.out.println(gdocument.getDocumentType());
            System.out.println("done.");

        } catch (IOException e) {
        }
    }

}
