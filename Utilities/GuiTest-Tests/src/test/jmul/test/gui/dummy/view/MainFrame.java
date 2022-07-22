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

package test.jmul.test.gui.dummy.view;


import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import static test.jmul.test.gui.dummy.component.MenuItems.EXIT;
import static test.jmul.test.gui.dummy.component.MenuItems.FILE;
import static test.jmul.test.gui.dummy.component.MenuItems.HELP;
import static test.jmul.test.gui.dummy.component.MenuItems.INFO;
import test.jmul.test.gui.dummy.control.MainFrameActionListener;
import test.jmul.test.gui.dummy.control.MainFrameListener;


/**
 * This class defines the look and feel of the GUI application.
 *
 * @author Kristian Kutin
 */
public class MainFrame extends JFrame {

    /**
     * A reference to the action listener.
     */
    private ActionListener actionListener;

    /**
     * A reference to the menu bar component.
     */
    private JMenuBar menuBar;

    /**
     * A reference to the text area component.
     */
    private JTextArea textArea;

    /**
     * The default constructor.
     */
    public MainFrame() {

        super();

        initListeners();
        initProperties();
        initMenuBar(actionListener);
        initComponents();
    }

    /**
     * Initializes general properties of the main frame.
     */
    private void initProperties() {

        setTitle("Dummy");
        setBounds(50, 50, 400, 300);
    }

    /**
     * Initializes all components found within the GUI application.
     */
    private void initComponents() {

        textArea = new JTextArea(25, 80);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
    }

    /**
     * Initializes all relevant listeners.
     */
    private void initListeners() {

        WindowListener windowListener = new MainFrameListener();
        addWindowListener(windowListener);

        actionListener = new MainFrameActionListener();
    }

    /**
     * Initializes the menu bar and links the menu items with the specified action listener.
     *
     * @param actionListener
     *        an action listener
     */
    private void initMenuBar(ActionListener actionListener) {

        JMenu fileMenu = new JMenu(FILE.menu());
        fileMenu.setMnemonic(FILE.mnemonic());
        JMenuItem exitMenuItem = new JMenuItem(EXIT.menu(), EXIT.mnemonic());
        exitMenuItem.addActionListener(actionListener);
        fileMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu(HELP.menu());
        helpMenu.setMnemonic(HELP.mnemonic());
        JMenuItem infoMenuItem = new JMenuItem(INFO.menu(), INFO.mnemonic());
        infoMenuItem.addActionListener(actionListener);
        helpMenu.add(infoMenuItem);

        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

}
