import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class IO {

	public File file;
	public ArrayList<String> lines;

	public IO() {
		this.lines = new ArrayList<String>();
	}
	
	public void openFile(String path) {
		try {
			file = new File(path);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			System.exit(-1);
		}
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
