package entities;

public enum Opcode {

    ADD("ADD"),
    SUB("SUB"),
    JMP("JMP"),
    BRZ("BRZ"),
    BRP("BRP"),
    HLT("HLT"),
    STO("STO"),
    LDA("LDA"),
    INC("INC"),
    DEC("DEC"),
    NOT("NOT"),
    MOV("MOV");

    private final String value;

    Opcode(String val){
        this.value = val;
    }

    public String getOpcode(){
        return this.value;
    }

    public static Opcode convertToOpcode(String opcode){
        return switch(opcode){
            case "ADD" -> Opcode.ADD;
            case "SUB" -> Opcode.SUB;
            case "JMP" -> Opcode.JMP;
            case "BRZ" -> Opcode.BRZ;
            case "BRP" -> Opcode.BRP;
            case "HLT" -> Opcode.HLT;
            case "STO" -> Opcode.STO;
            case "LDA" -> Opcode.LDA;
            case "INC" -> Opcode.INC;
            case "DEC" -> Opcode.DEC;
            case "NOT" -> Opcode.NOT;
            case "MOV" -> Opcode.MOV;
            default -> null;
        };
    }

    public static boolean isOpcode(String opcode){
        return switch (opcode) {
            case "ADD", "SUB", "JMP", "BRZ", "BRP", "HLT", "STO", "LDA", "INC", "DEC", "NOT", "MOV" -> true;
            default -> false;
        };
    }
}
