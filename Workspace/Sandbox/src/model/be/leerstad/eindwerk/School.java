// Generated by DB Solo 5.2.1 on Feb 14, 2017 at 9:31:39 PM
package be.leerstad.eindwerk;

public class School {
    private String ipaddress;
    private String site;
    private String street;
    private String zip;
    private String city;

    //
    // getters / setters
    //
    public String getIpaddress() {
        return this.ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getSite() {
        return this.site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (null == obj) {
            return false;
        }

        if (obj instanceof School == false) {
            return false;
        }

        School other = (School) obj;

        if (false == ipaddress.equals(other.ipaddress)) {
            return false;
        }

        if (false == site.equals(other.site)) {
            return false;
        }

        if (false == street.equals(other.street)) {
            return false;
        }

        if (false == zip.equals(other.zip)) {
            return false;
        }

        if (false == city.equals(other.city)) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result = 29;

        result = (29 * result) + ipaddress.hashCode();
        result = (29 * result) + site.hashCode();
        result = (29 * result) + street.hashCode();
        result = (29 * result) + zip.hashCode();
        result = (29 * result) + city.hashCode();

        return result;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer("be.leerstad.eindwerk").append(".").append("School")
                                                                      .append("(");

        buffer.append("[").append("ipaddress").append("=").append(ipaddress).append("]");
        buffer.append("[").append("site").append("=").append(site).append("]");
        buffer.append("[").append("street").append("=").append(street).append("]");
        buffer.append("[").append("zip").append("=").append(zip).append("]");
        buffer.append("[").append("city").append("=").append(city).append("]");

        return buffer.append(")").toString();
    }
}
