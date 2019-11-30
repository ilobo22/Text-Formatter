import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class StringFormatter {

	private String finalVal;
	
	ArrayList<String> commandList = new ArrayList<String>();
	
	public StringFormatter(BufferedReader file) throws IOException {
		
		finalVal = ""; //the string that is to be formatted
		//setting default formatting to one column, left justified, and single spaced
//		commandList.add("-1");
//		commandList.add("-l");
//		commandList.add("-s");
		int count1 = 0;
		int count2 = 0;
		
		//reads until end of file
		String strCurrentLine;
		while ((strCurrentLine = file.readLine()) != null) {
			
			//a command is detected and added to list
			if(strCurrentLine.indexOf('-') == 0) {
				
				
				if(count1>0 && count2>0) {
					commandList.clear();
				}
				
				count1++;
				commandList.add(strCurrentLine);
				
			}
			//time to format the string!
			else {
				count2++;
				
				String temp = "";
				temp += strCurrentLine;
				//System.out.println(Arrays.toString(commandList.toArray()));
				for(int i = 0; i < commandList.size(); i++) {
					switch(commandList.get(i).charAt(1)) {
					case 'r': temp = rightJustify(temp);
						break;
					case 'c': //temp = centerJustify(temp);
						break;
					case 'l': temp = leftJustify(temp);
						break;
					case 't': //temp = centerTitle(finalVal);
						break;
					case 'n': //temp = removeIndent(temp);
						break;
					case 'd': //temp = doubleSpace(temp);
						break;
					case 's': //temp = singleSpace(temp);
						break;
					case 'i': //temp = indent(temp);
						break;
					case 'b': //temp = multipleIndent(temp); Brandon
						break;
					case '2': //temp = twoColumns(temp); Brandon/Andrew
						break;
					case '1': //temp = oneColumn(temp);	Brandon
						break;
					case 'e': //temp = blankLine(temp); Brandon
						break;
					}
				}
				//setting command list back to defaults
//				commandList.clear();
//				commandList.add("-1");
//				commandList.add("-l");
//				commandList.add("-s");
				finalVal+= temp;
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
