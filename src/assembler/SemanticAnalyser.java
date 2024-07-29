package assembler;

import entities.*;
import utils.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

public class SemanticAnalyser {

    private static final HashMap<Opcode, Tuple<Integer, IdentifierType>> operandMetadata = new HashMap<>();

    static {
        operandMetadata.put(Opcode.ADD, new Tuple<>(2, IdentifierType.OPERAND));
        operandMetadata.put(Opcode.SUB, new Tuple<>(2, IdentifierType.OPERAND));
        operandMetadata.put(Opcode.JMP, new Tuple<>(1, IdentifierType.LABEL));
        operandMetadata.put(Opcode.BRZ, new Tuple<>(1, IdentifierType.LABEL));
        operandMetadata.put(Opcode.BRP, new Tuple<>(1, IdentifierType.LABEL));
        operandMetadata.put(Opcode.HLT, new Tuple<>(0, null));
        operandMetadata.put(Opcode.STO, new Tuple<>(2, IdentifierType.OPERAND));
        operandMetadata.put(Opcode.LDA, new Tuple<>(2, IdentifierType.OPERAND));
        operandMetadata.put(Opcode.INC, new Tuple<>(0, null));
        operandMetadata.put(Opcode.DEC, new Tuple<>(0, null));
        operandMetadata.put(Opcode.NOT, new Tuple<>(1, IdentifierType.OPERAND));
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

            Tuple<Integer, IdentifierType> metadata = operandMetadata.get(tokens.getFirst().getOpcode());
            if(tokens.size() - 1 != metadata.a()){
                verified = false;
            }

            tokens.removeFirst();
            for(Token token: tokens){
                if(!token.getType().equals(metadata.b())){
                    verified = false;
                    break;
                }
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
