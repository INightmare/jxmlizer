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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import junit.framework.TestCase;

/**
 *
 * @author giedrius
 */
public abstract class AbstractDefaultXmlizerTestCase extends TestCase {

    private Marshaller marshaller;
    
    private Unmarshaller unmarshaller;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        marshaller = setupMarshaller();
        unmarshaller = setupUnmarshaller();
    }
    
    
    protected Marshaller setupMarshaller() {
        Marshaller localMarshaller = XmlizerFactory.createDefaultMarshaller();
        setupMarshaller(localMarshaller);
        return localMarshaller;
    }
    
    protected Unmarshaller setupUnmarshaller() {
        Unmarshaller localUnmarshaller = XmlizerFactory.createDefaultUnmarshaller();
        setupUnmarshaller(localUnmarshaller);
        return localUnmarshaller;
    }
    
    protected Object marshalUnmarshall(Object object) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        marshaller.marshal(object, outputStream);
        return unmarshaller.unmarshal(new ByteArrayInputStream(outputStream.toByteArray()));
    }
    
    protected void setupMarshaller(Marshaller marshaller) {};
    
    protected void setupUnmarshaller(Unmarshaller unmarshaller) {};
    
}
