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

package jmul.external;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static jmul.string.Constants.SPACE;


/**
 * Implementation of a command entity.
 *
 * @author Kristian Kutin
 */
public class CommandImpl implements Command {

    private static final int COMMAND_INDEX = 0;

    /**
     * The command and all its parameters.
     */
    private final List<String> commandComponents;

    /**
     * Only the parameters of the command.
     */
    private final List<String> parametersOnly;

    /**
     * Creates a new command according to the specified parameters.
     *
     * @param allCommandComponents
     *        the command and all its parameters
     */
    public CommandImpl(String... allCommandComponents) {

        List<String> tmp = Arrays.asList(allCommandComponents);
        tmp = new ArrayList<>(tmp);

        commandComponents = Collections.unmodifiableList(tmp);

        tmp = new ArrayList<>(tmp);
        tmp.remove(0);
        parametersOnly = Collections.synchronizedList(tmp);
    }

    /**
     * Returns the command which is to be invoked.
     *
     * @return a command
     */
    @Override
    public String getCommand() {

        return commandComponents.get(COMMAND_INDEX);
    }

    /**
     * Returns all parameters which were specified.
     *
     * @return all specified parameters
     */
    @Override
    public String[] getParameters() {

        return parametersOnly.toArray(new String[] { });
    }

    /**
     * Returns the command and all parameters as array.
     *
     * @return an array containing the command and all parameters
     */
    @Override
    public String[] toArray() {

        return commandComponents.toArray(new String[] { });
    }

    /**
     * Returns a string representation of this command.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        boolean first = true;

        for (String component : commandComponents) {

            if (first) {

                first = !first;

            } else {

                buffer.append(SPACE);
            }

            buffer.append(component);
        }

        return buffer.toString();
    }

}
