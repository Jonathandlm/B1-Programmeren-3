package be.leerstad.zoo;

public class Animal {
    protected double weight;
    protected double size;
    protected String noise;
    private final String GOODWEIGHT = "I'm not Fat !";
    private final String OVERWEIGHT = "I need to go and see the Weightwatchers";

    public Animal(double weight, double size){
        this.weight = weight;
        this.size = size;
        this.noise = "";
    }

    public boolean isTooHeavy(){
        return weight > (size - 10);
    }

    public double getBMI(){
        return weight / (size * size);
    }

    public String toString(){
        return String.format("Hey, I'm a %s and %s", getClass().getSimpleName().toLowerCase(), (isTooHeavy()) ? OVERWEIGHT : GOODWEIGHT);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || (!(obj instanceof Animal))) {
            return false;
        }

        Animal animal = (Animal) obj;
        return this.getBMI() == animal.getBMI();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((noise == null) ? 0 : noise.hashCode());
        result = prime * result + (int)weight;
        result = prime * result + (int)size;
        return result;
    }

}
