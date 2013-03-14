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

import java.util.Collection;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.inightmare.xmlizer.ReaderContext;
import org.inightmare.xmlizer.TypeHandler;
import org.inightmare.xmlizer.WriterContext;
import org.inightmare.xmlizer.misc.MarshallerUtils;
import org.inightmare.xmlizer.reflection.Property;
import org.inightmare.xmlizer.reflection.ReflectionUtils;
import org.w3c.dom.Node;

/**
 * Marshals and unmarshals collections.
 * 
 * Collections are marshaled in JAXB compatible way (as implicit collections in
 * XStream terms).
 * 
 * That is 
 * {@code 
 * class Test { 
 *  private Collection<?> items 
 *  public Collection<?> getItems() { return items; }
 * } 
 * }
 * 
 * would be serialized to
 * {@code 
 * <test>
 *   <items>...</items>
 *   <items>...</items>
 * </test>
 * }
 * 
 * 
 * @author giedrius
 */
public class CollectionHandler implements TypeHandler {

    public void marshal(Property property, Object value, XMLStreamWriter writer, WriterContext context) throws XMLStreamException {
        for (Object val: (Collection)value) {
            Property elementProperty = new Property(property.getName(), ReflectionUtils.getFirstGenericType(property.getType()));
            
            writer.writeStartElement(property.getName());
            MarshallerUtils.writeTypeInfoIfNeeded(val, elementProperty, writer);
            context.writeObject(elementProperty, val);
            writer.writeEndElement();
        }
    }

    public boolean canHandle(Class<?> t) {
        return Collection.class.isAssignableFrom(t);
    }

    public Object unmarshal(Property property, Node node, ReaderContext context) {
        Property prop = new Property(property.getName(), ReflectionUtils.getFirstGenericType(property.getType()));
        return context.readObject(prop, node);
    }
    
}
