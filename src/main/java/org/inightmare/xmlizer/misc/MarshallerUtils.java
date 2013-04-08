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

package org.inightmare.xmlizer.misc;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.inightmare.xmlizer.reflection.Property;
import org.inightmare.xmlizer.reflection.ReflectionUtils;

/**
 *
 * @author giedrius
 */
public class MarshallerUtils {

    public static void writeTypeInfoIfNeeded(Class valueType, Property property, XMLStreamWriter writer) throws XMLStreamException {
        if (shouldWriteTypeInfo(property, valueType)) {
            writer.writeAttribute(XmlConstants.XML_SCHEMA_INSTANCE_PREFIX, XmlConstants.XML_SCHEMA_INSTANCE_NAMESPACE, XmlConstants.XML_TYPE,
                    ReflectionUtils.decapitalize(valueType.getSimpleName()));
        }
    }

    private static boolean shouldWriteTypeInfo(Property property, Class valueType) {
        return !property.getSimpleType().isPrimitive() // never for primitive types
                && !valueType.equals(property.getSimpleType()) // only when actual type is different from declared type
                && !valueType.getSimpleName().isEmpty(); // Only when simple name is available (otherwise it's likely an enum (inner class?)
    }
}
