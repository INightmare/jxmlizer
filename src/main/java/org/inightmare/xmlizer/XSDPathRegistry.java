package org.inightmare.xmlizer;

/**
 * Contains information about XSD file paths
 * 
 * @author klebedev
 * @since 2015-12
 */
public interface XSDPathRegistry {
    
    /**
     * Used to get a path to XSD for provided object type
     * @param type type of object that you need to acquire a schema for
     * @return String containing a path to XSD associated with provided type or <b>null</b> if no path is registered for provided type.
     */
    String getPath(Class<?> type);
    
}
