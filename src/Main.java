import assembler.*;
import entities.*;
import utils.Reader;
import utils.Writer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //Validate parameters and set the appropriate variables to their respective values.
        String inputFilename, outputFilename;
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of options provided.");
        }
        int pos = 0;

        if (!args[pos].contains(".asm")) {
            throw new IllegalArgumentException("Illegal input file type used.");
        }
        inputFilename = args[pos];
        pos++;

        if (!args[pos].contains(".img")) {
            throw new IllegalArgumentException("Illegal output file type used.");
        }
        outputFilename = args[pos];
        //Read source file.
        System.out.println("Reading file...");
        String[] lines = Reader.readFile(inputFilename);
        //Tokenise the source code, and convert each line into instructions.
        System.out.println("Generating tokens...");
        Instruction[] instructions = Tokeniser.generateInstructions(lines);
        //Generate symbol table for all symbols defined in the source code.
        System.out.println("Generating Symbol table...");
        SymbolTable symbolTable = SymbolTableGenerator.generateSymbolTable(instructions);
        //Complete semantic analysis, checking whether all the symbols are defined and whether each instruction contains
        //the correct number of operands.
        System.out.println("Analysing code...");
        SemanticAnalyser.analyseInstructions(instructions, symbolTable);
        //Check the operand type for each instruction, to ensure wrong types are not applied.
        TypeAnalyser.analyseTypes(instructions);
        //Encode the symbol table and instructions into integers, ready for serialisation.
        System.out.println("Generating binary...");
        byte[] bytes = BinaryGenerator.generateBinary(symbolTable, instructions);
        //Serialise the instructions and the symbol table.
        Writer.writeFile(outputFilename, bytes);
        System.out.println("File written, output file name: " + outputFilename);
    }

}
