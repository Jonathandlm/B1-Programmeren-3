package be.leerstad.javabasics.enums.coffee;


public enum CoffeeSize {
    BIG(1.2D), HUGE(2.8), OVERWHELMING(78.9);

    private double inhoud;

    private CoffeeSize(double inhoud) {
        this.inhoud = inhoud;
    }

    public double getInhoud() {
        return inhoud;
    }
}
