package org.inightmare.xmlizer.misc;

import java.io.IOException;

import org.inightmare.xmlizer.AbstractDefaultXmlizerTestCase;
import org.inightmare.xmlizer.Marshaller;
import org.inightmare.xmlizer.Unmarshaller;
import org.inightmare.xmlizer.XSDPathRegistry;
import org.inightmare.xmlizer.dataobjects.Book;
import org.inightmare.xmlizer.dataobjects.Student;
import org.inightmare.xmlizer.misc.XmlConstants.SchemaLocation;

/**
 * A unit test for {@link DefaultXSDPathRegistry} and related code.
 * @author klebedev
 * @since 2015-12
 */
public class DefaultXSDPathRegistryTest extends AbstractDefaultXmlizerTestCase {
    
    private static final String STUDENT_SCHEMA_FILE_NAME = "student_schema.xsd";
    
    private XSDPathRegistry pathRegistry = createDefaultXSDPathRegistry();
    
    public void testMarshallingAndUnmarshallingWithXSDPathRegistryAvailable() throws IOException {
        Student gojiraJr = buildStudent("GojiraJr");
        marshalUnmarshall(gojiraJr);
        Book unrelatedBook = buildBook("How to destroy Tokyo. For Dummies");
        marshalUnmarshall(unrelatedBook);
        
        assertEquals("Path should be equal to the one set in marshaller setup", STUDENT_SCHEMA_FILE_NAME, pathRegistry.getPath(Student.class));
        assertNull("Path should be null, because no such class is present in registry", pathRegistry.getPath(Book.class));
        assertEquals("Default registry implementation should return noNamespaceSchemaLocation attribute",
                        SchemaLocation.NO_NAMESPACE_SCHEMA_LOCATION, pathRegistry.getLocation());
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
