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

package jmul.reflection.classes;


import java.util.HashMap;
import java.util.Map;

import jmul.reflection.ClassLoaderHelper;


/**
 * An implementation of a wrapper repository.
 *
 * @author Kristian Kutin
 */
class DefinitionRepositoryImpl implements DefinitionRepository {

    /**
     * The class member contains references to all included class wrappers
     * which are accessible through an identifier.
     */
    private Map<ClassIdentifier, ClassDefinition> wrappersByIdentifier;

    /**
     * The class member contains references to all included class wrappers
     * which are accessible through a class reference.
     */
    private Map<Class, ClassDefinition> wrappersByClass;

    /**
     * The default constructor.
     */
    DefinitionRepositoryImpl() {

        wrappersByIdentifier = new HashMap<ClassIdentifier, ClassDefinition>();
        wrappersByClass = new HashMap<Class, ClassDefinition>();

        init();
    }

    /**
     * The method initializes this repository.
     */
    private void init() {

        // The initialization process will add types that need a special
        // handling.

        Class[] primitiveTypes = {
            Boolean.TYPE, Character.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE,
            Void.TYPE
        };

        Class[] primitiveWrappers = {
            Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class,
            Double.class
        };

        // Add the primitive types first.

        for (Class primitiveType : primitiveTypes) {

            ClassIdentifier identifier = createClassIdentifier(primitiveType.getName());

            ClassDefinition wrapper = new ClassDefinitionImpl(identifier, primitiveType);
            wrappersByIdentifier.put(identifier, wrapper);
            wrappersByClass.put(primitiveType, wrapper);
        }


        // Add the primitive wrappers now.

        try {

            for (int a = 0; a < primitiveWrappers.length; a++) {

                Class primitiveWrapper = primitiveWrappers[a];
                Class correspondingPrimitiveType = primitiveTypes[a];
                ClassDefinition correspondingPrimitiveTypeWrapper = getClassWrapper(correspondingPrimitiveType);

                ClassIdentifier identifier = createClassIdentifier(primitiveWrapper.getName());
                addWrapper(identifier, correspondingPrimitiveTypeWrapper);
            }

        } catch (ClassNotFoundException e) {

            String message = "The repository couldn't be initialized!";
            throw new IllegalArgumentException(message, e);
        }

        // The rest of the repository will be filled at runtime.
    }

    /**
     * The method retrieves a wrapper for the specified class. If no wrapper
     * exists then a new entry in the repository is created.
     *
     * @param aClassname
     *        the name of a class
     *
     * @return a wrapper for the specified class
     *
     * @throws ClassNotFoundException
     *         the exception is thrown if there is no class which matches the
     *         specified criteria
     */
    @Override
    public ClassDefinition getClassWrapper(String aClassname) throws ClassNotFoundException {

        ClassIdentifier identifier = createClassIdentifier(aClassname);
        return getClassWrapper(identifier);
    }

    /**
     * The method retrieves a wrapper for the specified class. If no wrapper
     * exists then a new entry in the repository is created.
     *
     * @param aClassname
     *        the name of a class
     * @param anAlternateClasspath
     *        an alternative classpath if the class is loaded at runtime
     *
     * @return a wrapper for the specified class
     *
     * @throws ClassNotFoundException
     *         the exception is thrown if there is no class which matches the
     *         specified criteria
     */
    @Override
    public ClassDefinition getClassWrapper(String aClassname,
                                           String anAlternateClasspath) throws ClassNotFoundException {

        ClassIdentifier identifier = createClassIdentifier(aClassname, anAlternateClasspath);
        return getClassWrapper(identifier);
    }

    /**
     * The method adds a new class wrapper.
     *
     * @param anIdentifier
     *
     * @return a wrapper for the specified class
     *
     * @throws ClassNotFoundException
     *         the exception is thrown if there is no class which matches the
     *         specified criteria
     */
    private ClassDefinition getClassWrapper(ClassIdentifier anIdentifier) throws ClassNotFoundException {

        if (!wrappersByIdentifier.containsKey(anIdentifier)) {

            addWrapper(anIdentifier);
        }

        return wrappersByIdentifier.get(anIdentifier);
    }

    /**
     * The method creates a new class wrapper entry.
     *
     * @param anIdentifier
     *        an class identifier
     *
     * @throws ClassNotFoundException
     *         the exception is thrown if there is no class which matches the
     *         specified criteria
     */
    private void addWrapper(ClassIdentifier anIdentifier) throws ClassNotFoundException {

        ClassDefinition wrapper = createClassWrapper(anIdentifier);

        wrappersByIdentifier.put(anIdentifier, wrapper);
        wrappersByClass.put(wrapper.getType(), wrapper);
    }

    /**
     * The method creates a new class wrapper entry.
     *
     * @param anIdentifier
     *        an class identifier
     * @param aCorrespondingPrimitiveType
     *        a corresponding primitive type
     *
     * @throws ClassNotFoundException
     *         the exception is thrown if there is no class which matches the
     *         specified criteria
     */
    private void addWrapper(ClassIdentifier anIdentifier,
                            ClassDefinition aCorrespondingPrimitiveType) throws ClassNotFoundException {

        ClassDefinition wrapper = createClassWrapper(anIdentifier, aCorrespondingPrimitiveType);

        wrappersByIdentifier.put(anIdentifier, wrapper);
        wrappersByClass.put(wrapper.getType(), wrapper);
    }

    /**
     * The method retrieves a wrapper for the specified class.
     *
     * @param aClass
     *        a class entity
     *
     * @return a wrapper for the specified class
     *
     * @throws ClassNotFoundException
     *         the exception is thrown if there is no class which matches the
     *         specified criteria
     */
    @Override
    public ClassDefinition getClassWrapper(Class aClass) throws ClassNotFoundException {

        if (!wrappersByClass.containsKey(aClass)) {

            getClassWrapper(aClass.getName());
        }

        return wrappersByClass.get(aClass);
    }

    /**
     * The method instantiates a class identifier.
     *
     * @param aClassname
     *        a classname
     *
     * @return a class identifier
     */
    private static ClassIdentifier createClassIdentifier(String aClassname) {

        return new ClassIdentifierImpl(aClassname);
    }

    /**
     * The method instantiates a class identifier.
     *
     * @param aClassname
     *        a classname
     * @param aClasspath
     *        a classpath
     *
     * @return a class identifier
     */
    private static ClassIdentifier createClassIdentifier(String aClassname, String aClasspath) {

        return new ClassIdentifierImpl(aClassname, aClasspath);
    }

    /**
     * The method instantiates a class wrapper.
     *
     * @param anIdentifier
     *        a class identifier
     *
     * @return a class wrapper
     *
     * @throws ClassNotFoundException
     *         the exception is thrown if the specified class couldn't be found
     */
    private static ClassDefinition createClassWrapper(ClassIdentifier anIdentifier) throws ClassNotFoundException {

        Class type = null;

        String classname = anIdentifier.getClassname();
        if (anIdentifier.isLocatedOnAlternateClasspath()) {

            String classpath = anIdentifier.getAlternateClasspath();
            type = ClassLoaderHelper.loadClass(classname, classpath);

        } else {

            type = ClassLoaderHelper.loadClass(classname);
        }

        return new ClassDefinitionImpl(anIdentifier, type);
    }

    /**
     * The method instantiates a class wrapper.
     *
     * @param anIdentifier
     *        a class identifier
     * @param aCorrespondingPrimitiveType
     *        a corresponding primitive type
     *
     * @return a class wrapper
     *
     * @throws ClassNotFoundException
     *         the exception is thrown if the specified class couldn't be found
     */
    private static ClassDefinition createClassWrapper(ClassIdentifier anIdentifier,
                                                      ClassDefinition aCorrespondingPrimitiveType) throws ClassNotFoundException {

        Class type = null;

        String classname = anIdentifier.getClassname();
        if (anIdentifier.isLocatedOnAlternateClasspath()) {

            String classpath = anIdentifier.getAlternateClasspath();
            type = ClassLoaderHelper.loadClass(classname, classpath);

        } else {

            type = ClassLoaderHelper.loadClass(classname);
        }

        return new ClassDefinitionImpl(anIdentifier, type, aCorrespondingPrimitiveType);
    }

}


