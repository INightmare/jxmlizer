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

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.inightmare.xmlizer.reflection.Property;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * 
 * @author giedrius
 */
public class Unmarshaller {
 
    private ReaderContext readerContext = new ReaderContext();
    
    private DocumentBuilder documentBuilder;

    Unmarshaller() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new XmlizerException("Exception occured during Unmarshaller initialization. "
                    + "Unable to create new document builder", ex);
        }
    }

    /**
     * Deserialize XML to an object.
     * 
     * @param inputStream
     * @return 
     */
    public Object unmarshal(InputStream inputStream) {
        Object result = null;

        try {
            Document document = documentBuilder.parse(inputStream);
            result = unmarshal(document);
        } catch (Exception ex) {
            throw new XmlizerException("Exception occured during unmarshalling", ex);
        }

        return result;
    }

    public void registerType(Class type) {
        readerContext.typeRegistry.registerType(type);
    }

    public void addAlias(String attributeName, String alias) {
        readerContext.aliasor.addAlias(attributeName, alias);
    }

    public void addAlias(Class type, String attributeName, String alias) {
        readerContext.aliasor.addAlias(type, attributeName, alias);
    }

    public void registerBeanFactory(BeanFactory beanFactory) {
        readerContext.factories.addHandler(beanFactory);
    }
    
    public void addAccessor(Accessor accessor) {
        readerContext.accessors.addHandler(accessor);
    }

    public void registerPrimitiveConverter(PrimitiveConverter primitiveConverter) {
        readerContext.primitiveConverters.addHandler(primitiveConverter);
    }
    
    public void addTypeHandler(TypeHandler typeHandler) {
        readerContext.handlers.addHandler(typeHandler);
    }

    public void setNamingStrategy(TypeNamingStrategy namingStrategy) {
        readerContext.typeNamingStrategy = namingStrategy;
    }
    
    public Object unmarshal(Document document) {
        Node rootNode = document.getFirstChild();
        String rootNodeName = rootNode.getNodeName();
        Class<?> rootType = readerContext.typeNamingStrategy.getTypeFromName(readerContext.typeRegistry, rootNodeName);
        
        if (rootType == null) {
            throw new UnmarshallerException("Unable to determine root node type. Please check if the type is registered "
                    + "or if your custom type naming strategy is correct.");
        }
        
        TypeHandler handler = readerContext.handlers.findHandler(rootType);
        
        return handler.unmarshal(new Property(rootNodeName, rootType), rootNode, readerContext);
    }
    
}
