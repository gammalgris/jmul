/*
 * SPDX-License-Identifier: GPL-3.0
 * 
 * 
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

package jmul.reflection.classes.signature;


import java.util.List;

import jmul.reflection.classes.ClassDefinition;
import jmul.reflection.classes.equivalence.EquivalenceMatcher;
import jmul.reflection.classes.equivalence.ExtendedEquivalenceCheck;


/**
 * An implementation of a signature matcher.
 *
 * @author Kristian Kutin
 */
public class SignatureMatcherImpl implements SignatureMatcher {

    /**
     * The default constructor.
     */
    public SignatureMatcherImpl() {

        super();
    }

    /**
     * The method checks if two signatures are equivalent.
     *
     * @param expectedSignature
     *        a parameter signature
     * @param foundSignature
     *        a parameter signature
     *
     * @return true, if both signatures are equivalent, else false
     */
    @Override
    public boolean matchingSignatures(List<ClassDefinition> expectedSignature, List<ClassDefinition> foundSignature) {

        // Check some plausibilities first.

        if (expectedSignature.size() != foundSignature.size()) {

            String message = "The specified signatures contain a different number of parameters!";
            throw new IllegalArgumentException(message);
        }


        int numberOfParameters = expectedSignature.size();
        boolean matches = true;

        for (int a = 0; a < numberOfParameters; a++) {

            ClassDefinition expectedParameter = expectedSignature.get(a);
            ClassDefinition foundParameter = foundSignature.get(a);

            EquivalenceMatcher matcher = new ExtendedEquivalenceCheck();
            matches = matcher.matchingClasses(expectedParameter, foundParameter);

            if (!matches) {

                break;
            }
        }

        return matches;
    }

}
