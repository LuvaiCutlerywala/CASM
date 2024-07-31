package assembler;

import entities.*;

import java.util.ArrayList;

public class SemanticAnalyser {

    public static void analyseInstructions(Instruction[] instructions, SymbolTable symbolTable){
        if(!verifyOperands(instructions)){
            throw new IllegalArgumentException("Illegal operand used.");
        } else if(!verifySymbols(instructions, symbolTable)){
            throw new IllegalArgumentException("Symbol undefined.");
        }
    }

    private static boolean verifyOperands(Instruction[] instructions){
        boolean verified = true;
        for(Instruction instruction: instructions){
            ArrayList<Token> tokens = new ArrayList<>();
            boolean collect = false;
            for(Token token: instruction.getInstruction()){
                if(token.getType().equals(IdentifierType.OPCODE)){
                    tokens.add(token);
                    collect = true;
                    continue;
                }
                if(collect){
                    tokens.add(token);
                }
            }

            int operandCount = tokens.getFirst().getOpcode().getArgs().length;
            if(tokens.size() - 1 != operandCount){
                verified = false;
            }
        }
        return verified;
    }


    private static boolean verifySymbols(Instruction[] instructions, SymbolTable symbolTable){
        //Collect all symbols that are called to in the instructions.
        ArrayList<Token> symbols = new ArrayList<>();
        boolean verified = true;
        for(Instruction instruction: instructions){
            boolean collect = false;
            for(Token token: instruction.getInstruction()){
                if(token.getType().equals(IdentifierType.OPCODE)){
                    collect = true;
                    continue;
                }

                if(collect && token.getType().equals(IdentifierType.LABEL)){
                    symbols.add(token);
                }
            }
        }
        //Check whether each symbol is defined in the symbol table.

        for(Token token: symbols) {
            if(!symbolTable.containsSymbol(token.getIdentifier())){
                verified = false;
            }
        }

        return verified;
    }
}
