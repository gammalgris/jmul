/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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

package test.jmul.test.gui.dummy.component;


/**
 * This enumeration class contains some information about menu items of the main frame.
 *
 * @author Kristian Kutin
 */
public enum MenuItems {


    FILE("File", 'F'),
    EXIT("Exit", 'E'),
    HELP("Help", 'H'),
    INFO("Info", 'I'), ;


    /**
     * The menu name.
     */
    private final String menu;

    /**
     * The mnemonic associated with the menu name.
     */
    private final char mnemonic;

    /**
     * Creates a new menu item according to the specified parameters.
     *
     * @param menu
     *        a menu name
     * @param mnemonic
     *        a mnemonic
     */
    private MenuItems(String menu, char mnemonic) {

        this.menu = menu;
        this.mnemonic = mnemonic;
    }

    /**
     * Returns the menu name.
     *
     * @return a menu name
     */
    public String menu() {

        return menu;
    }

    /**
     * Returns a mnemonic.
     *
     * @return a mnemonic
     */
    public char mnemonic() {

        return mnemonic;
    }

}
