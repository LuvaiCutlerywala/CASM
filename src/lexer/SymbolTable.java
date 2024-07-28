package lexer;

import java.util.HashMap;
import java.util.Set;

public class SymbolTable {

    private final HashMap<String, Integer> symbolTable;

    public SymbolTable(){
        this.symbolTable = new HashMap<>();
    }

    public void addSymbol(String label, int definitionPoint){
        symbolTable.put(label, definitionPoint);
    }

    public int findDefinitionPoint(String label){
        return symbolTable.get(label);
    }

    public Set<String> getAllSymbols(){
        return symbolTable.keySet();
    }

}
