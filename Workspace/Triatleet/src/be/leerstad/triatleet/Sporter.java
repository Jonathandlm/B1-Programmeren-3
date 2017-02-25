package be.leerstad.triatleet;

public abstract class Sporter {

    double hematocrietWaarde;

    Sporter(){
        this.hematocrietWaarde = 0.3;
    }

    public void setHematocrietWaarde(double hematocrietWaarde) {
        this.hematocrietWaarde = hematocrietWaarde;
    }

    void gebruiktDoping(){
        setHematocrietWaarde(0.6);
    }

    boolean isPositief(){
        return hematocrietWaarde > 0.5;
    }
}
