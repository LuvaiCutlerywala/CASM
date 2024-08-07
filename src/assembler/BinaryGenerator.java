package assembler;

import entities.SymbolTable;
import entities.Instruction;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Stack;

public class BinaryGenerator {

    private static final int RESERVED = 0;
    private static ArrayList<Byte> output;

    public static byte[] generateBinary(SymbolTable symbolTable, Instruction[] instructions){
        output = new ArrayList<>();
        int[] translatedInstructions = CodeGenerator.translateInstructions(instructions);
        int[] translatedSymbolTable = SymbolTableEncoder.encodeSymbolTable(symbolTable);
        add(RESERVED);
        add(translatedSymbolTable.length);
        add(translatedInstructions.length);
        add(RESERVED);
        for(int symbol: translatedSymbolTable){
            add(symbol);
        }
        add(RESERVED);
        for(int instruction: translatedInstructions){
            add(instruction);
        }
        return unboxArray(output.toArray(new Byte[0]));
    }

    private static void add(int target){
        byte[] bytes = ByteBuffer.allocate(4).putInt(target).array(); //Java integers are 32 bit, i.e. 4 bytes.
        for(byte element: bytes){
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
