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

package jmul.math.formula.parser.patterns;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * A utility class for converting token patterns.
 *
 * @author Kristian Kutin
 */
public final class TokenPatternHelper {

    /**
     * The default constructor.
     */
    private TokenPatternHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Transforms the token patterns enumeration into a pattern map.
     *
     * @return a pattern map
     */
    public static Map<TokenPattern, Pattern> toMap() {

        Map<TokenPattern, Pattern> tmp = new HashMap<>();

        for (TokenPattern tokenPattern : TokenPatterns.values()) {

            Pattern pattern = Pattern.compile(tokenPattern.toString());
            tmp.put(tokenPattern, pattern);
        }

        return tmp;
    }

}
