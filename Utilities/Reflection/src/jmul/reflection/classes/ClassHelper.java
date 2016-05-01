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


/**
 * A implementation of a wrapper repository as singleton.
 *
 * @author Kristian Kutin
 */
public final class ClassHelper {

    /**
     * A singleton repository.
     */
    private static DefinitionRepository repositorySingleton;

    /**
     * The default constructor.
     */
    private ClassHelper() {
    }

    /**
     * The method returns a refrence to a repository.
     *
     * @return a repository
     */
    private static DefinitionRepository getRepository() {

        if (repositorySingleton == null) {

            repositorySingleton = new DefinitionRepositoryImpl();
        }

        return repositorySingleton;
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
    public static ClassDefinition getClass(String aClassname) throws ClassNotFoundException {

        return getRepository().getClassWrapper(aClassname);
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
    public static ClassDefinition getClass(String aClassname,
                                           String anAlternateClasspath) throws ClassNotFoundException {

        return getRepository().getClassWrapper(aClassname, anAlternateClasspath);
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
    public static ClassDefinition getClass(Class aClass) throws ClassNotFoundException {

        return getRepository().getClassWrapper(aClass);
    }

}
