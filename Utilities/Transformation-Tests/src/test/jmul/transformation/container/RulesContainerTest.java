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

package test.jmul.transformation.container;


import java.util.Collection;

import jmul.transformation.TransformationParameters;
import jmul.transformation.TransformationPath;
import jmul.transformation.TransformationRule;
import jmul.transformation.TransformationRuleBase;
import jmul.transformation.container.ModifiableRulesContainer;
import jmul.transformation.container.RulesContainer;
import jmul.transformation.container.RulesContainerImpl;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains tests for rules containers.
 *
 * @author Kristian Kutin
 */
public class RulesContainerTest {

    /**
     * A container for transformation rules.
     */
    private RulesContainer container;

    /**
     * Preparations before a test.
     */
    @Before
    public void setUp() {

        container = new RulesContainerImpl();
    }

    /**
     * Cleanup after a test.
     */
    @After
    public void tearDown() {

        container = null;
    }

    /**
     * Tests properties of an empty rules container.
     */
    @Test
    public void testEmptyContainer() {

        assertEquals(0, container.size());
    }

    /**
     * Tests adding a rule to the container.
     */
    @Test
    public void testAddRule() {

        TransformationRule rule = new DummyTransformationRule();
        TransformationPath path = rule.getTransformationPath();
        ModifiableRulesContainer modifiableContainer = (ModifiableRulesContainer) container;

        assertEquals(0, container.size());
        assertEquals(0, modifiableContainer.size());

        modifiableContainer.addRule(rule);

        assertEquals(1, container.size());
        assertEquals(1, modifiableContainer.size());

        Collection<TransformationRule> subset = container.getRules(path);

        assertEquals(1, subset.size());
    }

    /**
     * Tests adding a rule twice to the container.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateRule() {

        TransformationRule rule = new DummyTransformationRule();
        ModifiableRulesContainer modifiableContainer = (ModifiableRulesContainer) container;

        modifiableContainer.addRule(rule);
        modifiableContainer.addRule(rule);
    }

    /**
     * Tests adding <code>null</code> to the container.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {

        TransformationRule rule = null;
        ModifiableRulesContainer modifiableContainer = (ModifiableRulesContainer) container;

        modifiableContainer.addRule(rule);
    }

    /**
     * Tests retrieving rules for a transformation path that doesn't exist.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetRules() {

        TransformationPath path = new TransformationPath("null", "dummy");
        container.getRules(path);
    }

    /**
     * Tests checking the existence of a specific transformation path
     * on an empty container.
     */
    @Test
    public void testQueryNonexistantPathOnEmptyContainer() {

        TransformationPath path = new TransformationPath("null", "dummy");
        boolean result = container.existsPath(path);

        assertEquals(false, result);
    }

    /**
     * Tests checking the existence of a specific transformation path
     * on a container with rules.
     */
    @Test
    public void testQueryExtistingPathOnContainer() {

        TransformationRule rule = new DummyTransformationRule();
        TransformationPath path = rule.getTransformationPath();
        ModifiableRulesContainer modifiableContainer = (ModifiableRulesContainer) container;
        modifiableContainer.addRule(rule);

        boolean result = container.existsPath(path);

        assertEquals(true, result);
    }

    /**
     * Tests checking the existence of a specific transformation path
     * on a container with rules.
     */
    @Test
    public void testQueryNonextistantPathOnContainer() {

        TransformationRule rule = new DummyTransformationRule();
        ModifiableRulesContainer modifiableContainer = (ModifiableRulesContainer) container;
        modifiableContainer.addRule(rule);

        TransformationPath nonexistantPath = new TransformationPath("null", "dummy");

        boolean result = container.existsPath(nonexistantPath);

        assertEquals(false, result);
    }

}


/**
 * A dummy transformation rule for testing purposes.
 *
 * @author Kristian Kutin
 */
class DummyTransformationRule extends TransformationRuleBase {

    /**
     * The default constructor.
     */
    public DummyTransformationRule() {

        super("dummy", "null", 1);
    }

    /**
     * Tests if the rule is applicable on the specified object.
     *
     * @param someParameters
     *
     * @return <code>true</code> if the rule is applicable,
     *         else <code>false</code>
     */
    @Override
    public boolean isApplicable(TransformationParameters someParameters) {

        return false;
    }

    /**
     * Performs the transformation.
     *
     * @param someParameters
     *
     * @return the transformation result
     */
    @Override
    public Object transform(TransformationParameters someParameters) {

        return null;
    }

}
