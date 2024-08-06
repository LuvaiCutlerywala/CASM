package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Stack;

public class Writer {

    public static void writeFile(String filename, int[] symbolTable, int[] instructions) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        fileOutputStream.write(0); //Signal beginning of file.
        fileOutputStream.write(symbolTable.length); //Symbol table size.
        fileOutputStream.write(instructions.length); //Instructions size.
        fileOutputStream.write(0);
        for(int symbol: symbolTable){
            byte[] bytes = ByteBuffer.allocate(4).putInt(symbol).array();
            flipBytes(bytes);
            fileOutputStream.write(bytes);
        }
        fileOutputStream.write(0); //Signal symbol table end.
        for(int instruction: instructions){
            byte[] bytes = ByteBuffer.allocate(4).putInt(instruction).array();
            flipBytes(bytes);
            fileOutputStream.write(bytes);
        }
        fileOutputStream.write(0); //Signal EOF.
        fileOutputStream.close();
    }

    private static void flipBytes(byte[] bytes){
        Stack<Byte> byteStack = new Stack<>();
        for(byte word: bytes){
            byteStack.add(word);
        }
        for(int i = 0; i < bytes.length; ++i){
            bytes[i] = byteStack.pop();
        }
    }
}
