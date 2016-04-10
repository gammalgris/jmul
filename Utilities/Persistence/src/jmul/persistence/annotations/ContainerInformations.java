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

package jmul.persistence.annotations;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * This annotation is used to provide informations about the elements of a
 * container (e.g. collection, set).<br>
 * <br>
 * <i>(1) A transformation should first check if a field is marked with this
 * annotation (i.e. this way collection types which are provided by the java
 * framework can be marked and used, as well as custom implementations of
 * collections).<br>
 * <br>
 * (2) If (1) does not apply then the transformation should check if a concrete
 * implementation class is marked with this annotation (i.e. this allows the
 * transformation to handle the elements of a container which are themselves
 * collections).<br>
 * <br>
 * It is not possible to fully specify the element type with this annotation
 * thus this two step approach is used. But still with this two step approach
 * some legit declarations (e.g. Collection&lt;Collection&lt;String&gt;&gt;)
 * cannot be serialized dreictly. Only by providing customs classes for elements
 * is serialization possible while retaining type informations.</i>
 *
 * @author Kristian Kutin
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainerInformations {

    Class declaredElementType();

}
