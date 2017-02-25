package be.leerstad.evaluatieoefeningen.sessions;

public class Student {

    private String matriculation;
    private String name;

    public Student(String matriculation, String name) {
        this.matriculation = matriculation;
        this.name = name;
    }

    public String getMatriculation() {
        return matriculation;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "matriculation='" + matriculation + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return matriculation.equals(student.matriculation);
    }

    @Override
    public int hashCode() {
        return matriculation.hashCode();
    }
}
