package be.leerstad.eindwerk.model;

public class Site {
    private int siteId;
    private String site;

    public Site() {
        this(0,"");
    }

    public Site(int siteId, String site) {
        this.siteId = siteId;
        this.site = site;
    }

    public int getSiteId() {
        return siteId;
    }
    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Site that = (Site) o;

        if (siteId != that.siteId) return false;
        if (site != null ? !site.equals(that.site) : that.site != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = siteId;
        result = 31 * result + (site != null ? site.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Site{" + site + '}';
    }

}
