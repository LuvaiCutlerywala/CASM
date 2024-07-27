package lexer;

public class Token {

    private final IdentifierType type;
    private final String identifier;
    private final Opcode opcode;
    private final Register register;

    public Token(IdentifierType type, String identifier){
        this.type = type;
        this.identifier = identifier;
        this.opcode = null;
        this.register = null;
    }

    public Token(IdentifierType type, Opcode opcode){
        this.type = type;
        this.identifier = null;
        this.opcode = opcode;
        this.register = null;
    }

    public Token(IdentifierType type, Register register){
        this.type = type;
        this.identifier = null;
        this.opcode = null;
        this.register = register;
    }

    public IdentifierType getType(){
        return this.type;
    }

    public String getIdentifier(){
        return this.identifier;
    }

    public Register getRegister(){
        return this.register;
    }

    public Opcode getOpcode(){
        return this.opcode;
    }

    @Override
    public String toString(){
        String representation = null;
        if(this.identifier != null){
            representation = "{" + this.type + ", " + this.identifier + "}";
        } else if(this.opcode != null){
            representation = "{" + this.type + ", " + this.opcode + "}";
        } else if(this.register != null){
            representation = "{" + this.type + ", " + this.register + "}";
        }

        return representation;
    }

}
