import java.util.ArrayList;

class VMTranslator {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Main example.vm");
			System.exit(-1);
		} 
		IO reader = new IO();
		reader.openFile(args[0]);
		ArrayList lines = reader.readFile();	
		System.out.println(lines);
		Parser parser = new Parser(lines);
		parser.parse();
	}
}
