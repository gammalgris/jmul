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


import javax.swing.JProgressBar;


/**
 * This class is responsible for updating the progress bar.
 *
 * @author Krsitian Kutin
 */
public class CurrentValueUpdater implements Runnable {

    /**
     * The new value of the progress bar.
     */
    private final int currentValue;

    /**
     * Constructs this updater.
     *
     * @param newCurrentValue
     * the new value of the progress bar
     */
    public CurrentValueUpdater(int newCurrentValue) {

        currentValue = newCurrentValue;
    }

    /**
     * Updates the progress bar.
     */
    public void run() {

        JProgressBar progressBar = (JProgressBar) GUIComponent.SleepMonitor.getComponent();

        synchronized (progressBar) {

            progressBar.setValue(currentValue);
        }
    }

}
