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
public class BooleanConverterTest extends TestCase {
    
    private static final String TRUESTR = "true";
    
    private static final String FALSESTR = "false";
    
    private BooleanConverter converter = new BooleanConverter();
    
    public void testEncode() {
        String result = converter.toString(Boolean.TRUE);
        assertEquals(TRUESTR, result);
        
        result = converter.toString(Boolean.FALSE);
        assertEquals(FALSESTR, result);
    }
    
    public void testDecode() {
        Boolean result = (Boolean) converter.fromString(TRUESTR, Boolean.class);
        assertEquals(result, Boolean.TRUE);
        
        result = (Boolean) converter.fromString(FALSESTR, Boolean.class);
        assertEquals(result, Boolean.FALSE);
        
        result = (Boolean) converter.fromString("tRuE", Boolean.class);
        assertEquals(result, Boolean.TRUE);
    }
    
    public void testCondition() {
        assertTrue(converter.canHandle(Boolean.class));
        assertFalse(converter.canHandle(Object.class));
    }
    
}
