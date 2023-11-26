import java.util.ArrayList;

public class Parser {

	public ArrayList<Line> code;
	ArrayList<String> lines;
	String _command = "";
	String _segment = "";
	String _index = "";

	public Parser(ArrayList<String> lines) {
		this.lines = lines;
		this.code = new ArrayList<Line>();
	}

	public void parse() {
		for (String line : lines) {
			int endCmd = commandType(line);
			int endArgOne = argOne(line, endCmd);
			argTwo(line, endArgOne);
			// System.out.print(_command);
			// System.out.print(_segment);
			// System.out.println(_index);
			Line codeLine = new Line(this._command, this._segment, this._index);
			code.addLast(codeLine);
		}
	}

	int commandType(String line) {
		_command = "";
		int endIndex = line.indexOf(' ');
		if (endIndex >= 0) {
			_command = line.substring(0, endIndex);
		} else {
			_command = line;
		}
		return endIndex;
	}

	int argOne(String line, int startIndex) {
		_segment = "";
		if (startIndex <= 0) {
			return -1;
		}
		int endIndex = line.indexOf(' ', startIndex + 1);
		if (endIndex >= 0) {
			_segment = line.substring(startIndex + 1, endIndex);
		}
		return endIndex;
	}

	void argTwo(String line, int startIndex) {
		_index = "";
		if ( !(startIndex <= 0) ) {
			_index = line.substring(startIndex + 1, line.length());
		}
	}

	ArrayList<Line> getCode() {
		return this.code;
	}
}
