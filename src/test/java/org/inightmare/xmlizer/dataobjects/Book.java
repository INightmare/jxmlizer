package org.inightmare.xmlizer.dataobjects;

/**
 *
 * @author giedrius
 */
public class Book {

    private String title;
    private int yearOfRelease;
    private Boolean studentOwned;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the yearOfRelease
     */
    public int getYearOfRelease() {
        return yearOfRelease;
    }

    /**
     * @param yearOfRelease the yearOfRelease to set
     */
    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    /**
     * @return the studentOwned
     */
    public Boolean getStudentOwned() {
        return studentOwned;
    }

    /**
     * @param studentOwned the studentOwned to set
     */
    public void setStudentOwned(Boolean studentOwned) {
        this.studentOwned = studentOwned;
    }
}
