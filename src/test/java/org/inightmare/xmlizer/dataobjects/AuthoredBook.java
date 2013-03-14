
package org.inightmare.xmlizer.dataobjects;

/**
 *
 * @author giedrius
 */
public class AuthoredBook extends Book {
    
    private String author;

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
}
