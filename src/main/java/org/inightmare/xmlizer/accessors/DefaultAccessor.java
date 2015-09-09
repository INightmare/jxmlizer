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

package org.inightmare.xmlizer.accessors;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.inightmare.xmlizer.Accessor;
import org.inightmare.xmlizer.reflection.CollectionUtils;
import org.inightmare.xmlizer.reflection.Property;
import org.inightmare.xmlizer.reflection.ReflectionUtils;

/**
 * Default accessor used for serializing and deserializing objects.
 * 
 * Accessor lists and sets properties based on available getters and setters. This
 * default implementation sets properties based on Java Beans convention with one
 * additional customization. If property being set is a collection, setting a value
 * to the property adds it to the collection.
 * 
 * @author giedrius
 */
public class DefaultAccessor implements Accessor {

    /**
     * Called when Xmlizer wants to set a property. Supports "setting" (adding) values
     * to Collection and Map instances.
     * 
     * 
     * @see Accessor#setProperty(java.lang.Object, java.lang.String, java.lang.Object) 
     * @param holder object containing the property
     * @param propertyName property name
     * @param propertyValue value stored in the property
     */
    public void setProperty(Object holder, String propertyName, Object propertyValue) {
        Class<?> propertyType = determinePropertyType(holder, propertyName);
        
        if (Collection.class.isAssignableFrom(propertyType)) {
            Method getter = ReflectionUtils.retrieveGetter(holder.getClass(), propertyName);
            Collection collection = (Collection) ReflectionUtils.getProperty(holder, propertyName);
            
            if (collection == null) {
                collection = CollectionUtils.defaultCollectionImplementationFor(getter.getReturnType()); // TODO: improve implicit collection support
                //ReflectionUtils.setProperty(holder, propertyName, collection);
                setPropertyDirect(holder, propertyName, collection);
            }
            
            collection.add(propertyValue);
        } else if (Map.class.isAssignableFrom(propertyType)) {
            Map map = (Map) getProperty(holder, propertyName);
            
            if (map == null) {
                map = (Map) propertyValue;
                setPropertyDirect(holder, propertyName, map);
            }
            
            map.putAll((Map)propertyValue);
        } else {
            setPropertyDirect(holder, propertyName, propertyValue, propertyType);
        }
    }

    /**
     * Can be overridden to change property setting logic. Unlike {@link DefaultAccessor#setProperty(java.lang.Object, java.lang.String, java.lang.Object)}
     * this method only sets values using setters.
     * 
     * @param holder
     * @param propertyName
     * @param propertyValue 
     */
    protected void setPropertyDirect(Object holder, String propertyName, Object propertyValue) {
        ReflectionUtils.setProperty(holder, propertyName, propertyValue);
    }
    
    /**
     * Can be overridden to change property setting logic. Unlike {@link DefaultAccessor#setProperty(java.lang.Object, java.lang.String, java.lang.Object)}
     * this method only sets values using setters.
     * 
     * @param holder
     * @param propertyName
     * @param propertyValue 
     * @param propertyType
     */
    protected void setPropertyDirect(Object holder, String propertyName, Object propertyValue, Class propertyType) {
        ReflectionUtils.setProperty(holder, propertyName, propertyValue, propertyType);
    }
    
    /**
     * Allows overriding property type resolution. 
     * 
     * @param holder
     * @param propertyName
     * @return 
     */
    protected Class determinePropertyType(Object holder, String propertyName) {
        return ReflectionUtils.determinePropertyType(holder.getClass(), propertyName);
    }

    /**
     * List all the readable properties in the object
     * 
     * @see Accessor#listProperties(java.lang.Class) 
     * @param type
     * @return 
     */
    public List<Property> listProperties(Class<?> type) {
        return ReflectionUtils.retrieveReadableProperties(type);
    }

    /**
     * Retrieve property value
     * 
     * @see Accessor#getProperty(java.lang.Object, java.lang.String) 
     * @param holder
     * @param propertyName
     * @return 
     */
    public Object getProperty(Object holder, String propertyName) {
        return ReflectionUtils.getProperty(holder, propertyName);
    }
    
    /**
     * Can accessor handle a given type? DefaultAccess can handly any type.
     * 
     * @see Accessor#listProperties(java.lang.Class) 
     * @param type
     * @return true
     */
    public boolean canHandle(Class<?> type) {
        return true;
    }

}
