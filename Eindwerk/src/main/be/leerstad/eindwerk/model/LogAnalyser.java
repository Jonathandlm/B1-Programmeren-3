package be.leerstad.eindwerk.model;

import be.leerstad.eindwerk.service.*;

import java.util.List;

public class LogAnalyser {

    private List<Interaction> interactions;
    private List<Logfile> logfileCache;
    private List<User> userCache;
    private List<Site> siteCache;
    private List<SiteApplication> siteApplicationCache;
    private List<School> schoolCache;

    private static LogAnalyser instance;

    private LogAnalyser() {
        this.interactions = InteractionDAOImpl.getInstance().getInteractions();
        this.logfileCache = LogfileDAOImpl.getInstance().getLogfiles();
        this.userCache = UserDAOImpl.getInstance().getUsers();
        this.siteCache = SiteDAOImpl.getInstance().getSites();
        this.siteApplicationCache = SiteApplicationDAOImpl.getInstance().getSiteApplications();
        this.schoolCache = SchoolDAOImpl.getInstance().getSchools();
    }

    public synchronized static LogAnalyser getInstance() {
        if (instance == null) {
            instance = new LogAnalyser();
        }
        return instance;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }
    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    public List<Logfile> getLogfileCache() {
        return logfileCache;
    }
    public void setLogfileCache(List<Logfile> logfileCache) {
        this.logfileCache = logfileCache;
    }

    public List<User> getUserCache() {
        return userCache;
    }
    public void setUserCache(List<User> userCache) {
        this.userCache = userCache;
    }

    public List<Site> getSiteCache() {
        return siteCache;
    }
    public void setSiteCache(List<Site> siteCache) {
        this.siteCache = siteCache;
    }

    public List<SiteApplication> getSiteApplicationCache() {
        return siteApplicationCache;
    }
    public void setSiteApplicationCache(List<SiteApplication> siteApplicationCache) {
        this.siteApplicationCache = siteApplicationCache;
    }

    public List<School> getSchoolCache() {
        return schoolCache;
    }
    public void setSchoolCache(List<School> schoolCache) {
        this.schoolCache = schoolCache;
    }

    public List<Interaction> getAllInteractions() {
        return this.interactions = InteractionDAOImpl.getInstance().getInteractions();
    }

    public List<User> getAllUsers() {
        return this.userCache = UserDAOImpl.getInstance().getUsers();
    }

    public List<Site> getAllSites() {
        return this.siteCache = SiteDAOImpl.getInstance().getSites();
    }

    public List<SiteApplication> getAllSiteApplications() {
        return this.siteApplicationCache = SiteApplicationDAOImpl.getInstance().getSiteApplications();
    }

    public List<School> getAllSchools() {
        return this.schoolCache = SchoolDAOImpl.getInstance().getSchools();
    }

    public void refreshCaches() {
        this.interactions = InteractionDAOImpl.getInstance().getInteractions();
        this.logfileCache = LogfileDAOImpl.getInstance().getLogfiles();
        this.userCache = UserDAOImpl.getInstance().getUsers();
        this.siteCache = SiteDAOImpl.getInstance().getSites();
        this.siteApplicationCache = SiteApplicationDAOImpl.getInstance().getSiteApplications();
        this.schoolCache = SchoolDAOImpl.getInstance().getSchools();
    }
}
