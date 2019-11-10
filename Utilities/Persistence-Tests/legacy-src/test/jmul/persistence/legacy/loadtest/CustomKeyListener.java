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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package test.jmul.persistence.legacy.loadtest;


import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
 * A custom implementation of a key listener.<br>
 * <br>
 * <i>Note:<br>
 * The method descriptions have been taken from the interface.</i>
 *
 * @author Kristian Kutin
 */
class CustomKeyListener implements KeyListener {

    /**
     * A separator character.
     */
    private static final String SEPARATOR = ",";

    /**
     * The frame where key events happen.
     */
    private TestControlFrame frame;

    /**
     *
     *
     * @param aFrame
     */
    public CustomKeyListener(TestControlFrame aFrame) {

        frame = aFrame;
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent}for a definition of
     * a key typed event.
     */
    public void keyTyped(KeyEvent e) {

        // Ignore this event.
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent}for a definition of
     * a key pressed event.
     */
    public void keyPressed(KeyEvent e) {

        // Ignore this event.
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent}for a definition of
     * a key released event.
     */
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            Component component = e.getComponent();

            if (component instanceof JTextField) {

                JTextField textField = (JTextField) component;

                String message = textField.getName() + " " + textField.getText();
                System.out.println(message);


                if (GUIComponent.SleepTimeField.equalsComponent(textField)) {

                    String text = textField.getText();
                    long newValue = frame.getSleepTime();

                    try {

                        newValue = Long.parseLong(text);

                    } catch (NumberFormatException f) {

                        System.out.println("Invalid input! Resetting settings.");
                        SwingUtilities.invokeLater(new DisplayTextUpdater(textField, String.valueOf(newValue)));
                    }

                    frame.setSleepTime(newValue);

                } else if (GUIComponent.ThreadsField.equalsComponent(textField)) {

                    String text = textField.getText();
                    int newValue = frame.getThreads();

                    try {

                        newValue = Integer.parseInt(text);

                    } catch (NumberFormatException f) {

                        System.out.println("Invalid input! Resetting settings.");
                        SwingUtilities.invokeLater(new DisplayTextUpdater(textField, String.valueOf(newValue)));
                    }

                    frame.setThreads(newValue);

                } else if (GUIComponent.ThreadTypeField.equalsComponent(textField)) {

                    String text = textField.getText();

                    String[] subtexts = text.split(SEPARATOR);
                    int[] weights = new int[subtexts.length];

                    try {

                        for (int a = 0; a < subtexts.length; a++) {

                            String subtext = subtexts[a];

                            weights[a] = Integer.parseInt(subtext.trim());
                        }

                    } catch (NumberFormatException f) {

                        System.out.println("Invalid input! Resetting settings.");

                        weights = frame.getThreadDistribution();
                        String oldText = "";

                        for (int a = 0; a < weights.length; a++) {

                            if (a != 0) {

                                oldText = oldText + SEPARATOR;
                            }

                            oldText = oldText + weights[a];
                        }

                        SwingUtilities.invokeLater(new DisplayTextUpdater(textField, oldText));
                    }

                    frame.setThreadDistribution(weights);
                }
            }
        }
    }

}


/**
 * This interface describes an entity which updates a text field.
 *
 * @author Kristian Kutin
 */
interface TextFieldUpdater extends Runnable {
}


/**
 * This class is responsible for updating the text field.
 *
 * @author Krsitian Kutin
 */
class DisplayTextUpdater implements TextFieldUpdater {

    /**
     * The text field which is to be updated.
     */
    private JTextField textField;

    /**
     * The new display text for this text field.
     */
    private String text;

    /**
     * Constructs an updater.
     *
     * @param aTextField
     *        the text field which is to be updated
     * @param aText
     *        the new display text for this text field
     */
    public DisplayTextUpdater(JTextField aTextField, String aText) {

        textField = aTextField;
        text = aText;
    }

    /**
     * Performs the actual update.
     */
    public void run() {

        textField.setText(text);
    }

}

