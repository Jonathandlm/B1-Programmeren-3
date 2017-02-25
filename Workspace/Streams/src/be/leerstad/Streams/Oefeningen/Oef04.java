package be.leerstad.Streams.Oefeningen;

import java.io.*;
import java.lang.invoke.MethodHandles;

public class Oef04 {
    public static void run() throws IOException {
        System.out.println(MethodHandles.lookup().lookupClass());
        Writer wr = new FileWriter("integerdata1.dat");
        for (int i = 1; i <= 100; i++) {
            wr.write(i + " ");
        }
        wr.close();
    }

}
