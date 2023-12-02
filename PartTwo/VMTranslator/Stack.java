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

	String pop() {
		String value = stack.remove(stackPointer - 1);
		decrementStackPointer();
		return value;
	} 

	void add() {
		String value1 = pop();
		String value2 = pop();

		int num1 = Integer.parseInt(value1);
		int num2 = Integer.parseInt(value2);

		int sum = num1 + num2;
		push(String.valueOf(sum));
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
