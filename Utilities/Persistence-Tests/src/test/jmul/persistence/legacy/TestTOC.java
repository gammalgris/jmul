/*
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

package test.jmul.persistence.legacy;


import java.util.Map;

import jmul.persistence.transformation.TransformationHelper;

import test.jmul.datatypes.legacy.company.CompanyDetails;
import test.jmul.datatypes.legacy.company.CompanyDetailsImpl4;
import test.jmul.datatypes.legacy.employee.EmployeeImpl6;

import jmul.transformation.TransformationFactory;
import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationResources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * A collection of tests concenring a subset of transformations.
 *
 * @author Kristian Kutin
 */
public class TestTOC extends SerializationBase {

    /**
     * Test the creation of a TOC.
     */
    @Test
    public void testSimpleTOC() {

        CompanyDetails details = new CompanyDetailsImpl4("Microsoft", "USA", "1975");
        details.addEmployee(new EmployeeImpl6("John", "Doe", "1.1.2000", "male", 1000.00f, "Engineer"));
        details.addEmployee(new EmployeeImpl6("Jane", "Doe", "2.2.2002", "female", 800.00f, "Engineer"));

        TransformationFactory factory = TransformationResources.getTransformationFactory();
        TransformationPath path = new TransformationPath("Object", "TOC");
        TransformationParameters parameters = TransformationHelper.newTransformationParameters(path, details);

        Map<String, String> toc = (Map<String, String>) factory.transform(parameters);

        assertTrue(toc != null);
        assertTrue(toc.size() == 3);

        assertTrue(toc.containsKey("companyName"));
        assertTrue(toc.containsKey("location"));
        assertTrue(toc.containsKey("foundationYear"));

        assertEquals(toc.get("companyName"), "Microsoft");
        assertEquals(toc.get("location"), "USA");
        assertEquals(toc.get("foundationYear"), "1975");
    }

    /**
     * The main method is only provided for purposes of manual testing.
     *
     * @param args
     *        command line arguments
     */
    public static void main(String[] args) {

        TestTOC test = new TestTOC();
        test.setUp();

        test.testSimpleTOC();

        test.tearDown();

        System.out.println("done.");
    }

}
