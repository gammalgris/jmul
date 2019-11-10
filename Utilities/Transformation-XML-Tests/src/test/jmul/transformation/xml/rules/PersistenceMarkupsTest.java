/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.transformation.xml.rules;


import java.util.ArrayList;
import java.util.Collection;

import jmul.test.classification.ConfigurationTest;

import static jmul.transformation.xml.rules.PersistenceMarkups.DECLARED_ELEMENT_TYPE_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.DECLARED_KEY_TYPE_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.DECLARED_TYPE_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.DECLARED_VALUE_TYPE_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.ELEMENT_ELEMENT;
import static jmul.transformation.xml.rules.PersistenceMarkups.ENTRY_ELEMENT;
import static jmul.transformation.xml.rules.PersistenceMarkups.FIELD_ELEMENT;
import static jmul.transformation.xml.rules.PersistenceMarkups.FORMAT_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.ID_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.NAME_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.OBJECTS_ELEMENT;
import static jmul.transformation.xml.rules.PersistenceMarkups.OBJECT_ELEMENT;
import static jmul.transformation.xml.rules.PersistenceMarkups.REFERENCED_ELEMENT_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.REFERENCED_KEY_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.REFERENCED_OBJECT_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.REFERENCED_VALUE_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.TYPE_ATTRIBUTE;
import static jmul.transformation.xml.rules.PersistenceMarkups.VALUE_ATTRIBUTE;

import jmul.xml.XmlMarkup;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This class contains tests to check the corresponding enumeration.
 *
 * @author Kristian Kutin
 */
@ConfigurationTest
@RunWith(Parameterized.class)
public class PersistenceMarkupsTest {

    /**
     * Returns a matrix of test data.
     *
     * @return some test data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, true });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_ELEMENT_TYPE_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, true });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_KEY_TYPE_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, true });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_TYPE_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, true });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { DECLARED_VALUE_TYPE_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { FORMAT_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, FORMAT_ATTRIBUTE, true });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { FORMAT_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { ID_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, ID_ATTRIBUTE, true });
        parameters.add(new Object[] { ID_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { ID_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { NAME_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, NAME_ATTRIBUTE, true });
        parameters.add(new Object[] { NAME_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { NAME_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, true });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_ELEMENT_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, true });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_KEY_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, true });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_OBJECT_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, true });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { REFERENCED_VALUE_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { TYPE_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, TYPE_ATTRIBUTE, true });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { TYPE_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { VALUE_ATTRIBUTE, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, VALUE_ATTRIBUTE, true });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, FIELD_ELEMENT, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { VALUE_ATTRIBUTE, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { ELEMENT_ELEMENT, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, ELEMENT_ELEMENT, true });
        parameters.add(new Object[] { ELEMENT_ELEMENT, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, FIELD_ELEMENT, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { ELEMENT_ELEMENT, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { ENTRY_ELEMENT, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, ENTRY_ELEMENT, true });
        parameters.add(new Object[] { ENTRY_ELEMENT, FIELD_ELEMENT, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { ENTRY_ELEMENT, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { FIELD_ELEMENT, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { FIELD_ELEMENT, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { FIELD_ELEMENT, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { FIELD_ELEMENT, FIELD_ELEMENT, true });
        parameters.add(new Object[] { FIELD_ELEMENT, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { FIELD_ELEMENT, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { OBJECT_ELEMENT, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, FIELD_ELEMENT, false });
        parameters.add(new Object[] { OBJECT_ELEMENT, OBJECT_ELEMENT, true });
        parameters.add(new Object[] { OBJECT_ELEMENT, OBJECTS_ELEMENT, false });

        parameters.add(new Object[] { OBJECTS_ELEMENT, DECLARED_ELEMENT_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, DECLARED_KEY_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, DECLARED_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, DECLARED_VALUE_TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, FORMAT_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, ID_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, NAME_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, REFERENCED_ELEMENT_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, REFERENCED_KEY_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, REFERENCED_OBJECT_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, REFERENCED_VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, TYPE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, VALUE_ATTRIBUTE, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, ELEMENT_ELEMENT, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, ENTRY_ELEMENT, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, FIELD_ELEMENT, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, OBJECT_ELEMENT, false });
        parameters.add(new Object[] { OBJECTS_ELEMENT, OBJECTS_ELEMENT, true });

        return parameters;
    }

    /**
     * An XML markup.
     */
    private XmlMarkup markup1;

    /**
     * An XML markup.
     */
    private XmlMarkup markup2;

    /**
     * The expected result when comparing the markups.
     */
    private boolean expectedResult;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aMarkup1
     * @param aMarkup2
     * @param anExpectedResult
     */
    public PersistenceMarkupsTest(XmlMarkup aMarkup1, XmlMarkup aMarkup2, boolean anExpectedResult) {

        markup1 = aMarkup1;
        markup2 = aMarkup2;
        expectedResult = anExpectedResult;
    }

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {

    }

    /**
     * Tests if an equality check of the tag names returns the expected result.
     */
    @Test
    public void testTagNameEquality() {

        assertEquals(expectedResult, markup1.equalsTagName(markup2.getTagName()));
    }

    /**
     * Tests if an equality check of the markups returns the expected result.
     */
    @Test
    public void testMarkupEquality() {

        assertEquals(expectedResult, markup1.equalsXmlMarkup(markup2));
    }

}
