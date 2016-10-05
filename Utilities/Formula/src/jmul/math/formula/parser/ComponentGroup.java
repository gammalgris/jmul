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

package jmul.math.formula.parser;


import java.util.ArrayList;
import java.util.List;

import jmul.math.formula.operations.Operator;

import jmul.string.StringConcatenator;


/**
 * A utility class which contains informations about component grouping.
 *
 * @author Kristian Kutin
 */
public class ComponentGroup {

    /**
     * The class member contains the starting index of components.
     */
    private final int startingItemIndex;

    /**
     * The class member contains the operator which is the basis for this group.
     */
    private Operator operator;

    /**
     * The class member contains the types of components which form this group
     * (in consecutive order).
     */
    private List<ComponentType> componentTypes;

    /**
     * The class member contains the components which form this group (in
     * consecutive order).
     */
    private List<String> components;

    /**
     * The default constructor.
     *
     * @param aStartingIndex
     *        the starting index for this group of components
     */
    public ComponentGroup(int aStartingIndex) {

        startingItemIndex = aStartingIndex;
        componentTypes = new ArrayList<ComponentType>();
        components = new ArrayList<String>();
    }

    /**
     * The method return the starting index for this group of components.
     *
     * @return a starting index for this group of components
     */
    public int getStartingItemIndex() {

        return startingItemIndex;
    }

    /**
     * The method sets the operator which is the basis for this group.
     *
     * @param anOperator
     *        an operator
     */
    public void setOperator(Operator anOperator) {

        operator = anOperator;
    }

    /**
     * The method returns the operator which is the basis for this group.
     *
     * @return
     */
    public Operator getOperator() {

        return operator;
    }

    /**
     * The method checks whether an operator has already been set or not.
     *
     * @return true, if an operator has been set, else false
     */
    public boolean containsOperator() {

        return operator != null;
    }

    /**
     * The method adds a component which belongs to this group.
     *
     * @param aComponentType
     *        a component type
     */
    public void addComponent(ComponentType aComponentType, String aComponent) {

        // check if a valid component is added
        if ((components.size() > 0) && !containsOperator()) {
            String message =
                "More than one operand has been associated with this group, but no operator was yet determined: \"" +
                getString() + " " + aComponent + "\"";
            throw new IllegalArgumentException(message);
        }

        componentTypes.add(aComponentType);
        components.add(aComponent);
    }

    /**
     * The method returns the number of components which belong to this group.
     * Each appearance of an operator, an operand and a sign will be counted.
     *
     * @return a component count
     */
    public int getComponentCount() {

        return getOperatorCount() + getSignCount() + getOperandCount();
    }

    /**
     * The method returns the number of operators which belong to this group.
     * Each operator is counted. Operators which are used as signs are not
     * counted.
     *
     * @return an operator count
     */
    public int getOperatorCount() {

        int count = 0;
        for (ComponentType component : componentTypes) {

            if (component.equals(ComponentType.OPERATOR)) {
                count++;
            }
        }

        return count;
    }

    /**
     * The method returns the number of signs which belong to this group. Each
     * signed operand is counted.
     *
     * @return a sign count
     */
    public int getSignCount() {

        int count = 0;
        for (ComponentType component : componentTypes) {

            if (component.equals(ComponentType.SIGNED_COMPONENT)) {
                count++;
            }
        }

        return count;
    }

    /**
     * The method returns the number of operands which belong to this group.
     * Each signed and unsigned operand is counted.
     *
     * @return an operand count
     */
    public int getOperandCount() {

        int count = 0;
        for (ComponentType component : componentTypes) {

            if ((component.equals(ComponentType.SIGNED_COMPONENT)) ||
                component.equals(ComponentType.UNSIGNED_COMPONENT)) {
                count++;
            }
        }

        return count;
    }

    /**
     * The method returns the sequence of component types.
     *
     * @return a sequence of component types
     */
    public String getComponentTypeSequence() {

        StringBuffer buffer = new StringBuffer();

        for (ComponentType componentType : componentTypes) {

            if (componentType.equals(ComponentType.OPERATOR)) {
                buffer.append(" " + getOperator() + " ");
            } else {
                buffer.append(componentType.getDescription());
            }
        }

        return buffer.toString();
    }

    /**
     * The method returns the sequence of components.
     *
     * @return a sequence of component types
     */
    public String getComponentSequence() {

        StringBuffer buffer = new StringBuffer();

        for (String component : components) {

            buffer.append(component);
        }

        return buffer.toString();
    }

    /**
     * The overridden toString-method.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringConcatenator representation = new StringConcatenator();

        if (containsOperator()) {

            representation.append("The group consists of ", getOperandCount(), " operand(s) on which the \"",
                                  getOperator(), "\" operation is performed ", "(", getComponentSequence(), ").");

        } else {

            representation.append("The group consists of ", getOperandCount(), " operand(s).");
        }

        return representation.toString();
    }

    /**
     * The method returns the component at the specified index.
     *
     * @param anIndex
     *        an index
     *
     * @return the component at the specified index
     */
    public String getComponent(int anIndex) {

        return components.get(anIndex);
    }

    /**
     * The method returns the component type at the specified index.
     *
     * @param anIndex
     *        an index
     *
     * @return the component type at the specified index
     */
    public ComponentType getComponentType(int anIndex) {

        return componentTypes.get(anIndex);
    }

    /**
     * The method returns the index of the last operator within this group. A
     * group can consist of more than two operands that are combined with the
     * same oeprator.
     *
     * @return the index of the last operator within this group
     */
    public int getLastOperatorIndex() {

        int end = getOperandCount() + getOperatorCount() - 1;
        int lastOperatorIndex = -1;

        for (int a = end; a > 0; a--) {

            ComponentType type = getComponentType(a);

            if (type.equals(ComponentType.OPERATOR)) {
                lastOperatorIndex = a;
                break;
            }
        }

        return lastOperatorIndex;
    }

    /**
     * The method returns a string of this group, but without the last operand.
     *
     * @return a string of this group
     */
    public String getStringWithoutLastOperand() {

        StringBuffer buffer = new StringBuffer();
        int index = getLastOperatorIndex();

        for (int a = 0; a <= index; a++) {
            buffer.append(getComponent(a));
        }

        return buffer.toString();
    }

    /**
     * The method returns a string of this group.
     *
     * @return a string of this group
     */
    public String getString() {

        return getComponentSequence();
    }

    /**
     * The method splits the group into two strings and returns the result as
     * a list of strings.
     *
     * @return a list of strings
     */
    public List<String> splitGroupOnLastOperator() {

        int start = 0;
        int middle = getLastOperatorIndex();
        int end = getOperandCount() + getOperatorCount() - 1;

        StringBuffer left = new StringBuffer();
        for (int a = start; a < middle; a++) {
            left.append(getComponent(a));
        }

        StringBuffer right = new StringBuffer();
        for (int a = middle + 1; a <= end; a++) {
            right.append(getComponent(a));
        }

        List<String> result = new ArrayList<String>();
        result.add(left.toString());
        result.add(right.toString());

        return result;
    }

    /**
     * The method returns a list of all operands.
     *
     * @return all operands
     */
    public List<String> getOperands() {

        List<String> operands = new ArrayList<String>();

        int length = componentTypes.size();
        for (int a = 0; a < length; a++) {

            ComponentType type = componentTypes.get(a);

            if (type.equals(ComponentType.SIGNED_COMPONENT) || type.equals(ComponentType.UNSIGNED_COMPONENT)) {

                operands.add(components.get(a));
            }
        }

        return operands;
    }

}
