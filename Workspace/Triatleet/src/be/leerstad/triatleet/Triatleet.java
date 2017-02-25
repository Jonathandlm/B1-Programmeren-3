package be.leerstad.triatleet;

public class Triatleet extends Sporter implements IZwemmer, IFietser, ILoper{
    @Override
    public void loopt() {
        System.out.println("Ik loop 42,195km.");
    }

    @Override
    public void fietst() {
        System.out.println("Ik fiets 180km.");
    }

    @Override
    public void zwemt() {
        System.out.println("Ik zwem 5km.");
    }
}
