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

package jmul.xpath;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class contains query details.
 *
 * @author Kristian Kutin
 */
public class XPathQuery {

    /**
     * An XPath expression.
     */
    private final String xpathExpression;

    /**
     * The value which is expected to be returned by the XPath expression.
     */
    private final String expectedValue;

    /**
     * An XPath evaluator.
     */
    private final XPath xpath;

    /**
     * Creates this object.
     *
     * @param anXpathExpression
     * @param anExpectedValue
     */
    public XPathQuery(String anXpathExpression, String anExpectedValue) {

        if (anXpathExpression == null) {

            String message = "No valid XPath expression has been specified!";
            throw new IllegalArgumentException(message);
        }

        if (anExpectedValue == null) {

            String message =
                "The expetced value is null which is not allowed!";
            throw new IllegalArgumentException(message);
        }

        xpathExpression = anXpathExpression;
        expectedValue = anExpectedValue;

        xpath = XPathFactory.newInstance().newXPath();
    }

    /**
     * Checks if the query finds matching entries within the specified document.
     *
     * @param aDocument
     *
     * @return <code>true</code> if the query finds matching entries, else
     *         <code>false</code>
     */
    public boolean performQuery(Document aDocument) {

        try {

            XPathExpression expr = xpath.compile(xpathExpression);
            NodeList list =
                (NodeList)expr.evaluate(aDocument, XPathConstants.NODESET);

            for (int a = 0; a < list.getLength(); a++) {

                Node node = list.item(a);
                String foundValue = node.getTextContent();

                if (foundValue.equals(expectedValue)) {

                    return true;
                }
            }

        } catch (XPathExpressionException e) {

            return false;
        }

        return false;
    }

    /**
     * Returns a string representation for this object.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append("xpath query =\"");
        buffer.append(xpathExpression);
        buffer.append("\"; expected value=\"");
        buffer.append(expectedValue);
        buffer.append("\"");

        return String.valueOf(buffer);
    }

}
