package be.leerstad.eindwerk.main.model;

public class SiteApplication {
    private int applicationId;
    private String application;

    public int getApplicationId() {
        return applicationId;
    }
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplication() {
        return application;
    }
    public void setApplication(String application) {
        this.application = application;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SiteApplication that = (SiteApplication) o;

        if (applicationId != that.applicationId) return false;
        if (application != null ? !application.equals(that.application) : that.application != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = applicationId;
        result = 31 * result + (application != null ? application.hashCode() : 0);
        return result;
    }
}
