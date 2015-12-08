package org.inightmare.xmlizer.misc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.inightmare.xmlizer.XSDPathRegistry;
import org.inightmare.xmlizer.misc.XmlConstants.SchemaLocation;

/**
 * Default implementation of {@link XSDPathRegistry}</br>
 * Uses LinkedHashMap to store type->path associations. </br>
 * 
 * <p><b>This implementation uses isAssignableFrom to find the path so make sure that you register less generic types before more generic ones.</b></p>
 * 
 * @author klebedev
 * @since 2015SP17
 */
public class DefaultXSDPathRegistry implements XSDPathRegistry {

    private Map<Class<?>, String> classToPathMap = new LinkedHashMap<Class<?>, String>();

    /**
     * @return a constant representing noNameSpaceSchemaLocation attribute
     */
    public SchemaLocation getLocation() {
        return SchemaLocation.NO_NAMESPACE_SCHEMA_LOCATION;
    }
    
    /**
     * Registers provided path as a path to XSD associated with provided type of exported object.
     * 
     * @param type exported object type
     * @param path path to XSD associated with this type
     */
    public void registerTypePath(Class<?> type, String path){
        classToPathMap.put(type, path);
    }
    
    /**
     * Get a path to XSD for provided object type
     * 
     * @param type type of object that you need to acquire a schema for
     * @return String containing a path to XSD associated with provided type or <b>null</b> if no path is registered for provided type.
     */
    public String getPath(Class<?> type) {
        for (Entry<Class<?>, String> entry: classToPathMap.entrySet()) {
            if (entry.getKey().isAssignableFrom(type)){
                return entry.getValue();
            }
        }
        return null;
    }

}
