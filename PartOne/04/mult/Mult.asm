// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//
// This program only needs to handle arguments that satisfy
// R0 >= 0, R1 >= 0, and R0*R1 < 32768.

// Psuedo Code

// X = RAM[0]
// Y = RAM[1]
// Z = RAM[2]

// for (i = 0 ; i < Y ; i++) {
//      Z = Z + X;
//}

// operator = RAM[0]
@R0
D = M

@operator
M = D

// operand = RAM[1]
@R1
D = M

@operand
M = D

//clear R2
@R2
M = 0

// Product = RAM[3]
@sum
M = 0

(LOOP)

    // d = operator
    @operator
    D = M

    // sum = sum + operator
    @sum
    M = M + D

    // decrement operand for eventual 0
    @operand
    M = M - 1
    D = M

    // if operand != 0 goto LOOP
    @LOOP
    D;JGT

    // if operand = 0 goto ZERO
    @ZERO
    D;JLT

    // otherwise goto STOP
    @STOP
    0;JMP 


// set RAM[2] = sum
(STOP)

    @sum
    D = M
    @R2
    M=D

    @END
    0;JMP

// case where operand is zero
(ZERO)
    @sum
    M = 0
    @STOP
    0;JMP


(END)
    @END
    0;JMP