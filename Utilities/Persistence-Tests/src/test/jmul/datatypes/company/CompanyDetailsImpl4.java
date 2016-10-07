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

package test.jmul.datatypes.company;


import jmul.persistence.annotations.RootNode;


/**
 * An implementation of company details.<br>
 * <br>
 * <i>Note:<br />
 * This class is used for testing purposes only.
 * <ol>
 *   <li>has no public default constructor</li>
 *   <li>all getter methods are public</li>
 *   <li>all setter methods are public</li>
 *   <li>can be serialized on its own</li>
 *   <li>cannot be deserialized because of (1)</li>
 * </ol></i>
 *
 * @author Kristian Kutin
 */
@RootNode(declaredType = CompanyDetails.class)
public class CompanyDetailsImpl4 extends CompanyDetailsImpl3 implements CompanyDetails {

    /**
     * The company's name.
     */
    private String companyName;

    /**
     * The company's loaction (i.e. country).
     */
    private String location;

    /**
     * The company's year of foundation.
     */
    private String foundationYear;

    /**
     * Constructs company details.
     *
     * @param aCompanyName
     *        the company's name
     * @param aLocation
     *        the company's loaction
     * @param aFoundationYear
     *        the company's year of foundation
     */
    public CompanyDetailsImpl4(String aCompanyName, String aLocation, String aFoundationYear) {

        super();

        companyName = aCompanyName;
        location = aLocation;
        foundationYear = aFoundationYear;
    }

    /**
     * A setter method.
     *
     * @param companyName
     *        a company's name
     */
    public void setCompanyName(String companyName) {

        this.companyName = companyName;
    }

    /**
     * A getter method.
     *
     * @return a company's name
     */
    public String getCompanyName() {

        return companyName;
    }

    /**
     * A setter method.
     *
     * @param location
     *        a company's location
     */
    public void setLocation(String location) {

        this.location = location;
    }

    /**
     * A getter method.
     *
     * @return a company's location
     */
    public String getLocation() {

        return location;
    }

    /**
     * A setter method.
     *
     * @param foundationYear
     *        a company's year of foundation
     */
    public void setFoundationYear(String foundationYear) {

        this.foundationYear = foundationYear;
    }

    /**
     * A getter method.
     *
     * @return a company's year of foundation
     */
    public String getFoundationYear() {

        return foundationYear;
    }

}
