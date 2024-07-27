package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    public static String[] readFile(String filename) throws FileNotFoundException {
        final Scanner scanner = new Scanner(new File(filename));
        ArrayList<String> content = new ArrayList<>();
        while(scanner.hasNextLine()){
            content.add(scanner.nextLine());
        }

        scanner.close();
        content.removeIf(String::isEmpty);
        return content.toArray(new String[0]);
    }

}
