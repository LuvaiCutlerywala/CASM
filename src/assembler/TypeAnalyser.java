package assembler;

import entities.IdentifierType;
import entities.Instruction;
import entities.Opcode;
import entities.Token;

import java.util.ArrayList;

public class TypeAnalyser {

    public static void analyseTypes(Instruction[] instructions){
        for(Instruction instruction: instructions){
            ArrayList<Token> tokens = new ArrayList<>();
            Opcode opcode = null;
            boolean check = false;
            for(Token token: instruction.getInstruction()){
                if(token.getType().equals(IdentifierType.OPCODE)){
                    opcode = token.getOpcode();
                    check = true;
                    continue;
                }

                if(check){
                    tokens.add(token);
                }
            }
            if(opcode != null) {
                if(!verifyTokenTypes(opcode, tokens.toArray(new Token[0]))){
                    throw new IllegalArgumentException("Illegal operand type used for opcode.");
                }
            }
        }


    }

    private static boolean verifyTokenTypes(Opcode opcode, Token[] tokens){
        String[] args = opcode.getArgs();
        for(int i = 0; i < args.length; ++i){
            if(args[i].equals("Address")){
                if(tokens[i].getIdentifier() == null) return false;
                int address = Integer.parseInt(tokens[i].getIdentifier());
                if(address < 0 || address > 65536) return false;
            } else if(args[i].equals("Label")){
                if(tokens[i].getIdentifier() == null || !isAlphabetic(tokens[i].getIdentifier())) return false;
            } else {
                if(tokens[i].getRegister() == null) return false;
            }
        }
        return true;
    }

    private static boolean isAlphabetic(String string){
        char[] characters = string.toCharArray();
        for(char character: characters){
            if(!Character.isLetter(character)){
                return false;
            }
        }

        return true;
    }

}
