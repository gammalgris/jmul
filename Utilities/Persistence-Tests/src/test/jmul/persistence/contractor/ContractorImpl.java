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

package test.jmul.persistence.contractor;


/**
 * An implementation of a contractor.<br>
 * <br>
 * <i>This class is used for testing purposes only.</i>
 *
 * @author Kristian Kutin
 */
public class ContractorImpl extends ContractorBase {

    /**
     * The number of currently active contracts.
     */
    private int currentContracts;

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
    public ContractorImpl(String aFirstName, String aLastName,
                          String aBirthdate, String aGender) {

        super(aFirstName, aLastName, aBirthdate, aGender);

        currentContracts = 0;
    }

    /**
     * Sets the number of curretnly active contracts.
     *
     * @param contracts
     *        number of curretnly active contracts
     */
    public void setCurrentContracts(int contracts) {

        currentContracts = contracts;
    }

    /**
     * Returns the number of curretnly active contracts.
     *
     * @return number of curretnly active contracts
     */
    public int getCurrentContracts() {

        return currentContracts;
    }

}
