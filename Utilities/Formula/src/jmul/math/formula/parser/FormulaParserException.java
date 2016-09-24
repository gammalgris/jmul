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

package jmul.math.formula.parser;


/**
 * A special exception type for the the formula parser, which is derived from
 * the base exception type.
 *
 * @author Kristian Kutin
 */
public class FormulaParserException extends RuntimeException {

    /**
     * The default constructor with no parameters.
     */
    public FormulaParserException() {

        super();
    }

    /**
     * A constructor which requires a message which describes the cause
     * of the exception.
     *
     * @param aMessage
     *        a message which contains the cause of the exception
     */
    public FormulaParserException(String aMessage) {

        super(aMessage);
    }

    /**
     * A constructor which wraps an existing exception, which represents
     * the cause of the exception
     *
     * @param aCause
     *        an exception which represents the cause of the exception
     */
    public FormulaParserException(Throwable aCause) {

        super(aCause);
    }

    /**
     * A constructor which requires a message and an excisting exception.
     * Both describe the cause of the exception.
     *
     * @param aMessage
     *        a message which contains the cause of the exception
     * @param aCause
     *        an exception which represents the cause of the exception
     */
    public FormulaParserException(String aMessage, Throwable aCause) {

        super(aMessage, aCause);
    }

}
