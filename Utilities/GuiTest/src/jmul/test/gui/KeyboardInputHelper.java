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

package jmul.test.gui;


import java.awt.Robot;
import java.awt.event.KeyEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static jmul.test.gui.logging.TestLogger.getLogger;


/**
 * A utility class for keyboard inputs in automated GUI tests.
 *
 * @author Kristian Kutin
 */
public final class KeyboardInputHelper {

    /**
     * A charcater to key code mapping for most commonly used characters.<br>
     * <br>
     * <i>Note:<br>
     * Keyboard events are defined by the actual keyboard/ input device. Not all
     * key codes have corresponding characters in a charset.
     * The purpose of this mapping is to take a string and identify for each character
     * a correpsonding key code. The mapping might be superfluous since for the basic
     * characters (i.e. letters, figures, space, etc.) the key codes should match, but
     * this was not verified.</i>
     */
    private final static Map<Character, Integer> keyMapping;

    /*
     * The static initializer.
     */
    static {

        Map<Character, Integer> tmp = new HashMap<>();

        // This list is incomplete and needs to be updated as needed.

        tmp.put('a', KeyEvent.VK_A);
        tmp.put('b', KeyEvent.VK_B);
        tmp.put('c', KeyEvent.VK_C);
        tmp.put('d', KeyEvent.VK_D);
        tmp.put('e', KeyEvent.VK_E);
        tmp.put('f', KeyEvent.VK_F);
        tmp.put('g', KeyEvent.VK_G);
        tmp.put('h', KeyEvent.VK_H);
        tmp.put('i', KeyEvent.VK_I);
        tmp.put('j', KeyEvent.VK_J);
        tmp.put('k', KeyEvent.VK_K);
        tmp.put('l', KeyEvent.VK_L);
        tmp.put('m', KeyEvent.VK_M);
        tmp.put('n', KeyEvent.VK_N);
        tmp.put('o', KeyEvent.VK_O);
        tmp.put('p', KeyEvent.VK_P);
        tmp.put('q', KeyEvent.VK_Q);
        tmp.put('r', KeyEvent.VK_R);
        tmp.put('s', KeyEvent.VK_S);
        tmp.put('t', KeyEvent.VK_T);
        tmp.put('u', KeyEvent.VK_U);
        tmp.put('v', KeyEvent.VK_V);
        tmp.put('w', KeyEvent.VK_W);
        tmp.put('x', KeyEvent.VK_X);
        tmp.put('y', KeyEvent.VK_Y);
        tmp.put('z', KeyEvent.VK_Z);

        tmp.put('_', KeyEvent.VK_UNDERSCORE);
        tmp.put('-', KeyEvent.VK_MINUS);
        tmp.put(' ', KeyEvent.VK_SPACE);

        tmp.put('0', KeyEvent.VK_0);
        tmp.put('1', KeyEvent.VK_1);
        tmp.put('2', KeyEvent.VK_2);
        tmp.put('3', KeyEvent.VK_3);
        tmp.put('4', KeyEvent.VK_4);
        tmp.put('5', KeyEvent.VK_5);
        tmp.put('6', KeyEvent.VK_6);
        tmp.put('7', KeyEvent.VK_7);
        tmp.put('8', KeyEvent.VK_8);
        tmp.put('9', KeyEvent.VK_9);

        keyMapping = Collections.unmodifiableMap(tmp);
    }

    /**
     * The default constructor.
     */
    private KeyboardInputHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Produces the specified key event.
     *
     * @param aRobot
     *        a robot that executes GUI actions
     * @param anEventName
     *        a name for the key event
     * @param aKeyCode
     *        a key code
     */
    public static void pressKey(Robot aRobot, String anEventName, int aKeyCode) {

        getLogger().logDebug("key event: " + anEventName);
        aRobot.keyPress(aKeyCode);
        aRobot.keyRelease(aKeyCode);
    }

    /**
     * Produces the specified key event.
     *
     * @param aTest
     *        a reference to the test and all its resources
     * @param anEventName
     *        a name for the key event
     * @param aKeyCode
     *        a key code
     */
    public static void pressKey(GUITestBase aTest, String anEventName, int aKeyCode) {

        pressKey(aTest.getRobot(), anEventName, aKeyCode);
    }

    /**
     * Produces the specified key event (i.e. two keys pressed simultaneously).
     *
     * @param aRobot
     *        a robot that executes GUI actions
     * @param anEventName
     *        a name for the key event
     * @param a1stKeyCode
     *        a key code
     * @param a2ndKeyCode
     *        a key code
     */
    public static void pressKey(Robot aRobot, String anEventName, int a1stKeyCode, int a2ndKeyCode) {

        getLogger().logDebug("key event: " + anEventName);
        aRobot.keyPress(a1stKeyCode);
        aRobot.keyPress(a2ndKeyCode);
        aRobot.keyRelease(a2ndKeyCode);
        aRobot.keyRelease(a1stKeyCode);
    }

    /**
     * Produces the specified key event (i.e. two keys pressed simultaneously).
     *
     * @param aTest
     *        a reference to the test and all its resources
     * @param anEventName
     *        a name for the key event
     * @param a1stKeyCode
     *        a key code
     * @param a2ndKeyCode
     *        a key code
     */
    public static void pressKey(GUITestBase aTest, String anEventName, int a1stKeyCode, int a2ndKeyCode) {

        pressKey(aTest.getRobot(), anEventName, a1stKeyCode, a2ndKeyCode);
    }

    /**
     * Triggers several sequential key events that correspond to the specified string.
     *
     * @param aRobot
     *        a robot that executes GUI actions
     * @param aString
     *        a string
     */
    public static void enterString(Robot aRobot, String aString) {

        for (char c : aString.toCharArray()) {

            if (Character.isLetter(c)) {

                char key = Character.toLowerCase(c);
                int value = keyMapping.get(key);

                if (Character.isLowerCase(c)) {

                    pressKey(aRobot, "" + c, value);

                } else {

                    pressKey(aRobot, "" + c, KeyEvent.VK_SHIFT, value);
                }

            } else {

                int value = keyMapping.get(c);

                pressKey(aRobot, "" + c, value);
            }
        }
    }

    /**
     * Triggers several sequential key events that correspond to the specified string.
     *
     * @param aTest
     *        a reference to the test and all its resources
     * @param aString
     *        a string
     */
    public static void enterString(GUITestBase aTest, String aString) {

        enterString(aTest.getRobot(), aString);
    }

}
