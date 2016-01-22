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
import org.inightmare.xmlizer.reflection.Property;

/**
 *
 * @author giedrius
 */
public interface Accessor extends ConditionalHandler<Class<?>> {
    
    /**
     * Called to get a property value from an object.
     * 
     * @param holder object holding a property
     * @param propertyName property name
     * @return property value
     */
    Object getProperty(Object holder, String propertyName);
    
    /**
     * Called to set a property value to an object.
     * 
     * @param holder property holder
     * @param propertyName name of the property (as in JavaBeans)
     * @param propertyValue value to set
     */
    void setProperty(Object holder, String propertyName, Object propertyValue);
    
    /**
     * List properties for a given type. Properties returned must be readable
     * by @{link Accessor#getProperty(java.lang.Object, java.lang.String)}
     * 
     * @param type type to list properties for
     * @return property list
     */
    List<Property> listProperties(Class<?> type);
    
}
