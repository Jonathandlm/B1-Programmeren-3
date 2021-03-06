// Generated by DB Solo 5.2.1 on Feb 14, 2017 at 9:31:39 PM
package be.leerstad.eindwerk;

public class Siteapplication {
    private int applicationid;
    private String application;

    //
    // getters / setters
    //
    public int getApplicationid() {
        return this.applicationid;
    }

    public void setApplicationid(int applicationid) {
        this.applicationid = applicationid;
    }

    public String getApplication() {
        return this.application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (null == obj) {
            return false;
        }

        if (obj instanceof Siteapplication == false) {
            return false;
        }

        Siteapplication other = (Siteapplication) obj;

        if (applicationid != other.applicationid) {
            return false;
        }

        if (false == application.equals(other.application)) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result = 29;

        result = (29 * result) + applicationid;
        result = (29 * result) + application.hashCode();

        return result;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer("be.leerstad.eindwerk").append(".")
                                                                      .append("Siteapplication")
                                                                      .append("(");

        buffer.append("[").append("applicationid").append("=").append(applicationid).append("]");
        buffer.append("[").append("application").append("=").append(application).append("]");

        return buffer.append(")").toString();
    }
}
