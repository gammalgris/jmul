/*
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2015  Kristian Kutin
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

package jmul.network.http;


import java.util.ResourceBundle;


/**
 * This enumeration contains response codes for HTTP requests.<br />
 * <br />
 * <i>Notes:<br />
 * The response codes are taken from following resources:<br />
 * <ul>
 *   <li><a href="https://en.wikipedia.org/wiki/List_of_HTTP_status_codes">Wikipedia</a></li>
 *   <li><a href="http://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml">IANA</a></li>
 * </ul></i>
 *
 * @author Kristian Kutin
 */
public enum ResponseCodes implements ResponseCode {


    NO_HTTP("NO_HTTP"),

    RC100("RC100"),
    RC101("RC101"),
    RC102("RC102"),

    RC200("RC200"),
    RC201("RC201"),
    RC202("RC202"),
    RC203("RC203"),
    RC204("RC204"),
    RC205("RC205"),
    RC206("RC206"),
    RC207("RC207"),
    RC208("RC208"),
    RC226("RC226"),

    RC300("RC300"),
    RC301("RC301"),
    RC302("RC302"),
    RC303("RC303"),
    RC304("RC304"),
    RC305("RC305"),
    RC306("RC306"),
    RC307("RC307"),
    RC308("RC308"),

    RC400("RC400"),
    RC401("RC401"),
    RC402("RC402"),
    RC403("RC403"),
    RC404("RC404"),
    RC405("RC405"),
    RC406("RC406"),
    RC407("RC407"),
    RC408("RC408"),
    RC409("RC409"),
    RC410("RC410"),
    RC411("RC411"),
    RC412("RC412"),
    RC413("RC413"),
    RC414("RC414"),
    RC415("RC415"),
    RC416("RC416"),
    RC417("RC417"),
    RC421("RC421"),
    RC422("RC422"),
    RC423("RC423"),
    RC424("RC424"),
    RC426("RC426"),
    RC428("RC428"),
    RC429("RC429"),
    RC431("RC431"),
    RC451("RC451"),

    RC500("RC500"),
    RC501("RC501"),
    RC502("RC502"),
    RC503("RC503"),
    RC504("RC504"),
    RC505("RC505"),
    RC506("RC506"),
    RC507("RC507"),
    RC508("RC508"),
    RC510("RC510"),
    RC511("RC511"), ;


    /**
     * A suffix for a property key.
     */
    private static final String VALUE_SUFFIX = ".value";

    /**
     * A suffix for a property key.
     */
    private static final String DESCRIPTION_SUFFIX = ".description";

    /**
     * A suffix for a property key.
     */
    private static final String REFERENCE_SUFFIX = ".reference";

    /**
     * A response code value.
     */
    private final int value;

    /**
     * A response code description.
     */
    private final String description;

    /**
     * A response code reference.
     */
    private final String reference;

    /**
     * Creates a new enumeration element.
     *
     * @param aValue
     * @param aDescription
     * @param aReference
     */
    private ResponseCodes(String entryName) {

        ResourceBundle bundle = ResourceBundle.getBundle(ResponseCodes.class.getName());

        value = Integer.parseInt(getString(bundle, entryName, VALUE_SUFFIX));
        description = getString(bundle, entryName, DESCRIPTION_SUFFIX);
        reference = getString(bundle, entryName, REFERENCE_SUFFIX);
    }

    /**
     * Retrieves the specified value from a resource bundle. If no such value exists an exception
     * is thrown.
     *
     * @param aBundle
     * @param anEntryName
     * @param aSuffix
     *
     * @return a value from a resource bundle
     */
    private static String getString(ResourceBundle aBundle, String anEntryName, String aSuffix) {

        String key = anEntryName + aSuffix;
        String value = aBundle.getString(key);

        if (value == null) {

            String message = "No value is associated with the specified key \"" + key + "\"!";
            throw new IllegalArgumentException(message);
        }

        value = value.trim();

        return value;
    }

    /**
     * A getter method.
     *
     * @return a response code value
     */
    @Override
    public int getValue() {

        return value;
    }

    /**
     * A getter method.
     *
     * @return a response code description
     */
    @Override
    public String getDescription() {

        return description;
    }

    /**
     * A getter method.
     *
     * @return a response code reference
     */
    @Override
    public String getReference() {

        return reference;
    }

    /**
     * Returns a string representation for this enumeration element.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append("value=");
        buffer.append(getValue());
        buffer.append("; description=");
        buffer.append(getDescription());
        buffer.append("; reference=");
        buffer.append(getReference());

        return String.valueOf(buffer);
    }

    /**
     * Returns details to the specified response code value. If no such response code
     * exists (i.e. because it is undefined) an exception is thrown.
     *
     * @param aValue
     *
     * @return a response code
     */
    public static ResponseCode getResponseCode(int aValue) {

        for (ResponseCode responseCode : ResponseCodes.values()) {

            int actualValue = responseCode.getValue();

            if (actualValue == aValue) {

                return responseCode;
            }
        }

        String message = "The value " + aValue + " is unassigned!";
        throw new IllegalArgumentException(message);
    }

}
