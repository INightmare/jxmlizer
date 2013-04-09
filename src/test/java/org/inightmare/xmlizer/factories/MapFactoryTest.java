
package org.inightmare.xmlizer.factories;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author giedrius
 */
public class MapFactoryTest extends TestCase {
    
    private MapFactory factory = new MapFactory();
    
    public void testInstantiate() {
        Object result = factory.instantiate(Map.class);
        assertTrue(result instanceof HashMap);
    }
    
    public void testCanHandle() {
        assertTrue(factory.canHandle(Map.class));
        assertTrue(factory.canHandle(HashMap.class));
        assertFalse(factory.canHandle(Object.class));
    }
    
}
