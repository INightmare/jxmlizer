
package org.inightmare.xmlizer.ifaces;

import org.inightmare.xmlizer.AbstractDefaultXmlizerTestCase;
import org.inightmare.xmlizer.Marshaller;
import org.inightmare.xmlizer.Unmarshaller;
import org.inightmare.xmlizer.ifaces.model.Book;
import org.inightmare.xmlizer.ifaces.model.ConcreteHolder;
import org.inightmare.xmlizer.ifaces.model.ModelFactory;
import org.inightmare.xmlizer.ifaces.model.Student;
import org.inightmare.xmlizer.ifaces.support.ModelBeanFactory;

/**
 *
 * @author giedrius
 */
public class InterfaceBasedModelTest extends AbstractDefaultXmlizerTestCase {

    private ModelFactory factory = new ModelFactory();
    
    public void testInterfaceBasedModel() {
        
        Student student = factory.createStudent();
        student.setName("John");
        
        Book book = factory.createBook();
        book.setTitle("Test");
        student.getBooks().add(book);
        
        ConcreteHolder holder = new ConcreteHolder();
        holder.setStudent(student);
        
        ConcreteHolder result = (ConcreteHolder) marshalUnmarshall(holder);
        
        assertEquals(student.getName(), result.getStudent().getName());
        assertEquals(student.getBooks().size(), result.getStudent().getBooks().size());
        assertEquals(student.getBooks().get(0).getTitle(), result.getStudent().getBooks().get(0).getTitle());
    }
    
    @Override
    protected void setupUnmarshaller(Unmarshaller unmarshaller) {
        unmarshaller.registerBeanFactory(new ModelBeanFactory());
        unmarshaller.registerType(ConcreteHolder.class);
    }

    @Override
    protected void setupMarshaller(Marshaller marshaller) {
        super.setupMarshaller(marshaller); //To change body of generated methods, choose Tools | Templates.
    }
    
}
