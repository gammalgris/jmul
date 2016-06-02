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

package test.jmul.markdown.pattern;


import java.util.List;

import jmul.markdown.pattern.PatternMatchListener;
import jmul.markdown.pattern.PatternMatcher;

import static org.junit.Assert.assertEquals;


/**
 * An implementation of {@link jmul.markdown.pattern.PatternMatchListener} for
 * testing purposes.
 *
 * @author Kristian Kutin
 */
public class TestPatternMatchListener implements PatternMatchListener {

    /**
     * The expected pattern matcher when
     * {@link jmul.markdown.pattern.PatternMatchListener#informOnMatch(PatternMatcher, String)}
     * is called.
     */
    private final PatternMatcher expectedPatternMatcher;

    /**
     * The expected match when
     * {@link jmul.markdown.pattern.PatternMatchListener#informOnMatch(PatternMatcher, String)}
     * is called.
     */
    private final List<String> expectedMatches;

    /**
     * The actual match when
     * {@link jmul.markdown.pattern.PatternMatchListener#informOnMatch(PatternMatcher, String)}
     * is called.
     */
    public String actualMatch;

    /**
     * Creates a new listener according to the specified parameters.
     *
     * @param anExpectedPatternMatcher
     * @param someExpectedMatches
     */
    public TestPatternMatchListener(PatternMatcher anExpectedPatternMatcher, List<String> someExpectedMatches) {

        expectedPatternMatcher = anExpectedPatternMatcher;
        expectedMatches = someExpectedMatches;
        actualMatch = null;
    }

    /**
     * Processes a "inform on match" event.
     *
     * @param aMatcher
     * @param someActualMatches
     */
    @Override
    public void informOnMatch(PatternMatcher aMatcher, List<String> someActualMatches) {

        assertEquals(expectedPatternMatcher, aMatcher);

        for (int a = 0; a < expectedMatches.size(); a++) {

            String expectedMatch = expectedMatches.get(a);

            if (a < someActualMatches.size()) {

                String match = someActualMatches.get(a);
                assertEquals(expectedMatch, match);
            }
        }


        String wholeMatch = someActualMatches.get(0);
        actualMatch = wholeMatch;
    }

    /**
     * Resets this test instance (i.e. discards any previously stored match).
     */
    public void reset() {

        actualMatch = null;
    }

    /**
     * Checks if a match exists.
     *
     * @return <code>true</code> if a match exists, else <code>false</code>
     */
    public boolean hasActualMatch() {

        return (actualMatch != null);
    }

    /**
     * Returns an actual match.
     *
     * @return a match or <code>null</code> if no such match exists
     */
    public String getActualMatch() {

        return actualMatch;
    }

}
