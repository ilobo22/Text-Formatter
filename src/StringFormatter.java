import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class StringFormatter {

	private String finalVal;
	
	ArrayList<String> commandList = new ArrayList<String>();
	ArrayList<String> strList = new ArrayList<String>();
	ArrayList<String> twoColOne = new ArrayList<String>();
	ArrayList<String> twoColTwo = new ArrayList<String>();
	
	
	
	public StringFormatter(BufferedReader file) throws IOException {
		
		finalVal = ""; //the string that is to be formatted
		//setting default formatting to one column, left justified, and single spaced
//		commandList.add("-1");
//		commandList.add("-l");
//		commandList.add("-s");
		int count1 = 0;
		int count2 = 0;
		System.out.println("Here?");
		//reads until end of file
		String strCurrentLine;
		while ((strCurrentLine = file.readLine()) != null) {
			
			//a command is detected and added to list
			if(strCurrentLine.indexOf('-') == 0) {
				
				System.out.println("Here2?");
				if(count1>0 && count2>0) {
					System.out.println("Here5?");
					for(int i = 0; i < commandList.size(); i++) {
						switch(commandList.get(i).charAt(1)) {
						case 'r': //temp = rightJustify(temp);
							break;
						case 'c': //temp = centerJustify(temp);
							break;
						case 'l':// temp = leftJustify(temp);
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
						case '2':  twoColumn(); //Brandon/Andrew
							break;
						case '1': //temp = oneColumn(temp);	Brandon
							break;
						case 'e': //temp = blankLine(temp); Brandon
							break;
						}
					}
					commandList.clear();
					strList.clear();
					count1 = 0;
					count2 = 0;
				}
				
				count1++;
				commandList.add(strCurrentLine);
				System.out.println(strCurrentLine);
				System.out.println(count1);
				
			}
			//time to format the string!
			else {
				count2++;
				System.out.println("Here3?");
				//String temp = "";
				//temp += strCurrentLine;
				strList.add(strCurrentLine);
				//System.out.println(Arrays.toString(commandList.toArray()));
				
				//setting command list back to defaults
//				commandList.clear();
//				commandList.add("-1");
//				commandList.add("-l");
//				commandList.add("-s");
				//finalVal+= temp;
				//finalVal += "\n"; //reset for a new string to be formatted
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
	
	public void twoColumn() {
		int val = strList.size()/2;
		int count = 0;
		String temp = "";
		String temp2 = "";
		System.out.println("Here6?");
		for(int i = 0; i < (strList.size()/2); i++) {
			for(int j = 0; j < strList.get(i).length(); j++) {
				if(temp.length() == 35) {
					twoColOne.add(temp);
					temp = "";
				}
				temp += strList.get(i).charAt(j);
			}
			
			
			System.out.println("Here7?");
			for(int j = 0; j < strList.get(val).length(); j++) {
				if(temp2.length() == 35) {
					twoColTwo.add(temp2);
					temp2 = "";
				}
				temp2 += strList.get(val).charAt(j);
			}
			
			val++;
		}
		twoColOne.add(temp);
		twoColTwo.add(temp2);
		
		System.out.println("Here8?");
		int size;
		int size1 = twoColOne.size();
		int size2 = twoColTwo.size();
		if(size1 > size2) {
			size = size1;
		}
		else {
			size = size2;
		}
		for(int i = 0; i < size; i++) {
			if(i < size1) {
				finalVal += twoColOne.get(i);
			}
			else {
				finalVal += "                                  ";
			}
			finalVal += "          ";
			if(i < size2) {
				finalVal += twoColTwo.get(i);
			}
			finalVal += "\n";
		}
		System.out.println("Here9?");
	}
}
