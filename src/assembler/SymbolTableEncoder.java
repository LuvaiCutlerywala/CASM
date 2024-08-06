package assembler;

import entities.SymbolTable;

public class SymbolTableEncoder {

    protected static int[] encodeSymbolTable(SymbolTable symbolTable){
        String[] symbols = symbolTable.getAllSymbols().toArray(new String[0]);
        int[] definitionPoints = new int[symbols.length];
        for(int i = 0; i < symbols.length; ++i){
            definitionPoints[i] = symbolTable.findDefinitionPoint(symbols[i]);
        }

        int[] encodedSymbols = new int[symbols.length];
        for(int i = 0; i < symbols.length; ++i){
            encodedSymbols[i] = encodeSymbol(symbols[i], definitionPoints[i]);
        }
        return encodedSymbols;
    }

    private static int encodeSymbol(String label, int definitionPoint){
        int encodedLabel = generateLabelHash(label);
        int encodedEntry = 0;
        encodedLabel = encodedLabel << 16;
        encodedEntry = encodedEntry | encodedLabel;
        encodedEntry = encodedEntry | definitionPoint;
        return encodedEntry;
    }

    private static int generateLabelHash(String label){
        int hashCode = label.hashCode();
        int leftBits = hashCode & 0xffff0000;
        int rightBits = hashCode & 0xffff;
        leftBits = leftBits >>> 16;
        return (leftBits ^ rightBits);
    }

}
