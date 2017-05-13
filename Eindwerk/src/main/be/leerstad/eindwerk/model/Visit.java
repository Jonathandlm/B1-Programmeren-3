package be.leerstad.eindwerk.model;

import java.time.LocalTime;

public class Visit extends Interaction<Visit> {
    private String user;
    private String siteApplicationId;
    private String ipSchool;

    public Visit() {
        super();
        this.user = "";
        this.siteApplicationId = "";
        this.ipSchool = "";
    }

    public Visit(LogFile logFile, String ipAddress, LocalTime time, Integer transferredBytes, String user,
                 String siteApplicationId, String ipSchool) {
        super(logFile, ipAddress, time, transferredBytes);
        this.user = user;
        this.siteApplicationId = siteApplicationId;
        this.ipSchool = ipSchool;
    }

    public Visit(String id, LogFile logFile, String ipAddress, LocalTime time, Integer totalTime, Integer transferredBytes,
                   Integer numberOfRequests, String user, String siteApplicationId, String ipSchool) {
        super(id, logFile, ipAddress, time, totalTime, transferredBytes, numberOfRequests);
        this.user = user;
        this.siteApplicationId = siteApplicationId;
        this.ipSchool = ipSchool;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getSiteApplicationId() {
        return siteApplicationId;
    }
    public void setSiteApplicationId(String siteApplicationId) {
        this.siteApplicationId = siteApplicationId;
    }

    public String getIpSchool() {
        return ipSchool;
    }
    public void setIpSchool(String ipSchool) {
        this.ipSchool = ipSchool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Visit visit = (Visit) o;

        if (!user.equals(visit.user)) return false;
        if (!siteApplicationId.equals(visit.siteApplicationId)) return false;
        return ipSchool.equals(visit.ipSchool);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + siteApplicationId.hashCode();
        result = 31 * result + ipSchool.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tVisit{" +
                "user='" + user + '\'' +
                ", siteApplicationId='" + siteApplicationId + '\'' +
                ", ipSchool='" + ipSchool + '\'' +
                '}';
    }
}
