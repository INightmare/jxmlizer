
package org.inightmare.xmlizer.ifaces.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giedrius
 */
public class StudentImpl implements Student {

    private String name;
    
    private List<Book> books = new ArrayList<Book>();
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }
    
}
