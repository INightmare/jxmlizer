
package org.inightmare.xmlizer;

import org.inightmare.xmlizer.misc.TypeRegistry;

/**
 *
 * @author giedrius
 */
public interface TypeNamingStrategy {
    
    String getTypeName(Class<?> type);
    
    Class<?> getTypeFromName(TypeRegistry typeRegistry, String name);
    
}
