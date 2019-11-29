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
		
		finalVal = "";
		
		String strCurrentLine;
		while ((strCurrentLine = file.readLine()) != null) {
			
			if(strCurrentLine.indexOf('-') == 0) {
				commandList.add(strCurrentLine);
			}
			else {
				finalVal += strCurrentLine;
			}
		}
		
		
	}

}
