/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2021  Kristian Kutin
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

package jmul.genetic;


/**
 * This interface describes a population.
 *
 * @param <T>
 *        the type of the individuals
 */
public interface Population<T> {

    /**
     * Returns the size of the population.
     *
     * @return the size of the population
     */
    int getSize();

    /**
     * Returns the individual with the specified index number. Index numbers start at zero
     * up to one below the population size.
     *
     * @param anIndex
     *        a number (i.e. equal or higher than 0 and lesser than the popuplation size)
     *
     * @return an individual
     */
    T getIndividual(int anIndex);

    /**
     * Returns a population with the fittest individuals. The new population has the specified
     * population size.
     *
     * @param aPopulationSize
     *        the size of the new population
     *
     * @return a new population
     */
    Population<T> getFittestIndividuals(int aPopulationSize);

    /**
     * Returns the next generation of this population. Each individual is mutated and the result
     * is a new individual of the next generation.
     *
     * @return a new population
     */
    Population<T> mutate();

    /**
     * Grows the population to the specified population size.
     *
     * @param aPopulationSize
     *        the new population size
     *
     * @return a population with the new population size
     */
    Population<T> grow(int aPopulationSize);

}
