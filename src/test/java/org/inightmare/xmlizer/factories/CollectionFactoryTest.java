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
import java.util.Map;
import java.util.Set;
import junit.framework.TestCase;

/**
 *
 * @author giedrius
 */
public class CollectionFactoryTest extends TestCase {
    
    private CollectionFactory factory = new CollectionFactory();
    
    public void testInstantiation() {
        Object result = factory.instantiate(List.class);
        assertTrue(result instanceof ArrayList);
        
        result = factory.instantiate(LinkedList.class);
        assertTrue(result instanceof LinkedList);
        
        result = factory.instantiate(HashSet.class);
        assertTrue(result instanceof HashSet);
        
        result = factory.instantiate(Set.class);
        assertTrue(result instanceof HashSet);
    }
    
    public void testCanHandle() {
        assertTrue(factory.canHandle(List.class));
        assertTrue(factory.canHandle(Set.class));
        assertTrue(factory.canHandle(ArrayList.class));
        assertTrue(factory.canHandle(ArrayList.class));
        assertFalse(factory.canHandle(Map.class));
        assertFalse(factory.canHandle(Object.class));
    }
    
}
