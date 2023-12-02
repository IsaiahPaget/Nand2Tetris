import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class IO {

	private String path;
	public File file;
	public ArrayList<String> lines;

	public IO(String path) {
		this.lines = new ArrayList<String>();
		this.path = path;
	}
	
	public void openFile() {
		try {
			file = new File(path);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			System.exit(-1);
		}
	}
	public void writeFile(String code) {
		try {
			FileWriter output = new FileWriter(getPathDotASM(path));
			output.write(code);
			System.out.println("Done");
			output.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	String getPathDotASM(String path) {
		int end = path.lastIndexOf('.');
		String substring = path.substring(0, end);
		System.out.println(substring);
		return String.format(substring + ".asm");
	}

	public ArrayList readFile() {
		if (file == null) {
			System.out.println("No file");
			System.exit(-1);
		}
		readInLines();
		if (lines.isEmpty()) {
			System.out.println("Nothing in file");
			System.exit(-1);
		} else {
			return lines;
		}
		return new ArrayList<String>();
	}

	private void readInLines() {
		try {
			Scanner reader = new Scanner(file);
			do {
				String line = reader.nextLine();
				if (line.length() > 0 && line.charAt(0) != '/') {
					lines.add(line);
				}
			} while (reader.hasNextLine());

			reader.close();

		} catch (Exception e) {
			System.out.println("Error: " + e);
			System.exit(-1);
		}
	}
}
