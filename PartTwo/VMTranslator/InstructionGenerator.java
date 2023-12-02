import java.util.ArrayList;

/**
 * InstructionGenerator
 */
public class InstructionGenerator {

	ArrayList<Line> code;
	ArrayList<String> linesOfASM = new ArrayList<String>();

	public InstructionGenerator(ArrayList<Line> code) {
		this.code = code;
	}

	String generateCode() {
		for (Line line : code) {
			System.out.println(line.command);
			translateCommand(line);
		}
		String allInOne = String.join("", linesOfASM);
		return allInOne;	
	}
	void translateCommand(Line line) {
		String translatedCommand = "";
		String comment = "// " + line.command + "\n";

		if (line.segment.equals("constant")) {
			translatedCommand = getConstPushCommand(line.segment, line.index);
			linesOfASM.add(comment + translatedCommand);
			return;
		}

		switch (line.command) {
			case "push":
				translatedCommand = getPushCommand(line.segment, line.index);
				break;
			case "pop":
				translatedCommand = getPopCommand(line.segment, line.index);
				break;
			case "add":
				translatedCommand = getAddCommand();
				break;
			case "sub":
				translatedCommand = getSubCommand();
				break;
			default:
				System.out.println("Syntax error");
				break;
		}
		linesOfASM.add(comment + translatedCommand);
	}

	String getPopCommand(String segment, String index) {
		return String.format("@" + getShortNameSegment(segment) + "\nD=M\n@" + index + "\nD=D+A\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n");
	}

	String getPushCommand(String segment, String index) {
		return String.format("@" + getShortNameSegment(segment) + "\nD=M\n@" + index + "\nD=D+A\nA=D\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
	}

	String getConstPushCommand(String segment, String index) {
		return String.format("@" + index + "\nD=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
	}

	String getAddCommand() {
		return String.format("@SP\nAM=M-1\nD=M\n@SP\nAM=M-1\nM=D+M\n@SP\nM=M+1\n");
	}
	String getSubCommand() {
		return String.format("@SP\nAM=M-1\nD=M\n@SP\nAM=M-1\nM=D-M\n@SP\nM=M+1\n");
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
