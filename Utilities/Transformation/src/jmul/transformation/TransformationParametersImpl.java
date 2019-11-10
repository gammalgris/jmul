/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
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

package jmul.transformation;


import java.util.HashMap;
import java.util.Map;

import jmul.string.TextHelper;


/**
 * An implementation of transformation parameters.
 *
 * @author Kristian Kutin
 */
public class TransformationParametersImpl implements TransformationParameters {

    /**
     * Informations about the transformation path.
     */
    private final TransformationPath transformationPath;

    /**
     * The object which is to be transformed.
     */
    private final Object object;

    /**
     * The real type of the object which is to be transformed.
     */
    private final Class realType;

    /**
     * The declared type of the object which is to be transformed.
     */
    private final Class declaredType;

    /**
     * Prerequisites and additional informations which are required for the
     * transformation.
     */
    private Map<CharSequence, Object> prerequisites;

    /**
     * Constructs the transformation parameters.
     *
     * @param aTransformationPath
     *        informations about the transformation path
     * @param anObject
     *        the object which is to be transformed
     */
    public TransformationParametersImpl(TransformationPath aTransformationPath, Object anObject) {

        // To avoid a possible null pointer exception the 3rd parameter is
        // replaced by an expression.

        this(aTransformationPath, anObject, (anObject != null) ? anObject.getClass() : null);
    }

    /**
     * Constructs the transformation parameters.
     *
     * @param aTransformationPath
     *        informations about the transformation path
     * @param anObject
     *        the object which is to be transformed
     * @param aDeclaredType
     *        the delcared type of the object which is to be transformed
     */
    public TransformationParametersImpl(TransformationPath aTransformationPath, Object anObject, Class aDeclaredType) {

        if (anObject == null) {

            //TODO
            // An object whose value is null was not yet considered for
            // transformation. The main issue with null is that without knowing
            // the context where this null object is used it's impossible to
            // determine enough informations to restore it correctly (e.g.
            // type and field name by using reflection mechanisms).
            //
            // This may change in the future, provided the concrete
            // transformation rules support this. Such a rule implementation
            // would require all those additional informations mentioned above
            // to be provided with the transformation parameters.

            String message = "The object which is to be transformed is not supposed to be null!";
            throw new IllegalArgumentException(message);
        }

        transformationPath = aTransformationPath;

        object = anObject;
        realType = anObject.getClass();
        declaredType = aDeclaredType;

        prerequisites = new HashMap<>();
    }

    /**
     * Returns informations about the transformation path.
     *
     * @return informations about the transformation path
     */
    @Override
    public TransformationPath getTransformationPath() {

        return transformationPath;
    }

    /**
     * Returns the object which is to be transformed.
     *
     * @return the object which is to be transformed
     */
    @Override
    public Object getObject() {

        return object;
    }

    /**
     * Returns the real type of the object which is to be transformed.
     *
     * @return the real type of the object which is to be transformed
     */
    @Override
    public Class getRealType() {

        return realType;
    }

    /**
     * Returns the declared type of the object which is to be transformed. In
     * some cases the declared type and the true type are not equal (e.g. if
     * the declared type is an interface type and the true type an
     * implementation of the interface) or the infroamtion is unavailable (e.g.
     * with primitive type like int, boolean, etc.)
     *
     * @return the declared type of the object which is to be transformed
     */
    @Override
    public Class getDeclaredType() {

        return declaredType;
    }

    /**
     * A transformation might require addititional informations or certain
     * prerequisites. This method allows to check if a certain information or
     * prerequisite is available.
     *
     * @param aName
     *        the name of a prerequisite
     *
     * @return <code>true</code> if the specified prerequisite is available,
     *         else <code>false</code>
     */
    @Override
    public boolean containsPrerequisite(CharSequence aName) {

        return prerequisites.containsKey(aName);
    }

    /**
     * A transformation might require addititional informations or certain
     * prerequisites. This method allows access to such an information or
     * prerequisite.
     *
     * @param aName
     *        the name of a prerequisite
     *
     * @return a prerequisite for the transformation
     */
    @Override
    public Object getPrerequisite(CharSequence aName) {

        if (!containsPrerequisite(aName)) {

            String message = TextHelper.concatenateStrings("No entry with the name \"", aName, "\" exists!");
            throw new IllegalArgumentException(message);
        }

        return prerequisites.get(aName);
    }

    /**
     * The method allows to specify more additional informations or
     * prerequisites.
     *
     * @param aName
     *        the name of a prerequisite
     * @param aPrerequisite
     *        a prerequisite
     */
    @Override
    public void addPrerequisite(CharSequence aName, Object aPrerequisite) {

        if (containsPrerequisite(aName)) {

            String message = TextHelper.concatenateStrings("An entry with the name \"", aName, "\" exists already!");
            throw new IllegalArgumentException(message);
        }

        prerequisites.put(aName, aPrerequisite);
    }

}
