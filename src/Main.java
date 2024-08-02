import assembler.*;
import entities.*;
import utils.Reader;
import utils.Writer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //Validate parameters and set the appropriate variables to their respective values.
        boolean hexdump = args[0].equals("-hd");
        String inputFilename, outputFilename;
        if(args.length < 2 || args.length > 3){
            throw new IllegalArgumentException("Too many options provided.");
        }
        int pos = 0;
        if(args.length == 3){
            if(!args[pos].equals("-hd")){
                throw new IllegalArgumentException("Illegal option used.");
            }
            pos++;
        }

        if(!args[pos].contains(".asm")){
            throw new IllegalArgumentException("Illegal input file type used.");
        }
        inputFilename = args[pos];
        pos++;

        if(!args[pos].contains(".img")){
            throw new IllegalArgumentException("Illegal output file type used.");
        }
        outputFilename = args[pos];
        //Read source file.
        String[] lines = Reader.readFile(inputFilename);
        //Tokenise the source code, and convert each line into instructions.
        Instruction[] instructions = Tokeniser.generateInstructions(lines);
        //Generate symbol table for all symbols defined in the source code.
        SymbolTable symbolTable = SymbolTableGenerator.generateSymbolTable(instructions);
        //Complete semantic analysis, checking whether all the symbols are defined and whether each instruction contains
        //the correct number of operands.
        SemanticAnalyser.analyseInstructions(instructions, symbolTable);
        //Check the operand type for each instruction, to ensure wrong types are not applied.
        TypeAnalyser.analyseTypes(instructions);
        //Encode the symbol table and instructions into integers, ready for serialisation.
        int[] encodedSymbolTable = SymbolTableEncoder.encodeSymbolTable(symbolTable);
        int[] encodedInstructions = CodeGenerator.translateInstructions(instructions);
        //Serialise the instructions and the symbol table.
        Writer.writeFile(outputFilename, encodedSymbolTable, encodedInstructions);
        //If hex dumping is enabled, then the assembler prints the hex code of the file out on the console.
        if(hexdump){
            System.out.println(0);
            for(int symbol: encodedSymbolTable){
                System.out.println(Integer.toHexString(symbol));
            }
            System.out.println(0);
            for(int instruction: encodedInstructions){
                System.out.println(Integer.toHexString(instruction));
            }
            System.out.println(0);
        }
    }

}
