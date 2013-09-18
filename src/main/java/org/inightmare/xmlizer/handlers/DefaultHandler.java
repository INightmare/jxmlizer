/*
 *  Copyright 2013 Giedrius 'inightmare' Graževičius
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.inightmare.xmlizer.handlers;

import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.inightmare.xmlizer.ReaderContext;
import org.inightmare.xmlizer.TypeHandler;
import org.inightmare.xmlizer.WriterContext;
import org.inightmare.xmlizer.misc.MarshallerUtils;
import org.inightmare.xmlizer.misc.XmlConstants;
import org.inightmare.xmlizer.reflection.Property;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Default handler for all types not handled by other handlers.
 * 
 * @author giedrius
 */
public class DefaultHandler implements TypeHandler {

    public void marshal(Property property, Object value, XMLStreamWriter writer, WriterContext context) throws XMLStreamException {
        if (value != null) {
            writer.writeStartElement(property.getName());
            writeTypeInfoIfNeeded(value, property, writer);
            
            context.writeObject(property, value);
            
            writer.writeEndElement();
        }
    }

    public boolean canHandle(Class<?> t) {
        return true;
    }

    public Object unmarshal(Property property, Node node, ReaderContext context) {
        Class<?> type = determineType(property, node, context);
        //List<Property> properties = context.listProperties(property.getSimpleType());
        List<Property> properties = context.listProperties(type);
        Object object = context.instantiate(type);
        
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            Property prop = findProperty(context.getRealName(object.getClass(), childNode.getNodeName()), properties);
            
            if (prop == null) {
                continue; // TODO: log that non-existent property was skipped
            }
            
            Object propValue = context.readObject(prop, childNode);
            context.setProperty(prop, object, propValue);
        }
        
        return object;
    }
    
    private Property findProperty(String name, List<Property> properties) {
        for (Property prop: properties) {
            if (prop.getName().equals(name)) {
                return prop;
            }
        }
        return null;
    }

    private Class determineType(Property property, Node node, ReaderContext context) {
        Node attributeNode = node.getAttributes().getNamedItemNS(XmlConstants.XML_SCHEMA_INSTANCE_NAMESPACE, XmlConstants.XML_TYPE);
        
        Class type = null;
        
        if (attributeNode != null) {
            type = context.determineType(attributeNode.getNodeValue());
        }
        
        if (type == null) {
            type = property.getSimpleType();
        }
        
        return type;
    }

    protected void writeTypeInfoIfNeeded(Object value, Property property, XMLStreamWriter writer) throws XMLStreamException {
        MarshallerUtils.writeTypeInfoIfNeeded(value.getClass(), property, writer);
    }

}
