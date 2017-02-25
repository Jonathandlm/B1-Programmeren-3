package be.leerstad.javabasics.exercises.ex1;

import java.time.LocalDate;

/**
 * Created by peterhardeel on 13/09/16.
 */
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("be.leerstad.javabasics.exercises.HelloWorld");
        LocalDate now = LocalDate.now();
        System.out.println("Today is : " + now);
    }
}
