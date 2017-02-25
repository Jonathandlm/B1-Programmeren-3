package be.leerstad.eindwerk.main.model;

public class Visit extends Interaction{
    private String user;
    private String siteApplicationId;
    private String ipSchool;



    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getSiteApplicationId() {
        return siteApplicationId;
    }
    public void setSiteApplicationId(String siteApplicationId) {
        this.siteApplicationId = siteApplicationId;
    }

    public String getIpSchool() {
        return ipSchool;
    }
    public void setIpSchool(String ipSchool) {
        this.ipSchool = ipSchool;
    }

}
