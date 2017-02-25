package be.leerstad.collections.set;

/**
 * @author hardeel.p
 */
public class Person {
    private static final char TAB = '\t';
    private int id;
    private String name;
    private String firstName;

    public Person(int id, String name, String firstName) {
        super();
        this.id = id;
        this.name = name;
        this.firstName = firstName;
    }

    public String toString() {
        String sb = String.valueOf(id) +
                TAB +
                name +
                TAB +
                firstName;
        return sb;

    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


    public int getId() {
        return id;
    }

    /**
     * @return Returns the firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

}
