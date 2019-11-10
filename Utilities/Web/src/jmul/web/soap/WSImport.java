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

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.web.soap;


import java.io.File;

import java.util.ResourceBundle;

import jmul.checks.ParameterCheckHelper;

import jmul.external.Command;
import jmul.external.CommandImpl;
import jmul.external.CommandInvoker;
import jmul.external.CommandInvokerImpl;
import jmul.external.InvocationResult;

import jmul.io.FileDeletionHelper;

import static jmul.string.Constants.SPACE;


/**
 * This class implements the invocation of the wsimport tool which is provided
 * with a java development environment (see
 * <a href="http://docs.oracle.com/javase/6/docs/technotes/tools/share/wsimport.html">documentation</a>).
 * The tool will generate code according to a specified WSDL contract. The
 * generated code allows the invocation of a web service.<br>
 * <br>
 * <i>Notes:<br>
 * The actual invocation relies on <code>Runtime.exec()</code>. See following
 * <a href="http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps.html?page=1">article</a>
 * for details.</i>
 *
 * @author Kristian Kutin
 */
public class WSImport implements CodeGenerator {

    /**
     * An entity for invoking external commands.
     */
    private CommandInvoker invoker;

    /**
     * The default constructor.
     */
    public WSImport() {

        super();

        invoker = new CommandInvokerImpl();
    }

    /**
     * Generates code according to the specified source and returns
     * details of the build target (i.e. output directory).
     *
     * @param aSource
     *        the source which provides details about the code which is to be generated
     *
     * @return details of the build target
     */
    @Override
    public String generateCode(String aSource) {

        return generateCode(aSource, getDefaultTarget());
    }

    /**
     * Generates code according to the specified source and returns
     * details of the build target (i.e. output directory).
     *
     * @param aSource
     *        the source which provides details about the code which is to be generated
     * @param aTarget
     *        the place where to put the generated code
     *
     * @return details of the build target
     */
    @Override
    public String generateCode(String aSource, String aTarget) {

        ParameterCheckHelper.checkStringParameter(aSource);
        ParameterCheckHelper.checkStringParameter(aTarget);


        cleanTarget(aTarget);

        Command command = getResolvedCommandLine(aSource, aTarget);
        InvocationResult result = invoker.invoke(command);


        int exitCode = result.getExitValue();

        if (result.hasFailed()) {

            String message = "The code generator has finished with an error (exit code=" + exitCode + ")!";
            throw new CodeGeneratorException(message);
        }

        return aTarget;
    }

    /**
     * Returns the default target (i.e. output directory) for generating
     * code.
     *
     * @return the default target
     */
    @Override
    public String getDefaultTarget() {

        return ConfigurationReader.getGeneratorTarget();
    }

    /**
     * Cleans the output target (i.e. output directory).
     *
     * @param aTarget
     *        the place where to put the generated code
     */
    public void cleanTarget(String aTarget) {

        File directory = new File(aTarget);

        if (directory.exists()) {

            FileDeletionHelper.delete(directory, true);
        }

        directory.mkdirs();
    }

    /**
     * Returns the resolved command line (i.e. placeholders are replaced by their
     * actual values).
     *
     * @param aSource
     *        the source which provides details about the code which is to be generated
     * @param aTarget
     *        the place where to put the generated code
     *
     * @return a command line
     */
    public Command getResolvedCommandLine(String aSource, String aTarget) {

        String commandLine = ConfigurationReader.getCommandLine();

        commandLine =
            commandLine.replace(ConfigurationReader.GENERATOR_EXECUTABLE_PLACEHOLDER,
                                ConfigurationReader.getGeneratorExecutable());
        commandLine = commandLine.replace(ConfigurationReader.GENERATOR_INPUT_PLACEHOLDER, aSource);
        commandLine = commandLine.replace(ConfigurationReader.GENERATOR_TARGET_PLACEHOLDER, aTarget);

        return new CommandImpl(commandLine.split(SPACE));
    }

}


/**
 * This utility class loads the configuration which is required for invoking
 * wsimport.
 *
 * @author Kristian Kutin
 */
final class ConfigurationReader {

    /**
     * A property key.
     */
    private static final String GENERATOR_EXECUTABLE_KEY = "generator.executable";

    /**
     * A property key.
     */
    private static final String GENERATOR_TARGET_KEY = "generator.target";

    /**
     * A property key.
     */
    private static final String TARGET_SOURCE_FILETYPE_KEY = "target.source.fileType";

    /**
     * A property key.
     */
    private static final String TARGET_BUILD_FILETYPE_KEY = "target.build.fileType";

    /**
     * A property key.
     */
    private static final String COMMAND_LINE = "command.line";

    /**
     * A placeholder.
     */
    public static final String GENERATOR_EXECUTABLE_PLACEHOLDER = "%" + GENERATOR_EXECUTABLE_KEY + "%";

    /**
     * A placeholder.
     */
    public static final String GENERATOR_TARGET_PLACEHOLDER = "%" + GENERATOR_TARGET_KEY + "%";

    /**
     * A placeholder.
     */
    public static final String GENERATOR_INPUT_PLACEHOLDER = "%generator.input%";

    /**
     * The default constructor.
     */
    private ConfigurationReader() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns a resource bundle.
     *
     * @return a resource bundle
     */
    private static ResourceBundle getResourceBundle() {

        return ResourceBundle.getBundle(WSImport.class.getName());
    }

    /**
     * Returns the location of the executable.
     *
     * @return the location of the executable
     */
    public static String getGeneratorExecutable() {

        String fileName = getResourceBundle().getString(GENERATOR_EXECUTABLE_KEY);
        ParameterCheckHelper.checkFileNameParameter(fileName);

        return fileName;
    }

    /**
     * Returns the location of the generated output.
     *
     * @return the location of the generated output
     */
    public static String getGeneratorTarget() {

        String path = getResourceBundle().getString(GENERATOR_TARGET_KEY);
        ParameterCheckHelper.checkDirectoryNameParameter(path);

        return path;
    }

    /**
     * Returns the file extension of generated build files.
     *
     * @return a file extension
     */
    public static String getTargetBuildFileType() {

        String fileType = getResourceBundle().getString(TARGET_BUILD_FILETYPE_KEY);
        ParameterCheckHelper.checkStringParameter(fileType);

        return fileType;
    }

    /**
     * Returns the file extension of generated source files.
     *
     * @return a file extension
     */
    public static String getTargetSourceFileType() {

        String fileType = getResourceBundle().getString(TARGET_SOURCE_FILETYPE_KEY);
        ParameterCheckHelper.checkStringParameter(fileType);

        return fileType;
    }

    /**
     * Returns the command line for invoking the code generator.
     *
     * @return a command line
     */
    public static String getCommandLine() {

        String commandLine = getResourceBundle().getString(COMMAND_LINE);
        ParameterCheckHelper.checkStringParameter(commandLine);

        return commandLine;
    }

}
