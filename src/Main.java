import assembler.SemanticAnalyser;
import entities.Instruction;
import entities.SymbolTable;
import assembler.SymbolTableGenerator;
import assembler.Tokeniser;
import utils.Reader;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String[] lines = Reader.readFile(args[args.length - 1]);

        Instruction[] instructions = Tokeniser.generateInstructions(lines);

        SymbolTable symbolTable = SymbolTableGenerator.generateSymbolTable(instructions);

        SemanticAnalyser.analyseInstructions(instructions, symbolTable);

        for (Instruction instruction: instructions){
            System.out.println(instruction.toString());
        }
    }

}
