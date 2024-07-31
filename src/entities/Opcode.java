package entities;

public enum Opcode {

    ADD("ADD", new String[]{"Register", "Register"}),
    SUB("SUB", new String[]{"Register", "Register"}),
    JMP("JMP", new String[]{"Label"}),
    BRZ("BRZ", new String[]{"Label"}),
    BRP("BRP", new String[]{"Label"}),
    HLT("HLT", new String[]{}),
    STO("STO", new String[]{"Register", "Address"}),
    LDA("LDA", new String[]{"Address", "Register"}),
    INC("INC", new String[]{}),
    DEC("DEC", new String[]{}),
    NOT("NOT", new String[]{"Register"}),
    MOV("MOV", new String[]{"Register", "Register"});

    private final String value;
    private final String[] args;

    Opcode(String val, String[] args){
        this.value = val;
        this.args = args;
    }

    public String getOpcode(){
        return this.value;
    }

    public String[] getArgs() {
        return this.args;
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
