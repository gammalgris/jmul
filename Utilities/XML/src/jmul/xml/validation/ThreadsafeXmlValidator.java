/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.xml.validation;


/**
 * A threadsafe implemetation of a validator.
 *
 * @author Kristian Kutin
 */
public class ThreadsafeXmlValidator implements XmlValidator {

    /**
     * The actual validator.
     */
    private XmlValidator validator;

    /**
     * Creates a new validator.
     *
     * @param aValidator
     */
    public ThreadsafeXmlValidator(XmlValidator aValidator) {

        validator = aValidator;
    }

    /**
     * Checks the specified file versus one or more internally managed XML
     * schemas.
     *
     * @param aFileName
     *
     * @return a validation result
     *
     * @throws ValidationException
     *         is thrown if the validation fails
     */
    @Override
    public XmlValidationResult validate(String aFileName) {

        XmlValidationResult result;

        synchronized (this) {

            result = validator.validate(aFileName);
        }

        return result;
    }

}
