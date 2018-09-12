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

package jmul.web.service;


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
     * Creates a new web service configuration according to the specified parameters.
     *
     * @param aWebPath
     *        a web path for invoking the web service
     * @param aScriptPath
     *        a relative or absolute file path to invoke a script file
     */
    public WebServiceConfigurationImpl(String aWebPath, String aScriptPath) {

        super();

        webPath = checkStringParameter(aWebPath);
        scriptPath = checkFileNameParameter(aScriptPath);
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
     * The script which generates a the response to the web service call.
     *
     * @return a relative or absolute script path
     */
    @Override
    public String getScriptPath() {

        return scriptPath;
    }

}
