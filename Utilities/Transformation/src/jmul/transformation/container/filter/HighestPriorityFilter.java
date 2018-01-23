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

package jmul.transformation.container.filter;


import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import jmul.string.TextHelper;

import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationResources;
import jmul.transformation.TransformationRule;
import jmul.transformation.container.RulesContainer;
import jmul.transformation.message.MessageFactory;


/**
 * An implementation of a rules filter.
 *
 * @author Kristian Kutin
 */
public class HighestPriorityFilter implements RulesFilter {

    /**
     * The default constructor.
     */
    public HighestPriorityFilter() {

        super();
    }

    /**
     * Filters all rules from the specified container that can process
     * the specified transformation parameters. The rules are sorted
     * by their priority (lowest=high priority to highest=high priority).
     *
     * @param aContainer
     *        a container with transformation rules
     * @param someParameters
     *        contains all information to perform a transformation
     *
     * @return all rules that can process the specified transformation
     *         parameters
     */
    @Override
    public SortedMap<Integer, Collection<TransformationRule>> filterRules(RulesContainer aContainer,
                                                                          TransformationParameters someParameters) {

        TransformationPath path = someParameters.getTransformationPath();


        // Find all applicable rules and sort them according to their
        // priorities.
        Collection<TransformationRule> ruleset = aContainer.getRules(path);
        SortedMap<Integer, Collection<TransformationRule>> sortedRules = new TreeMap<>();

        for (TransformationRule rule : ruleset) {

            boolean isApplicableRule = rule.isApplicable(someParameters);
            if (isApplicableRule) {

                Integer priority = rule.getPriority();
                boolean existsPriority = sortedRules.containsKey(priority);
                if (!existsPriority) {
                    Collection<TransformationRule> subset = new ArrayList<>();
                    sortedRules.put(priority, subset);
                }

                Collection<TransformationRule> subset = sortedRules.get(priority);
                subset.add(rule);
            }
        }


        MessageFactory messageFactory = TransformationResources.getMessageFactory();
        String objectMessage = messageFactory.newMessage(someParameters.getObject());

        // Get the rule with the highest priority
        if (sortedRules.isEmpty()) {

            String message =
                TextHelper.concatenateStrings("The transformation path ", path,
                                              " doesn't know a rule for objects of type ", objectMessage, "!");
            throw new IllegalArgumentException(message);
        }


        return sortedRules;
    }

}
