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
import java.util.Date;

/**
 *
 * @author giedrius
 */
public class DateConverter implements PrimitiveConverter {

    @Override
    public Object fromString(String value, Class type) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return new Date(Long.parseLong(value));
    }

    @Override
    public String toString(Object object) {
        return Long.toString(((Date)object).getTime());
    }

    @Override
    public boolean canHandle(Class type) {
        return Date.class.isAssignableFrom(type);
    }

}
