# Syntax

All mnemonic keywords are to be typed using capital letters, as presented in the keywords table below.

## Keywords
| Keyword | Definition                                                                                 | Usage      |
|---------|--------------------------------------------------------------------------------------------|------------|
| ADD     | Adds two numbers in specified registers and stores value in accumulator.                   | ADD R0, R1 |
| SUB     | Subtracts the number stored in the latter register from the number in the former register. | SUB R1, R0 |
| JMP     | Jumps to the label specified.                                                              | JMP .end   |
| BRZ     | Branches to the label specified if value in accumulator is zero.                           | BRZ .loop  |
| BRP     | Branches to the label specified if value in accumulator is positive.                       | BRP .loop  |
| HLT     | Halts the program.                                                                         | HLT        |
| STO     | Stores the value in the former register to the value in the latter register.               | STO R0, R1 |
| LDA     | Loads the value in the former register to the latter register.                             | LDA R1, R0 |
| INC     | Increments the value stored in the accumulator.                                            | INC        |
| DEC     | Decrements the value stored in the accumulator.                                            | DEC        |


## Comments
Comments are denoted using the ';' symbol to start the comment, until the end of the line. Both CRLF and LF line endings
can be used for the line endings.

## Labels
Each label must begin with a '.' symbol, and can only be used before or after an instuction, with labels prior to an instruction assumed
as being a labelling of the line itself, whilst labels after an instruction will be assumed to be a reference to another line.

## Registers
There are 8 general purpose registers that are available to the programmer, as well as the accumulator as a separate register.

| Register Reference | Tag |
| ------------------ | --- |
| Register 0         | R0  |
| Register 1         | R1  |
| Register 2         | R2  |
| Register 3         | R3  |
| Register 4         | R4  |
| Register 5         | R5  |
| Register 6         | R6  |
| Register 7         | R7  |
| Accumulator        | ACC |

## Main memory

As this version of assembly is designed to be basic, it assumes no cache memory is added the processor, therefore any memory access is directly done from the main memory.
The main memory consists of a 256kB chunk, with no virtualisation layer. Each cell consists of a 32-bit number, with 65536 unique addresses.
The only number type supported will unsigned integers, with the range 0 - 4,294,967,296. No floating point numbers are supported, i.e. no compliance with IEEE 754.