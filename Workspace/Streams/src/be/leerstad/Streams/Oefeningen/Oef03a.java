package be.leerstad.Streams.Oefeningen;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.util.Scanner;

public class Oef03a {
    public static void run() throws IOException {
        System.out.println(MethodHandles.lookup().lookupClass());
        Scanner inputReader = new Scanner(System.in);
        System.out.print("Please enter the filenames (separated by a semi-colon): ");
        String files = inputReader.nextLine();
        String[] filenames = files.split(";");

        for (String filename : filenames) {
            filename = filename.trim();
            FileInputStream fileInputStream = new FileInputStream(filename);
            InputStream input = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[8192];
            System.out.println("\n" + filename + ":");
            try {
                for (int length; (length = input.read(buffer)) != -1;) {
                    System.out.write(buffer, 0, length);
                }
            } finally {
                input.close();
            }
        }


    }
}
