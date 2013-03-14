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

import org.inightmare.xmlizer.dataobjects.AuthoredBook;
import org.inightmare.xmlizer.dataobjects.Book;
import org.inightmare.xmlizer.dataobjects.Student;

/**
 *
 * @author giedrius
 */
public class PolymorphismTest extends AbstractDefaultXmlizerTestCase {
    
    public void testPolymorphicTypeSupport() {
        Student student = buildData();
        
        Student result = (Student) marshalUnmarshall(student);
        
        assertEquals(student.getName(), result.getName());
        assertEquals(student.getBooks().size(), result.getBooks().size());
        assertTrue("First book should be of type Book", result.getBooks().get(0).getClass().equals(Book.class));
        assertTrue("Second book should be of type AuthoredBook", result.getBooks().get(1).getClass().equals(AuthoredBook.class));
    }

    @Override
    protected void setupUnmarshaller(Unmarshaller unmarshaller) {
        unmarshaller.registerType(Student.class);
        unmarshaller.registerType(AuthoredBook.class);
    }

    private Student buildData() {
        Student student = new Student();
        student.setName("Jane Locke");
        Book book1 = new Book();
        book1.setTitle("Test");
        AuthoredBook book2 = new AuthoredBook();
        book2.setTitle("Test2");
        book2.setAuthor("John Smith");
        
        student.getBooks().add(book1);
        student.getBooks().add(book2);
        return student;
    }
    
}
