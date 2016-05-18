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


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jmul.reflection.classes.filter.MethodFilter;
import jmul.reflection.classes.filter.MethodNameFilter;
import jmul.reflection.classes.filter.PublicMethodFilter;
import jmul.reflection.classes.filter.SignatureLengthFilter;
import jmul.reflection.classes.signature.SignatureMatcher;
import jmul.reflection.classes.signature.SignatureMatcherImpl;

import jmul.string.StringConcatenator;


/**
 * An implementation of a class wrapper.
 *
 * @author Kristian Kutin
 */
class ClassDefinitionImpl implements ClassDefinition {

    /**
     * The method contains some basic informations about this class.
     */
    private final ClassIdentifier identifier;

    /**
     * The class member contains the class definition.
     */
    private final Class type;

    /**
     * The class member contains the corresponding primitive type.
     */
    private final ClassDefinition correspondingPrimitiveType;

    /**
     * The default constructor.
     *
     * @param anIdentifier
     *        an identifier
     * @param aType
     *        the class corresponding with the identifier
     */
    ClassDefinitionImpl(ClassIdentifier anIdentifier, Class aType) {

        identifier = anIdentifier;
        type = aType;
        correspondingPrimitiveType = null;
    }

    /**
     * The default constructor.
     *
     * @param anIdentifier
     *        an identifier
     * @param aType
     *        the class corresponding with the identifier
     * @param aCorrespondingPrimitiveType
     *        a corresponding primitive type
     */
    ClassDefinitionImpl(ClassIdentifier anIdentifier, Class aType, ClassDefinition aCorrespondingPrimitiveType) {

        identifier = anIdentifier;
        type = aType;
        correspondingPrimitiveType = aCorrespondingPrimitiveType;
    }

    /**
     * The method returns the classname.
     *
     * @return a classname
     */
    @Override
    public String getClassname() {

        return identifier.getClassname();
    }

    /**
     * The method returns the wrapped class.
     *
     * @return the wrapped class
     */
    @Override
    public Class getType() {

        return type;
    }

    /**
     * The method determines if this class is located on the default classpath.
     *
     * @return true, if this class is located on the default classpath, else
     *         false
     */
    @Override
    public boolean isLocatedOnDefaultClasspath() {

        return identifier.isLocatedOnDefaultClasspath();
    }

    /**
     * The method determines if this class is located on an alternate classpath.
     *
     * @return true, if this class is located on an alternate classpath, else
     *         false
     */
    @Override
    public boolean isLocatedOnAlternateClasspath() {

        return identifier.isLocatedOnAlternateClasspath();
    }

    /**
     * The method returns the alternate classpath where this class is located.
     *
     * @return an alternative classpath or <code>null</code>
     */
    @Override
    public String getAlternateClasspath() {

        return identifier.getAlternateClasspath();
    }

    /**
     * The method determines if this class represents a primitive type.
     *
     * @return true, if this class represents a primitive type, else false
     */
    @Override
    public boolean isPrimitiveType() {

        return type.isPrimitive();
    }

    /**
     * The method determines if this class represents a wrapper for a primitive
     * type.
     *
     * @return true, if this class represents a wrapper for a primitive type,
     *         else false
     */
    @Override
    public boolean isPrimitiveWrapper() {

        return (correspondingPrimitiveType != null);
    }

    /**
     * The method returns the corresponding primitive type if this class is a
     *
     * @return the corresponding primitive type
     */
    @Override
    public ClassDefinition getCorrespondingPrimitiveType() {

        return correspondingPrimitiveType;
    }

    /**
     * The method determines if this class represents a class (i.e. it is no
     * interface and no primitive type).
     *
     * @return true, if this class represents a class, else false
     */
    @Override
    public boolean isClass() {

        return !isPrimitiveType() && !isInterface();
    }

    /**
     * The method determines if this class represents an interface.
     *
     * @return true, if this class represents an interface, else false
     */
    @Override
    public boolean isInterface() {

        return getType().isInterface();
    }

    /**
     * The method determines if this class implements a specified interface.
     *
     * TODO
     * Inefficient implementation / high memory consumption
     *
     * @param anInterface
     *        an interface
     *
     * @return true, if this class implements the specified interface, else
     *         false
     */
    @Override
    @Deprecated
    public boolean implementsInterface(ClassDefinition anInterface) {

        return implementsInterface(anInterface, false);
    }

    /**
     * The method determines if this class implements a specified interface.
     *
     * TODO
     * Inefficient implementation / high memory consumption
     *
     * @param anInterface
     *        an interface
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return true, if this class implements the specified interface, else
     *         false
     */
    @Override
    @Deprecated
    public boolean implementsInterface(ClassDefinition anInterface, boolean recurse) {

        // Check some plausibilities.

        if (isInterface()) {

            StringConcatenator message =
                new StringConcatenator("This class (", this.getType(),
                                       ") represents an interface and cannot implement an interface!");
            throw new UnsupportedOperationException(message.toString());
        }


        return implementsInterface(anInterface.getType(), recurse);
    }

    /**
     * The method determines if this class implements a specified interface.
     *
     * TODO
     * Inefficient implementation / high memory consumption
     *
     * @param anInterface
     *        an interface
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return true, if this class implements the specified interface, else
     *         false
     */
    @Deprecated
    private boolean implementsInterface(Class anInterface, boolean recurse) {

        // Check some plausibilities.

        if (!anInterface.isInterface()) {

            StringConcatenator message =
                new StringConcatenator("The specified class ", anInterface, " is no interface!");
            throw new IllegalArgumentException(message.toString());
        }


        Class contemplatedClass = getType();
        boolean loop = true;
        boolean found = false;

        while (loop) {

            Collection<Class> implementedInterfaces = determineInterfaces(contemplatedClass, recurse);

            for (Class foundInterface : implementedInterfaces) {

                found = equalClasses(anInterface, foundInterface);

                if (found) {

                    break;
                }
            }

            if (found) {

                break;
            }

            contemplatedClass = contemplatedClass.getSuperclass();

            loop = recurse && (contemplatedClass != null);
        }

        return found;
    }

    /**
     * The method determines if this class extends a specified interface.
     *
     * TODO
     * Inefficient implementation / high memory consumption
     *
     * @param anInterface
     *        an interface
     *
     * @return true, if this class implements the specified interface, else
     *         false
     */
    @Override
    @Deprecated
    public boolean extendsInterface(ClassDefinition anInterface) {

        return extendsInterface(anInterface, false);
    }

    /**
     * The method determines if this class extends a specified interface.
     *
     * TODO
     * Inefficient implementation / high memory consumption
     *
     * @param anInterface
     *        an interface
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return true, if this class implements the specified interface, else
     *         false
     */
    @Override
    @Deprecated
    public boolean extendsInterface(ClassDefinition anInterface, boolean recurse) {

        // Check some plausibilities.

        if (!isInterface()) {

            StringConcatenator message =
                new StringConcatenator("This class (", this.getType(),
                                       ") is no interface and thus cannot extend an interface!");
            throw new UnsupportedOperationException(message.toString());
        }


        return extendsInterface(anInterface.getType(), recurse);
    }

    /**
     * The method determines if this class extends a specified interface.
     *
     * TODO
     * Inefficient implementation / high memory consumption
     *
     * @param anInterface
     *        an interface
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return true, if this class implements the specified interface, else
     *         false
     */
    @Deprecated
    private boolean extendsInterface(Class anInterface, boolean recurse) {

        // Check some plausibilities.

        if (!anInterface.isInterface()) {

            StringConcatenator message =
                new StringConcatenator("The specified class ", anInterface, " is no interface!");
            throw new IllegalArgumentException(message.toString());
        }


        Class contemplatedInterface = getType();
        Collection<Class> parentInterfaces = determineParentInterfaces(contemplatedInterface, recurse);
        boolean found = false;

        for (Class parentInterface : parentInterfaces) {

            found = equalClasses(parentInterface, anInterface);

            if (found) {

                break;
            }
        }

        return found;
    }

    /**
     * The method determines if this class extends a specified class.
     *
     * @param aParentClass
     *        a parent class
     *
     * @return true, if this class extends the specified class, else false
     */
    @Override
    public boolean extendsClass(ClassDefinition aParentClass) {

        return extendsClass(aParentClass, false);
    }

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
    @Override
    public boolean extendsClass(ClassDefinition aParentClass, boolean recurse) {

        // Check some plausibilities.

        if (isInterface()) {

            StringConcatenator message =
                new StringConcatenator("This class (", this, ") is an interface and thus cannot extend a class!");
            throw new UnsupportedOperationException(message.toString());
        }


        return extendsClass(aParentClass.getType(), recurse);
    }

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
    private boolean extendsClass(Class aParentClass, boolean recurse) {

        // Check some plausibilities.

        if (aParentClass.isInterface()) {

            StringConcatenator message =
                new StringConcatenator("The specified class ", aParentClass,
                                       " is an interface and cannot be extended by a class!");
            throw new IllegalArgumentException(message.toString());
        }


        Class contemplatedClass = getType();
        boolean found = false;
        boolean loop = true;

        while (loop) {

            contemplatedClass = contemplatedClass.getSuperclass();
            found = equalClasses(contemplatedClass, aParentClass);

            loop = recurse && !found;
        }

        return found;
    }

    /**
     * The method determines a method of this class with a specified name and
     * signature.
     *
     * @param aMethodname
     *        the name of a method
     * @param aMethodSignature
     *        the parameter signature of the method
     *
     * @return the speficied method
     *
     * @throws NoSuchMethodException
     *         the exception is thrown if there is no method which matches the
     *         specified criteria
     */
    @Override
    public Method getMethod(String aMethodname, List<ClassDefinition> aMethodSignature) throws NoSuchMethodException {

        return getMethod(aMethodname, aMethodSignature, false);
    }

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
     * @return the speficied method
     *
     * @throws NoSuchMethodException
     *         the exception is thrown if there is no method which matches the
     *         specified criteria
     */
    @Override
    public Method getMethod(String aMethodname, List<ClassDefinition> aMethodSignature,
                            boolean recurse) throws NoSuchMethodException {

        Class[] nativeSignature = { };
        nativeSignature = aMethodSignature.toArray(nativeSignature);

        Class probedClass = getType();
        Method identifiedMethod = null;

        try {

            // Can the method be identified by standard means?
            identifiedMethod = probedClass.getMethod(aMethodname, nativeSignature);

        } catch (NoSuchMethodException e) {

            // Apparently there doesn't exist a method with the specified name
            // and signature.

            // Examine the class for similar methods.

            Collection<Method> filteredMethods = getMethods(aMethodname, aMethodSignature.size(), recurse);

            // Determine which method might be a good match.

            boolean found = false;
            SignatureMatcher matcher = new SignatureMatcherImpl();

            for (Method method : filteredMethods) {

                Class[] requiredNativeSignature = method.getParameterTypes();
                List<ClassDefinition> requiredSignature = new ArrayList<ClassDefinition>();

                try {

                    for (Class parameter : requiredNativeSignature) {

                        ClassDefinition wrapper = ClassHelper.getClass(parameter);
                        requiredSignature.add(wrapper);
                    }

                } catch (ClassNotFoundException f) {

                    StringConcatenator message = new StringConcatenator("Method signature contains an unknown class:");

                    for (Class clazz : requiredNativeSignature) {

                        message.append(" ", clazz.getName());
                    }

                    throw new IllegalArgumentException(message.toString());
                }

                found = matcher.matchingSignatures(requiredSignature, aMethodSignature);

                if (found) {

                    identifiedMethod = method;
                    break;
                }
            }

            if (!found) {
                StringConcatenator message =
                    new StringConcatenator("The class ", getType().getName(), " doesn't possess a method \"",
                                           aMethodname, " and the signature ", aMethodSignature, "!");
                throw new NoSuchMethodException(message.toString());
            }
        }

        return identifiedMethod;
    }

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
    @Override
    public Method getAccessor(String aPrefix, String aFieldname) throws NoSuchMethodException {

        return getAccessor(aPrefix, aFieldname, false);
    }

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
    @Override
    public Method getAccessor(String aPrefix, String aFieldname, boolean recurse) throws NoSuchMethodException {

        // Check first if there exists a field with the specified name.

        Field foundField = null;

        try {

            foundField = getField(aFieldname, recurse);

        } catch (NoSuchFieldException e) {

            StringConcatenator message =
                new StringConcatenator("No field with the specified name \"", aFieldname, "\" exists!");
            throw new NoSuchMethodException(message.toString());
        }


        // Now check if there exists an accessor for the specified field.

        String methodname = AccessorHelper.determineAccesorName(aPrefix, aFieldname);

        if (AccessorHelper.SETTER_PREFIX.equals(aPrefix)) {

            Collection<Method> foundMethods = getMethods(methodname, 1, recurse);

            if (foundMethods.size() == 0) {

                StringConcatenator message =
                    new StringConcatenator("No setter method with the name ", methodname, " was found!");
                throw new NoSuchMethodException(message.toString());

            } else if (foundMethods.size() == 1) {

                return foundMethods.iterator().next();

            } else {

                // Since we started looking from a sublass we can safely take
                // the first method.

                //TODO
                // Actually matching the parameter signature and the type of the
                // returned object would be better.

                return foundMethods.iterator().next();

                /* String message =
                    "Too many setter methods with the name " + methodname +
                    " were found!";
                throw new NoSuchMethodException(message); */
            }

        } else if (AccessorHelper.GETTER_PREFIX.equals(aPrefix)) {

            Collection<Method> foundMethods = getMethods(methodname, 0, recurse);

            if (foundMethods.size() == 0) {

                StringConcatenator message =
                    new StringConcatenator("No getter method with the name ", methodname, " was found!");
                throw new NoSuchMethodException(message.toString());

            } else if (foundMethods.size() == 1) {

                return foundMethods.iterator().next();

            } else {

                // Since we started looking from a sublass we can safely take
                // the first method.

                //TODO
                // Actually matching the parameter signature and the type of the
                // returned object would be better.

                return foundMethods.iterator().next();

                /* String message =
                    "Too many getter methods with the name " + methodname +
                    " were found!";
                throw new NoSuchMethodException(message); */
            }
        }

        StringConcatenator message = new StringConcatenator("Unknown prefix \"", aPrefix, "\"!");
        throw new IllegalArgumentException(message.toString());
    }

    /**
     * The method returns all methods of a class with a specified name and
     * number of parameters.
     *
     * @param aMethodname
     *        a method name
     * @param numberOfParameters
     *        a number of parameters
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return all methods with the specified name and the same number of
     *         parameters
     */
    private Collection<Method> getMethods(String aMethodname, int numberOfParameters, boolean recurse) {

        Collection<Method> matchingMethods = new ArrayList<Method>();

        Class probedClass = getType();
        boolean loop = true;

        MethodFilter[] methodFilters = {
            new PublicMethodFilter(), new MethodNameFilter(aMethodname),
            new SignatureLengthFilter(numberOfParameters) };

        while (loop) {

            Method[] methods = probedClass.getDeclaredMethods();

            for (MethodFilter methodFilter : methodFilters) {

                methods = methodFilter.filterMethods(methods);
            }

            for (Method method : methods) {

                matchingMethods.add(method);
            }


            probedClass = probedClass.getSuperclass();
            loop = recurse && (probedClass != null);
        }

        return matchingMethods;
    }

    /**
     * The method determines if this class equals the specified class.
     *
     * @param aClass
     *        a class
     *
     * @return true, if this class equals the specified class, else false
     */
    @Override
    public boolean equalsClass(ClassDefinition aClass) {

        // Check some plausibilities.

        if (aClass.isInterface()) {

            StringConcatenator message = new StringConcatenator("The specified class ", aClass, " is an interface!");
            throw new IllegalArgumentException(message.toString());
        }

        if (isInterface()) {

            StringConcatenator message = new StringConcatenator("This class ", getType(), " is an interface!");
            throw new UnsupportedOperationException(message.toString());
        }


        return equalClasses(this, aClass);
    }

    /**
     * The method determines if this interface equals the specified interface.
     *
     * @param anInterface
     *        an interface
     *
     * @return true, if this class equals the specified interface, else false
     */
    @Override
    public boolean equalsInterface(ClassDefinition anInterface) {

        // Check some plausibilities.

        if (!anInterface.isInterface()) {

            StringConcatenator message =
                new StringConcatenator("The specified class ", anInterface, " is no interface!");
            throw new IllegalArgumentException(message.toString());
        }

        if (!isInterface()) {

            StringConcatenator message = new StringConcatenator("This class ", this, " is no interface!");
            throw new UnsupportedOperationException(message.toString());
        }


        return equalClasses(this, anInterface);
    }

    /**
     * The method determines if two classes are equal.
     *
     * @param firstClass
     *        a classname
     * @param secondClass
     *        a classname
     *
     * @return true, if the two classes are equal, else false
     */
    private static boolean equalClasses(ClassDefinition firstClass, ClassDefinition secondClass) {

        boolean onDefaultClasspath =
            firstClass.isLocatedOnDefaultClasspath() && secondClass.isLocatedOnDefaultClasspath();
        boolean onAlternateClasspath =
            firstClass.isLocatedOnAlternateClasspath() && secondClass.isLocatedOnAlternateClasspath();

        boolean equal = false;
        if (onDefaultClasspath) {

            equal = equalClasses(firstClass.getType(), secondClass.getType());

        } else if (onAlternateClasspath) {

            boolean sameClasspath = firstClass.getAlternateClasspath().equals(secondClass.getAlternateClasspath());

            if (sameClasspath) {

                equal = equalClasses(firstClass.getType(), secondClass.getType());
            }
        }

        return equal;
    }

    /**
     * The method determines if two classes are equal.
     *
     * @param firstClass
     *        a classname
     * @param secondClass
     *        a classname
     *
     * @return true, if the two classes are equal, else false
     */
    private static boolean equalClasses(Class firstClass, Class secondClass) {

        return equalClassnames(firstClass.getName(), secondClass.getName());
    }

    /**
     * The method determines if two classnames are equal.
     *
     * @param firstClassname
     *        a classname
     * @param secondClassname
     *        a classname
     *
     * @return true, if the two classnames are equal, else false
     */
    private static boolean equalClassnames(String firstClassname, String secondClassname) {

        return firstClassname.equals(secondClassname);
    }

    /**
     * The method determines the interfaces which are implemented by the
     * specified class.
     *
     * @param aClass
     *        a class
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return a collection of implemented interfaces
     */
    private static Collection<Class> determineInterfaces(Class aClass, boolean recurse) {

        // Check some plausibilities first.

        if (aClass.isInterface()) {

            StringConcatenator message = new StringConcatenator("The specified class ", aClass, " is an interface!");
            throw new IllegalArgumentException(message.toString());
        }


        Collection<Class> allImplementedInterfaces = new ArrayList<Class>();
        Class consideredClass = aClass;
        boolean loop = true;

        while (loop) {

            Class[] implementedInterfaces = consideredClass.getInterfaces();

            for (Class foundInterface : implementedInterfaces) {

                allImplementedInterfaces.add(foundInterface);

                if (recurse) {

                    allImplementedInterfaces.addAll(determineParentInterfaces(foundInterface, recurse));
                }
            }

            consideredClass = consideredClass.getSuperclass();

            loop = recurse && (consideredClass != null);
        }

        return allImplementedInterfaces;
    }

    /**
     * The method determines parent interfaces of a specified interface.
     *
     * @param anInterface
     *        an interface
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return a collection of parent interfaces
     */
    private static Collection<Class> determineParentInterfaces(Class anInterface, boolean recurse) {

        // Check some plausibilities first.

        if (!anInterface.isInterface()) {

            StringConcatenator message =
                new StringConcatenator("The specified class ", anInterface, " is no interface!");
            throw new IllegalArgumentException(message.toString());
        }


        Collection<Class> parentInterfaces = new ArrayList<Class>();
        Class[] foundInterfaces = anInterface.getInterfaces();

        for (Class foundInterface : foundInterfaces) {

            parentInterfaces.add(foundInterface);

            if (recurse) {

                parentInterfaces.addAll(determineParentInterfaces(foundInterface, recurse));
            }
        }

        return parentInterfaces;
    }

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
    @Override
    public Field getField(String aFieldname) throws NoSuchFieldException {

        return getField(aFieldname, false);
    }

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
    @Override
    public Field getField(String aFieldname, boolean recurse) throws NoSuchFieldException {

        Field foundField = null;
        boolean loop = true;
        Class probedType = getType();

        while (loop) {

            try {

                foundField = probedType.getDeclaredField(aFieldname);

            } catch (NoSuchFieldException e) {

                // Ignore this exception, because we may still be walking
                // through the inheritance structure.
                ;
            }

            probedType = probedType.getSuperclass();
            loop = recurse && (foundField == null);
        }

        if (foundField == null) {

            String message = "No field with the specified name could be found!";
            throw new NoSuchFieldException(message);
        }

        return foundField;
    }

    /**
     * The method returns a string representation of this entity.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return identifier.toString();
    }

    /**
     * The method determines all fields of this class.
     *
     * @param recurse
     *        a flag indicating to recurse through the inheritence structure
     *
     * @return a list of all fields
     */
    @Override
    public Field[] getFields(boolean recurse) {

        Collection<Field> fields = new ArrayList<Field>();
        Class probedClass = getType();


        while (recurse && (probedClass != null)) {

            for (Field field : probedClass.getDeclaredFields()) {

                if (Modifier.isStatic(field.getModifiers())) {

                    continue;

                } else if (Modifier.isFinal(field.getModifiers())) {

                    continue;
                }

                fields.add(field);
            }

            probedClass = probedClass.getSuperclass();
        }


        Field[] result = { };
        result = fields.toArray(result);

        return result;
    }

}
