package be.leerstad.eindwerk.model;

import be.leerstad.eindwerk.util.RegexUtil;

public class School {
    private String ipAddress;
    private String site;
    private String street;
    private String zip;
    private String city;

    public School() {
        this("10.110.c.d");
    }

    public School(String ipAddress) {
        this(RegexUtil.getNetworkAddress(ipAddress), "UNKNOWN", null, null, null);
    }

    public School(String ipAddress, String site, String street, String zip, String city) {
        this.ipAddress = ipAddress;
        this.site = site;
        this.street = street;
        this.zip = zip;
        this.city = city;
    }

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

        School school = (School) o;

        if (!(ipAddress == null || school.ipAddress == null))
            if (RegexUtil.getNetworkAddress(ipAddress).equals(RegexUtil.getNetworkAddress(school.ipAddress)))
                return true;
        return false;

    }

    @Override
    public int hashCode() {
        return ipAddress.hashCode();
    }

    @Override
    public String toString() {
        return "School{" + site + " (" + ipAddress + ")}";
    }
}
