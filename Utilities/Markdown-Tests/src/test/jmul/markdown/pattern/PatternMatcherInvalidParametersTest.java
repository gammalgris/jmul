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

import jmul.misc.exceptions.EmptyStringParameterException;
import jmul.misc.exceptions.NullParameterException;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
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
public class PatternMatcherInvalidParametersTest {

    /**
     * A pattern matcher.
     */
    private PatternMatcher patternMatcher;

    /**
     * A buffer.
     */
    private String buffer;

    /**
     * A pattern match listener to verify a pattern match.
     */
    private TestPatternMatchListener patternMatchListener;

    /**
     * The expected match.
     */
    private List<String> expectedMatch;

    /**
     * The expected exception. <code>null</code> signals that no exception
     * is expected.
     */
    private Class expectedExceptionClass;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param aPatternMatcher
     * @param aBuffer
     * @param anExpectedExceptionClass
     * @param anExpectedMatch
     */
    public PatternMatcherInvalidParametersTest(PatternMatcher aPatternMatcher, String aBuffer,
                                               Class anExpectedExceptionClass, String... anExpectedMatch) {

        patternMatcher = aPatternMatcher;
        buffer = aBuffer;

        patternMatchListener = new TestPatternMatchListener(patternMatcher, Arrays.asList(anExpectedMatch));

        expectedExceptionClass = anExpectedExceptionClass;
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

        Exception actualException = null;
        Class actualExceptionClass = null;

        try {

            patternMatcher.informOnChange(buffer);
            assertFalse("A match exists!", patternMatchListener.hasActualMatch());
            return;

        } catch (Exception e) {

            actualException = e;
            actualExceptionClass = e.getClass();
        }


        if (!wasSuccessfulTest(expectedExceptionClass, actualExceptionClass)) {

            fail(newMessage(expectedExceptionClass, actualExceptionClass));
        }
    }

    /**
     * Tests if the test was successful according to the specified parameters.
     *
     * @param anExpectedExceptionClass
     * @param anActualExceptionClass
     *
     * @return <code>true</code> if the test was successful, else
     *         <code>false</code>
     */
    private static boolean wasSuccessfulTest(Class anExpectedExceptionClass, Class anActualExceptionClass) {

        if ((anExpectedExceptionClass != null) && (anActualExceptionClass != null)) {

            return anExpectedExceptionClass.isAssignableFrom(anActualExceptionClass);

        } else if ((anExpectedExceptionClass == null) && (anActualExceptionClass != null)) {

            return false;

        } else if ((anExpectedExceptionClass != null) && (anActualExceptionClass == null)) {

            return false;

        } else { // (expectedException == null) && (actualException == null)

            return true;
        }
    }

    /**
     * Creates a message according to the specified parameters.
     *
     * @param anExpectedExceptionClass
     * @param anActualExceptionClass
     *
     * @return a message
     */
    private static String newMessage(Class anExpectedExceptionClass, Class anActualExceptionClass) {

        StringBuilder message = new StringBuilder();

        if (anActualExceptionClass == null) {

            message.append("No exception is expected");

        } else {

            message.append("An exception is expected (");
            message.append(anActualExceptionClass.getCanonicalName());
            message.append(")");
        }

        if (anActualExceptionClass == null) {

            message.append(" and no exception was caught!");

        } else {

            if (anExpectedExceptionClass.isAssignableFrom(anActualExceptionClass)) {

                message.append(" and a matching exception was caught (");
                message.append(anExpectedExceptionClass.getCanonicalName());
                message.append(").");

            } else {

                message.append(" and an unexpected exception was caught (");
                message.append(anExpectedExceptionClass.getCanonicalName());
                message.append(")!");
            }
        }

        return message.toString();
    }

    /**
     * Returns a matrix of formula strings and expected results.
     *
     * @return a matrix of formula strings and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(newTest(HEADING, null, NullParameterException.class));
        parameters.add(newTest(HEADING, "", EmptyStringParameterException.class));
        parameters.add(newTest(HEADING, " ", EmptyStringParameterException.class));
        parameters.add(newTest(HEADING, "[a", null));

        parameters.add(newTest(LINK, null, IllegalArgumentException.class));
        parameters.add(newTest(LINK, "", IllegalArgumentException.class));
        parameters.add(newTest(LINK, " ", IllegalArgumentException.class));

        parameters.add(newTest(LINK, "see this [Link](http://www.heise.de/ today", IllegalArgumentException.class));
        parameters.add(newTest(LINK, "see this [Link]http://www.heise.de/) today", IllegalArgumentException.class));
        parameters.add(newTest(LINK, "see this [Link(http://www.heise.de/) today", IllegalArgumentException.class));
        parameters.add(newTest(LINK, "see this Link](http://www.heise.de/) today", IllegalArgumentException.class));

        parameters.add(newTest(IMAGE, "see this ![Image](./image.jpg today", IllegalArgumentException.class));
        parameters.add(newTest(IMAGE, "see this ![Image]./image.jpg) today", IllegalArgumentException.class));
        parameters.add(newTest(IMAGE, "see this ![Image(./image.jpg) today", IllegalArgumentException.class));
        parameters.add(newTest(IMAGE, "see this !Image](./image.jpg) today", IllegalArgumentException.class));

        parameters.add(newTest(UNORDERED_LIST, null, IllegalArgumentException.class));
        parameters.add(newTest(UNORDERED_LIST, "", IllegalArgumentException.class));
        parameters.add(newTest(UNORDERED_LIST, " ", IllegalArgumentException.class));

        parameters.add(newTest(ORDERED_LIST, null, IllegalArgumentException.class));
        parameters.add(newTest(ORDERED_LIST, "", IllegalArgumentException.class));
        parameters.add(newTest(ORDERED_LIST, " ", IllegalArgumentException.class));

        parameters.add(newTest(LINE, null, IllegalArgumentException.class));
        parameters.add(newTest(LINE, "", IllegalArgumentException.class));
        parameters.add(newTest(LINE, " ", IllegalArgumentException.class));

        parameters.add(newTest(QUOTE, null, IllegalArgumentException.class));
        parameters.add(newTest(QUOTE, "", IllegalArgumentException.class));
        parameters.add(newTest(QUOTE, " ", IllegalArgumentException.class));

        return parameters;
    }

    /**
     * Creates a new test.
     *
     * @param aMatcher
     * @param aBuffer
     * @param anExpectedExceptionClass
     * @param someExpectedGroups
     *
     * @return a new test
     */
    private static Object[] newTest(PatternMatcher aMatcher, String aBuffer, Class anExpectedExceptionClass,
                                    String... someExpectedGroups) {

        return new Object[] { aMatcher, aBuffer, anExpectedExceptionClass, someExpectedGroups };
    }

}
