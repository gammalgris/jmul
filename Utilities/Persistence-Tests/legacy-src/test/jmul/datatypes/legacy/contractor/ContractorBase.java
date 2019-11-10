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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.datatypes.legacy.contractor;


import jmul.transformation.xml.annotations.RootNode;

import test.jmul.datatypes.legacy.person.PersonImpl;


/**
 * An implementation of a contractor.<br>
 * <br>
 * <i>This code is used for testing purposes only.</i>
 *
 * @author Kristian Kutin
 */
@RootNode(declaredType = Contractor.class)
public abstract class ContractorBase extends PersonImpl implements Contractor {

    /**
     * The default constructor.
     */
    public ContractorBase() {

        super();
    }

    /**
     * Constructs a contractor.
     *
     * @param aFirstName
     *        a contractor's first name
     * @param aLastName
     *        a contractor's last name
     * @param aBirthdate
     *        a contractor's birth date
     * @param aGender
     *        a contractor's gender
     */
    public ContractorBase(String aFirstName, String aLastName, String aBirthdate, String aGender) {

        super(aFirstName, aLastName, aBirthdate, aGender);
    }

}
