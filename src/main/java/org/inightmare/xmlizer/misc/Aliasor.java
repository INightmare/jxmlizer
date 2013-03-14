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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author giedrius
 */
public class Aliasor {
    
    private Map<Class, List<Tuple<String, String>>> aliases = new HashMap<Class, List<Tuple<String, String>>>();
    
    private Map<String, String> globalAliases = new HashMap<String, String>();
    
    public void addAlias(Class<?> type, String name, String alias) {
        List<Tuple<String, String>> typeAliases = aliases.get(type);
        
        if (typeAliases == null) {
            typeAliases = new ArrayList<Tuple<String, String>>();
            aliases.put(type, typeAliases);
        }
        
        typeAliases.add(new Tuple<String, String>(name, alias));
    }
    
    public void addAlias(String name, String alias) {
        globalAliases.put(name, alias);
    }
    
    public String getRealName(Class type, String alias) {
        List<Tuple<String, String>> typeAliases = aliases.get(type);
        
        if (typeAliases != null) {
            for (Tuple<String, String> ali: typeAliases) {
                if (ali.getSecond().equals(alias)) {
                    return ali.getFirst();
                }
            }
        }
        
        for (Map.Entry<String, String> ali: globalAliases.entrySet()) {
            if (ali.getValue().equals(alias)) {
                return ali.getKey();
            }
        }
        return alias;
    }
    
    public String getAlias(Class type, String name) {
        List<Tuple<String, String>> typeAliases = aliases.get(type);
        
        if (typeAliases != null) {
            for (Tuple<String, String> ali: typeAliases) {
                if (ali.getFirst().equals(name)) {
                    return ali.getSecond();
                }
            }
        }
        String alias = globalAliases.get(name);
        return alias != null ? alias : name;
    }
}
