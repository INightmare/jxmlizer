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
public class EnumConverterTest extends TestCase {
    
    private static final String VALUE1STR = "VALUE1";
    private static final String VALUE2STR = "VALUE2";
    
    public enum Test {
        VALUE1,
        VALUE2
    }
    
    private EnumConverter converter = new EnumConverter();
    
    public void testEncode() {
        String value1 = converter.toString(Test.VALUE1);
        String value2 = converter.toString(Test.VALUE2);
        
        assertEquals(VALUE1STR, value1);
        assertEquals(VALUE2STR, value2);
    }
    
    public void testDecode() {
        Test test1 = (Test) converter.fromString(VALUE1STR, Test.class);
        Test test2 = (Test) converter.fromString(VALUE2STR, Test.class);
        
        assertEquals(Test.VALUE1, test1);
        assertEquals(Test.VALUE2, test2);
    }
    
    public void testHandlingCondition() {
        assertTrue(converter.canHandle(Test.class));
        assertFalse(converter.canHandle(Object.class));
    }
    
}
