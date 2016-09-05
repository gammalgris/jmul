/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2016  Kristian Kutin
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

package jmul.webservice;


/**
 * This interface describes an entity which generates code.
 *
 * @author Kristian Kutin
 */
public interface CodeGenerator {

    /**
     * Generates code according to the specified source and returns
     * details of the build target (e.g. output directory).
     *
     * @param aSource
     *
     * @return details of the build target
     */
    String generateCode(String aSource);

    /**
     * Generates code according to the specified source and returns
     * details of the build target (e.g. output directory).
     *
     * @param aSource
     * @param aTarget
     *
     * @return details of the build target
     */
    String generateCode(String aSource, String aTarget);

    /**
     * Returns the default target (e.g. output directory) for generating
     * code.
     *
     * @return the default target
     */
    String getDefaultTarget();

}
