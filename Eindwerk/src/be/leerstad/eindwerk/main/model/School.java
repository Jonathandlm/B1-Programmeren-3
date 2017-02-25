package be.leerstad.eindwerk.main.model;

public class School {
    private String ipAddress;
    private String site;
    private String street;
    private String zip;
    private String city;

    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School that = (School) o;

        if (ipAddress != null ? !ipAddress.equals(that.ipAddress) : that.ipAddress != null) return false;
        if (site != null ? !site.equals(that.site) : that.site != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ipAddress != null ? ipAddress.hashCode() : 0;
        result = 31 * result + (site != null ? site.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
