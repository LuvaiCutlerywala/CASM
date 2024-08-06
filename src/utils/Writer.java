package utils;

import java.io.FileOutputStream;
import java.io.IOException;

public class Writer {

    public static void writeFile(String filename, byte[] bytes) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
