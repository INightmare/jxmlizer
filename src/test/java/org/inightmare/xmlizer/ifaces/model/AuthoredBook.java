
package org.inightmare.xmlizer.ifaces.model;

/**
 *
 * @author giedrius
 */
public interface AuthoredBook extends Book {
    
    String getAuthor();
    
    void setAuthor(String author);
    
}
