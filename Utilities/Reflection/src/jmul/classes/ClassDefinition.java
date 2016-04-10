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

package jmul.classes;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.List;


/**
 * This interface describes a wrapper for <code>java.lang.Class</code> which
 * provides some custom functionality.
 *
 * @author Kristian Kutin
 */
public interface ClassDefinition extends ClassIdentifier {

    /**
     * The method returns the wrapped class.
     *
     * @return the wrapped class
     */
    Class getType();

    /**
     * The method determines if this class represents a primitive type.
     *
     * @return true, if this class represents a primitive type, else false
     */
    boolean isPrimitiveType();

    /**
     * The method determines if this class represents a wrapper for a primitive
     * type.
     *
     * @return true, if this class represents a wrapper for a primitive type,
     *         else false
     */
    boolean isPrimitiveWrapper();

    /**
     * The method returns the corresponding primitive type if this class is a
     *
     * @return the corresponding primitive type
     */
    ClassDefinition getCorrespondingPrimitiveType();

    /**
     * The method determines if this class represents a class (i.e. it is no
     * interface and no primitive type).
     *
     * @return true, if this class represents a class, else false
     */
    boolean isClass();

    /**
     * The method determines if this class represents an interface.
     *
     * @return true, if this class represents an interface, else false
     */
    boolean isInterface();

    /**
     * The method determines if this class implements a specified interface.
     *
     * @param anInterface
     *        an interface
     *
     * @return true, if this class implements the specified interface, else
     *         false
     */
    boolean implementsInterface(ClassDefinition anInterface);

    /**
     * The method determines if this class implements a specified interface.
     *
     * @param anInterface
     *        an interface
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return true, if this class implements the specified interface, else
     *         false
     */
    boolean implementsInterface(ClassDefinition anInterface, boolean recurse);

    /**
     * The method determines if this class extends a specified interface.
     *
     * @param anInterface
     *        an interface
     *
     * @return true, if this class implements the specified interface, else
     *         false
     */
    boolean extendsInterface(ClassDefinition anInterface);

    /**
     * The method determines if this class extends a specified interface.
     *
     * @param anInterface
     *        an interface
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return true, if this class implements the specified interface, else
     *         false
     */
    boolean extendsInterface(ClassDefinition anInterface, boolean recurse);

    /**
     * The method determines if this class extends a specified class.
     *
     * @param aParentClass
     *        a parent class
     *
     * @return true, if this class extends the specified class, else false
     */
    boolean extendsClass(ClassDefinition aParentClass);

    /**
     * The method determines if this class extends a specified class.
     *
     * @param aParentClass
     *        a parent class
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return true, if this class extends the specified class, else false
     */
    boolean extendsClass(ClassDefinition aParentClass, boolean recurse);

    /**
     * The method determines a method of this class with a specified name and
     * signature.
     *
     * @param aMethodname
     *        the name of a method
     * @param aMethodSignature
     *        the parameter signature of the method
     *
     * @return the specified method
     *
     * @throws NoSuchMethodException
     *         the exception is thrown if there is no method which matches the
     *         specified criteria
     */
    Method getMethod(String aMethodname,
                     List<ClassDefinition> aMethodSignature) throws NoSuchMethodException;

    /**
     * The method determines a method of this class with a specified name and
     * signature.
     *
     * @param aMethodname
     *        the name of a method
     * @param aMethodSignature
     *        the parameter signature of the method
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return the specified method
     *
     * @throws NoSuchMethodException
     *         the exception is thrown if there is no method which matches the
     *         specified criteria
     */
    Method getMethod(String aMethodname,
                     List<ClassDefinition> aMethodSignature,
                     boolean recurse) throws NoSuchMethodException;

    /**
     * The method determines an accessor for a specified field.
     *
     * @param aPrefix
     *        the prefix which identifies the method (e.g. set or get)
     * @param aFieldname
     *        the name of the field
     *
     * @return the specified method
     *
     * @throws NoSuchMethodException
     *         the exception is thrown if there is no method which matches the
     *         specified criteria
     */
    Method getAccessor(String aPrefix,
                       String aFieldname) throws NoSuchMethodException;

    /**
     * The method determines an accessor for a specified field.
     *
     * @param aPrefix
     *        the prefix which identifies the method (e.g. set or get)
     * @param aFieldname
     *        the name of the field
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return the specified method
     *
     * @throws NoSuchMethodException
     *         the exception is thrown if there is no method which matches the
     *         specified criteria
     */
    Method getAccessor(String aPrefix, String aFieldname,
                       boolean recurse) throws NoSuchMethodException;

    /**
     * The method returns the field with the specified name.
     *
     * @param aFieldname
     *        the name of the field
     *
     * @return the specified field
     *
     * @throws NoSuchFieldException
     *         the exception is thrown if there is no field which matches the
     *         specified criteria
     */
    Field getField(String aFieldname) throws NoSuchFieldException;

    /**
     * The method returns the field with the specified name.
     *
     * @param aFieldname
     *        the name of the field
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return the specified field
     *
     * @throws NoSuchFieldException
     *         the exception is thrown if there is no field which matches the
     *         specified criteria
     */
    Field getField(String aFieldname,
                   boolean recurse) throws NoSuchFieldException;

    /**
     * The method determines if this class equals the specified class.
     *
     * @param aClass
     *        a class
     *
     * @return true, if this class equals the specified class, else false
     */
    boolean equalsClass(ClassDefinition aClass);

    /**
     * The method determines if this interface equals the specified interface.
     *
     * @param anInterface
     *        an interface
     *
     * @return true, if this class equals the specified interface, else false
     */
    boolean equalsInterface(ClassDefinition anInterface);

    /**
     * The method determines all fields of this class.
     *
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return a list of all fields
     */
    Field[] getFields(boolean recurse);

}
