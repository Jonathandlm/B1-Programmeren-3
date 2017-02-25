package be.leerstad;

public class Auto implements Comparable<Auto> {

    private String merk;
    private String kleur;
    private String VIN;

    Auto(String merk, String kleur, String VIN) {
        this.merk = merk;
        this.kleur = kleur;
        this.VIN = VIN;
    }

    public String getMerk() { return merk; }
    public String getKleur() { return kleur; }
    public String getVIN() { return VIN; }

    @Override
    public String toString() {
        return "Auto{" +
                "merk='" + merk + '\'' +
                ", kleur='" + kleur + '\'' +
                ", VIN='" + VIN + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auto auto = (Auto) o;

        return getVIN().equals(auto.getVIN());
    }

    @Override
    public int hashCode() {
        return getVIN().hashCode();
    }

    @Override
    public int compareTo(Auto o) {
        return this.getVIN().compareTo(o.getVIN());
    }
}
