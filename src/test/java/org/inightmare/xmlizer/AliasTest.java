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
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.Date;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.inightmare.xmlizer.dataobjects.Student;

/**
 *
 * @author giedrius
 */
public class AliasTest extends TestCase {
    
    public static final String XML = "<student><firstName>John</firstName></student>";
    
    public void testUnmarshallingWithGlobalAlias() {
        Unmarshaller unmarshaller = XmlizerFactory.createDefaultUnmarshaller();
        unmarshaller.registerType(Student.class);
        unmarshaller.addAlias("name", "firstName");
        Student student = (Student) unmarshaller.unmarshal(new StringBufferInputStream(XML));
        assertEquals("John", student.getName());
    }
    
    public void testUnmarshallingWithLocalAlias() {
        Unmarshaller unmarshaller = XmlizerFactory.createDefaultUnmarshaller();
        unmarshaller.registerType(Student.class);
        unmarshaller.addAlias(Student.class, "name", "firstName");
        Student student = (Student) unmarshaller.unmarshal(new StringBufferInputStream(XML));
        assertEquals("John", student.getName());
    }
    
    public void testMarshalUnmarshalWithAlias() throws IOException {
        Student student = new Student();
        student.setName("John");
        student.setDateOfBirth(new Date());
        
        Marshaller marshaller = XmlizerFactory.createDefaultMarshaller();
        marshaller.addAlias("name", "firstName");
        marshaller.addAlias(Student.class, "dateOfBirth", "birthday");
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        marshaller.marshal(student, outputStream);
        outputStream.close();
        
        Unmarshaller unmarshaller = XmlizerFactory.createDefaultUnmarshaller();
        unmarshaller.registerType(Student.class);
        unmarshaller.addAlias("name", "firstName");
        unmarshaller.addAlias(Student.class, "dateOfBirth", "birthday");
        
        Student resultingStudent = (Student) unmarshaller.unmarshal(new ByteArrayInputStream(outputStream.toByteArray()));
        
        assertEquals("Student names must match", student.getName(), resultingStudent.getName());
        assertEquals("Student birthdays must match", student.getDateOfBirth(), resultingStudent.getDateOfBirth());
    }
    
}
