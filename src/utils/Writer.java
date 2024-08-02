package utils;

import java.io.FileOutputStream;
import java.io.IOException;

public class Writer {

    public static void writeFile(String filename, int[] symbolTable, int[] instructions) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        fileOutputStream.write(0); //Signal beginning of file.
        for(int symbol: symbolTable){
            fileOutputStream.write(symbol);
        }
        fileOutputStream.write(0); //Signal symbol table end.
        for(int instruction: instructions){
            fileOutputStream.write(instruction);
        }
        fileOutputStream.write(0); //Signal EOF.
        fileOutputStream.close();
    }

}
