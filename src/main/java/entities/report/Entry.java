package entities.report;

import java.util.Date;

public class Entry {
    private Date start;
    private Date end;
    private String title;

    // Constructors
    public Entry() {}

    public Entry(Date start, Date end, String title) {
        this.start = start;
        this.end = end;
        this.title = title;
    }

    // Getters and Setters
    public Date getStart() { return start; }
    public void setStart(Date start) { this.start = start; }

    public Date getEnd() { return end; }
    public void setEnd(Date end) { this.end = end; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {
        return "Entry{" +
                "start=" + start +
                ", end=" + end +
                ", title='" + title + '\'' +
                '}';
    }
}