package be.leerstad.eindwerk.business.report;

public enum SessionReport {
    USER_BYTES_BY_MONTH("User Bytes By Month", "UserBytesByMonth",
            "This report gives the Top 10 of users for transferred bytes, for any given month.", "month"),
    USER_TIME_BY_MONTH("User Time By Month", "UserTimeByMonth",
            "This report gives the Top 10 of users for total time, for any given month.", "month"),
    MONTHlY_BYTES_BY_YEAR("Monthly Bytes By Year", "MonthlyBytesByYear",
            "This report gives the total transferred bytes per month, for any given year.", "year"),
    TOP_SITES_BY_VISITS("Top Sites By Visits", "TopSitesByVisits",
            "This report gives an overview of the most visited sites in number of requests.", ""),
    TOP_SITES_BY_TIME("Top Sites By Total Time", "TopSitesByTotalTime",
            "This report gives an overview of the most visited sites in number of total visit time.", ""),
    TOP_SITES_BY_BYTES("Top Sites By Bytes", "TopSitesByBytes",
            "This report gives an overview of the most visited sites in number of transferred bytes.", "");

    private final String listName;
    private final String fileName;
    private final String description;
    private final String selector;

    SessionReport(String listName, String fileName, String description, String selector) {
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

    public String getSelector() {
        return selector;
    }

}
