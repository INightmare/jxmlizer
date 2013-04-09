
package org.inightmare.xmlizer.misc;

import org.inightmare.xmlizer.TypeNamingStrategy;
import org.inightmare.xmlizer.reflection.ReflectionUtils;

/**
 *
 * @author giedrius
 */
public class DefaultTypeNamingStrategy implements TypeNamingStrategy {

    public String getTypeName(Class<?> type) {
        return ReflectionUtils.decapitalize(type.getSimpleName());
    }

    public Class<?> getTypeFromName(TypeRegistry typeRegistry, String name) {
        return findClassBySimpleName(typeRegistry, name);
    }
    
    private Class<?> findClassBySimpleName(TypeRegistry typeRegistry, String name) {
        for (Class<?> type: typeRegistry.getTypes()) {
            if (type.getSimpleName().equalsIgnoreCase(name)) {
                return type;
            }
        }

        return null;
    }
}
