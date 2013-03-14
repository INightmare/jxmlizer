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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.inightmare.xmlizer.ConditionalHandler;

/**
 *
 * @author giedrius
 */
public class Handlers<ConditionType, HandlerType extends ConditionalHandler<ConditionType>> {
    private List<HandlerType> handlers = new ArrayList<HandlerType>();
    
    public void addHandler(HandlerType handler) {
        handlers.add(handler);
    }
    
    public HandlerType findHandler(ConditionType condition) {
        ListIterator<HandlerType> iter = handlers.listIterator(handlers.size());
        
        while (iter.hasPrevious()) {
            HandlerType handler = iter.previous();
            
            if (handler.canHandle(condition)) {
                return handler;
            }
        }
        
        return null;
    }
}
