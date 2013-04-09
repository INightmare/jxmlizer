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
package org.inightmare.xmlizer.converters;

import junit.framework.TestCase;

/**
 *
 * @author giedrius
 */
public class StringConverterTest extends TestCase {
    
    private static final String STR = "testString";
    
    private StringConverter converter = new StringConverter();
    
    public void testEncode() {
        String result = converter.toString(STR);
        assertEquals(STR, result);
    }
    
    public void testDecode() {
        String result = (String) converter.fromString(STR, String.class);
        assertEquals(STR, result);
    }
    
    public void testCondition() {
        assertTrue(converter.canHandle(String.class));
        assertFalse(converter.canHandle(Object.class));
    }
    
}
