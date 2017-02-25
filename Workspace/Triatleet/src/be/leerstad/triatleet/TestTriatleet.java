package be.leerstad.triatleet;

public class TestTriatleet {

    public static void main(String[] args) {
	// write your code here
        Sporter russischeSporter = new Triatleet();
        System.out.println(russischeSporter.isPositief());
        russischeSporter.gebruiktDoping();
        System.out.println(russischeSporter.isPositief());

        Triatleet vanLierde = new Triatleet();
        vanLierde.zwemt();
        vanLierde.fietst();
        vanLierde.loopt();
    }
}
