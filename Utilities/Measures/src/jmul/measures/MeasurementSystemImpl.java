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

package jmul.measures;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import jmul.io.ResourceScanner;
import jmul.io.ResourceType;

import jmul.measures.definitions.reader.Definition;
import jmul.measures.definitions.reader.DefinitionReader;
import jmul.measures.definitions.reader.DefinitionReaderImpl;
import jmul.measures.definitions.reader.Unit;
import jmul.measures.rules.ConversionRule;
import jmul.measures.rules.ConversionRuleImpl;

import jmul.misc.exceptions.InitializationException;

import jmul.string.StringConcatenator;

import jmul.xml.reader.ReaderException;

import org.xml.sax.SAXException;


/**
 * An implementation of a system of measurement.
 *
 * @author Kristian Kutin
 */
public class MeasurementSystemImpl implements MeasurementSystem {

    /**
     * The class member contains all defined units of measurement.
     */
    private Set<MeasurementUnit> units;

    /**
     * The class member contains all defined units of measurement, but organized
     * by category.
     */
    private Map<String, Set<MeasurementUnit>> unitsByCategory;

    /**
     * The class member contains all defined units of measurement, but organized
     * by name.
     */
    private Map<String, MeasurementUnit> unitsByName;

    /**
     * The default constructor.
     */
    public MeasurementSystemImpl() {

        units = new HashSet<MeasurementUnit>();
        unitsByCategory = new HashMap<String, Set<MeasurementUnit>>();
        unitsByName = new HashMap<String, MeasurementUnit>();

        init();
    }

    /**
     * The method initializes the system of measurement.
     */
    private void init() {

        DefinitionsLoader loader = new DefinitionsLoader();

        for (Definition definition : loader.loadAllDefinitions()) {

            processDefinition(definition);
        }
    }

    /**
     * The method will process a definition.
     *
     * @param aDefinition
     *        a definition
     */
    private void processDefinition(Definition aDefinition) {

        // instantiate all new units of measurement
        List<MeasurementUnit> newUnits = new ArrayList<MeasurementUnit>();

        int size = aDefinition.units();
        for (int a = 0; a < size; a++) {

            Unit unitEntry = aDefinition.getUnit(a);
            MeasurementUnit newUnit =
                new MeasurementUnitImpl(this, unitEntry.getName(), unitEntry.getAbbreviation(), aDefinition.category);
            newUnits.add(newUnit);
        }


        // build conversion rules for the new units of measurement
        for (int a = 0; a < size; a++) {

            MeasurementUnit origin = newUnits.get(a);
            double originNormalizedValue = aDefinition.getUnit(a).getNormalizedValue();

            for (int b = 0; b < size; b++) {

                MeasurementUnit target = newUnits.get(b);
                double targetNormalizedValue = aDefinition.getUnit(b).getNormalizedValue();

                if (a != b) {

                    double conversionRatio = targetNormalizedValue / originNormalizedValue;

                    ConversionRule conversionRule = new ConversionRuleImpl(target, conversionRatio);
                    origin.addConversionRule(conversionRule);
                }
            }
        }


        // update the system of measurement
        //TODO check for duplicates

        // updates the set which contains all units of measurement
        units.addAll(newUnits);

        // update the map which contains all units of measurement arranged by
        // category
        boolean existsCategory = unitsByCategory.containsKey(aDefinition.category);
        if (!existsCategory) {

            Set<MeasurementUnit> subset = new HashSet<MeasurementUnit>();
            unitsByCategory.put(aDefinition.category, subset);
        }

        Set<MeasurementUnit> subset = unitsByCategory.get(aDefinition.category);
        subset.addAll(newUnits);

        // update the map which contains all units of measurement arranged by
        // their names
        for (int a = 0; a < size; a++) {

            MeasurementUnit measurementUnit = newUnits.get(a);
            unitsByName.put(measurementUnit.getName(), measurementUnit);
            unitsByName.put(measurementUnit.getAbbreviation(), measurementUnit);
        }
    }

    /**
     * The method checks if this system of measurement contains a specific
     * category of units of measurement.
     *
     * @param aCategory
     *        a name or abbreviation
     *
     * @return true, if the category exists, else false
     */
    @Override
    public boolean hasCategory(String aCategory) {

        return unitsByCategory.containsKey(aCategory);
    }

    /**
     * The method returns a set containing all units of measurement which belong
     * to a cetain category.
     *
     * @param aCategory
     *        a category
     *
     * @return a set containing units of measurement
     */
    @Override
    public Set<MeasurementUnit> getUnitsByCategory(String aCategory) {

        return unitsByCategory.get(aCategory);
    }

    /**
     * The method checks if this system of measurement contains a specific unit
     * of measurement.
     *
     * @param aName
     *        a name or abbreviation
     *
     * @return true, if this unit of measurement exists, else false
     */
    @Override
    public boolean hasUnit(String aName) {

        return unitsByName.containsKey(aName);
    }

    /**
     * The method returns a unit of measurement with a specific name.
     *
     * @param aName
     *        a name or abbreviation
     *
     * @return a unit of measurement
     */
    @Override
    public MeasurementUnit getUnit(String aName) {

        return unitsByName.get(aName);
    }

    /**
     * The method returns a set containing all defined categories.
     *
     * @return a set containing all categories
     */
    @Override
    public Set<String> getCategories() {

        return unitsByCategory.keySet();
    }

    /**
     * Returns a string representation for this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return getCategories().toString();
    }

}


/**
 * The class is responsible for parsing all definition files found on the classpath.
 *
 * @author Kristian Kutin
 */
class DefinitionsLoader {

    /**
     * The class member contains a property key.
     */
    private static final String DEFINITIONS_FILEEXTENSION = "definitions.file-extension";

    /**
     * The class member contains a reader for configuration files.
     */
    private DefinitionReader reader;

    /**
     * Creates a new instance.
     */
    public DefinitionsLoader() {

        try {

            reader = new DefinitionReaderImpl();

        } catch (ReaderException e) {

            String message = "The definition reader couldn't be instantiated!";
            throw new InitializationException(message, e);
        }
    }

    /**
     * Returns the file extension of measurement unit definitions.
     *
     * @return a file extension
     */
    private static String getDefinitionsFileExtension() {

        ResourceBundle resourceBundle = ResourceBundle.getBundle(MeasurementSystem.class.getName());
        return resourceBundle.getString(DEFINITIONS_FILEEXTENSION);
    }

    /**
     * Loads all definitions which are found on the classpath.
     *
     * @return all definitions
     */
    public Collection<Definition> loadAllDefinitions() {

        Collection<Definition> allDefinitions = new ArrayList<Definition>();

        String fileExtension = getDefinitionsFileExtension();

        ResourceType resourceType = new ResourceType(fileExtension);
        ResourceScanner resourceScanner = new ResourceScanner(resourceType);

        Map<String, Collection<File>> foundResources = resourceScanner.getFoundResources();

        boolean noResourceFiles = foundResources.size() == 0;

        if (noResourceFiles) {

            String message = "No resource files were found on the classpath!";
            throw new InitializationException(message);
        }


        for (String key : foundResources.keySet()) {

            File configurationFile = foundResources.get(key).iterator().next();
            Definition definition = parseConfigurationFile(configurationFile);
            allDefinitions.add(definition);
        }


        return allDefinitions;
    }

    /**
     * Parses a single configuration file.
     *
     * @param aConfigurationFile
     *
     * @return a definition for a subset of units of measurements
     */
    public Definition parseConfigurationFile(File aConfigurationFile) {

        Definition definition = null;

        try {

            definition = reader.readDefinition(aConfigurationFile);

        } catch (SAXException e) {

            StringConcatenator message =
                new StringConcatenator("The file \"", aConfigurationFile, "\" has an invalid XML structure!");
            throw new IllegalArgumentException(message.toString(), e);

        } catch (IOException e) {

            StringConcatenator message =
                new StringConcatenator("Couldn't read from file \"", aConfigurationFile, "\"!");
            throw new IllegalArgumentException(message.toString(), e);
        }

        return definition;
    }

}
