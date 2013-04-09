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

import java.math.BigDecimal;
import java.math.BigInteger;
import junit.framework.TestCase;

/**
 *
 * @author giedrius
 */
public class NumberConverterTest extends TestCase {
    
    private NumberConverter converter = new NumberConverter();
    
    private Integer ONE = 1;
    
    private String ONESTR = "1";
    
    private Double TWO = 2.0;
    
    private String TWOSTR = "2.0";
    
    private short THREE = 3;
    
    private String THREESTR = "3";
    
    public void testEncode() {
        String result = converter.toString(ONE);
        assertEquals(ONESTR, result);
        
        result = converter.toString(TWO);
        assertEquals(TWOSTR, result);
        
        result = converter.toString(THREE);
        assertEquals(THREESTR, result);
    }
    
    public void testDecode() {
        Integer result = (Integer) converter.fromString(ONESTR, Integer.class);
        assertEquals(result, ONE);
        
        Double dresult = (Double) converter.fromString(TWOSTR, Double.class);
        assertEquals(dresult, TWO);
        
        short tresult = (Short) converter.fromString(THREESTR, short.class);
        assertEquals(tresult, THREE);
        
        BigInteger biresult = (BigInteger) converter.fromString(ONESTR, BigInteger.class);
        assertEquals(biresult, BigInteger.ONE);
    }
    
    public void testCondition() {
        assertTrue(converter.canHandle(Integer.class));
        assertTrue(converter.canHandle(Long.class));
        assertTrue(converter.canHandle(Float.class));
        assertTrue(converter.canHandle(float.class));
        assertTrue(converter.canHandle(short.class));
        assertTrue(converter.canHandle(BigDecimal.class));
        assertFalse(converter.canHandle(Object.class));
    }
    
}
