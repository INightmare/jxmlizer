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

package org.inightmare.xmlizer;

import java.io.IOException;
import org.inightmare.xmlizer.dataobjects.MapHolder;

/**
 *
 * @author giedrius
 */
public class MapTest extends AbstractDefaultXmlizerTestCase {
    
    public void testMapMarshallingAndUnmarshalling() throws IOException {
        MapHolder mapHolder = buildData();
        
        MapHolder result = (MapHolder) marshalUnmarshall(mapHolder);
        
        assertEquals("Simple property should match", mapHolder.getSimpleProperty(), result.getSimpleProperty());
        assertEquals("Map sizes should match", mapHolder.getAMap().size(), result.getAMap().size());
        assertEquals("Map values should match", mapHolder.getAMap(), result.getAMap());
    }

    private MapHolder buildData() {
        MapHolder mapHolder = new MapHolder();
        mapHolder.setSimpleProperty("abc");
        mapHolder.getAMap().put("a", "b");
        mapHolder.getAMap().put("c", "d");
        return mapHolder;
    }

    @Override
    protected void setupUnmarshaller(Unmarshaller unmarshaller) {
        unmarshaller.registerType(MapHolder.class);
    }
    
    
}
