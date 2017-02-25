package be.leerstad.eindwerk.main.model;

import java.sql.Time;

public class Session extends Interaction {
    private String userId;
    private String site;

    public Session() {
        super();
        this.userId = "";
        this.site = "";
    }

    public Session(LogFile logFile, String ipAddress, Time time, Integer transferredBytes, String userId, String site) {
        super(logFile, ipAddress, time, transferredBytes);
        this.userId = userId;
        this.site = site;
    }

    public Session(String id, LogFile logFile, String ipAddress, Time time, Integer totalTime, Integer transferredBytes,
                   Integer numberOfRequests, String userId, String site) {
        super(id, logFile, ipAddress, time, totalTime, transferredBytes, numberOfRequests);
        this.userId = userId;
        this.site = site;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }

    public Session add(Session session) {
        this.setTotalTime((int) (session.getTime().getTime() - this.getTime().getTime()));
        this.setTransferredBytes(this.getTransferredBytes() + session.getTransferredBytes());
        this.setNumberOfRequests(this.getNumberOfRequests() + 1);
        return this;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSession{" +
                "userId='" + userId + '\'' +
                ", site='" + site + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Session session = (Session) o;

        if (userId != null ? !userId.equals(session.userId) : session.userId != null) return false;
        return site.equals(session.site);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + site.hashCode();
        return result;
    }
}
