package lexer;

import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, Integer> symbolTable;

    public SymbolTable(){
        this.symbolTable = new HashMap<>();
    }

    public void addSymbol(String label, int definitionPoint){
        symbolTable.put(label, definitionPoint);
    }

    public int findDefinitionPoint(String label){
        return symbolTable.get(label);
    }

}
