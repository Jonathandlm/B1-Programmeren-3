package be.leerstad.Streams.Oefeningen;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Oef01 {
    public static void run() throws IOException {
        System.out.println(MethodHandles.lookup().lookupClass());
        Path textFile = Paths.get("first.txt");
        String line1 = "Hello World! This is my first file input/output exercise";
        List<String> lines = Arrays.asList(line1);

        try {
            Files.write(textFile, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
