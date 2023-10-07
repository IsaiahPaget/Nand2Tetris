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

// Product = RAM[3]
@sum
M = 0

// i = 0
@i
M = 1

// For i in range of operator:
//      Product = Product + operand
(LOOP)

    // get value of i
    @i
    D = M

    // if i >= operator goto STOP
    @operator
    D = D - M
    @STOP
    D;JGT

    // sum = sum + operand
    @sum
    D = M

    @operand
    D = D + M

    @sum
    M = D

    // i = i + 1
    @i
    M = M + 1
    @LOOP
    0;JMP 


// set RAM[2] = sum
(STOP)

    @sum
    D = M
    @R2
    M=D


(END)
    @END
    0;JMP