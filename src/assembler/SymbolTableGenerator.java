package assembler;

import entities.IdentifierType;
import entities.Instruction;
import entities.SymbolTable;
import entities.Token;

public class SymbolTableGenerator {

    public static SymbolTable generateSymbolTable(Instruction[] instructions){
        validateOperands(instructions);
        SymbolTable table = new SymbolTable();
        for(short i = 0; i < instructions.length; ++i){
            Token token = instructions[i].getInstruction()[0];
            if(token.getType().equals(IdentifierType.LABEL)){
                table.addSymbol(token.getIdentifier(), i);
            }
        }
        return table;
    }

    private static void validateOperands(Instruction[] instructions) throws IllegalArgumentException{
        for(Instruction instruction: instructions){
            validateInstructionOperands(instruction);
        }
    }

    private static void validateInstructionOperands(Instruction instruction) throws IllegalArgumentException{
        for(Token token: instruction.getInstruction()){
            if(token.getType().equals(IdentifierType.OPERAND) && token.getRegister() == null){
                try{
                    Integer.parseInt(token.getIdentifier());
                } catch(NumberFormatException nf){
                    throw new IllegalArgumentException("Illegal address used, symbol: " + token.getIdentifier());
                }
            }
        }
    }

}
