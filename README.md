# CASM
CoreASM, or CASM, is a simple assembly language that can be used a way of learning the basics of low level programming.
The instruction count is 11, and the language is designed to run on the soon to come CASM VM. The idea of the language
is to be familiar with fundamental operations that are provided by almost all forms of assembly language, as well as
becoming familiar with necessary operations in assembly, such moving data around registers and composing different
operations from the two basic arithmetic operations.

## Architecture
The CASM language is based on the CASM VM architecture, which itself is a simple Von Neumann computer. The programmer
has access to 8 general purpose register, as well as the accumulator separately. In the CASM architecture, there are
only 11 registers, 2 of which are inaccessible to the programmer. The programmer has access to 256kB of memory space, 
which is occupied by data specified, as well as the program itself. Each instructions is 32 bits, i.e. 4 bytes long.

## Syntax
See [Syntax.md](Syntax.md) 