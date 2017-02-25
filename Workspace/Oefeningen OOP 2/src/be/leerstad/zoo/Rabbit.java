package be.leerstad.zoo;

public class Rabbit extends Animal {
    public Rabbit(double weight, double size){
        super(weight,size);
        this.noise = "ribbit";
    }

    @Override
    public boolean isTooHeavy(){
        return weight > 1;
    }

}
