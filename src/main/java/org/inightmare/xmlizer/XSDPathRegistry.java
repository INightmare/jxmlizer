package org.inightmare.xmlizer;

import org.inightmare.xmlizer.misc.XmlConstants.SchemaLocation;

/**
 * Contains information about XSD file paths
 * 
 * @author klebedev
 * @since 2015-12
 */
public interface XSDPathRegistry {
    
    String getPath(Class<?> type);
    
    SchemaLocation getLocation();
    
}
