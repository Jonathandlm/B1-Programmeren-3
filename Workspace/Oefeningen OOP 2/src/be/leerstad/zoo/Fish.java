package be.leerstad.zoo;

public class Fish extends Animal {
    public Fish(double weight, double size){
        super(weight,size);
        this.noise = "blub";
    }

    @Override
    public boolean isTooHeavy(){
        return false;
    }

}

