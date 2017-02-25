package be.leerstad.evaluatieoefeningen.sessions;

public class Inscription implements Comparable<Inscription> {

    private Student student;
    private Examination examination;

    public Inscription(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    public Student getStudent() {
        return student;
    }
    public Examination getExamination() {
        return examination;
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "student=" + student +
                ", examination=" + examination +
                '}';
    }

    @Override
    public int compareTo(Inscription o) {
        if (!(this.getStudent().getName().equals(o.getStudent().getName()))) {
            return this.getStudent().getName().compareTo(o.getStudent().getName());
        }
        return this.getStudent().getMatriculation().compareTo(o.getStudent().getMatriculation());
    }
}
