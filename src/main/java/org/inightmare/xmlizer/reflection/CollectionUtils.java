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

package org.inightmare.xmlizer.reflection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.inightmare.xmlizer.XmlizerException;

/**
 *
 * @author giedrius
 */
public class CollectionUtils {
    
    public static Collection defaultCollectionImplementationFor(Class<?> collectionType) {
        
        if (!collectionType.isInterface()) { 
            try {
                return (Collection) collectionType.newInstance();
            } catch (InstantiationException ex) {
                throw new XmlizerException("Could not instantiate collection", ex);
            } catch (IllegalAccessException ex) {
                throw new XmlizerException("Could not instantiate collection", ex);
            }
        }
        
        if (collectionType.equals(List.class)) {
            return new ArrayList();
        } else if (collectionType.equals(Set.class)) {
            return new HashSet();
        }
        
        throw new IllegalArgumentException("Unable to determine collection type for " + collectionType.getName());
    }
    
}
