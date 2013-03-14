
package org.inightmare.xmlizer.ifaces.support;

import org.inightmare.xmlizer.BeanFactory;
import org.inightmare.xmlizer.ifaces.model.AuthoredBook;
import org.inightmare.xmlizer.ifaces.model.Book;
import org.inightmare.xmlizer.ifaces.model.ModelFactory;
import org.inightmare.xmlizer.ifaces.model.Student;

/**
 *
 * @author giedrius
 */
public class ModelBeanFactory implements BeanFactory {

    private ModelFactory modelFactory = new ModelFactory();
    
    public Object instantiate(Class<?> type) {
        if (Student.class.isAssignableFrom(type)) {
            return modelFactory.createStudent();
        } else if (AuthoredBook.class.isAssignableFrom(type)) {
            return modelFactory.createAuthoredBook();
        } else if (Book.class.isAssignableFrom(type)) {
            return modelFactory.createBook();
        }
        throw new IllegalArgumentException("Can not instantiate " + type.getName());
    }

    public boolean canHandle(Class<?> t) {
        return Student.class.isAssignableFrom(t) || Book.class.isAssignableFrom(t);
    }
    
}
