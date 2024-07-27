package lexer;

public class SymbolTableGenerator {

    public static SymbolTable generateSymbolTable(Instruction[] instructions){
        //TODO: Complete symbol table generator for labels.
        return null;
    }

    public static void validateOperands(Instruction[] instructions) throws IllegalArgumentException{
        for(Instruction instruction: instructions){
            validateInstructionOperands(instruction);
        }
    }

    private static void validateInstructionOperands(Instruction instruction) throws IllegalArgumentException{
        for(Token token: instruction.getInstruction()){
            if(token.getType().equals(IdentifierType.OPERAND) && token.getRegister() == null){
                if(!token.getIdentifier().matches("[0-9]+")){
                    throw new IllegalArgumentException("Undefined operand used.");
                }
            }
        }
    }

}
