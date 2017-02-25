package be.leerstad.evaluatieoefeningen.sessions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Session {

    private String sessionName;
    private int nbrStudents;
    private List<Inscription> inscriptionList;

    public Session(String sessionName){
        this.sessionName = sessionName;
        this.nbrStudents = 0;
        this.inscriptionList = new ArrayList<>();
    }

    public int getNbrStudents() {
        return nbrStudents;
    }

    public double getStudentResult(String matriculation){
        for (Inscription inscription : inscriptionList) {
            if (inscription.getStudent().getMatriculation() == matriculation)
                return inscription.getExamination().getResult();
        }
        return -1.0;
    }

    public List<Inscription> getInscriptionList() {
        // Sorts the shallow copy and leaves original list untouched
        List<Inscription> newInscriptionList = new ArrayList<>(inscriptionList);
        Collections.sort(newInscriptionList);
        return Collections.unmodifiableList(newInscriptionList);
    }

    public List<Inscription> getInscriptionResultList(){
        // Sorts the shallow copy and leaves original list untouched
        List<Inscription> newInscriptionList = new ArrayList<>(inscriptionList);
        Collections.sort(newInscriptionList, new InscriptionResultComparator());
        return newInscriptionList;
    }

    public boolean addInscription(Inscription newInscription) {
        for (Inscription pastInscription : inscriptionList) {
            if (pastInscription.getStudent() == newInscription.getStudent())
                return false;
        }
        inscriptionList.add(newInscription);
        nbrStudents++;
        return true;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionName='" + sessionName + '\'' +
                ", nbrStudents=" + nbrStudents +
                ", inscriptionList=" + inscriptionList +
                '}';
    }
}
