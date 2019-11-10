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


import java.lang.reflect.InvocationTargetException;

import java.util.ResourceBundle;

import jmul.checks.ParameterCheckHelper;

import jmul.misc.exceptions.InitializationException;

import jmul.reflection.constructors.ConstructorInvoker;

import jmul.string.TextHelper;

import jmul.transformation.container.RulesContainer;
import jmul.transformation.container.application.RulesApplicationStrategy;
import jmul.transformation.container.filter.RulesFilter;
import jmul.transformation.container.initialization.RulesContainerInitializer;


/**
 * An implementation of a transformation factory.
 *
 * @author Kristian Kutin
 */
public class TransformationFactoryImpl implements TransformationFactory {

    /**
     * A property key.
     */
    private static final String FILE_EXTENSION = "file.extension";

    /**
     * The class member manages all transformation rules.
     */
    private final RulesContainer container;

    /**
     * A filter for transformation rules.
     */
    private final RulesFilter filter;

    /**
     * A strategy for applying transformation rules.
     */
    private final RulesApplicationStrategy applicationStrategy;

    /**
     * The default constructor.
     *
     * @param anInitializerClass
     *        the class name of an entity which does the actual initialization of this factory
     * @param aFilterClass
     *        the class name of a file filter
     * @param anApplicationStrategyClass
     *        the class name of an entity which applies the transformation rules
     */
    public TransformationFactoryImpl(Class anInitializerClass, Class aFilterClass, Class anApplicationStrategyClass) {

        super();


        ParameterCheckHelper.checkClassParameter(anInitializerClass);
        ParameterCheckHelper.checkClassParameter(aFilterClass);
        ParameterCheckHelper.checkClassParameter(anApplicationStrategyClass);


        ResourceBundle resourceBundle = ResourceBundle.getBundle(TransformationFactory.class.getName());
        String defaultFileExtension = resourceBundle.getString(FILE_EXTENSION);


        RulesContainerInitializer initializer;

        try {

            initializer = newRulesContainerInitializer(anInitializerClass, defaultFileExtension);

        } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                 NoSuchMethodException e) {

            String message = "The rules container initializer cannot initialized!";
            throw new InitializationException(message, e);
        }

        try {

            filter = newRulesFilter(aFilterClass);

        } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                 NoSuchMethodException e) {

            String message = "The rules filter cannot be initialized!";
            throw new InitializationException(message, e);
        }

        try {

            applicationStrategy = newRulesApplicationStrategy(anApplicationStrategyClass, filter);

        } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                 NoSuchMethodException e) {

            String message = "The rules application strategy cannot be initialized!";
            throw new InitializationException(message, e);
        }

        container = initializer.newContainer();
    }

    /**
     * Performs a transformation. The specified parameters provides all
     * necessary informations.
     *
     * @param someParameters
     *        all transformation parameters, including the object which is to be
     *        transformed
     *
     * @return the result of the transformation
     */
    @Override
    public Object transform(TransformationParameters someParameters) {

        return applicationStrategy.applyRules(container, someParameters);
    }

    /**
     * Instantiates an rules container initializer according to the specified
     * parameters.
     *
     * @param anInitializerClass
     *        the rules container initalizer class
     * @param aFileExtension
     *        the file extension for configuration files
     *
     * @return an rules container initializer
     *
     * @throws NoSuchMethodException
     *         is thrown if the initializer doesn't have a corresponding constructor
     * @throws InstantiationException
     *         is thrown if the class cannot be instantiated (e.g. it's an abstract class)
     * @throws IllegalAccessException
     *         is thrown if the correpsonding constructor is not public
     * @throws InvocationTargetException
     *         is thrown if an error occurs within the constructor
     */
    private static RulesContainerInitializer newRulesContainerInitializer(Class anInitializerClass,
                                                                          String aFileExtension) throws NoSuchMethodException,
                                                                                                        InstantiationException,
                                                                                                        IllegalAccessException,
                                                                                                        InvocationTargetException {

        ParameterCheckHelper.checkClassParameter(anInitializerClass);

        Class expectedClass = RulesContainerInitializer.class;
        if (!expectedClass.isAssignableFrom(anInitializerClass)) {

            String message =
                TextHelper.concatenateStrings("The specified class (", anInitializerClass,
                                              ") is not derived from the expected class (", expectedClass, ")!");
            throw new IllegalArgumentException(message);
        }

        Class[] signature = new Class[] { String.class };
        Object[] parameters = new Object[] { aFileExtension };

        ConstructorInvoker invoker = new ConstructorInvoker(anInitializerClass, signature);

        return (RulesContainerInitializer) invoker.invoke(parameters);
    }

    /**
     * Instantiates a rules filter according to the specified parameters.
     *
     * @param aFilterClass
     *        the rules filter class
     *
     * @return a rules filter
     *
     * @throws NoSuchMethodException
     *         is thrown if the initializer doesn't have a corresponding constructor
     * @throws InstantiationException
     *         is thrown if the class cannot be instantiated (e.g. it's an abstract class)
     * @throws IllegalAccessException
     *         is thrown if the correpsonding constructor is not public
     * @throws InvocationTargetException
     *         is thrown if an error occurs within the constructor
     */
    private static RulesFilter newRulesFilter(Class aFilterClass) throws NoSuchMethodException, InstantiationException,
                                                                         IllegalAccessException,
                                                                         InvocationTargetException {

        ParameterCheckHelper.checkClassParameter(aFilterClass);

        Class expectedClass = RulesFilter.class;
        if (!expectedClass.isAssignableFrom(aFilterClass)) {

            String message =
                TextHelper.concatenateStrings("The specified class (", aFilterClass,
                                              ") is not derived from the expected class (", expectedClass, ")!");
            throw new IllegalArgumentException(message);
        }

        Class[] signature = new Class[] { };
        Object[] parameters = new Object[] { };

        ConstructorInvoker invoker = new ConstructorInvoker(aFilterClass, signature);

        return (RulesFilter) invoker.invoke(parameters);
    }

    /**
     * Instantiates a rules application strategy according to the specified
     * parameters.
     *
     * @param aStrategyClass
     *        the rules application strategy
     * @param aFilter
     *        a rules filter
     *
     * @return a rules application strategy
     *
     * @throws NoSuchMethodException
     *         is thrown if the initializer doesn't have a corresponding constructor
     * @throws InstantiationException
     *         is thrown if the class cannot be instantiated (e.g. it's an abstract class)
     * @throws IllegalAccessException
     *         is thrown if the correpsonding constructor is not public
     * @throws InvocationTargetException
     *         is thrown if an error occurs within the constructor
     */
    private static RulesApplicationStrategy newRulesApplicationStrategy(Class aStrategyClass,
                                                                        RulesFilter aFilter) throws NoSuchMethodException,
                                                                                                    InstantiationException,
                                                                                                    IllegalAccessException,
                                                                                                    InvocationTargetException {

        ParameterCheckHelper.checkClassParameter(aStrategyClass);

        Class expectedClass = RulesApplicationStrategy.class;
        if (!expectedClass.isAssignableFrom(aStrategyClass)) {

            String message =
                TextHelper.concatenateStrings("The specified class (", aStrategyClass,
                                              ") is not derived from the expected class (", expectedClass, ")!");
            throw new IllegalArgumentException(message);
        }

        Class[] signature = new Class[] { RulesFilter.class };
        Object[] parameters = new Object[] { aFilter };

        ConstructorInvoker invoker = new ConstructorInvoker(aStrategyClass, signature);

        return (RulesApplicationStrategy) invoker.invoke(parameters);
    }

}
