package be.leerstad.eindwerk.model;

import java.time.LocalTime;

public class Session extends Interaction<Session> {
    private User user;
    private Site site;

    public Session() {
        super();
        this.user = new User();
        this.site = new Site();
    }

    public Session(Logfile logfile, String ipAddress, LocalTime time, Integer transferredBytes, User user, Site site) {
        super(logfile, ipAddress, time, transferredBytes);
        this.user = user;
        this.site = site;
    }

    public Session(String id, Logfile logfile, String ipAddress, LocalTime time, Integer totalTime, Integer transferredBytes,
                   Integer numberOfRequests, User user, Site site) {
        super(id, logfile, ipAddress, time, totalTime, transferredBytes, numberOfRequests);
        this.user = user;
        this.site = site;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Site getSite() {
        return site;
    }
    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Session session = (Session) o;

        if (user != null ? !user.equals(session.user) : session.user != null) return false;
        return site.equals(session.site);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + site.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tSession{" +
                "user='" + user + '\'' +
                ", site='" + site + '\'' +
                '}';
    }

}
