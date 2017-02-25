package be.leerstad.Streams.Oefeningen;

import java.io.*;
import java.lang.invoke.MethodHandles;

public class Oef02 {
    public static void run() throws IOException {
        System.out.println(MethodHandles.lookup().lookupClass());
        InputStream stdin = System.in;
        FileInputStream fileInputStream = new FileInputStream("first.txt");
        System.setIn(fileInputStream);

        PrintStream stdout = System.out;
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");
        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);

        int data = System.in.read();
        while (data != -1) {
            System.out.print((char) data);
            data = System.in.read();
        }

        // Reset System in and out to standard streams
        System.setIn(stdin);
        System.setOut(stdout);
    }
}
