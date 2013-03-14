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
import org.inightmare.xmlizer.misc.Aliasor;
import org.inightmare.xmlizer.misc.Handlers;
import org.inightmare.xmlizer.misc.TypeRegistry;
import org.inightmare.xmlizer.reflection.Property;
import org.w3c.dom.Node;

/**
 *
 * @author giedrius
 */
public class ReaderContext {
    
    Aliasor aliasor = new Aliasor();
    Handlers<Class<?>, PrimitiveConverter> primitiveConverters = new Handlers<Class<?>, PrimitiveConverter>();
    Handlers<Class<?>, TypeHandler> handlers = new Handlers<Class<?>, TypeHandler>();
    Handlers<Class<?>, BeanFactory> factories = new Handlers<Class<?>, BeanFactory>();
    Handlers<Class<?>, Accessor> accessors = new Handlers<Class<?>, Accessor>();
    TypeRegistry typeRegistry = new TypeRegistry();
    
    
    public Object readObject(Property property, Node node) {
        PrimitiveConverter primitiveConverter = primitiveConverters.findHandler(property.getSimpleType());
        
        if (primitiveConverter != null) {
            return primitiveConverter.fromString(node.getTextContent(), property.getSimpleType());
        } else {
            TypeHandler handler = handlers.findHandler(property.getSimpleType());
            return handler.unmarshal(property, node, this);
        }        
        
    }
    
    public List<Property> listProperties(Class<?> type) {
        Accessor accessor = accessors.findHandler(type);
        return accessor.listProperties(type);
    }
    
    public void setProperty(Property property, Object holder, Object value) {
        Accessor accessor = accessors.findHandler(property.getSimpleType());
        accessor.setProperty(holder, property.getName(), value);
    }
    
    public String getRealName(Class type, String alias) {
        return aliasor.getRealName(type, alias);
    }
    
    public String getAlias(Class type, String realName) {
        return aliasor.getAlias(type, realName);
    }
    
    public Object instantiate(Class type) {
        return factories.findHandler(type).instantiate(type);
    }
    
    public Class<?> determineType(String typeName) {
        return typeRegistry.determineType(typeName);
    }
}
