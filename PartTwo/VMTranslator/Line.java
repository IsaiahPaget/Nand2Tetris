/**
 * Line
 */
public class Line {

	String command = "";
	String segment = "";
	String index = "";

	public Line(String command, String segment, String index) {
		this.command = command;
		this.segment = segment;
		this.index = index;
	}
	String getCommand() {
		return this.command;
	}
	String getSegment() {
		return this.segment;
	}
	String getIndex() {
		return this.index;
	}
}
