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

## Instruction encoding

| Instruction | Binary | Hex | 
|-------------|--------|-----|
| ADD R0, R1  | 0001   | 1   |
| SUB         | 0010   | 2   |
| JMP         | 0011   | 3   |
| BRZ         | 0100   | 4   |
| BRP         | 0101   | 5   |
| HLT         | 0110   | 6   |
| STO         | 0111   | 7   |
| LDA         | 1000   | 8   |
| INC         | 1001   | 9   |
| DEC         | 1010   | A   |
| NOT         | 1011   | B   |
| MOV         | 1100   | C   |