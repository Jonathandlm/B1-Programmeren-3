package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Site;

import java.util.List;
import java.util.Map;

public interface SiteDAO {
    
    List<Site> getSites();

    Site getSite(int siteId);

    void insertSite(Site site);

    void insertSites(List<Site> sites);

    Map<Integer,String> fillCache();
}
