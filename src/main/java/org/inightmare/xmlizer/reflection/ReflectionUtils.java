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

package org.inightmare.xmlizer.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.inightmare.xmlizer.XmlizerException;

/**
 *
 * @author giedrius
 */
public class ReflectionUtils {

    public static Class determinePropertyType(Class holder, String propertyName) {
        Method accessor = retrieveGetter(holder, propertyName);
        if (accessor != null) {
            return accessor.getReturnType();
        } else {
            return null;
        }
    }
    
    public static Type determineGenericPropertyType(Class holder, String propertyName) {
        Method accessor = retrieveGetter(holder, propertyName);
        if (accessor != null) {
            return accessor.getGenericReturnType();
        } else {
            return null;
        }
    }
    
    public static Method retrieveGetter(Class holder, String propertyName) {
        Method readMethod = null;
        String capitalizedName = capitalize(propertyName);
        try {
            readMethod = holder.getMethod("get" + capitalizedName);
        } catch (Exception ex) {}
        
        if (readMethod == null) {
            try {
                readMethod = holder.getMethod("is" + capitalizedName);
            } catch (Exception ex) {}
        }
                
        return readMethod;
    }
    
    public static Method retrieveSetter(Class holder, String propertyName, Class propertyType) {
        Method readMethod = null;
        String capitalizedName = capitalize(propertyName);
        try {
            readMethod = holder.getMethod("set" + capitalizedName, propertyType);
        } catch (Exception ex) {}
        
                
        return readMethod;
    }
    
    public static List<Property> retrieveReadableProperties(Class<?> type) {
        ArrayList<Property> properties = new ArrayList<Property>();
        Method[] methods = type.getMethods();
        
        for (Method method: methods) {
            if (isGetter(method)) {
                if (method.getName().equals("getClass")) {
                    continue;
                }
                properties.add(new Property(extractPropertyName(method.getName()), method.getGenericReturnType()));
            }
        }
        
        return properties;
    }
    
    public static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
    
    public static String decapitalize(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    private static boolean isGetter(Method method) {
        return (method.getName().startsWith("get") || method.getName().startsWith("is"))
                   && method.getParameterTypes().length == 0;
    }
    
    private static String extractPropertyName(String getterName) {
        if (getterName.startsWith("get")) {
            return decapitalize(getterName.substring(3));
        } else if (getterName.startsWith("is")) {
            return decapitalize(getterName.substring(2));
        }
        throw new IllegalArgumentException("Invalid name passed as a getter: " + getterName);
    }
    
    public static Class<?> getFirstGenericType(Type sourceType) {
        ParameterizedType type = (ParameterizedType) sourceType;
        if (type.getActualTypeArguments().length == 0) {
            throw new IllegalArgumentException("No generic type arguments found on " + type + " return type");
        }
        return (Class) type.getActualTypeArguments()[0];
    }
    
    public static Class<?> getSecondGenericType(Type sourceType) {
        ParameterizedType type = (ParameterizedType) sourceType;
        if (type.getActualTypeArguments().length == 0) {
            throw new IllegalArgumentException("No generic type arguments found on " + type + " return type");
        }
        return (Class) type.getActualTypeArguments()[1];
    }
    
    public static Class<?> getFirstGenericFromReturnType(Method method) {
        return getFirstGenericType(method.getGenericReturnType());
    }
    
    public static void setProperty(Object target, String propertyName, Object value) {
        Method getter = retrieveGetter(target.getClass(), propertyName);
        Method setter = retrieveSetter(target.getClass(), propertyName, getter.getReturnType());
        
        try {
            setter.invoke(target, value); 
        } catch (IllegalAccessException ex) {
            throw new XmlizerException("Unable to set property " + propertyName + " on " + target.getClass(), ex);
        } catch (IllegalArgumentException ex) {
            throw new XmlizerException("Unable to set property " + propertyName + " on " + target.getClass(), ex);
        } catch (InvocationTargetException ex) {
            throw new XmlizerException("Unable to set property " + propertyName + " on " + target.getClass(), ex);
        }
    }
    
    public static Object getProperty(Object target, String propertyName) {
        Method getter = retrieveGetter(target.getClass(), propertyName);
        try {
            return getter.invoke(target);
        } catch (IllegalAccessException ex) {
            throw new XmlizerException("Unable to get property " + propertyName + " from " + target.getClass(), ex);
        } catch (IllegalArgumentException ex) {
            throw new XmlizerException("Unable to get property " + propertyName + " from " + target.getClass(), ex);
        } catch (InvocationTargetException ex) {
            throw new XmlizerException("Unable to get property " + propertyName + " from " + target.getClass(), ex);
        }
    
    }
}
