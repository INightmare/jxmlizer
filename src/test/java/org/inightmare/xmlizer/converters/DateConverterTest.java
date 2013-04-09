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

import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author giedrius
 */
public class DateConverterTest extends TestCase {
    
    private DateConverter converter = new DateConverter();
    
    public void testEncode() {
        Date date = new Date(123456);
        String result = converter.toString(date);
        assertEquals("123456", result);
    }
    
    public void testDecode() {
        Date date = new Date(123456);
        Date result = (Date) converter.fromString("123456", Date.class);
        assertEquals(date, result);
    }
    
    public void testCondition() {
        assertTrue(converter.canHandle(Date.class));
        assertFalse(converter.canHandle(Object.class));
    }
}
