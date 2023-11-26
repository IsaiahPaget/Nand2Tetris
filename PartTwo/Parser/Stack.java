import java.util.ArrayList;

/**
 * Stack
 */
public class Stack {

	private int stackPointer = 0;
	private int stackPointerValue = 256;
	public ArrayList<Integer> stack = new ArrayList<Integer>();
	
	void push(String index) {
		int num = Integer.valueOf(index);
		stack.add(num);
		incrementStackPointer();
	}

	void pop() {
		stack.remove(stackPointer - 1);
		decrementStackPointer();
	} 

	private void incrementStackPointer() {
		stackPointer++;
		stackPointerValue++;
		if (stackPointerValue > 24576) {
			System.err.println("Stack Overflow!");
			System.exit(-1);
		}
	}

	private void decrementStackPointer() {
		stackPointer--;
		stackPointerValue--;
		if (stackPointerValue < 256) {
			System.err.println("Stack Underflow!");
			System.exit(-1);
		}
	}
	int getStackPointer() {
		return this.stackPointerValue;
	}
}
