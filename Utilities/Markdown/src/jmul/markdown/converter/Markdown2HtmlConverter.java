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

package jmul.markdown.converter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jmul.io.CoupledStreams;

import jmul.markdown.StreamsHelper;
import jmul.markdown.processor.StreamProcessor;

import static jmul.misc.checks.ParameterCheckHelper.checkFileNameParameter;
import static jmul.misc.checks.ParameterCheckHelper.checkFileParameter;

import jmul.string.StringConcatenator;


/**
 * An implementation of {@link jmul.markdown.converter.TextConverter}which converts
 * markdown to html.
 *
 * @author Kristian Kutin
 */
public class Markdown2HtmlConverter implements TextConverter {

    /**
     * The default constructor.
     */
    public Markdown2HtmlConverter() {
    }

    /**
     * The text content of the specified source file is converted and written
     * to the specified output file.
     *
     * @param aSourceFileName
     * @param anOutputFileName
     */
    @Override
    public void convert(String aSourceFileName, String anOutputFileName) {

        checkFileNameParameter(aSourceFileName);
        checkFileNameParameter(anOutputFileName);

        convert(new File(aSourceFileName), new File(anOutputFileName));
    }


    /**
     * The text content of the specified source file is converted and written
     * to the specified output file.
     *
     * @param aSourceFile
     * @param anOutputFile
     */
    @Override
    public void convert(File aSourceFile, File anOutputFile) {

        checkFileParameter(aSourceFile);
        checkFileParameter(anOutputFile);


        CoupledStreams coupledStreams = null;

        try {

            coupledStreams = StreamsHelper.openStreams(aSourceFile, anOutputFile);

        } catch (FileNotFoundException e) {

            StringConcatenator message =
                new StringConcatenator("The specified source file (", aSourceFile.getAbsolutePath(),
                                       ") doesn't exist!");
            throw new TextConverterException(String.valueOf(message), e);

        } catch (IOException e) {

            StringConcatenator message =
                new StringConcatenator("The specified output file (", anOutputFile.getAbsolutePath(),
                                       ") cannot be opened!");
            throw new TextConverterException(String.valueOf(message), e);
        }


        StreamProcessor processor = null;
        processor.processFile(coupledStreams);
    }

}
