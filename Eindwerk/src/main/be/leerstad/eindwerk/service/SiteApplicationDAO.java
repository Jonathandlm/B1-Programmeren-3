package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.SiteApplication;

import java.util.List;
import java.util.Map;

public interface SiteApplicationDAO {

    List<SiteApplication> getSiteApplications();

    SiteApplication getSiteApplication(int applicationId);

    void insertSiteApplication(SiteApplication siteApplication);

    void insertSiteApplications(List<SiteApplication> siteApplications);

    Map<Integer,String> fillCache();
}
