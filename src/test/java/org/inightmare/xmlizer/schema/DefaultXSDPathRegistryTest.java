package org.inightmare.xmlizer.schema;

import java.io.IOException;

import org.inightmare.xmlizer.AbstractDefaultXmlizerTestCase;
import org.inightmare.xmlizer.Marshaller;
import org.inightmare.xmlizer.Unmarshaller;
import org.inightmare.xmlizer.XSDPathRegistry;
import org.inightmare.xmlizer.dataobjects.Book;
import org.inightmare.xmlizer.dataobjects.Student;
import org.inightmare.xmlizer.misc.DefaultXSDPathRegistry;

/**
 * A unit test for {@link DefaultXSDPathRegistry} and related code.
 * @author klebedev
 * @since 1.6
 */
public class DefaultXSDPathRegistryTest extends AbstractDefaultXmlizerTestCase {
    
    private static final String STUDENT_SCHEMA_FILE_NAME = "student_schema.xsd";
    
    private static final String GOJIRA_NAME = "GojiraJr";
    
    private static final String BOOK_TITLE = "How to destroy Tokyo. For Dummies";
    
    private XSDPathRegistry pathRegistry = createDefaultXSDPathRegistry();

    public void testMarshallingAndUnmarshallingWithXSDPathRegistryAvailable() throws IOException {
        Student gojiraJr = buildStudent(GOJIRA_NAME);
        Student unmGojiraJr = (Student)marshalUnmarshall(gojiraJr);
        Book unrelatedBook = buildBook(BOOK_TITLE);
        Book unmBook = (Book) marshalUnmarshall(unrelatedBook);
        
        assertEquals("Student name should stay the same after marshalling and unmarshalling", GOJIRA_NAME, unmGojiraJr.getName());
        assertEquals("Book title should stay the same after marshalling and unmarshalling", BOOK_TITLE, unmBook.getTitle());
        assertEquals("Path should be equal to the one set in marshaller setup", STUDENT_SCHEMA_FILE_NAME, pathRegistry.getPath(Student.class));
        assertNull("Path should be null, because no such class is present in registry", pathRegistry.getPath(Book.class));
    }
    
    @Override
    protected void setupMarshaller(Marshaller marshaller) {
        super.setupMarshaller(marshaller);
        marshaller.setXsdPathRegistry(pathRegistry);
    }
    
    @Override
    protected void setupUnmarshaller(Unmarshaller unmarshaller) {
        unmarshaller.registerType(Student.class);
        unmarshaller.registerType(Book.class);
    }
    
    private DefaultXSDPathRegistry createDefaultXSDPathRegistry(){
        DefaultXSDPathRegistry registry = new DefaultXSDPathRegistry();
        registry.registerTypePath(Student.class, STUDENT_SCHEMA_FILE_NAME);
        return registry;
    }

    private Student buildStudent(String name) {
        Student student = new Student();
        student.setName(name);
        student.getBooks().add(buildBook("DefaultStudentBook"));
        return student;
    }
    
    private Book buildBook(String title){
        Book book = new Book();
        book.setTitle(title);
        return book;
    }
}
