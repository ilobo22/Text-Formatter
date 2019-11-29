import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class StringFormatter {
	
	private boolean rJustify = false;
	private boolean centerJustify = false;
	private boolean leftJustify = false;
	private boolean titleCenter = false;
	private boolean doubleSpace = false;
	private boolean singleSpace = false;
	private boolean singleIndent = false;
	private boolean multipleIndent = false;
	private boolean twoCol = false;
	private boolean singleCol = false;
	private boolean blankLine = false;
	private boolean removeIndent = false;
	private String finalVal;
	
	ArrayList<String> commandList = new ArrayList<String>();
	
	public StringFormatter(BufferedReader file) throws IOException {
		
		finalVal = ""; //the string that is to be formatted
		//setting default formatting to one column, left justified, and single spaced
		commandList.add("-1");
		commandList.add("-l");
		commandList.add("-s");
		
		//reads until end of file
		String strCurrentLine;
		while ((strCurrentLine = file.readLine()) != null) {
			
			//a command is detected and added to list
			if(strCurrentLine.indexOf('-') == 0) {
				commandList.add(strCurrentLine);
			}
			//time to format the string!
			else {
				finalVal += strCurrentLine;
				for(int i = commandList.size()-1; i >= 0; i--) {
					switch(commandList.get(i).charAt(1)) {
					case 'r': finalVal = rightJustify(finalVal);
						break;
					case 'c': //finalVal = centerJustify(finalVal);
						break;
					case 'l': finalVal = leftJustify(finalVal);
						break;
					case 't': //centerTitle(finalVal);
						break;
					case 'n': //removeIndent(finalVal);
						break;
					case 'd': //doubleSpace(finalVal);
						break;
					case 's': //singleSpace(finalVal);
						break;
					case 'i': //indent(finalVale);
						break;
					case 'b': //multipleIndent(finalVal);
						break;
					case '2': //twoColumns(finalVal);
						break;
					case '1': //oneColumn(finalVal);
						break;
					case 'e': //blankLine(finalVal);
						break;
					}
				}
				//setting command list back to defaults
				commandList.clear();
				commandList.add("-1");
				commandList.add("-l");
				commandList.add("-s");
				finalVal += "\n"; //reset for a new string to be formatted
			}
		}		
	}
	
	//right justifies 
	public String rightJustify(String str) {
		return String.format("%80s", str);
	}
	
	//left justifies
	public String leftJustify(String str) {
		return String.format("%-80s", str);
	}
	
	//returns the final string
	public String toString() {
		return finalVal;
	}

}
