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

package jmul.web.soap;


import java.io.File;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.ws.Service;

import jmul.io.FileHelper;
import jmul.io.ResourceType;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.ClassHelper;

import static jmul.string.Constants.FILE_SEPARATOR;


/**
 * This class implements a class scanner which is useful for identifying
 * and classifying generated classes.
 *
 * @author Kristian Kutin
 */
public class ClassScanner {

    /**
     * A class.
     */
    private static final Class serviceClass = Service.class;

    /**
     * A file type.
     */
    private ResourceType fileType;

    /**
     * The path which is to be searched.
     */
    private String path;

    /**
     * Details about a service class.
     */
    private ClassDefinition serviceClassDefinition;

    /**
     * Creates a new scanner and looks for a service class according to the specified
     * parameters.
     *
     * @param aFileType
     *        the file type for which to look for
     * @param aPath
     *        the base directory
     *
     * @throws ClassNotFoundException
     *         is thrown if no service class can be found
     */
    public ClassScanner(ResourceType aFileType, String aPath) throws ClassNotFoundException {

        fileType = aFileType;
        path = aPath;

        scanForClasses();
    }

    /**
     * Looks for a service class in a specified folder.
     *
     * @throws ClassNotFoundException
     *         is thrown if no service class can be found
     */
    private void scanForClasses() throws ClassNotFoundException {

        Collection<File> foundFiles = FileHelper.getFiles(path, fileType.getFileSuffix(), true);


        Collection<ClassDefinition> interfaces = new ArrayList<>();
        Collection<ClassDefinition> markerInterfaces = new ArrayList<>();
        Collection<ClassDefinition> classes = new ArrayList<>();
        Collection<ClassDefinition> serviceClasses = new ArrayList<>();

        for (File file : foundFiles) {

            String classname = buildClassname(file.getAbsolutePath(), fileType.getFileSuffixWithSeparator(), path);
            ClassDefinition classDefinition = null;

            try {

                classDefinition = ClassHelper.getClass(classname, path);

                boolean isInterface = classDefinition.isInterface();
                if (isInterface) {

                    Method[] methods = classDefinition.getMethods(false);
                    int methodCount = methods.length;

                    boolean isMarkerInterface = methodCount == 0;
                    if (isMarkerInterface) {

                        markerInterfaces.add(classDefinition);

                    } else {

                        interfaces.add(classDefinition);
                    }

                } else {

                    boolean isServiceClass = serviceClass.isAssignableFrom(classDefinition.getType());
                    if (isServiceClass) {

                        serviceClasses.add(classDefinition);

                    } else {

                        classes.add(classDefinition);
                    }
                }

            } catch (ClassNotFoundException e) {

                // The current file doesn't represent a class. Ignore this exception and continue with
                // the next file.
            }
        }


        // Look into the relevant classes.

        serviceClassDefinition = null;

        boolean noServiceClass = serviceClasses.isEmpty();
        boolean oneServiceClass = serviceClasses.size() == 1;
        boolean moreThanOneServiceClass = serviceClasses.size() > 1;


        if (noServiceClass) {

            String message = "No class exists which is derived from " + serviceClass.getName() + "!";
            throw new ClassNotFoundException(message);

        } else if (oneServiceClass) {

            serviceClassDefinition = serviceClasses.iterator().next();

        } else if (moreThanOneServiceClass) {

            String message = "There exist two or more classes which are derived from " + serviceClass.getName() + "!";
            throw new TooManyClassesException(message);
        }
    }

    /**
     * Returns the class definition of the identified service class.
     *
     * @return a class definition
     */
    public ClassDefinition getServiceClassDefinition() {

        return serviceClassDefinition;
    }

    /**
     * Builds a class name according to the specified parameters.
     *
     * @param aFilename
     *        a file name
     * @param aFileSuffix
     *        a file suffix
     * @param aPath
     *        a base directory
     *
     * @return a class name
     */
    private static String buildClassname(String aFilename, String aFileSuffix, String aPath) {

        String name = aFilename;
        name = name.replace(aFileSuffix, "");
        name = name.replace(aPath, "");
        name = name.substring(1);
        name = name.replace(FILE_SEPARATOR, ".");

        return name;
    }

}
