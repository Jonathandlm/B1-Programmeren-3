package be.leerstad.evaluatieoefeningen.sessions;

import java.util.Comparator;

public class InscriptionResultComparator implements Comparator<Inscription> {

    @Override
    public int compare(Inscription o1, Inscription o2) {
        int i = Double.compare(o2.getExamination().getResult(), o1.getExamination().getResult());
        if (i != 0) return i;

        i = o1.getStudent().getName().compareTo(o2.getStudent().getName());
        if (i != 0) return i;

        return o1.getStudent().getMatriculation().compareTo(o2.getStudent().getMatriculation());
    }
}
