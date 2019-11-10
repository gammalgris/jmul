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


import java.util.ArrayList;
import java.util.List;

import jmul.concurrent.threads.ObservableThread;


/**
 * This class randomly instantiates and initalizes threads according to a
 * specified random distribution.
 *
 * @author Kristian Kutin
 */
public class ThreadRandomizer {

    /**
     * Informations about all threads.
     */
    private List<ThreadInitializer> threads;

    /**
     * The probability distribution / weight.
     */
    private List<Integer> weights;

    /**
     * The sum of all weights.
     */
    private int weightSum;

    /**
     * The default constructor.
     */
    public ThreadRandomizer() {

        threads = new ArrayList<ThreadInitializer>();
        weights = new ArrayList<Integer>();
    }

    /**
     * Adds informations about threads which are to be created randomly. The
     * percentage for all threads is distributed evenly.
     *
     * @param someThreadInformations
     *        informations about the threads which are to be created randomly
     */
    public void setThreads(ThreadInitializer... someThreadInformations) {


        threads.clear();
        weights.clear();
        weightSum = 0;

        for (ThreadInitializer threadInitializer : someThreadInformations) {

            threads.add(threadInitializer);
            weights.add(1);
            weightSum += 1;
        }
    }

    /**
     * Changes the percentage distribution for all threads.
     *
     * @param someWeights
     *        the probability distribution / weight
     */
    public void setWeights(int... someWeights) {

        if (weights.size() < someWeights.length) {

            String message = "There are fewer threads than specified percentaes!";
            throw new IllegalArgumentException(message);

        } else if (weights.size() < someWeights.length) {

            String message = "There are more threads than specified percentaes!";
            throw new IllegalArgumentException(message);
        }


        weights.clear();
        weightSum = 0;

        for (int weight : someWeights) {

            weights.add(weight);
            weightSum += weight;
        }
    }

    /**
     * Randomly creates a new thread.
     *
     * @return a new thread
     */
    public ObservableThread newThread() {

        int randomNumber = (int) (Math.random() * weightSum);

        int max = weights.size();
        int total = 0;

        for (int a = 0; a < max; a++) {

            int weight = weights.get(a);
            total += weight;

            if (randomNumber < total) {

                ThreadInitializer threadInitializer = threads.get(a);
                return threadInitializer.newThread();
            }
        }

        return null;
    }

    /**
     * Returns the weights for the random thread generation.
     */
    public int[] getWeights() {

        int length = weights.size();
        int[] result = new int[length];

        for (int a = 0; a < length; a++) {

            result[a] = weights.get(a);
        }

        return result;
    }

}
