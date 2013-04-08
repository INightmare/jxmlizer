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

import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.inightmare.xmlizer.misc.Aliasor;
import org.inightmare.xmlizer.misc.DefaultTypeNamingStrategy;
import org.inightmare.xmlizer.misc.Handlers;
import org.inightmare.xmlizer.misc.TypeRegistry;
import org.inightmare.xmlizer.reflection.Property;

/**
 *
 * @author giedrius
 */
public class WriterContext {
    
    Handlers<Class<?>, PrimitiveConverter> primitiveConverters = new Handlers<Class<?>, PrimitiveConverter>();
    Handlers<Class<?>, TypeHandler> handlers = new Handlers<Class<?>, TypeHandler>();
    Handlers<Class<?>, Accessor> accessors = new Handlers<Class<?>, Accessor>();
    XMLStreamWriter writer;
    Aliasor aliasor = new Aliasor();
    TypeNamingStrategy typeNamingStrategy = new DefaultTypeNamingStrategy();
    
    
    public void writeObject(Property property, Object object) throws XMLStreamException {
        if (object == null) {
            return;
        }
        
        PrimitiveConverter primitiveConverter = primitiveConverters.findHandler(object.getClass());
        
        if (primitiveConverter != null) {
            writer.writeCharacters(primitiveConverter.toString(object));
        } else {
            Accessor accessor = accessors.findHandler(object.getClass());
            List<Property> properties = accessor.listProperties(object.getClass());
            
            for (Property prop: properties) {
                TypeHandler handler = handlers.findHandler(prop.getSimpleType());
                handler.marshal(prop, accessor.getProperty(object, prop.getName()), writer, this);
            }
        }
    }
    
    public String getAlias(Class<?> type, String realName) {
        return aliasor.getAlias(type, realName);
    }
    
    public String getRealName(Class<?> type, String alias) {
        return aliasor.getRealName(type, alias);
    }
    
}
