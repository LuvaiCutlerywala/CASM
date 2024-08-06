package assembler;

import entities.IdentifierType;
import entities.Instruction;
import entities.Token;

import java.util.ArrayList;

public class CodeGenerator {

    //This generator does not use aligned instructions, therefore the bitset for each instruction almost unique.

    protected static int[] translateInstructions(Instruction[] instructions){
        Instruction[] strippedInstructions = stripLabels(instructions);
        int[] encodedInstructions = new int[strippedInstructions.length];
        for(int i = 0; i < strippedInstructions.length; ++i){
            encodedInstructions[i] = encodeInstruction(strippedInstructions[i]);
        }

        return encodedInstructions;
    }

    private static Instruction[] stripLabels(Instruction[] instructions){
        Instruction[] strippedInstructions = new Instruction[instructions.length];
        for(int i = 0; i < instructions.length; ++i){
            if(instructions[i].getInstruction()[0]
                    .getType().equals(IdentifierType.LABEL))
            {
                strippedInstructions[i] = copyInstruction(instructions[i], 1);
            } else {
                strippedInstructions[i] = copyInstruction(instructions[i], 0);
            }
        }

        return strippedInstructions;
    }

    private static int encodeInstruction(Instruction instruction){
        int encodedInstruction = 0;
        int pos = 32;
        for(Token token: instruction.getInstruction()){
            if(token.getType().equals(IdentifierType.OPCODE)){
                int code = token.getOpcode().getCode();
                code = code << 28;
                encodedInstruction  = encodedInstruction | code;
                pos -= 4;
            } else if(token.getType().equals(IdentifierType.LABEL)){
                int labelHash = generateLabelHash(token.getIdentifier());
                labelHash = labelHash << (pos - 16);
                encodedInstruction = encodedInstruction | labelHash;
                pos -= 16;
            } else if(token.getRegister() != null){
                int code = token.getRegister().getCode();
                code = code << (pos - 4);
                encodedInstruction = encodedInstruction | code;
                pos -= 4;
            } else {
                int address = Integer.parseInt(token.getIdentifier());
                address = address << (pos - 16);
                encodedInstruction = encodedInstruction | address;
                pos -= 16;
            }
        }

        return encodedInstruction;
    }

    private static Instruction copyInstruction(Instruction instruction, int pos){
        ArrayList<Token> tokens = new ArrayList<>();
        for(; pos < instruction.getInstruction().length; ++pos){
            tokens.add(instruction.getInstruction()[pos]);
        }
        return new Instruction(tokens.toArray(new Token[0]));
    }

    private static int generateLabelHash(String label){
        int hashCode = label.hashCode();
        int leftBits = hashCode & 0xffff0000;
        int rightBits = hashCode & 0xffff;
        leftBits = leftBits >>> 16;
        return (leftBits ^ rightBits);
    }

}
