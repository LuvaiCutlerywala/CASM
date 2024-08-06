package assembler;

import entities.SymbolTable;
import entities.Instruction;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Stack;

public class BinaryGenerator {

    private static final byte[] RESERVED = translateIntoBytes(0);
    private static ArrayList<Byte> output;

    public static byte[] generateBinary(SymbolTable symbolTable, Instruction[] instructions){
        output = new ArrayList<>();
        int[] translatedInstructions = CodeGenerator.translateInstructions(instructions);
        int[] translatedSymbolTable = SymbolTableEncoder.encodeSymbolTable(symbolTable);
        addArray(RESERVED);
        addArray(translateIntoBytes(translatedSymbolTable.length));
        addArray(translateIntoBytes(translatedInstructions.length));
        addArray(RESERVED);
        for(int symbol: translatedSymbolTable){
            addArray(translateIntoBytes(symbol));
        }
        addArray(RESERVED);
        for(int instruction: translatedInstructions){
            addArray(translateIntoBytes(instruction));
        }
        return unboxArray(output.toArray(new Byte[0]));
    }

    private static byte[] translateIntoBytes(int target){
        byte[] bytes = ByteBuffer.allocate(4).putInt(target).array(); //Java integers are 32 bit, i.e. 4 bytes.
        Stack<Byte> byteStack = new Stack<>();
        for(byte unit: bytes){
            byteStack.add(unit);
        }

        for(int i = 0; i < bytes.length; ++i){
            bytes[i] = byteStack.pop();
        }
    }

    private static void addArray(byte[] array){
        for(byte element: array){
            output.add(element);
        }
    }

    private static byte[] unboxArray(Byte[] array){
        byte[] primitiveArray = new byte[array.length];
        for(int i = 0; i < array.length; ++i){
            primitiveArray[i] = array[i];
        }
        return primitiveArray;
    }

}
