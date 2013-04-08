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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author giedrius
 */
public class TypeRegistry {
    private Set<Class<?>> types = new HashSet<Class<?>>();
    
    public void registerType(Class<?> type) {
        types.add(type);
    }
    
    public Set<Class<?>> getTypes() {
        return Collections.unmodifiableSet(types);
    }
}
