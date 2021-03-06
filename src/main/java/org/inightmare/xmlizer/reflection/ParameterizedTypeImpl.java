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

package org.inightmare.xmlizer.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *
 * @author giedrius
 */
public class ParameterizedTypeImpl implements ParameterizedType {

    private Type rawType;
    private Type ownerType;
    private Type[] typeArgs;
    
    public ParameterizedTypeImpl(Type rawType, Type ownerType, Type[] typeArgs) {
        this.rawType = rawType;
        this.ownerType = ownerType;
        this.typeArgs = typeArgs;
    }
    
    public Type[] getActualTypeArguments() {
        return typeArgs;
    }

    public Type getRawType() {
        return rawType;
    }

    public Type getOwnerType() {
        return ownerType;
    }
    
}
