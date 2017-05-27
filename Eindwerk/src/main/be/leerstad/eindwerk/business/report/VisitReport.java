package be.leerstad.eindwerk.business.report;

public enum VisitReport {
    MONTHLY_APPLICATION_VISITS("Monthly Application Visits", "MonthlyApplicationVisits",
            "This report gives an overview of the total visits per month, grouped by every application.", false),
    SCHOOL_VISITS_BY_MONTH("School Visits By Month", "SchoolVisitsByMonth",
            "This report gives the Top 10 of visiting schools, for any given month.", true),
    MONTHLY_APPLICATION_BYTES("Monthly Application Traffic", "MonthlyApplicationBytes",
            "This report gives an overview of the total transferred bytes per month, grouped by every application.", false);

    private final String listName;
    private final String fileName;
    private final String description;
    private final Boolean selector;

    VisitReport(String listName, String fileName, String description, Boolean selector) {
        this.listName = listName;
        this.fileName = fileName;
        this.description = description;
        this.selector = selector;
    }

    public String getListName() {
        return listName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getSelector() {
        return selector;
    }

}
