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

/**
 *
 * @author giedrius
 */
public class Tuple <T1, T2> {
    private T1 first;
    
    private T2 second;
    
    public Tuple(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
    
    public T1 getFirst() {
        return first;
    }
    
    public T2 getSecond() {
        return second;
    }

}
