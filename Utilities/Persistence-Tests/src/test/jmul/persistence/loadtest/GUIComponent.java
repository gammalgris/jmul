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

package test.jmul.persistence.loadtest;


import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;


/**
 * The enumeration contains the gui components (including some layout
 * informations) for a specific frame.
 *
 * @author Kristian Kutin
 */
enum GUIComponent {

    ThreadsLabel("threads label", JLabel.class, new JLabel("threads to be started:"),
                 new GridBagConstraints(-1, -1, 1, 1, 1.0, 0.0, 10, 0, new Insets(0, 0, 0, 0), 0, 0)),
    ThreadsField("threads field", JTextField.class, new JTextField(20),
                 new GridBagConstraints(-1, -1, GridBagConstraints.REMAINDER, 1, 0.0, 0.0, 10, 0,
                                        new Insets(0, 0, 0, 0), 0, 0)),
    SleepTimeLabel("sleep time label", JLabel.class, new JLabel("sleep time (in ms):"),
                   new GridBagConstraints(-1, -1, 1, 1, 1.0, 0.0, 10, 0, new Insets(0, 0, 0, 0), 0, 0)),
    SleepTimeField("sleep time field", JTextField.class, new JTextField(20),
                   new GridBagConstraints(-1, -1, GridBagConstraints.REMAINDER, 1, 1.0, 0.0, 10, 0,
                                          new Insets(0, 0, 0, 0), 0, 0)),
    ThreadTypeLabel("thread type label", JLabel.class, new JLabel("thread type distribution:"),
                    new GridBagConstraints(-1, -1, GridBagConstraints.REMAINDER, 1, 0.0, 0.0, 10, 0,
                                           new Insets(0, 0, 0, 0), 0, 0)),
    ThreadTypeField("thread type field", JTextField.class, new JTextField(30),
                    new GridBagConstraints(-1, -1, GridBagConstraints.REMAINDER, 1, 0.0, 0.0, 10, 0,
                                           new Insets(0, 0, 0, 0), 0, 0)),
    SleepMonitor("sleep monitor", JProgressBar.class, new JProgressBar(0, 1000),
                 new GridBagConstraints(-1, -1, GridBagConstraints.REMAINDER, 1, 0.0, 0.0, 10, 0,
                                        new Insets(0, 0, 0, 0), 0, 0));

    /**
     * The actual gui component.
     */
    private JComponent component;

    /**
     * The type of this gui component.
     */
    private Class type;

    /**
     * Layout informations for this gui component.
     */
    private GridBagConstraints constraints;

    /**
     * Constructs the enum element.
     *
     * @param componentName
     * @param type
     * @param component
     * @param constraints
     */
    private GUIComponent(String componentName, Class type, JComponent component, GridBagConstraints constraints) {

        this.component = component;
        this.component.setName(componentName);

        this.type = type;

        this.constraints = constraints;
    }

    /**
     * Returns the name of this gui component.
     *
     * @return a name
     */
    public String getComponentName() {

        return component.getName();
    }

    /**
     * Returns the type of this gui component.
     *
     * @return a type
     */
    public Class getType() {

        return type;
    }

    /**
     * Returns the actual gui component.
     *
     * @return a gui component
     */
    public JComponent getComponent() {

        return component;
    }

    /**
     * Returns layout informations.
     *
     * @return layout informations
     */
    public GridBagConstraints getLayoutInformations() {

        return constraints;
    }

    /**
     * Checks if the specified component equals this component.
     *
     * @param aComponent
     * another component
     *
     * @return <code>true</code> if both components are equals, else
     * <code>false</code>
     */
    public boolean equalsComponent(JComponent aComponent) {

        return component.equals(aComponent);
    }

}
