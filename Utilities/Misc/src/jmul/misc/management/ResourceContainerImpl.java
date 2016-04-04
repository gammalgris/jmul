/*
 * (J)ava (M)iscellaneous (U)tility (L)ibraries
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

package jmul.misc.management;

import java.util.HashMap;
import java.util.Map;


/**
 * An implementation of a resource container.<br />
 * <br />
 * <i>Note:</br />
 * All resource keys must be unique. Resources cannot be removed from the container nor be replaced.</i>
 *
 * @author Kristian Kutin
 */
public class ResourceContainerImpl implements ResourceContainer {

    /**
     * Internally all resources are managed in a map.
     */
    private Map<ResourceIdentifier, Object> resources;

    /**
     * Creates an empty resource container.
     */
    public ResourceContainerImpl() {

        resources = new HashMap<ResourceIdentifier, Object>();
    }

    /**
     * Adds a new resource to the resource container.
     *
     * @param aResourceIdentifier
     * @param aResource
     */
    public void putResource(ResourceIdentifier aResourceIdentifier, Object aResource) {

        if (resources.containsKey(aResourceIdentifier)) {

            String message = "The resource \"" + aResourceIdentifier + "\" already exists!";
            throw new IllegalArgumentException(message);
        }

        resources.put(aResourceIdentifier, aResource);
    }

    /**
     * Returns the resource which is associated with the specified resource key.
     *
     * @param aResourceIdentifier
     *
     * @return a resource
     */
    public Object getResource(ResourceIdentifier aResourceIdentifier) {

        return resources.get(aResourceIdentifier);
    }

}
