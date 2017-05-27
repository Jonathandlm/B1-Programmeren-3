package be.leerstad.eindwerk.model;

public class User {
    private String userId;
    private String name;
    private String firstName;
    private String cat;

    public User() {
        this("");
    }

    public User(String userId) {
        this(userId, null, null, null);
    }

    public User(String userId, String name, String firstName, String cat) {
        this.userId = userId;
        this.name = name;
        this.firstName = firstName;
        this.cat = cat;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCat() {
        return cat;
    }
    public void setCat(String cat) {
        this.cat = cat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    @Override
    public String toString() {
        return "User {" + userId + '}';
    }
}
