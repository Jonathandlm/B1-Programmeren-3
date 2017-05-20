package be.leerstad.eindwerk.model;

import java.time.LocalTime;

public class Visit extends Interaction<Visit> {
    private String user;
    private SiteApplication siteApplication;
    private School school;

    public Visit() {
        super();
        this.user = "";
        this.siteApplication = new SiteApplication();
        this.school = new School();
    }

    public Visit(Logfile logfile, String ipAddress, LocalTime time, Integer transferredBytes, String user,
                 SiteApplication siteApplication, School school) {
        super(logfile, ipAddress, time, transferredBytes);
        this.user = user;
        this.siteApplication = siteApplication;
        this.school = school;
    }

    public Visit(String id, Logfile logfile, String ipAddress, LocalTime time, Integer totalTime, Integer transferredBytes,
                 Integer numberOfRequests, String user, SiteApplication siteApplication, School school) {
        super(id, logfile, ipAddress, time, totalTime, transferredBytes, numberOfRequests);
        this.user = user;
        this.siteApplication = siteApplication;
        this.school = school;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public SiteApplication getSiteApplication() {
        return siteApplication;
    }
    public void setSiteApplication(SiteApplication siteApplication) {
        this.siteApplication = siteApplication;
    }

    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Visit visit = (Visit) o;

        if (!user.equals(visit.user)) return false;
        if (!siteApplication.equals(visit.siteApplication)) return false;
        return school.equals(visit.school);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + siteApplication.hashCode();
        result = 31 * result + school.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tVisit{" +
                "user='" + user + '\'' +
                ", siteApplication='" + siteApplication + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
