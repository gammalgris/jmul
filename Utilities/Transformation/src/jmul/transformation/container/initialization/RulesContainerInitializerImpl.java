/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package jmul.transformation.container.initialization;


import java.io.File;
import java.io.IOException;

import java.util.Collection;
import java.util.Map;

import jmul.checks.ParameterCheckHelper;

import jmul.io.JarResources;
import jmul.io.ResourceScanner;
import jmul.io.ResourceType;

import jmul.transformation.TransformationException;
import jmul.transformation.TransformationResources;
import jmul.transformation.TransformationRule;
import jmul.transformation.configuration.ConfigurationReader;
import jmul.transformation.container.ModifiableRulesContainer;
import jmul.transformation.container.RulesContainer;
import jmul.transformation.container.RulesContainerImpl;

import jmul.xml.reader.XmlDocumentReader;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;


/**
 * An implementation of a container initializer.
 *
 * @author Kristian Kutin
 */
public class RulesContainerInitializerImpl implements RulesContainerInitializer {

    /**
     * The file extension for configuration files.
     */
    private final String fileExtension;

    /**
     * Creates a new initialier according to the specified parameters.
     *
     * @param aFileExtension
     *        the file extension for configuration files
     */
    public RulesContainerInitializerImpl(String aFileExtension) {

        super();

        ParameterCheckHelper.checkStringParameter(aFileExtension);
        fileExtension = aFileExtension;
    }

    /**
     * Initializs a container for transformation rules.
     *
     * @return a container for transformation rules
     */
    @Override
    public RulesContainer newContainer() {

        // TODO
        // An entity is needed which keeps track of all rules and which rules couldn't be loaded. The invoker
        // has to decide if an exception has to be thrown or not. This way it is not necessary to use a logger
        // or print to the console in this section of the code.
        // Additionally filter parameters (e.g. classpath only, archives only, classpath and archives) should
        // be provided which decide where to look for configuration files. The current implementation is
        // insufficient.

        RulesContainer container = new RulesContainerImpl();
        ModifiableRulesContainer modifiableContainer = (ModifiableRulesContainer) container;


        ResourceType resourceType = new ResourceType(fileExtension);

        ConfigurationReader configurationReader = TransformationResources.getConfigurationReader();
        XmlDocumentReader documentReader = TransformationResources.getXmlDocumentReader();


        ResourceScanner resourceScanner = new ResourceScanner(resourceType);

        Map<String, Collection<File>> foundResources = resourceScanner.getFoundResources();
        Map<String, JarResources> foundArchives = resourceScanner.getFoundArchives();

        boolean existResourceFiles = foundResources.size() > 0;
        boolean existArchiveFiles = foundArchives.size() > 0;

        if (existResourceFiles) {

            int processedResources = 0;

            for (Map.Entry<String, Collection<File>> entry : foundResources.entrySet()) {

                String filename = entry.getValue()
                                       .iterator()
                                       .next()
                                       .getAbsolutePath();

                try {

                    TransformationRule rule = configurationReader.parseConfiguration(filename);
                    modifiableContainer.addRule(rule);
                    processedResources++;

                    System.out.println("processed rule: " + filename);

                } catch (SAXException | IOException e) {

                    // Ignore this exception. Continue with the next
                    // configuration file.
                    continue;
                }
            }

            if (processedResources == 0) {

                String message = "No configuration files were successfully processed!";
                throw new TransformationException(message);
            }

        } else if (existArchiveFiles) {

            int processedEmbeddedResources = 0;

            for (Map.Entry<String, JarResources> entry : foundArchives.entrySet()) {

                String archiveName = entry.getKey();
                JarResources jar = entry.getValue();

                Collection<String> embeddedResources = jar.getResourceNamesWithSuffix(fileExtension);

                for (String embeddedResource : embeddedResources) {

                    try {

                        jar.getResource(embeddedResource);
                        Document document = documentReader.readFrom(archiveName, embeddedResource);
                        TransformationRule rule = configurationReader.parseConfiguration(document);
                        modifiableContainer.addRule(rule);
                        processedEmbeddedResources++;

                    } catch (SAXException | IOException e) {

                        // Ignore this exception and continbue with the next
                        // configuration.
                        continue;
                    }
                }
            }

            if (processedEmbeddedResources == 0) {

                String message = "No configuration files were successfully processed!";
                throw new TransformationException(message);
            }

        } else {

            String message = "No configuration files exist!";
            throw new TransformationException(message);
        }


        return container;
    }

}
