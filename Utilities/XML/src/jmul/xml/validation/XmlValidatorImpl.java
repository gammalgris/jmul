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


import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.ValidationException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * An implementation of a validator.
 *
 * @author Kristian Kutin
 */
public class XmlValidatorImpl implements XmlValidator {

    /**
     * The archive which contains all XML schemas.
     */
    private SchemaArchive archive;

    /**
     * Creates a new validator with the specified parameters.
     *
     * @param anArchive
     */
    public XmlValidatorImpl(SchemaArchive anArchive) {

        archive = anArchive;
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

        if (aFileName == null) {

            String message = "No file name was specified (null)!";
            throw new IllegalArgumentException(message);

        } else if (aFileName.trim().isEmpty()) {

            String message = "No file name was specified (empty string)!";
            throw new IllegalArgumentException(message);
        }


        Collection<XmlValidationResult> results = new ArrayList<XmlValidationResult>();

        Exception cause = null;

        for (String schemaKey : archive.getSchemaKeys()) {

            Schema schema = archive.getSchema(schemaKey);
            Validator validator = schema.newValidator();

            try {

                validator.validate(new SAXSource(new InputSource(aFileName)));
                return new XmlValidationResult(aFileName, schemaKey);

            } catch (SAXException e) {

                cause = e;

            } catch (IOException e) {

                cause = e;
            }

            XmlValidationResult result = new XmlValidationResult(aFileName, schemaKey, cause);
            results.add(result);
        }

        throw new XmlValidationException(aFileName, results);
    }

}
