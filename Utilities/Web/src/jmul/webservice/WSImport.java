/*
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

package jmul.webservice;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ResourceBundle;

import jmul.io.FileHelper;

import jmul.misc.checks.ParameterCheckHelper;

import static jmul.string.StringConstants.NEW_LINE;


/**
 * This class implements the invocation of the wsimport tool which is provided
 * with a java development environment (see
 * <a href="http://docs.oracle.com/javase/6/docs/technotes/tools/share/wsimport.html">documentation</a>).
 * The tool will generate code according to a specified WSDL contract. The
 * generated code allows the invocation of a web service.<br />
 * <br />
 * <i>Notes:<br />
 * The actual invocation relies on <code>Runtime.exec()</code>. See following
 * <a href="http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps.html?page=1">article</a>
 * for details.</i>
 *
 * @author Kristian Kutin
 */
public class WSImport implements CodeGenerator {

    /**
     * An exit code.
     */
    private static final int EXECUTION_OK = 0;

    /**
     * The default constructor.
     */
    public WSImport() {
    }

    /**
     * Generates code according to the specified source and returns
     * details of the build target (i.e. output directory).
     *
     * @param aSource
     *
     * @return details of the build target
     */
    public String generateCode(String aSource) {

        return generateCode(aSource, getDefaultTarget());
    }

    /**
     * Generates code according to the specified source and returns
     * details of the build target (i.e. output directory).
     *
     * @param aSource
     * @param aTarget
     *
     * @return details of the build target
     */
    public String generateCode(String aSource, String aTarget) {

        ParameterCheckHelper.checkStringParameter(aSource);
        ParameterCheckHelper.checkStringParameter(aTarget);


        cleanTarget(aTarget);

        String commandLine = getResolvedCommandLine(aSource, aTarget);
        int exitCode = invokeGenerator(commandLine);

        if (exitCode != EXECUTION_OK) {

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
    public String getDefaultTarget() {

        return ConfigurationReader.getGeneratorTarget();
    }

    /**
     * Cleans the output target (i.e. output directory).
     *
     * @param aTarget
     */
    public void cleanTarget(String aTarget) {

        File directory = new File(aTarget);

        if (directory.exists()) {

            FileHelper.delete(directory);
        }

        directory.mkdirs();
    }

    /**
     * Returns the resolved command line (i.e. placeholders are replaced by their
     * actual values).
     *
     * @param aSource
     * @param aTarget
     *
     * @return a command line
     */
    public String getResolvedCommandLine(String aSource, String aTarget) {

        String commandLine = ConfigurationReader.getCommandLine();

        commandLine =
            commandLine.replace(ConfigurationReader.GENERATOR_EXECUTABLE_PLACEHOLDER,
                                ConfigurationReader.getGeneratorExecutable());
        commandLine = commandLine.replace(ConfigurationReader.GENERATOR_INPUT_PLACEHOLDER, aSource);
        commandLine = commandLine.replace(ConfigurationReader.GENERATOR_TARGET_PLACEHOLDER, aTarget);

        return commandLine;
    }

    /**
     * Invokes the actual code generator as specified in the specified
     * command line.
     *
     * @param aCommandLine
     *
     * @return an exit code
     */
    public int invokeGenerator(String aCommandLine) {

        Runtime runtime = Runtime.getRuntime();

        Process process = null;
        int exitValue = -1;

        try {

            process = runtime.exec(aCommandLine);

        } catch (IOException e) {

            String message = "It was not possible to invoke the code generator!";
            throw new CodeGeneratorException(message, e);
        }


        ProcessMonitor monitor = new ProcessMonitor(process);

        StringBuffer consoleOutput = new StringBuffer();
        StringBuffer errorOutput = new StringBuffer();

        try {

            // Assuming that the process ended, the exit code and console
            // outputs are stored.

            exitValue = process.exitValue();

            consoleOutput.append(monitor.getOutputMessage());
            errorOutput.append(monitor.getErrorMessage());

        } catch (IllegalThreadStateException e) {

            // The process has not finished yet. Wait for the next opportunity
            // to get the exit code and console outputs.

            try {

                exitValue = process.waitFor();

                consoleOutput.append(monitor.getOutputMessage());
                errorOutput.append(monitor.getErrorMessage());

            } catch (InterruptedException f) {

                String message = "The code generator has exited with an error!" + NEW_LINE + monitor.getErrorMessage();
                throw new CodeGeneratorException(message, e);
            }
        }

        return exitValue;
    }


    /**
     * Die innere Klasse überwacht die Ausgabe eines Runtime-Prozesses.
     */
    class ProcessMonitor {

        /**
         * Das Klassenattribut referenziert einen Runtime-Prozess.
         */
        private Process process;

        /**
         * Das Klassenattribut überwacht einen Eingabestrom.
         */
        private InputStreamMonitor outputStreamMonitor;

        /**
         * Das Klassenattribut überwacht einen Eingabestrom.
         */
        private InputStreamMonitor errorStreamMonitor;

        /**
         * Der Standardkonstruktor.
         *
         * @param aProcess
         *        ein Runtime-Prozess
         */
        ProcessMonitor(Process aProcess) {

            process = aProcess;

            outputStreamMonitor = new InputStreamMonitor(process.getInputStream());
            errorStreamMonitor = new InputStreamMonitor(process.getErrorStream());


            Thread t = null;

            t = new Thread(outputStreamMonitor);
            t.start();
            t = new Thread(errorStreamMonitor);
            t.start();
        }

        /**
         * Die Methode liefert eine Ausgabe des Runtime-Prozesses.
         *
         * @return einen Meldungstext
         */
        public String getOutputMessage() {

            return outputStreamMonitor.getStreamContent();
        }

        /**
         * Die Methode liefert eine Ausgabe des Runtime-Prozesses.
         *
         * @return einen Meldungstext
         */
        public String getErrorMessage() {

            return errorStreamMonitor.getStreamContent();
        }

    }


    /**
     * Die innere Klasse überwacht einen Eingabestrom.
     */
    class InputStreamMonitor implements Runnable {

        /**
         * Das Klassenattribut liest den Eingabestrom aus.
         */
        private BufferedReader reader;

        /**
         * Das Klassenattribut enthält den Inhalt des Eingabestroms, soweit
         * dieser auslesbar war.
         */
        private StringBuffer streamContent;

        /**
         * Das Klassenattribut enthält die Information ob der Eingabestrom
         * zu Ende ist.
         */
        private boolean endOfStream;

        /**
         * Der Standardkonstruktor.
         *
         * @param anInputStream
         *        ein Eingabestrom
         */
        InputStreamMonitor(InputStream anInputStream) {

            InputStreamReader isr = new InputStreamReader(anInputStream);
            reader = new BufferedReader(isr);

            streamContent = new StringBuffer();

            endOfStream = false;
        }

        /**
         * Die Methode liest den Eingabestrom vollständig aus und schreibt die
         * Daten in einen StringBuffer.
         */
        public void run() {

            try {

                while (!endOfStream) {

                    String line = reader.readLine();

                    endOfStream = (line == null);
                    if (!endOfStream) {

                        streamContent.append(line);
                        streamContent.append(NEW_LINE);
                    }
                }

                reader.close();

            } catch (IOException e) {

                endOfStream = true;
            }
        }

        /**
         * Die Methode prüft ob der EIngabestrom vollständig ausgelesen wurde.
         *
         * @return true, wenn der Eingabestrom ausgelesen wurde, ansonsten false
         */
        public boolean reachedEndOfStream() {

            return endOfStream;
        }

        /**
         * Die Methode liefert den Inhalt des Eingabestroms zurück.
         *
         * @return der Inhalt des Eingabestroms oder <code>null</code> falls der
         *         Eingabestrom noch nicht vollständig ausgelesen wurde.
         */
        public String getStreamContent() {

            if (endOfStream) {

                return streamContent.toString();

            } else {

                return null;
            }
        }

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
