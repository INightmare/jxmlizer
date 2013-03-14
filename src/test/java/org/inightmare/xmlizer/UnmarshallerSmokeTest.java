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

import java.io.IOException;
import java.util.Date;
import static junit.framework.Assert.assertEquals;
import org.inightmare.xmlizer.dataobjects.Book;
import org.inightmare.xmlizer.dataobjects.Student;

/**
 *
 * @author giedrius
 */
public class UnmarshallerSmokeTest extends AbstractDefaultXmlizerTestCase {

    public void testMarshallingAndUnmarshalling() throws IOException {
        Student student = buildSampleData();
        
        Student student2 = (Student) marshalUnmarshall(student);
        
        assertEquals("Names should match", student.getName(), student2.getName());
        assertEquals("Dates should match", student.getDateOfBirth(), student2.getDateOfBirth());
        assertEquals("Collection sizes should match", student.getBooks().size(), student2.getBooks().size());
        Book book1 = student.getBooks().get(0);
        Book book2 = student2.getBooks().get(0);
        assertEquals("Book names should match", book1.getTitle(), book2.getTitle());
        assertEquals("Book release dates should match", book1.getYearOfRelease(), book2.getYearOfRelease());
        assertEquals("Book ownership status should match", book1.getStudentOwned(), book2.getStudentOwned());
    }

    private Student buildSampleData() {
        Date dateOfBirth = new Date();
        Student student = new Student();
        student.setName("Johnny");
        student.setDateOfBirth(dateOfBirth);
        
        Book book1 = new Book();
        book1.setStudentOwned(Boolean.TRUE);
        book1.setTitle("Test book");
        book1.setYearOfRelease(2010);
        
        Book book2 = new Book();
        book1.setStudentOwned(Boolean.FALSE);
        book1.setTitle("Test book #2");
        book1.setYearOfRelease(2011);
        
        student.getBooks().add(book1);
        student.getBooks().add(book2);
        
        return student;
    }

    @Override
    protected void setupUnmarshaller(Unmarshaller unmarshaller) {
        unmarshaller.registerType(Student.class);
        unmarshaller.addAlias(Student.class, "name", "fullName");
    }
}
