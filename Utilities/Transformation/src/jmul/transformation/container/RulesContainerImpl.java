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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.transformation.container;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jmul.string.TextHelper;

import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationRule;


/**
 * An implementation of container for transformation rules.
 *
 * @author Kristian Kutin
 */
public class RulesContainerImpl implements RulesContainer, ModifiableRulesContainer {

    /**
     * The class member manages all transformation rules.
     */
    private Map<TransformationPath, Collection<TransformationRule>> transformationRules;

    /**
     * The default constructor.
     */
    public RulesContainerImpl() {

        super();

        transformationRules = new HashMap<>();
    }

    /**
     * Returns all transformation rules for the specified transformation
     * path.
     *
     * @param aTransformationPath
     *        a transformation path
     *
     * @return a container with transformation rules
     */
    @Override
    public Collection<TransformationRule> getRules(TransformationPath aTransformationPath) {

        if (aTransformationPath == null) {

            String message = "No transformation path (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (!transformationRules.containsKey(aTransformationPath)) {

            String message =
                TextHelper.concatenateStrings("There are no rules for the specified transformation path (",
                                              aTransformationPath, ")!");
            throw new IllegalArgumentException(message);
        }

        return transformationRules.get(aTransformationPath);
    }

    /**
     * Checks if the container contains transformation rules for the specified
     * transformation path.
     *
     * @param aTransformationPath
     *        a transformation path
     *
     * @return <code>true</code> if rules exist, else <code>false</code>
     */
    @Override
    public boolean existsPath(TransformationPath aTransformationPath) {

        return transformationRules.containsKey(aTransformationPath);
    }

    /**
     * Returns the size of this container (i.e. number of transformation
     * rules).
     *
     * @return a container size
     */
    @Override
    public int size() {

        int total = 0;

        for (Map.Entry<TransformationPath, Collection<TransformationRule>> entry : transformationRules.entrySet()) {

            Collection<TransformationRule> subset = entry.getValue();
            total += subset.size();
        }

        return total;
    }

    /**
     * Adds the specified transformation rule to this container.
     *
     * @param aTransformationRule
     *        a transformation rule
     */
    @Override
    public void addRule(TransformationRule aTransformationRule) {

        if (aTransformationRule == null) {

            String message = "No transformation rule (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        TransformationPath path = aTransformationRule.getTransformationPath();

        Collection<TransformationRule> subset;

        if (transformationRules.containsKey(path)) {

            subset = transformationRules.get(path);

        } else {

            subset = new ArrayList<>();
            transformationRules.put(path, subset);
        }

        if (subset.contains(aTransformationRule)) {

            String message =
                TextHelper.concatenateStrings("A transformation rule cannot be added multiple times! Check the rules for transformation path \"",
                                              path, "\".");
            throw new IllegalArgumentException(message);
        }

        subset.add(aTransformationRule);
    }

}
