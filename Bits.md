# Bit Alignment Chart for Instructions

## Bit Representations:

### Opcode

| Keyword | Binary | Hex | 
|---------|--------|-----|
| ADD     | 0001   | 1   |
| SUB     | 0010   | 2   |
| JMP     | 0011   | 3   |
| BRZ     | 0100   | 4   |
| BRP     | 0101   | 5   |
| HLT     | 0110   | 6   |
| STO     | 0111   | 7   |
| LDA     | 1000   | 8   |
| INC     | 1001   | 9   |
| DEC     | 1010   | A   |
| NOT     | 1011   | B   |
| MOV     | 1100   | C   |


### Register

| Keyword | Binary | Hex | 
|---------|--------|-----|
| R0      | 0001   | 1   |
| R1      | 0010   | 2   |
| R2      | 0011   | 3   |
| R3      | 0100   | 4   |
| R4      | 0101   | 5   |
| R5      | 0110   | 6   |
| R6      | 0111   | 7   |
| R7      | 1000   | 8   |
| ACC     | 1001   | 9   |

## Special characaters

To represent the file's beginning and end, as well as signalling the end of the symbol table, we shall use the number 0.
As 0 is reserved in the opcodes, the registers, and the labels, it can be safely used as the reserve character for the 
alignment markers.

## Byte Ordering for .img file.
The first 4 bytes are always 0, as it signals the beginning of the file. Then the next eight bytes, 4 for the symbol
table and 4 for the instructions respectively, represent the symbol table size and the instructions length. The next 4
bytes are reserved bytes, therefore 0, and then the next bytes up to the next set of the reserved 4 bytes are symbol
symbol table definitions. The next bytes after the reserved bytes are the instructions, and the EOF is defined by the
lst sequence of 4 reserved bytes.