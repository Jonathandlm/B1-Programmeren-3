package be.leerstad.Streams.Oefeningen;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.util.Scanner;

public class Oef05 {
    public static void run() throws IOException {
        System.out.println(MethodHandles.lookup().lookupClass());
        Scanner scanner = new Scanner(new File("integerdata1.dat"));
        while (scanner.hasNextInt()){
            int i = scanner.nextInt();
            System.out.print(i % 10 == 0 ? i + "\n" : i + " ");
        }
    }
}