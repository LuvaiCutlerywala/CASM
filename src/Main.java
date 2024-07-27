import lexer.Instruction;
import lexer.Lexer;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String[] lines = {
                ".label  ADD R0, R1",
                "SUB R1, 334",
                "JMP .label"
        };

        Instruction[] instructions = Lexer.generateInstructions(lines);


        for (Instruction instruction: instructions){
            System.out.println(instruction.toString());
        }
    }

}
