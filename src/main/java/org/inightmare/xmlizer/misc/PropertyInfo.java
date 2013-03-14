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

import org.inightmare.xmlizer.reflection.Property;

/**
 *
 * @author giedrius
 */
public class PropertyInfo {
    private Property property;
    
    private boolean explicit;

    /**
     * @return the explicit
     */
    public boolean isExplicit() {
        return explicit;
    }

    /**
     * @param explicit the explicit to set
     */
    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    /**
     * @return the property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(Property property) {
        this.property = property;
    }

}
