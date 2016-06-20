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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import jmul.markdown.pattern.PatternMatcher;
import static jmul.markdown.pattern.RegexPatternMatchers.HEADING;
import static jmul.markdown.pattern.RegexPatternMatchers.IMAGE;
import static jmul.markdown.pattern.RegexPatternMatchers.LINE;
import static jmul.markdown.pattern.RegexPatternMatchers.LINK;
import static jmul.markdown.pattern.RegexPatternMatchers.ORDERED_LIST;
import static jmul.markdown.pattern.RegexPatternMatchers.QUOTE;
import static jmul.markdown.pattern.RegexPatternMatchers.UNORDERED_LIST;

import static jmul.string.StringConstants.NEW_LINE;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * The class contains various tests to verify the correctness of pattern matchers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class PatternMatcherValidParametersTest {

    /**
     * A pattern matcher.
     */
    private PatternMatcher patternMatcher;

    /**
     * A buffer.
     */
    private String buffer;

    /**
     * The expected match.
     */
    private List<String> expectedMatch;

    /**
     * A pattern match listener to verify a pattern match.
     */
    private TestPatternMatchListener patternMatchListener;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aPatternMatcher
     * @param aBuffer
     * @param anExpectedMatch
     */
    public PatternMatcherValidParametersTest(PatternMatcher aPatternMatcher, String aBuffer,
                                             String... anExpectedMatch) {

        patternMatcher = aPatternMatcher;
        buffer = aBuffer;
        expectedMatch = Arrays.asList(anExpectedMatch);

        patternMatchListener = new TestPatternMatchListener(patternMatcher, expectedMatch);
    }

    /**
     * Steps which have to be performed before a test.
     */
    @Before
    public void setUp() {

        patternMatcher.addPatternMatchListener(patternMatchListener);
    }

    /**
     * Steps which have to be performed after a test.
     */
    @After
    public void tearDown() {

        patternMatcher.removePatternMatchListener(patternMatchListener);
    }

    /**
     * Tests a specific pattern matcher.
     */
    @Test
    public void testPatternMatcher() {

        patternMatcher.informOnChange(buffer);
        assertTrue("No match exists!", patternMatchListener.hasActualMatch());
    }

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();


        parameters.add(newTest(HEADING, "# Header1", "# Header1", "#", "Header1"));
        parameters.add(newTest(HEADING, "  # Header1", "  # Header1", "#", "Header1"));
        parameters.add(newTest(HEADING, "#  Header1", "#  Header1", "#", "Header1"));
        parameters.add(newTest(HEADING, "## Header2", "## Header2", "##", "Header2"));
        parameters.add(newTest(HEADING, "### Header3", "### Header3", "###", "Header3"));


        parameters.add(newTest(LINK, "see this [Link](http://www.heise.de/) today", "[Link](http://www.heise.de/)",
                               "Link", "http://www.heise.de/"));
        parameters.add(newTest(LINK, NEW_LINE + "see this [Link](http://www.heise.de/) today",
                               "[Link](http://www.heise.de/)", "Link", "http://www.heise.de/"));
        parameters.add(newTest(LINK, "see this [Link](http://www.heise.de/) today" + NEW_LINE,
                               "[Link](http://www.heise.de/)", "Link", "http://www.heise.de/"));
        parameters.add(newTest(LINK, NEW_LINE + "see this [Link](http://www.heise.de/) today" + NEW_LINE,
                               "[Link](http://www.heise.de/)", "Link", "http://www.heise.de/"));


        parameters.add(newTest(IMAGE, "see this ![Image](./image.jpg) today", "![Image](./image.jpg)", "Image",
                               "./image.jpg"));


        parameters.add(newTest(QUOTE, "```", "```"));


        parameters.add(newTest(UNORDERED_LIST, "* Hello World", "* Hello World", "", "*", "Hello World"));
        parameters.add(newTest(UNORDERED_LIST, " * Hello World", " * Hello World", " ", "*", "Hello World"));
        parameters.add(newTest(UNORDERED_LIST, "  * Hello World", "  * Hello World", "  ", "*", "Hello World"));
        parameters.add(newTest(UNORDERED_LIST, "*  Hello World", "*  Hello World", "", "*", "Hello World"));
        parameters.add(newTest(UNORDERED_LIST, "*   Hello World", "*   Hello World", "", "*", "Hello World"));


        parameters.add(newTest(ORDERED_LIST, "1. Hello World", "1. Hello World", "", "1.", "Hello World"));
        parameters.add(newTest(ORDERED_LIST, " 1. Hello World", " 1. Hello World", " ", "1.", "Hello World"));
        parameters.add(newTest(ORDERED_LIST, "  1. Hello World", "  1. Hello World", "  ", "1.", "Hello World"));
        parameters.add(newTest(ORDERED_LIST, "1.  Hello World", "1.  Hello World", "", "1.", "Hello World"));
        parameters.add(newTest(ORDERED_LIST, "1.   Hello World", "1.   Hello World", "", "1.", "Hello World"));
        parameters.add(newTest(ORDERED_LIST, "42. Hello World", "42. Hello World", "", "42.", "Hello World"));


        parameters.add(newTest(LINE, "# Header1", "# Header1", "", "# Header1"));
        parameters.add(newTest(LINE, "```", "```", "", "```"));
        parameters.add(newTest(LINE, "* Hello World", "* Hello World", "", "* Hello World"));
        parameters.add(newTest(LINE, "1. Hello World", "1. Hello World", "", "1. Hello World"));


        return parameters;
    }

    /**
     * Creates a new test.
     *
     * @param aMatcher
     * @param aBuffer
     * @param someExpectedMatches
     *
     * @return a new test
     */
    private static Object[] newTest(PatternMatcher aMatcher, String aBuffer, String... someExpectedMatches) {

        return new Object[] { aMatcher, aBuffer, someExpectedMatches };
    }

}
