
package org.inightmare.xmlizer.ifaces.model;

/**
 *
 * @author giedrius
 */
public class BookImpl implements Book {

    private String title;
    
    private int yearOfRelease;
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }
    
}
