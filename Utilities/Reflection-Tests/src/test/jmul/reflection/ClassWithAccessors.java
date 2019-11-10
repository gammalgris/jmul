/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2019  Kristian Kutin
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

package test.jmul.reflection;


/**
 * The class is used for testing purposes (i.e. calling getter- and setter-methods) and
 * contains different constellations of fields and corresponding accessors.
 *
 * @author Kristian Kutin
 */
class ClassWithAccessors {

    /**
     * An integer field.
     */
    private int number;

    /**
     * An integer field.
     */
    private int number2;

    /**
     * An integer field.
     */
    private int number3;

    /**
     * An integer field.
     */
    private int number4;

    /**
     * An integer field.
     */
    private int number5;

    /**
     * A string field.
     */
    private String text;

    /**
     * The default constructor.
     */
    public ClassWithAccessors() {

        super();
    }

    /**
     * Creates a new test object according to the specified parameters.
     *
     * @param aNumber
     * @param aNumber3
     * @param aNumber4
     * @param aNumber5
     * @param aText
     */
    public ClassWithAccessors(int aNumber, int aNumber3, int aNumber4, int aNumber5, String aText) {

        super();

        number = aNumber;
        number3 = aNumber3;
        number4 = aNumber4;
        number5 = aNumber5;

        text = aText;
    }

    /**
     * A setter method.
     *
     * @param aNumber
     */
    public void setNumber(int aNumber) {

        number = aNumber;
    }

    /**
     * A getter method.
     *
     * @return a field value
     */
    public int getNumber() {

        return number;
    }

    /**
     * A setter method.
     *
     * @param aNumber2
     */
    public void setNumber2(int aNumber2) {

        throw new IllegalArgumentException();
    }

    /**
     * A getter method.
     *
     * @return a field value
     */
    public int getNumber2() {

        throw new UnsupportedOperationException();
    }

    /**
     * A setter method.
     *
     * @param aNumber3
     */
    void setNumber3(int aNumber3) {

        number3 = aNumber3;
    }

    /**
     * A getter method.
     *
     * @return a field value
     */
    int getNumber3() {

        return number3;
    }

    /**
     * A setter method.
     *
     * @param aNumber4
     */
    protected void setNumber4(int aNumber4) {

        number4 = aNumber4;
    }

    /**
     * A getter method.
     *
     * @return a field value
     */
    protected int getNumber4() {

        return number4;
    }

    /**
     * A setter method.
     *
     * @param aNumber5
     */
    private void setNumber5(int aNumber5) {

        number5 = aNumber5;
    }

    /**
     * A getter method.
     *
     * @return a field value
     */
    private int getNumber5() {

        return number5;
    }

    /**
     * A setter method.
     *
     * @param aText
     */
    public void setText(String aText) {

        text = aText;
    }

    /**
     * A getter method.
     *
     * @return a field value
     */
    public String getText() {

        return text;
    }

}
