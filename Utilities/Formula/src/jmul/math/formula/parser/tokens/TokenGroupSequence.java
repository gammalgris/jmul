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

package jmul.math.formula.parser.tokens;


import java.util.ArrayList;
import java.util.List;

import jmul.math.formula.parser.GroupPosition;


/**
 * A helper class for better readability.
 *
 * @author Kristian Kutin
 */
public class TokenGroupSequence extends ArrayList<TokenGroup> implements List<TokenGroup> {

    /**
     * The default constructor.
     */
    public TokenGroupSequence() {

        super();
    }

    /**
     * The method determines a group with the lowest priority operator within a
     * sequence of groups. If there are several groups with the lowest priority
     * operator then the last one that is encountered will be chosen. If no
     * such group could be detrmined -1 is returned.
     *
     * @return the index of the group with the lowest priority operator, or -1
     *         if no such group could be determined
     */
    public int determineLastGroupWithLowestPriorityOperator() {

        TokenGroup determinedGroup = null;
        int index = -1;

        for (int a = 0; a < size(); a++) {

            TokenGroup group = get(a);

            if ((determinedGroup == null) && (group.containsOperator())) {

                determinedGroup = group;
                index = a;
            }

            if (group.containsOperator() &&
                (group.getOperator().getPriority() <= determinedGroup.getOperator().getPriority())) {

                determinedGroup = group;
                index = a;
            }
        }

        return index;
    }

    /**
     * The method will group a token sequence into several groups.
     *
     * @param aTokenSequence
     *        a token sequence
     *
     * @return a sequence of groups
     */
    public static TokenGroupSequence groupTokens(TokenSequence aTokenSequence) {

        TokenGroupSequence groupSequence = new TokenGroupSequence();

        int index = 0;
        int length = aTokenSequence.size();

        if (index < (length - 1)) {

            // The token sequence contains more than one token.
            boolean loop = true;
            while (loop) {

                TokenGroup group = new TokenGroup(aTokenSequence, index);

                while (group.hasNextGroupToken()) {

                    group.addNextGroupToken();
                }

                groupSequence.add(group);
                index = group.getIndexOfLastGroupOperand();

                if (group.getPosition().equals(GroupPosition.RIGHT)) {

                    // Within this sequence more than one group has been
                    // identified and the sequence has been traversed (the right
                    // end has been reached).
                    loop = false;

                } else if (group.getPosition().equals(GroupPosition.NOT_APPLICABLE)) {

                    // Only one group has been identified which is spanning the
                    // whole sequence. This means that neither the position
                    // left, right nor middle can be applied (due to ambiguity).
                    loop = false;

                }
            }

        } else {

            // The token sequence contains just one token.
            TokenGroup group = new TokenGroup(aTokenSequence, index);
            group.addNextGroupToken();

            groupSequence.add(group);
        }

        return groupSequence;
    }

}
