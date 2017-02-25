package be.leerstad;

import be.leerstad.transport.Car;
import be.leerstad.transport.Ship;
import be.leerstad.transport.Van;
import be.leerstad.zoo.Animal;
import be.leerstad.zoo.Cat;
import be.leerstad.zoo.Fish;
import be.leerstad.zoo.Rabbit;

public class Main {

    public static void main(String[] args) {
	    // ANIMAL
        Animal heavyAnimal = new Animal(50,10);
        Animal lightAnimal = new Animal(5,20);
        Cat heavyCat = new Cat(11,10);
        Cat lightCat = new Cat(10,10);
        Fish fish = new Fish(10,10);
        Rabbit heavyRabbit = new Rabbit(2,10);
        Rabbit lightRabbit = new Rabbit(0.5,10);

        // TESTING ANIMALS
        System.out.println("--- TESTING ANIMALS ---");
        System.out.println("- toString() -");
        System.out.println(heavyAnimal.toString());
        System.out.println(lightAnimal.toString());
        System.out.println(heavyCat.toString());
        System.out.println(lightCat.toString());
        System.out.println(fish.toString());
        System.out.println(heavyRabbit.toString());
        System.out.println(lightRabbit.toString());
        System.out.println("");
        System.out.println("- equals() -");
        System.out.println(heavyRabbit.equals(heavyRabbit));
        System.out.println(lightCat.equals(fish));
        System.out.println("");

        // SHIP
        Ship ship = new Ship();
        Car car = new Car();
        Van van = new Van();

        // TESTING SHIP
        System.out.println("--- TESTING SHIP ---");
        System.out.println(ship.getContent());
        ship.removeVehicle();
        ship.addVehicle(car);
        System.out.println(ship.getContent());
        ship.addVehicle(car);
        System.out.println(ship.getContent());
        ship.addVehicle(van);
        System.out.println(ship.getContent());
        ship.removeVehicle();
        System.out.println(ship.getContent());
        ship.removeVehicle();
        System.out.println(ship.getContent());
        ship.removeVehicle();
        System.out.println(ship.getContent());
        ship.removeVehicle();
        System.out.println(ship.getContent());

    }
}
