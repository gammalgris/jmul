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


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jmul.math.random.Die;
import jmul.math.random.DieImpl;


/**
 * An implementation of a population.
 *
 * @param <T>
 *       the type of the individual
 *
 * @author Kristian Kutin
 */
public class PopulationImpl<T> implements Population<T> {

    /**
     * An entity which can evaluate individuals.
     */
    private final Evaluator<T> evaluator;

    /**
     * An entity which can mutate individuals.
     */
    private final Mutator<T> mutator;

    /**
     * A container which contains all individuals.
     */
    private final List<T> population;

    /**
     * Creates a new population with the specified parameters.
     *
     * @param anEvaluator
     * @param aMutator
     * @param aPopulationSize
     * @param anIndividual
     */
    public PopulationImpl(Evaluator<T> anEvaluator, Mutator<T> aMutator, int aPopulationSize, T anIndividual) {

        super();

        evaluator = anEvaluator;
        mutator = aMutator;

        List<T> tmp = new ArrayList<>(aPopulationSize);

        for (int a = 0; a < aPopulationSize; a++) {

            tmp.add(anIndividual);
        }

        population = Collections.unmodifiableList(tmp);
    }

    /**
     * Creates a new population with the specified parameters.
     *
     * @param anEvaluator
     * @param aMutator
     * @param aPopulation
     */
    public PopulationImpl(Evaluator<T> anEvaluator, Mutator<T> aMutator, Collection<T> aPopulation) {

        this(anEvaluator, aMutator, new ArrayList<T>(aPopulation));
    }

    /**
     * Creates a new population with the specified parameters.
     *
     * @param anEvaluator
     * @param aMutator
     * @param aPopulation
     */
    public PopulationImpl(Evaluator<T> anEvaluator, Mutator<T> aMutator, List<T> aPopulation) {

        super();

        evaluator = anEvaluator;
        mutator = aMutator;

        population = Collections.unmodifiableList(aPopulation);
    }

    /**
     * Returns the size of the population.
     *
     * @return the size of the population
     */
    @Override
    public int getSize() {

        return population.size();
    }

    /**
     * Returns the individual with the specified index number. Index numbers start at zero
     * up to one below the population size.
     *
     * @param anIndex
     *
     * @return an individual
     */
    @Override
    public T getIndividual(int anIndex) {

        return population.get(anIndex);
    }

    /**
     * Returns a population with the fittest individuals. The new population has the specified
     * population size.
     *
     * @param aPopulationSize
     *        the size of the new population
     *
     * @return a new population
     */
    @Override
    public Population<T> getFittestIndividuals(int aPopulationSize) {

        if (aPopulationSize >= getSize()) {

            String message =
                String.format("The specified population size (%d) is greater than the actual population size (%d)!",
                              aPopulationSize, getSize());
            throw new IllegalArgumentException(message);
        }

        Map<T, Integer> assessedPopulation = new HashMap<>();
        for (int a = 0; a < getSize(); a++) {

            T individual = getIndividual(a);
            int score = evaluator.calculateScore(individual);
            assessedPopulation.put(individual, score);
        }

        List<Entry<T, Integer>> orderedAssessedPopulation = new ArrayList<>(assessedPopulation.entrySet());
        orderedAssessedPopulation.sort(Entry.comparingByValue());

        List<T> newPopulation = new ArrayList<>(aPopulationSize);
        for (int a = 0; a < aPopulationSize; a++) {

            Map.Entry<T, Integer> entry = orderedAssessedPopulation.get(a);
            T individual = entry.getKey();
            newPopulation.add(individual);
        }

        return new PopulationImpl<>(evaluator, mutator, newPopulation);
    }

    /**
     * Returns the next generation of this population. Each individual is mutated and the result
     * is a new individual of the next generation.
     *
     * @return a new population
     */
    @Override
    public Population<T> mutate() {

        List<T> newPopulation = new ArrayList<>(getSize());

        for (int a = 0; a < getSize(); a++) {

            T individual = getIndividual(a);
            T mutatedIndividual = mutator.mutate(individual);
            newPopulation.add(mutatedIndividual);
        }

        return new PopulationImpl<>(evaluator, mutator, newPopulation);
    }

    /**
     * Grows the population to the specified population size. Randomly picked individuals are
     * cloned until the new population has the specified population size.
     *
     * @param aPopulationSize
     *
     * @return a population with the new population size
     */
    public Population<T> grow(int aPopulationSize) {

        if (aPopulationSize <= getSize()) {

            String message =
                String.format("The specified population size (%d) must be greater than the actual population size (%d)!",
                              aPopulationSize, getSize());
            throw new IllegalArgumentException(message);
        }

        Die die = new DieImpl(getSize());
        List<T> newPopulation = new ArrayList<>(population);

        for (int a = newPopulation.size(); a < aPopulationSize; a++) {

            int index = die.roll();
            T clone = getIndividual(index);
            newPopulation.add(clone);
        }

        return new PopulationImpl<>(evaluator, mutator, newPopulation);
    }

}
