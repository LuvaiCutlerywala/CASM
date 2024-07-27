package lexer;

import java.util.ArrayList;

public class Tokeniser {

    public static Instruction[] generateInstructions(String[] lines){
        Instruction[] instructions = new Instruction[lines.length];
        for(int i = 0; i < lines.length; ++i){
            Instruction instruction = convertToInstruction(lines[i]);
            convertInstructionToInternalSymbols(instruction);
            instructions[i] = instruction;
        }
        return instructions;
    }

    private static void convertInstructionToInternalSymbols(Instruction instruction){
        Token[] tokens = instruction.getInstruction();
        for(int i = 0; i < tokens.length; ++i){
            if(tokens[i].getType().equals(IdentifierType.LABEL)){
                continue;
            }

            if(tokens[i].getType().equals(IdentifierType.OPCODE)){
                tokens[i] = new Token(tokens[i].getType(), Opcode.convertToOpcode(tokens[i].getIdentifier()));
            } else if(Register.isRegister(tokens[i].getIdentifier())){
                tokens[i] = new Token(tokens[i].getType(), Register.convertToRegister(tokens[i].getIdentifier()));
            }
        }
    }

    private static Instruction convertToInstruction(String instruction){
        String[] strings = instruction.split("\\s");
        ArrayList<Token> tokens = new ArrayList<>();
        for(String string: strings){
            if(!string.isEmpty()){
                tokens.add(convertToToken(string));
            }
        }

        return new Instruction(tokens.toArray(new Token[0]));
    }

    private static Token convertToToken(String token){
        Token labelledToken;
        if(token.startsWith(".")){
            labelledToken = new Token(IdentifierType.LABEL, token.substring(1));
        } else if(Opcode.isOpcode(token)){
            labelledToken = new Token(IdentifierType.OPCODE, token);
        } else {
            labelledToken = new Token(IdentifierType.OPERAND, token.replaceAll(",", ""));
        }
        return labelledToken;
    }
}
