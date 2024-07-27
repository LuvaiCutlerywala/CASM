package lexer;

public enum Register {

    R0("R0"),
    R1("R1"),
    R2("R2"),
    R3("R3"),
    R4("R4"),
    R5("R5"),
    R6("R6"),
    R7("R7"),
    ACC("ACC");

    private final String register;

    Register(String register){
        this.register = register;
    }

    public String getRegister(){
        return this.register;
    }

    public static Register convertToRegister(String register){
        return switch(register){
            case "R0" -> Register.R0;
            case "R1" -> Register.R1;
            case "R2" -> Register.R2;
            case "R3" -> Register.R3;
            case "R4" -> Register.R4;
            case "R5" -> Register.R5;
            case "R6" -> Register.R6;
            case "R7" -> Register.R7;
            case "ACC" -> Register.ACC;
            default -> null;
        };
    }

    public static boolean isRegister(String register){
        return switch(register){
          case "R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "ACC" -> true;
          default -> false;
        };
    }

}
