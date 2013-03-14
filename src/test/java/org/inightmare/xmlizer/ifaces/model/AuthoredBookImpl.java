
package org.inightmare.xmlizer.ifaces.model;

/**
 *
 * @author giedrius
 */
public class AuthoredBookImpl extends BookImpl implements AuthoredBook {
    
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
}
