package entities;

public enum Opcode {

    ADD("ADD", new String[]{"Register", "Register"}, (byte) 0x01),
    SUB("SUB", new String[]{"Register", "Register"}, (byte) 0x02),
    JMP("JMP", new String[]{"Label"}, (byte) 0x03),
    BRZ("BRZ", new String[]{"Label"}, (byte) 0x04),
    BRP("BRP", new String[]{"Label"}, (byte) 0x05),
    HLT("HLT", new String[]{}, (byte) 0x06),
    STO("STO", new String[]{"Register", "Address"}, (byte) 0x07),
    LDA("LDA", new String[]{"Address", "Register"}, (byte) 0x08),
    INC("INC", new String[]{}, (byte) 0x09),
    DEC("DEC", new String[]{}, (byte) 0x0a),
    NOT("NOT", new String[]{"Register"}, (byte) 0x0b),
    MOV("MOV", new String[]{"Register", "Register"}, (byte) 0x0c),
    OUT("OUT", new String[]{"Register"}, (byte) 0x0d);

    private final String value;
    private final String[] args;
    private final byte code;

    Opcode(String val, String[] args, byte code){
        this.value = val;
        this.args = args;
        this.code = code;
    }

    public String getOpcode(){
        return this.value;
    }

    public String[] getArgs() {
        return this.args;
    }

    public byte getCode() {
        return this.code;
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
            case "OUT" -> Opcode.OUT;
            default -> null;
        };
    }

    public static boolean isOpcode(String opcode){
        return switch (opcode) {
            case "ADD", "SUB", "JMP", "BRZ", "BRP", "HLT", "STO", "LDA", "INC", "DEC", "NOT", "MOV", "OUT" -> true;
            default -> false;
        };
    }
}
