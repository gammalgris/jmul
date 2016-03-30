/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.transformation;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;

import jmul.transformation.configuration.ConfigurationReader;

import jmul.io.JarResources;
import jmul.io.ResourceScanner;
import jmul.io.ResourceType;
import jmul.string.StringConcatenator;
import jmul.xml.reader.XmlDocumentReader;


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
     * The default file extension for configuration files.
     */
    private final String defaultFileExtension;

    /**
     * The class member manages all transformation rules.
     */
    private Map<TransformationPath, Collection<TransformationRule>> transformationRules;

    /**
     * The default constructor.
     */
    public TransformationFactoryImpl() {

        ResourceBundle resourceBundle =
            ResourceBundle.getBundle(TransformationFactory.class.getName());

        defaultFileExtension = resourceBundle.getString(FILE_EXTENSION);

        transformationRules =
                new HashMap<TransformationPath, Collection<TransformationRule>>();

        init();
    }

    /**
     * Post-instantiation initialization.
     */
    private void init() {

        ResourceType resourceType = new ResourceType(defaultFileExtension);

        ConfigurationReader configurationReader =
            TransformationResources.getConfigurationReader();
        XmlDocumentReader documentReader =
            TransformationResources.getXmlDocumentReader();


        ResourceScanner resourceScanner = new ResourceScanner(resourceType);

        Map<String, Collection<File>> foundResources =
            resourceScanner.getFoundResources();
        Map<String, JarResources> foundArchives =
            resourceScanner.getFoundArchives();

        boolean existResourceFiles = (foundResources.size() > 0);
        boolean existArchiveFiles = (foundArchives.size() > 0);

        if (existResourceFiles) {

            int processedResources = 0;

            for (String key : foundResources.keySet()) {

                String filename =
                    foundResources.get(key).iterator().next().getAbsolutePath();

                try {

                    TransformationRule rule =
                        configurationReader.parseConfiguration(filename);
                    addTransformationRule(rule);
                    processedResources++;

                    System.out.println("processed rule: " + filename);

                } catch (SAXException e) {

                    // Ignore this exception. Continue with the next
                    // configuration file.
                    continue;

                } catch (IOException e) {

                    // Ignore this exception. Continue with the next
                    // configuration file.
                    continue;
                }
            }

            if (processedResources == 0) {

                String message =
                    "No configuration files were successfully processed!";
                throw new TransformationException(message);
            }

        } else if (existArchiveFiles) {

            int processedEmbeddedResources = 0;

            for (String archiveName : foundArchives.keySet()) {

                JarResources jar = foundArchives.get(archiveName);

                Collection<String> embeddedResources =
                    jar.getResourceNamesWithSuffix(defaultFileExtension);

                for (String embeddedResource : embeddedResources) {

                    try {

                        jar.getResource(embeddedResource);
                        Document document =
                            documentReader.parseArchivedDocument(archiveName,
                                                                 embeddedResource);
                        TransformationRule rule =
                            configurationReader.parseConfiguration(document);
                        addTransformationRule(rule);
                        processedEmbeddedResources++;

                    } catch (SAXException e) {

                        // Ignore this exception and continbue with the next
                        // configuration.
                        continue;

                    } catch (IOException e) {

                        // Ignore this exception and continbue with the next
                        // configuration.
                        continue;
                    }
                }
            }

            if (processedEmbeddedResources == 0) {

                String message =
                    "No configuration files were successfully processed!";
                throw new TransformationException(message);
            }

        } else {

            String message = "No configuration files exist!";
            throw new TransformationException(message);
        }
    }

    /**
     * Adds a transformation rule.
     *
     * @param aRule
     *        a new transformation rule
     */
    public void addTransformationRule(TransformationRule aRule) {

        TransformationPath path = aRule.getTransformationPath();

        boolean existsPath = transformationRules.containsKey(path);
        if (existsPath) {

            Collection<TransformationRule> ruleset =
                transformationRules.get(path);
            ruleset.add(aRule);

        } else {

            Collection<TransformationRule> ruleset =
                new ArrayList<TransformationRule>();
            transformationRules.put(path, ruleset);
            ruleset.add(aRule);
        }
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
    public Object transform(TransformationParameters someParameters) {

        TransformationPath path = someParameters.getTransformationPath();

        boolean existsPath = transformationRules.containsKey(path);
        if (!existsPath) {

            StringConcatenator message =
                new StringConcatenator("Unknown transformation path: ", path);
            throw new IllegalArgumentException(message.toString());
        }


        // Find all applicable rules and sort them according to their
        // priorities.
        Collection<TransformationRule> ruleset = transformationRules.get(path);
        SortedMap<Integer, Collection<TransformationRule>> sortedRules =
            new TreeMap<Integer, Collection<TransformationRule>>();

        for (TransformationRule rule : ruleset) {

            boolean isApplicableRule = rule.isApplicable(someParameters);
            if (isApplicableRule) {

                Integer priority = rule.getPriority();
                boolean existsPriority = sortedRules.containsKey(priority);
                if (!existsPriority) {
                    Collection<TransformationRule> subset =
                        new ArrayList<TransformationRule>();
                    sortedRules.put(priority, subset);
                }

                Collection<TransformationRule> subset =
                    sortedRules.get(priority);
                subset.add(rule);
            }
        }


        // Get the rule with the highest priority
        Integer highestPriority = null;

        try {

            highestPriority = sortedRules.firstKey();

        } catch (NoSuchElementException e) {

            StringConcatenator message =
                new StringConcatenator("The transformation path ", path,
                                       " doesn't know a rule for objects of type ",
                                       someParameters.getObject().getClass().getName(),
                                       "!");
            throw new IllegalArgumentException(message.toString());
        }

        Collection<TransformationRule> applicableRules =
            sortedRules.get(highestPriority);

        boolean existsAmbiguity = (applicableRules.size() > 1);
        if (existsAmbiguity) {

            StringConcatenator message =
                new StringConcatenator("The transformation path ", path,
                                       " knows several rules with the same priority for objects of type ",
                                       someParameters.getObject().getClass().getName(),
                                       "!");
            throw new IllegalArgumentException(message.toString());
        }


        // Apply the rule
        TransformationRule rule = applicableRules.iterator().next();
        Object result = rule.transform(someParameters);
        return result;
    }

}
