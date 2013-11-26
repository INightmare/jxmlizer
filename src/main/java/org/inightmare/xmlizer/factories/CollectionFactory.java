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

package org.inightmare.xmlizer.factories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.inightmare.xmlizer.BeanFactory;

/**
 * Instantiates Lists and Sets
 * 
 * @author giedrius
 */
public class CollectionFactory implements BeanFactory {

    /**
     * Handles ArrayList, List, HashSet, Set, LinkedList.
     * 
     * @param type
     * @return true if supplied type is java.util.List or java.util.Set
     */
    public boolean canHandle(Class<?> type) {
        return ArrayList.class.equals(type) || List.class.equals(type)
                || HashSet.class.equals(type) || Set.class.equals(type)
                || LinkedList.class.equals(type);
                
    }

    /**
     * 
     * @param type
     * @return new java.util.ArrayList or java.util.HashSet
     */
    public Object instantiate(Class type) {
        if (List.class.equals(type) || ArrayList.class.equals(type)) {
            return new ArrayList();
        } else if (Set.class.equals(type) || HashSet.class.equals(type)) {
            return new HashSet();
        } else if (LinkedList.class.equals(type)) {
            return new LinkedList();
        }
        throw new IllegalArgumentException("Can not instantiate: " + type.getName());
    }
    
}
