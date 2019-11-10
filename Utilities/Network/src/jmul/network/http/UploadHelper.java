/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2017  Kristian Kutin
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

package jmul.network.http;


import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static jmul.checks.ParameterCheckHelper.checkFileNameParameter;
import static jmul.checks.ParameterCheckHelper.checkFileParameter;
import static jmul.checks.ParameterCheckHelper.checkStringParameter;

import jmul.io.NestedStreams;
import jmul.io.NestedStreamsImpl;
import jmul.io.StreamsHelper;

import jmul.network.NetworkException;
import static jmul.network.http.Protocols.HTTP;
import static jmul.network.http.Protocols.HTTPS;

import static jmul.string.Constants.NEW_LINE_WINDOWS;


/**
 * A utility class for HTTP uploads.<br>
 * <br>
 * For more details see
 * <ul>
 *   <li><a href="https://www.ietf.org/rfc/rfc2388.txt">technical specification</a></li>
 *   <li><a href="https://stackoverflow.com/questions/2469451/upload-files-from-java-client-to-a-http-server">example</a></li>
 * </ul>
 *
 * @author Kristian Kutin
 */
public final class UploadHelper {

    /**
     * The charset which will be used.
     */
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * The default constructor.
     */
    private UploadHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Performs a file upload via the HTTP protocol according to the specified
     * parameters.
     *
     * @param aURLString
     *        a URL string
     * @param aFileName
     *        a filename
     *
     * @return a response code
     *
     * @throws IOException
     *         is thrown if an error occurs during the upload
     */
    public static ResponseCode uploadFile(String aURLString, String aFileName) throws IOException {

        return uploadFile(new URL(checkStringParameter(aURLString)), new File(checkFileNameParameter(aFileName)));
    }

    /**
     * Performs a file upload via the HTTP protocol according to the specified
     * parameters.
     *
     * @param aURL
     *        a URL
     * @param aFileName
     *        a filename
     *
     * @return a response code
     *
     * @throws IOException
     *         is thrown if an error occurs during the upload
     */
    public static ResponseCode uploadFile(URL aURL, String aFileName) throws IOException {

        return uploadFile(aURL, new File(checkFileNameParameter(aFileName)));
    }

    /**
     * Performs a file upload via the HTTP protocol according to the specified
     * parameters.
     *
     * @param aURLString
     *        a URL string
     * @param aFile
     *        a file
     *
     * @return a response code
     *
     * @throws IOException
     *         is thrown if an error occurs during the upload
     */
    public static ResponseCode uploadFile(String aURLString, File aFile) throws IOException {

        return uploadFile(new URL(checkStringParameter(aURLString)), checkFileParameter(aFile));
    }

    /**
     * Performs a file upload via the HTTP protocol according to the specified
     * parameters.
     *
     * @param aURL
     *        a URL
     * @param aFile
     *        a file
     *
     * @return a response code
     *
     * @throws IOException
     *         is thrown if an error occurs during the upload
     */
    public static ResponseCode uploadFile(URL aURL, File aFile) throws IOException {

        String actualProtocol = aURL.getProtocol();

        if (HTTP.equalsProtocolName(actualProtocol)) {

            return uploadFileViaHttp(aURL, aFile);

        } else if (HTTPS.equalsProtocolName(actualProtocol)) {

            return uploadFileViaHttps(aURL, aFile);
        }

        String message = "Unknown protocol: \"" + actualProtocol + "\"";
        throw new NetworkException(message);
    }

    /**
     * Performs a file upload via the HTTP protocol according to the specified
     * parameters.
     *
     * @param aURL
     *        a URL
     * @param aFile
     *        a file
     *
     * @return a response code
     *
     * @throws IOException
     *         is thrown if an error occurs during the upload
     */
    private static ResponseCode uploadFileViaHttp(URL aURL, File aFile) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) aURL.openConnection();

        connection.setDoOutput(true);

        String boundary = createUniqueHexString();
        String contentType = URLConnection.guessContentTypeFromName(aFile.getName());

        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        NestedStreams nestedStreams = openStream(connection);

        Writer writer = (Writer) nestedStreams.getOuterStream();
        OutputStream outputStream = (OutputStream) nestedStreams.getStream(1);

        try {

            writer.append("--");
            writer.append(boundary);
            writer.append(NEW_LINE_WINDOWS);

            writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"");
            writer.append(contentType);
            writer.append(NEW_LINE_WINDOWS);

            writer.append("Content-Transfer-Encoding: binary");
            writer.append(NEW_LINE_WINDOWS);
            writer.append(NEW_LINE_WINDOWS);

            Files.copy(aFile.toPath(), outputStream);

            writer.append(NEW_LINE_WINDOWS);
            writer.flush();

            writer.append("--");
            writer.append(boundary);
            writer.append(NEW_LINE_WINDOWS);

            writer.flush();

        } catch (IOException e) {

            StreamsHelper.closeStreamAfterException(nestedStreams, e);
        }

        int value = -1;
        try {

            value = connection.getResponseCode();

        } catch (IOException e) {

            StreamsHelper.closeStreamAfterException(nestedStreams, e);
        }

        StreamsHelper.closeStream(nestedStreams);

        return ResponseCodes.getResponseCode(value);
    }

    /**
     * Performs a file upload via the HTTPS protocol according to the specified
     * parameters.
     *
     * @param aURL
     *        a URL
     * @param aFile
     *        a file
     *
     * @return a response code
     *
     * @throws IOException
     *         is thrown if an error occurs during the upload
     */
    private static ResponseCode uploadFileViaHttps(URL aURL, File aFile) {

        //TODO implementation is missing

        throw new UnsupportedOperationException();
    }

    /**
     * Opens an output stream to perform a file upload.
     *
     * @param aConnection
     *        a connection object
     *
     * @return a stream container
     *
     * @throws IOException
     *         is thrown if no connection could be established
     */
    private static NestedStreams openStream(URLConnection aConnection) throws IOException {

        OutputStream os = aConnection.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, DEFAULT_CHARSET);
        Writer w = new BufferedWriter(osw);

        return new NestedStreamsImpl(w, osw, os);
    }

    /**
     * Returns a unique Hex-String.
     *
     * @return a hex string
     */
    private static String createUniqueHexString() {

        return Long.toHexString(System.currentTimeMillis());
    }

}
