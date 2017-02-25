package be.leerstad.zoo;

public class Cat extends Animal {
    public Cat(double weight, double size){
        super(weight,size);
        this.noise = "miauw";
    }

    @Override
    public boolean isTooHeavy(){
        return weight > size;
    }

}
