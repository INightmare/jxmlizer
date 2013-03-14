
package org.inightmare.xmlizer.ifaces.model;

import java.util.List;

/**
 *
 * @author giedrius
 */
public interface Student {
    
    String getName();
    
    void setName(String name);
    
    List<Book> getBooks();
    
}
