package assembler;

import entities.*;
import utils.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

public class SemanticAnalyser {

    private static final HashMap<Opcode, Integer> operandMetadata = new HashMap<>();

    static {
        operandMetadata.put(Opcode.ADD, 2);
        operandMetadata.put(Opcode.SUB, 2);
        operandMetadata.put(Opcode.JMP, 1);
        operandMetadata.put(Opcode.BRZ, 1);
        operandMetadata.put(Opcode.BRP, 1);
        operandMetadata.put(Opcode.HLT, 0);
        operandMetadata.put(Opcode.STO, 2);
        operandMetadata.put(Opcode.LDA, 2);
        operandMetadata.put(Opcode.INC, 0);
        operandMetadata.put(Opcode.DEC, 0);
        operandMetadata.put(Opcode.NOT, 1);
        operandMetadata.put(Opcode.MOV, 2);
    }

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
                //Check if opcode has been reached. Collect opcode into tokens list.
                if(token.getType().equals(IdentifierType.OPCODE)){
                    tokens.add(token);
                    collect = true;
                    continue;
                }
                //If collect flag is enabled, collect all subsequent tokens.
                if(collect){
                    tokens.add(token);
                }
            }

            int operandCount = operandMetadata.get(tokens.getFirst().getOpcode());
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
