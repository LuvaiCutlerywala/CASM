LDA ACC, R0
STO R1, 124
.loop LDA R2, ACC
INC
INC ; This is an increment statement
INC
STO ACC, R2
LDA R0, 345
JMP .loop

; That is an above code section
; This is a new one.

.data LDA ACC, 142
STO 142, R0