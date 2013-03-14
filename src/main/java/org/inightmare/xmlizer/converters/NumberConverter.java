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

import org.inightmare.xmlizer.PrimitiveConverter;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author giedrius
 */
public class NumberConverter implements PrimitiveConverter {

    @Override
    public Object fromString(String value, Class type) {
        
        BigDecimal convertedValue = new BigDecimal(value);
        
        if (Integer.class.equals(type) || int.class.equals(type)) {
            return convertedValue.intValue();
        } else if (Long.class.equals(type) || long.class.equals(type)) {
            return convertedValue.longValue();
        } else if (Double.class.equals(type) || double.class.equals(type)) {
            return convertedValue.doubleValue();
        } else if (Float.class.equals(type) || float.class.equals(type)) {
            return convertedValue.floatValue();
        } else if (BigInteger.class.equals(type)) {
            return convertedValue.toBigInteger();
        } else if (BigDecimal.class.equals(type)) {
            return convertedValue;
        } else if (Short.class.equals(type) || short.class.equals(type)) {
            return convertedValue.shortValue();
        } else if (Byte.class.equals(type) || byte.class.equals(type)) {
            return convertedValue.byteValue();
        } else {
            throw new IllegalArgumentException("Unknown number type: " + type.getName());
        }
    }

    @Override
    public String toString(Object object) {
        return ((Number)object).toString();
    }

    @Override
    public boolean canHandle(Class type) {
        return Number.class.isAssignableFrom(type) 
                || int.class.isAssignableFrom(type)
                || long.class.isAssignableFrom(type)
                || double.class.isAssignableFrom(type)
                || float.class.isAssignableFrom(type)
                || byte.class.isAssignableFrom(type)
                || short.class.isAssignableFrom(type); // TODO fill in other primitive types
    }

}
