// Generated by DB Solo 5.2.1 on Feb 14, 2017 at 9:31:39 PM
package be.leerstad.eindwerk;

import java.util.Date;


public class Session {
    private String sessionid;
    private String logfile;
    private String ipaddress;
    private Date sessiontime;
    private Integer totaltime;
    private Integer transferredbytes;
    private Integer numberofrequests;
    private String userid;
    private Integer siteid;

    //
    // getters / setters
    //
    public String getSessionid() {
        return this.sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getLogfile() {
        return this.logfile;
    }

    public void setLogfile(String logfile) {
        this.logfile = logfile;
    }

    public String getIpaddress() {
        return this.ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public Date getSessiontime() {
        return this.sessiontime;
    }

    public void setSessiontime(Date sessiontime) {
        this.sessiontime = sessiontime;
    }

    public Integer getTotaltime() {
        return this.totaltime;
    }

    public void setTotaltime(Integer totaltime) {
        this.totaltime = totaltime;
    }

    public Integer getTransferredbytes() {
        return this.transferredbytes;
    }

    public void setTransferredbytes(Integer transferredbytes) {
        this.transferredbytes = transferredbytes;
    }

    public Integer getNumberofrequests() {
        return this.numberofrequests;
    }

    public void setNumberofrequests(Integer numberofrequests) {
        this.numberofrequests = numberofrequests;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getSiteid() {
        return this.siteid;
    }

    public void setSiteid(Integer siteid) {
        this.siteid = siteid;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (null == obj) {
            return false;
        }

        if (obj instanceof Session == false) {
            return false;
        }

        Session other = (Session) obj;

        if (false == sessionid.equals(other.sessionid)) {
            return false;
        }

        if (false == logfile.equals(other.logfile)) {
            return false;
        }

        if (false == ipaddress.equals(other.ipaddress)) {
            return false;
        }

        if (false == sessiontime.equals(other.sessiontime)) {
            return false;
        }

        if (false == totaltime.equals(other.totaltime)) {
            return false;
        }

        if (false == transferredbytes.equals(other.transferredbytes)) {
            return false;
        }

        if (false == numberofrequests.equals(other.numberofrequests)) {
            return false;
        }

        if (false == userid.equals(other.userid)) {
            return false;
        }

        if (false == siteid.equals(other.siteid)) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result = 29;

        result = (29 * result) + sessionid.hashCode();
        result = (29 * result) + logfile.hashCode();
        result = (29 * result) + ipaddress.hashCode();
        result = (29 * result) + sessiontime.hashCode();
        result = (29 * result) + totaltime.hashCode();
        result = (29 * result) + transferredbytes.hashCode();
        result = (29 * result) + numberofrequests.hashCode();
        result = (29 * result) + userid.hashCode();
        result = (29 * result) + siteid.hashCode();

        return result;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer("be.leerstad.eindwerk").append(".").append("Session")
                                                                      .append("(");

        buffer.append("[").append("sessionid").append("=").append(sessionid).append("]");
        buffer.append("[").append("logfile").append("=").append(logfile).append("]");
        buffer.append("[").append("ipaddress").append("=").append(ipaddress).append("]");
        buffer.append("[").append("sessiontime").append("=").append(sessiontime).append("]");
        buffer.append("[").append("totaltime").append("=").append(totaltime).append("]");
        buffer.append("[").append("transferredbytes").append("=").append(transferredbytes)
              .append("]");
        buffer.append("[").append("numberofrequests").append("=").append(numberofrequests)
              .append("]");
        buffer.append("[").append("userid").append("=").append(userid).append("]");
        buffer.append("[").append("siteid").append("=").append(siteid).append("]");

        return buffer.append(")").toString();
    }
}
