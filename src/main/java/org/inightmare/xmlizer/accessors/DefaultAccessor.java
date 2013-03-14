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
 * Accessor lists and sets properties based on available getters and setters.
 * 
 * @author giedrius
 */
public class DefaultAccessor implements Accessor {

    public void setProperty(Object holder, String propertyName, Object propertyValue) {
        Class<?> propertyType = ReflectionUtils.determinePropertyType(holder.getClass(), propertyName);
        
        if (Collection.class.isAssignableFrom(propertyType)) {
            Method getter = ReflectionUtils.retrieveGetter(holder.getClass(), propertyName);
            Collection collection = (Collection) ReflectionUtils.getProperty(holder, propertyName);
            
            if (collection == null) {
                collection = CollectionUtils.defaultCollectionImplementationFor(getter.getReturnType());
                ReflectionUtils.setProperty(holder, propertyName, collection);
            }
            
            collection.add(propertyValue);
        } else {
            ReflectionUtils.setProperty(holder, propertyName, propertyValue);
        }
    }

    public List<Property> listProperties(Class<?> type) {
        return ReflectionUtils.retrieveReadableProperties(type);
    }

    public Object getProperty(Object holder, String propertyName) {
        return ReflectionUtils.getProperty(holder, propertyName);
    }
    
    public boolean canHandle(Class<?> type) {
        return true;
    }

}
