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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.markdown.processor;


import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.util.Collection;
import java.util.List;

import jmul.io.CoupledStreams;

import static jmul.markdown.StreamsHelper.INPUT_STREAM;
import static jmul.markdown.StreamsHelper.OUTPUT_STREAM;
import jmul.markdown.buffer.TextBuffer;
import jmul.markdown.buffer.TextBufferImpl;
import jmul.markdown.pattern.PatternMatcher;


/**
 * An implementation of {@link jmul.markdown.processor.StreamProcessor} which processes
 * a markdown file.
 *
 * @author Kristian Kutin
 */
public class MarkdownFileProcessor implements StreamProcessor {

    /**
     * All pattern matchers.
     */
    private final Collection<PatternMatcher> patternMatchers;

    /**
     * The default constructor.
     */
    public MarkdownFileProcessor(Collection<PatternMatcher> somePatternMatchers) {

        patternMatchers = somePatternMatchers;

        //TODO
    }

    /**
     * Processes the specified input stream and writes the output to
     * the specified output stream.
     *
     * @param someCoupledStream
     */
    @Override
    public void processFile(CoupledStreams someCoupledStream) {

        BufferedReader reader = (BufferedReader) someCoupledStream.getStream(INPUT_STREAM);
        BufferedWriter writer = (BufferedWriter) someCoupledStream.getStream(OUTPUT_STREAM);


        TextBuffer buffer = new TextBufferImpl(reader);


        for (PatternMatcher patternMatcher : patternMatchers) {

            buffer.addBufferChangeListener(patternMatcher);
        }


        for (PatternMatcher patternMatcher : patternMatchers) {

            patternMatcher.addPatternMatchListener(this);
        }


        //TODO missing loop to iterate through file
        //TODO error handling for deregistering listeners


        for (PatternMatcher patternMatcher : patternMatchers) {

            patternMatcher.removePatternMatchListener(this);
        }
    }

    /**
     * Informs this listener if a matching pattern was encountered.
     *
     * @param aMatcher
     * @param aMatch
     */
    @Override
    public void informOnMatch(PatternMatcher aMatcher, List<String> aMatch) {

        //TODO missing translation of text sections
        //TODO missing writing to output file
    }

}
