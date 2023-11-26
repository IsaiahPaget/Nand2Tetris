import java.util.ArrayList;

/**
 * InstructionGenerator
 */
public class InstructionGenerator {

	ArrayList<Line> code;
	ArrayList<String> linesOfASM = new ArrayList<String>();
	Stack stack = new Stack();
	ArrayList<Integer> memory = new ArrayList<Integer>();

	public InstructionGenerator(ArrayList<Line> code) {
		this.code = code;
		memory.add(0, stack.getStackPointer());
	}

	ArrayList<String> generateCode() {
		for (Line line : code) {
			System.out.println(line.command);
			switch (line.command) {
				case "push":
					translatePush(line.segment, line.index);
					break;
				case "pop":
					translatePop(line.segment, line.index);
					break;

				default:
					System.out.println("Syntax error");
					break;
			}
			System.out.println(stack.stack);
		}
		return linesOfASM;	
	}

	void translatePush(String segment, String index) {
		String lineOfCode = "";
		if (segment.equals("constant")) {
			stack.push(index);
			lineOfCode = String.format("@" + index + "\nD=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
			linesOfASM.add(lineOfCode);
		} else {
			stack.push(index);
			lineOfCode = String.format("@" + getShortNameSegment(segment) + "\nD=M\n@" + index + "\nD=D+A\nA=D\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
			linesOfASM.add(lineOfCode);
		}
	}

	void translatePop(String segment, String index) {
		String lineOfCode = "";
		stack.pop();
		lineOfCode = String.format("@" + getShortNameSegment(segment) + "\nD=M\n@" + index + "\nD=D+A\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n");
		linesOfASM.add(lineOfCode);
	}
	private String getShortNameSegment(String segment) {
		String shortNameSegment = "";
		switch (segment) {
			case "local":
			shortNameSegment = "LCL";	
			break;
			case "argument":
			shortNameSegment = "ARG";	
			break;
			case "this":
			shortNameSegment = "THIS";	
			break;
			case "that":
			shortNameSegment = "THAT";	
			break;
			case "static":
			shortNameSegment = "Static";	
			break;
			case "pointer":
			shortNameSegment = "Pointer";	
			break;
			case "temp":
			shortNameSegment = "Temp";	
			break;

			default:
			System.out.println("Syntax error");
			System.exit(-1);
			break;
		}
		return shortNameSegment;
	}
} 
