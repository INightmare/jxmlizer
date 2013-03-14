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

package org.inightmare.xmlizer;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.inightmare.xmlizer.reflection.Property;
import org.w3c.dom.Node;

/**
 *
 * @author giedrius
 */
public interface TypeHandler extends ConditionalHandler<Class<?>> {
    
    void marshal(Property property, Object value, XMLStreamWriter writer, WriterContext context) throws XMLStreamException;
    
    Object unmarshal(Property property, Node node, ReaderContext context);
}
