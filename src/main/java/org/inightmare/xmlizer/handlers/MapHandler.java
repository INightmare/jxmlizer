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

import java.util.Map;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.inightmare.xmlizer.ReaderContext;
import org.inightmare.xmlizer.TypeHandler;
import org.inightmare.xmlizer.WriterContext;
import org.inightmare.xmlizer.misc.MarshallerUtils;
import org.inightmare.xmlizer.reflection.Property;
import org.inightmare.xmlizer.reflection.ReflectionUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Marshals and unmarshals Maps in JAXB compatible way.
 * @author giedrius
 */
public class MapHandler implements TypeHandler {

    private static final String MAP_ENTRY_TAG_NAME = "entry";
    private static final String MAP_KEY_TAG_NAME = "key";
    private static final String MAP_VALUE_TAG_NAME = "value";

    public void marshal(Property property, Object value, XMLStreamWriter writer, WriterContext context) throws XMLStreamException {
        Map<?, ?> mapValue = (Map) value;

        writer.writeStartElement(property.getName());

        for (Map.Entry entry : mapValue.entrySet()) {
            writer.writeStartElement(MAP_ENTRY_TAG_NAME);

            writeKey(writer, context, property, entry);
            writeValue(entry, property, writer, context);

            writer.writeEndElement();
        }
        writer.writeEndElement();

    }

    private void writeKey(XMLStreamWriter writer, WriterContext context, Property property, Map.Entry entry) throws XMLStreamException {
        // Write key
        writer.writeStartElement(MAP_KEY_TAG_NAME);
        context.writeObject(new Property(MAP_KEY_TAG_NAME, ReflectionUtils.getFirstGenericType(property.getType())), entry.getKey());
        writer.writeEndElement();
    }

    private void writeValue(Map.Entry entry, Property property, XMLStreamWriter writer, WriterContext context) throws XMLStreamException {
        Object entryValue = entry.getValue();
        Class<?> valueType = entryValue.getClass();
        //Class<?> declaredValueType = ReflectionUtils.getSecondGenericType(property.getType());

        // TODO: if valueType != declaredType prepend JAXB compatible type information

        // Write value
        writer.writeStartElement(MAP_VALUE_TAG_NAME);
        //writer.writeStartElement(ReflectionUtils.decapitalize(declaredValueType.getSimpleName()));
        Property valueProperty = new Property(ReflectionUtils.decapitalize(valueType.getSimpleName()), valueType);
        writeTypeInfoIfNeeded(entryValue, property, writer);
        
        context.writeObject(valueProperty, entry.getValue());
        
        //writer.writeEndElement();
        writer.writeEndElement();
    }

    public Object unmarshal(Property property, Node node, ReaderContext context) {
        NodeList entryNodes = node.getChildNodes();

        Map resultingMap = (Map) context.instantiate(Map.class);

        for (int i = 0; i < entryNodes.getLength(); i++) {
            Node currentNode = entryNodes.item(i);
            
            if (!"entry".equals(currentNode.getNodeName())) {
                continue;
            }
            
            Object key = null;
            Object value = null;

            for (int j = 0; j < currentNode.getChildNodes().getLength(); j++) {
                Node currentSubNode = currentNode.getChildNodes().item(j);

                if (currentSubNode.getNodeName().equals(MAP_KEY_TAG_NAME)) {
                    key = context.readObject(new Property(MAP_KEY_TAG_NAME,
                            ReflectionUtils.getFirstGenericType(property.getType())), currentSubNode);
                } else if (currentSubNode.getNodeName().equals(MAP_VALUE_TAG_NAME)) {
                    Node valueNode = currentSubNode;

                    value = context.readObject(new Property(valueNode.getNodeName(),
                            ReflectionUtils.getSecondGenericType(property.getType())), valueNode);
                }
            }

            resultingMap.put(key, value);
        }

        return resultingMap;
    }

    public boolean canHandle(Class<?> t) {
        return Map.class.isAssignableFrom(t);
    }

    protected void writeTypeInfoIfNeeded(Object entryValue, Property property, XMLStreamWriter writer) throws XMLStreamException {
        MarshallerUtils.writeTypeInfoIfNeeded(entryValue.getClass(), property, writer);
    }
}
