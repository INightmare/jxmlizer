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

import java.util.HashMap;
import java.util.Map;
import org.inightmare.xmlizer.reflection.Property;

/**
 *
 * @author giedrius
 */
public class DefaultPropertyInfoHolder implements PropertyInfoHolder {
    private Map<Property, PropertyInfo> propertyInfos = new HashMap<Property, PropertyInfo>();
    
    public void setExplicit(Property property, boolean explicit) {
        PropertyInfo propertyInfo = get(property);
        propertyInfo.setExplicit(explicit);
    }
    
    public PropertyInfo getPropertyInfo(Property property) {
        return get(property);
    }

    private PropertyInfo get(Property property) {
        PropertyInfo propertyInfo = propertyInfos.get(property);
        
        if (propertyInfo == null) {
            propertyInfo = new PropertyInfo();
            propertyInfos.put(property, propertyInfo);
        }
        
        return propertyInfo;
    }
    
}
