
package org.inightmare.xmlizer.ifaces.model;

/**
 *
 * @author giedrius
 */
public class ModelFactory {
    
    public Student createStudent() {
        return new StudentImpl();
    }
    
    public Book createBook() {
        return new BookImpl();
    }
    
    public AuthoredBook createAuthoredBook() {
        return new AuthoredBookImpl();
    }
    
}
