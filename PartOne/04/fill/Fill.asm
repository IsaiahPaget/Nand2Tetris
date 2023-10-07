// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

//top left corner
@point
M = 0

(LOOP)

    // if keyboard is not pressed
    @KBD
    D = M
    @BLANK
    D;JEQ

    @FILL
    0;JMP

(BLANK)

    // d = point
    @point
    D = M

    // if d > 0 goto LOOP
    @LOOP
    D;JLT

    // d = point
    @point
    D = M

    // calculate point
    @SCREEN
    A = A + D

    // fill with white
    M = 0

    // point = point - 1
    @point
    M = M - 1

    // goto LOOP
    @LOOP
    0;JMP

(FILL)
    
    // d = point
    @point
    D = M

    // max that point can be
    @8192
    D = D - A

    // if d > 0 goto LOOP
    @LOOP
    D;JGE

    // d = point
    @point
    D = M

    // calculate point
    @SCREEN
    A = A + D

    // fill black
    M = -1

    // point = point - 1
    @point
    M = M + 1

    //goto FILL
    @FILL
    0;JMP