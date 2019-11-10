/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2018  Kristian Kutin
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

package jmul.web.service;


import java.util.Collections;
import java.util.List;

import static jmul.checks.ParameterCheckHelper.checkFileNameParameter;
import static jmul.checks.ParameterCheckHelper.checkStringParameter;


/**
 * An implementation of a web service configuration.
 *
 * @author Kristian Kutin
 */
public class WebServiceConfigurationImpl implements WebServiceConfiguration {

    /**
     * Contains a relative web path.
     */
    private final String webPath;

    /**
     * Contains a relative or absolute file path.
     */
    private final String scriptPath;

    /**
     * Contains all parameters.
     */
    private final List<String> parameters;

    /**
     * Creates a new web service configuration according to the specified parameters.
     *
     * @param aWebPath
     *        a web path for invoking the web service
     * @param aScriptPath
     *        a relative or absolute file path to invoke a script file
     * @param someParameters
     *        all parameters which are needed to invoke the script
     */
    public WebServiceConfigurationImpl(String aWebPath, String aScriptPath, List<String> someParameters) {

        super();

        webPath = checkStringParameter(aWebPath);
        scriptPath = checkFileNameParameter(aScriptPath);
        parameters = Collections.unmodifiableList(someParameters);
    }

    /**
     * Returns the relative path under which the web service can be called.
     *
     * @return a relative web path
     */
    @Override
    public String getWebPath() {

        return webPath;
    }

    /**
     * Returns the path to the script which generates the response to the web service call.
     *
     * @return a relative or absolute script path
     */
    @Override
    public String getScriptPath() {

        return scriptPath;
    }

    /**
     * Returns all parameters with which to invoke the script.
     *
     * @return all parameters
     */
    @Override
    public String[] getParameters() {

        return parameters.toArray(new String[] { });
    }

}
