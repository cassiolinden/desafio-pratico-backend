package entities.report;

import java.util.List;

public class Report {
    private List<Entry> report;

    // Constructors
    public Report() {}

    public Report(List<Entry> report) {
        this.report = report;
    }

    // Getters and Setters
    public List<Entry> getReport() { return report; }
    public void setReport(List<Entry> report) { this.report = report; }

    @Override
    public String toString() {
        return "Report{" +
                "report=" + report +
                '}';
    }
}