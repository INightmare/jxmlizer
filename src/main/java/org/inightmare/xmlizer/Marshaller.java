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

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.inightmare.xmlizer.reflection.Property;
import org.inightmare.xmlizer.reflection.ReflectionUtils;

/**
 *
 * @author giedrius
 */
public class Marshaller {
    
    private WriterContext writerContext = new WriterContext();
    
    
    Marshaller() {
    }
    
    /**
     * Serialize object to XML and output it to given OutputStream
     * @param object
     * @param outputStream 
     */
    public void marshal(Object object, OutputStream outputStream) {
        try {
            XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
            outputFactory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(outputStream);
            writerContext.writer = writer;
            
            writer.writeStartDocument();
            
            marshalObject(writer, object);
            
            writer.writeEndDocument();
        } catch (XMLStreamException ex) {
            throw new XmlizerException("Error occured while writting", ex);
        } catch (IllegalAccessException ex) {
            throw new XmlizerException("Error while trying to read a property", ex);
        } catch (IllegalArgumentException ex) {
            throw new XmlizerException("Error while trying to read a property", ex);
        } catch (InvocationTargetException ex) {
            throw new XmlizerException("Error while trying to read a property", ex);
        } 
    }
    
    public void addAlias(Class<?> type, String attributeName, String alias) {
        writerContext.aliasor.addAlias(type, attributeName, alias);
    }
    
    public void addAlias(String attributeName, String alias) {
        writerContext.aliasor.addAlias(attributeName, alias);
    }
    
    public void registerPrimitiveConverter(PrimitiveConverter primitiveConverter) {
        writerContext.primitiveConverters.addHandler(primitiveConverter);
    }
    
    public void addAccessor(Accessor accessor) {
        writerContext.accessors.addHandler(accessor);
    }
    
    public void addTypeHandler(TypeHandler typeHandler) {
        writerContext.handlers.addHandler(typeHandler);
    }

    public void setNamingStrategy(TypeNamingStrategy namingStrategy) {
        writerContext.typeNamingStrategy = namingStrategy;
    }
    
    private void marshalObject(XMLStreamWriter writer, Object object) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, XMLStreamException {
        String name = writerContext.typeNamingStrategy.getTypeName(object.getClass());
        writer.writeStartElement(name);
        writerContext.writeObject(new Property(name, object.getClass()), object);
        writer.writeEndElement();
    }

}
