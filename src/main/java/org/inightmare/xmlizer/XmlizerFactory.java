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

import org.inightmare.xmlizer.accessors.DefaultAccessor;
import org.inightmare.xmlizer.converters.BooleanConverter;
import org.inightmare.xmlizer.converters.DateConverter;
import org.inightmare.xmlizer.converters.EnumConverter;
import org.inightmare.xmlizer.converters.NumberConverter;
import org.inightmare.xmlizer.converters.StringConverter;
import org.inightmare.xmlizer.factories.CollectionFactory;
import org.inightmare.xmlizer.factories.DefaultBeanFactory;
import org.inightmare.xmlizer.factories.MapFactory;
import org.inightmare.xmlizer.handlers.CollectionHandler;
import org.inightmare.xmlizer.handlers.DefaultHandler;
import org.inightmare.xmlizer.handlers.MapHandler;


/**
 * Factory for Marshallers and Unmarshallers.
 * 
 * @author giedrius
 */
public class XmlizerFactory {
    
    /**
     * Instantiates {@link Unmarshaller} without any
     * converters and factories allowing full customization
     * by the user.
     * @return new bare Unmarshaller instance
     */
    public static Unmarshaller createCleanUnmarshaller() {
        return new Unmarshaller();
    }
    
    /**
     * Instantiates {@link Marshaller} without any
     * converters and factories allowing full customization
     * by the user.
     * @return new bare Marshaller instance
     */
    public static Marshaller createCleanMarshaller() {
        return new Marshaller();
    }
    
    /**
     * Creates {@link Unmarshaller} with the default configuration
     * 
     * @return {@link Unmarshaller} with default configuration for serializing Java Beans
     */
    public static Unmarshaller createDefaultUnmarshaller() {
        Unmarshaller unmarshaller = new Unmarshaller();
        unmarshaller.registerPrimitiveConverter(new StringConverter());
        unmarshaller.registerPrimitiveConverter(new NumberConverter());
        unmarshaller.registerPrimitiveConverter(new BooleanConverter());
        unmarshaller.registerPrimitiveConverter(new DateConverter());
        unmarshaller.registerPrimitiveConverter(new EnumConverter());
        unmarshaller.registerBeanFactory(new DefaultBeanFactory());
        unmarshaller.registerBeanFactory(new CollectionFactory());
        unmarshaller.registerBeanFactory(new MapFactory());
        unmarshaller.addTypeHandler(new DefaultHandler());
        unmarshaller.addTypeHandler(new CollectionHandler());
        unmarshaller.addTypeHandler(new MapHandler());
        unmarshaller.addAccessor(new DefaultAccessor());
        return unmarshaller;
    }
    
    /**
     * Creates {@link Marshaller} with the default configuration
     * @return {@link Marshaller} with default configuration for serializing Java Beans
     */
    public static Marshaller createDefaultMarshaller() {
        Marshaller marshaller = new Marshaller();
        marshaller.registerPrimitiveConverter(new StringConverter());
        marshaller.registerPrimitiveConverter(new NumberConverter());
        marshaller.registerPrimitiveConverter(new BooleanConverter());
        marshaller.registerPrimitiveConverter(new DateConverter());
        marshaller.registerPrimitiveConverter(new EnumConverter());
        marshaller.addAccessor(new DefaultAccessor());
        marshaller.addTypeHandler(new DefaultHandler());
        marshaller.addTypeHandler(new CollectionHandler());
        marshaller.addTypeHandler(new MapHandler());
        return marshaller;
    }
    
}
