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

package jmul.transformation.container.application;


import java.util.Collection;
import java.util.SortedMap;

import jmul.checks.ParameterCheckHelper;

import jmul.string.TextHelper;

import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationResources;
import jmul.transformation.TransformationRule;
import jmul.transformation.container.RulesContainer;
import jmul.transformation.container.filter.RulesFilter;
import jmul.transformation.message.MessageFactory;


/**
 * An implementation of a rule application strategy.
 *
 * @author Kristian Kutin
 */
public class HighestPriorityRuleOnlyStrategy implements RulesApplicationStrategy {

    /**
     * An entity for filtering transformation rules.
     */
    private final RulesFilter filter;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param aFilter
     *        a filter for transformation rules
     */
    public HighestPriorityRuleOnlyStrategy(RulesFilter aFilter) {

        super();

        ParameterCheckHelper.checkObjectParameter(aFilter);
        filter = aFilter;
    }

    /**
     * Applies the specified rules on the specified transformation
     * parmaeters.
     *
     * @param aContainer
     *        a container with transformation rules
     * @param someParameters
     *        contains all information to perform a transformation
     *
     * @return the transformation result
     */
    @Override
    public Object applyRules(RulesContainer aContainer, TransformationParameters someParameters) {

        SortedMap<Integer, Collection<TransformationRule>> sortedRules = filter.filterRules(aContainer, someParameters);


        TransformationPath path = someParameters.getTransformationPath();
        MessageFactory messageFactory = TransformationResources.getMessageFactory();
        String objectMessage = messageFactory.newMessage(someParameters.getObject());

        Integer highestPriority = sortedRules.firstKey();

        Collection<TransformationRule> applicableRules = sortedRules.get(highestPriority);

        boolean existsAmbiguity = applicableRules.size() > 1;
        if (existsAmbiguity) {

            String message =
                TextHelper.concatenateStrings("The transformation path ", path,
                                              " knows several rules with the same priority for objects of type ",
                                              objectMessage, "!");
            throw new IllegalArgumentException(message);
        }


        // Apply the rule
        TransformationRule rule = applicableRules.iterator().next();
        return rule.transform(someParameters);
    }

}
