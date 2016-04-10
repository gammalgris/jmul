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

package jmul.persistence.id;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import jmul.math.markow.Algorithm;
import jmul.math.markow.Rule;


/**
 * An implementation of an ID generator.<br>
 * <br>
 * Instead of a numerical ID this generator creates a string ID. A markov
 * algorithm is used to compute an ID. See the description of the implementation
 * of the algorithm for more informations.<br>
 * <br>
 * The benefits are:
 * <ul>
 *   <li>the generator (presumably) creates unique IDs</li>
 *   <li>there is no upper limit concerning possible IDs (except for machine and
 *   memory limitations)</li>
 * </ul>
 *
 * The drawbacks are:
 * <ul>
 *   <li>The costs of calculating an ID are higher than with numeric IDs</li>
 *   <li>Comparison of IDs depends on the length of ID strings.</li>
 * </ul>
 *
 * @author Kristian Kutin
 */
public final class StringIDGenerator implements IDGenerator {

    /**
     * The class member contains the next available id.
     */
    private ID nextID;

    /**
     * The class member contains informations about the file which is used to
     * store the next available id.
     */
    private final File file;

    /**
     * The class member contains the algorithm which is used to calculate the
     * next available id.
     */
    private final Algorithm algorithm;

    /**
     * Constructs the id generator.
     *
     * @param aFilename
     *        the name of the file which is used to store the next available id
     * @param anAlgorithm
     *        the algorithm which calculates the next available id
     */
    public StringIDGenerator(String aFilename, Algorithm anAlgorithm) {

        this(new File(aFilename), anAlgorithm, null);
    }

    /**
     * Constructs the id generator.
     *
     * @param aFilename
     *        the name of the file which is used to store the next available id
     * @param anAlgorithm
     *        the algorithm which calculates the next available id
     * @param anInitialID
     *        the initial ID
     */
    public StringIDGenerator(String aFilename, Algorithm anAlgorithm, ID anInitialID) {

        this(new File(aFilename), anAlgorithm, anInitialID);
    }

    /**
     * Constructs the id generator.
     *
     * @param aFile
     *        the file which is used to store the next available id
     * @param anAlgorithm
     *        the algorithm which calculates the next available id
     * @param anInitialID
     *        the initial ID <i>(optional parameter)</i>
     */
    private StringIDGenerator(File aFile, Algorithm anAlgorithm, ID anInitialID) {

        if (aFile == null) {

            String message = "No valid file has been specified!";
            throw new IllegalArgumentException(message);
        }

        if (anAlgorithm == null) {

            String message = "No valid algorithm has been specified!";
            throw new IllegalArgumentException(message);
        }


        file = aFile;
        algorithm = anAlgorithm;


        if (file.exists()) {

            // If an initial ID has been specified ignore it.
            loadNextID();

        } else {

            if (anInitialID == null) {

                String message = "No initial ID has been specified!";
                throw new IllegalArgumentException(message);
            }

            nextID = anInitialID;
            saveNextID();
        }
    }

    /**
     * The next avilable ID is saved to a file.
     */
    private void saveNextID() {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(nextID.toString());
            writer.newLine();
            writer.flush();
            writer.close();

        } catch (IOException e) {

            String message = "Unable to persist IDs!";
            throw new RuntimeException(message, e);
        }
    }

    /**
     * The next available ID is loaded from a file.
     */
    private void loadNextID() {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            nextID = new StringID(line);
            reader.close();

        } catch (IOException e) {

            String message = "Unable to retrieve persisted IDs!";
            throw new RuntimeException(message, e);
        }
    }

    /**
     * The method creates an object which contains an id.
     *
     * @return an object containing an id
     */
    public ID generateID() {

        ID id = nextID;
        nextID = new StringID(algorithm.applyRules(id.toString()));
        saveNextID();

        return id;
    }

    /**
     * The method returns a new default id generator.
     *
     * @param aFilename
     *        the name of the file which is used to store the next available id
     *
     * @return a new id generator
     */
    public static IDGenerator getDefaultGenerator(String aFilename) {

        ID initialID = new StringID("_a0_");

        Algorithm algorithm =
            new Algorithm(new Rule("zU", "z1"), new Rule("1U", "2"), new Rule("2U", "3"), new Rule("3U", "4"),
                          new Rule("4U", "5"), new Rule("5U", "6"), new Rule("6U", "7"), new Rule("7U", "8"),
                          new Rule("8U", "9"), new Rule("9U", "U0"), new Rule("9Z_", "U0_"), new Rule("Z1", "1Z"),
                          new Rule("Z2", "2Z"), new Rule("Z3", "3Z"), new Rule("Z4", "4Z"), new Rule("Z5", "5Z"),
                          new Rule("Z6", "6Z"), new Rule("Z7", "7Z"), new Rule("Z8", "8Z"), new Rule("Z9", "9Z"),
                          new Rule("Z0", "0Z"), new Rule("zz", "zZ"), new Rule("0Z", "1"), new Rule("1Z", "2"),
                          new Rule("2Z", "3"), new Rule("3Z", "4"), new Rule("4Z", "5"), new Rule("5Z", "6"),
                          new Rule("6Z", "7"), new Rule("7Z", "8"), new Rule("8Z", "9"), new Rule("9Z", "Z0"),
                          new Rule("_z", "#z"), new Rule("_y", "_z"), new Rule("_x", "_y"), new Rule("_w", "_x"),
                          new Rule("_v", "_w"), new Rule("_u", "_v"), new Rule("_t", "_u"), new Rule("_s", "_t"),
                          new Rule("_r", "_s"), new Rule("_q", "_r"), new Rule("_p", "_q"), new Rule("_o", "_p"),
                          new Rule("_n", "_o"), new Rule("_m", "_n"), new Rule("_l", "_m"), new Rule("_k", "_l"),
                          new Rule("_j", "_k"), new Rule("_i", "_j"), new Rule("_h", "_i"), new Rule("_g", "_h"),
                          new Rule("_f", "_g"), new Rule("_e", "_f"), new Rule("_d", "_e"), new Rule("_c", "_d"),
                          new Rule("_b", "_c"), new Rule("_a", "_b"), new Rule("#", "_a"));

        IDGenerator generator = new StringIDGenerator(aFilename, algorithm, initialID);

        return generator;
    }

    /**
     * The method returns a new id generator.
     *
     * @param aFilename
     *        the name of the file which is used to store the next available id
     *
     * @return a new id generator
     */
    public static IDGenerator getAlternativeGenerator(String aFilename) {

        ID initialID = new StringID("_a0_");

        Algorithm algorithm =
            new Algorithm(new Rule("z%", "z1"), new Rule("1%", "2"), new Rule("2%", "3"), new Rule("3%", "4"),
                          new Rule("4%", "5"), new Rule("5%", "6"), new Rule("6%", "7"), new Rule("7%", "8"),
                          new Rule("8%", "9"), new Rule("9%", "%0"), new Rule("9§_", "%0_"), new Rule("§1", "1§"),
                          new Rule("§2", "2§"), new Rule("§3", "3§"), new Rule("§4", "4§"),
                          new Rule("§5", "5§"), new Rule("§6", "6§"), new Rule("§7", "7§"),
                          new Rule("§8", "8§"), new Rule("§9", "9§"), new Rule("§0", "0§"), new Rule("zz", "z§"),
                          new Rule("0§", "1"), new Rule("1§", "2"), new Rule("2§", "3"), new Rule("3§", "4"),
                          new Rule("4§", "5"), new Rule("5§", "6"), new Rule("6§", "7"), new Rule("7§", "8"),
                          new Rule("8§", "9"), new Rule("9§", "§0"), new Rule("_z", "#z"), new Rule("_y", "_z"),
                          new Rule("_x", "_y"), new Rule("_w", "_x"), new Rule("_v", "_w"), new Rule("_u", "_v"),
                          new Rule("_t", "_u"), new Rule("_s", "_t"), new Rule("_r", "_s"), new Rule("_q", "_r"),
                          new Rule("_p", "_q"), new Rule("_o", "_p"), new Rule("_n", "_o"), new Rule("_m", "_n"),
                          new Rule("_l", "_m"), new Rule("_k", "_l"), new Rule("_j", "_k"), new Rule("_i", "_j"),
                          new Rule("_h", "_i"), new Rule("_g", "_h"), new Rule("_f", "_g"), new Rule("_e", "_f"),
                          new Rule("_d", "_e"), new Rule("_c", "_d"), new Rule("_b", "_c"), new Rule("_a", "_b"),
                          new Rule("#", "_a"));

        IDGenerator generator = new StringIDGenerator(aFilename, algorithm, initialID);

        return generator;
    }

}
