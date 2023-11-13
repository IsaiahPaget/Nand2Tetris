### the stack machine An array
the stack operation:
- push -> add to the top of the stack
- pop -> remove the from the top eg: if you have a variable y in ram[12] and you do a pop y, it will replace ram[12] with whatever is on top of the stack and y will now equal that. 

stack arithmetic:
- add -> pop the two top most values of the stack and add them up on the side then push the result to the top of the stack
- neg -> pop the top value negate it on the side and then push to the top most
- eq -> pop the two top values check if they are equal and then push the result
- or ->pop the two top values check the result of the OR and push the result
 
stack pointer -> points to the next place in memory that something can be pushed to, for example pointing one unit above the top of the stack

### Memory Segment Commands

```java
class Foo {
	// static
	static int s1, s2;
	// arguments
	function int bar(int x, int y) {
		// local
		var int a, b, c;
		let c = s1 + y;
	}
}
```

to preserve the scope of these variables we use multiple virtual memory segments to keep track of scope eg:
- arguments
- local
- static
- constant
- and more

and in VM code it can look like this
```c
// s1 is static, y is an argument and c is local
push s1
push y
add
pop c

// syntax: commmand
// translates to this
push static 0
push argument 1
add 
pop local 2 
```

in summary the stack interacts with multiple memory segments

### pointer manipulation

![[Pasted image 20231105091839.png]]

notation:

```c
d = *p; // *p the memory location that p points at - a hex value aka address and d becomes the value that is at that memory address

p--;// if there is a ram of size 3 where there is 0, 1, 2 and *p points to ->
d = *p; // ram[2] then p-- means that it now points to ram[1] and D becomes the value at ram[1] 

*q = 9; // because q contains 1024 that means that *q is ram[1024] so assigning 9 makes ram[1024] contain 9
q++; // makes it reference ram[1025] now
```