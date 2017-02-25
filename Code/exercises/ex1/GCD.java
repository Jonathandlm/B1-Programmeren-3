package be.leerstad.javabasics.exercises.ex1;

import java.math.BigInteger;

public class GCD {
    public static void main(String[] args) {
        BigInteger number1 = new BigInteger(args[0]);
        BigInteger number2 = new BigInteger(args[1]);
        System.out.println("The GCD of " + number1 + " and " + number2 + " is " + number1.gcd(number2));
    }

}
