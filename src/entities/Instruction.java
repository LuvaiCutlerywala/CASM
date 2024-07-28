package entities;

public class Instruction {

    private final Token[] instruction;

    public Instruction(Token[] tokens){
        this.instruction = tokens;
    }

    public Token[] getInstruction(){
        return this.instruction;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(Token token: instruction){
            builder.append(token.toString()).append(" ");
        }

        return builder.toString();
    }

}
