import lexer.Instruction;
import lexer.SymbolTableGenerator;
import lexer.Tokeniser;
import utils.Reader;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String[] lines = Reader.readFile(args[args.length - 1]);

        Instruction[] instructions = Tokeniser.generateInstructions(lines);

        SymbolTableGenerator.validateOperands(instructions);

        for (Instruction instruction: instructions){
            System.out.println(instruction.toString());
        }
    }

}
