package be.leerstad.Streams.Oefeningen;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.util.Scanner;

public class Oef03 {
    public static void run() throws IOException {
        System.out.println(MethodHandles.lookup().lookupClass());
        Scanner inputReader = new Scanner(System.in);
        System.out.print("Please enter the filename: ");
        String filename = inputReader.nextLine();

        FileInputStream fileInputStream = new FileInputStream(filename);
        InputStream input = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[8192];

        try {
            for (int length; (length = input.read(buffer)) != -1;) {
                System.out.write(buffer, 0, length);
            }
        } finally {
            input.close();
        }
    }
}
