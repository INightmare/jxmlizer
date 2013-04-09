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

import junit.framework.TestCase;

/**
 *
 * @author giedrius
 */
public class AssertionsTest extends TestCase {
    
    private static String MSG = "Msg";
    
    public void testAssertNotNull() {
        boolean exceptionThrown = false;
        
        try {
            Assertions.assertNotNull(null, MSG);
        } catch (IllegalArgumentException ex) {
            assertEquals(MSG, ex.getMessage());
            exceptionThrown = true;
        }
        assertTrue("exception should be thrown", exceptionThrown);
        
        exceptionThrown = false;
        
        try {
            Assertions.assertNotNull(this, MSG);
        } catch (Exception ex) {
            exceptionThrown = true;
        }
        
        assertFalse("exception should not be thrown", exceptionThrown);
    }
    
}
