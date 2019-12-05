import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class StringFormatter {

	private class WorkList {
		public boolean rJust;// right justification
		public boolean cJust;// center
		public boolean lJust;// left
		public boolean dCol;
		public boolean sCol;
		public boolean indent;
		public boolean multIndent;
		public boolean removeIndent;
		public boolean blankline;
		public boolean tCenter;// title center
		public boolean singleSpace;
		public boolean doubleSpace;
		// public ArrayList<String> text;
		String text;

		public WorkList() {
			lJust = true;
			sCol = true;
			singleSpace = true;
			text = "";
			finalVal = "";
		}

		public void formatAndClear() {
			if (rJust) {
				finalVal += rightJustify(text);
			}
			if (cJust) {

			}
			if (lJust) {

			}
			if (tCenter) {

			}
			if (indent) {

			}
			if (multIndent) {

			}
			if (blankline) {

			}
			if (singleSpace) {

			}
			if (doubleSpace) {

			}
			if (sCol) {
				finalVal += text;
			}
			if (dCol) {
				finalVal += twoColumn(text);
			}

			// text.clear();
			text = "";
		}
	}

	private String finalVal;

	// right justifies
	String rightJustify(String str) {
		System.out.println(str);

		int limit = 80;
		ArrayList<String> strings = new ArrayList<String>();
		String finalString = "";

		// breaks strings into 80 character substrings and stores them
		while (str.length() > limit) {
			finalString = "";
			strings.add(str.substring(0, limit));
			str = str.substring(limit + 1, str.length());
		}
		strings.add(str);

		// combines substrings into one string
		for (int i = 0; i < strings.size(); i++) {
			if (i < strings.size() - 1)
				finalString += String.format("%80s", strings.get(i)) + "\n";
			else
				finalString += String.format("%80s", strings.get(i));
		}
		System.out.println(finalString);

		return finalString;
	}

	// left justifies
	public String leftJustify(String str) {
		return String.format("%-80s", str);
	}

	// returns the final string
	public String toString() {
		return finalVal;
	}

	public String twoColumn(String str) {
		int size = str.length();
		int split = size/2;
		
		String first = "";
		String second = "";
		String finalStr = "";
		
		first = str.substring(0, split);
		second = str.substring(split, size);
		
		int count = 0;
		int trackingOne = 0;
		int trackingTwo = 0;
		while(trackingOne < first.length() && trackingTwo < second.length()){
			while(count < 35 && trackingOne < first.length()) {
				finalStr += first.charAt(trackingOne);
				count++;
				trackingOne++;
			}
			if(count != 34) {
				for(int i = 0; i <= (34 - count);i++) {
					System.out.println(count);
					finalStr += " ";
				}
			}
			count = 0;
			finalStr += "          ";
			while(count < 35 && trackingTwo < second.length()) {
				finalStr += second.charAt(trackingTwo);
				count++;
				trackingTwo++;
			}
			finalStr += "\n";
			count = 0;
		}	
		return finalStr;
	}
	
	ArrayList<String> commandList = new ArrayList<String>();

	public StringFormatter(BufferedReader file) throws IOException {

		WorkList format = new WorkList();

		finalVal = ""; // the string that is to be formatted

		boolean comSeenB4 = false;

		// reads until end of file
		String strCurrentLine;
		while ((strCurrentLine = file.readLine()) != null) {

			// a command is detected and added to list
			if (strCurrentLine.indexOf('-') == 0) {
				if (comSeenB4) {
					format.formatAndClear();
					comSeenB4 = false;
					System.out.println("here");
				}

				switch (strCurrentLine.charAt(1)) {
				case 'r':
					format.rJust = true;
					format.lJust = false;
					format.cJust = false;
					// temp = rightJustify(temp);
					break;
				case 'c':
					format.cJust = true;
					format.lJust = false;
					format.rJust = false;
					// temp = centerJustify(temp);
					break;
				case 'l':
					format.rJust = false;
					format.cJust = false;
					format.lJust = true;
					// temp = leftJustify(temp);
					break;
				case 't':
					format.tCenter = true;
					// temp = centerTitle(finalVal);
					break;
				case 'n': // temp = removeIndent(temp);
					break;
				case 'd': // temp = doubleSpace(temp);
					break;
				case 's': // temp = singleSpace(temp);
					break;
				case 'i': // temp = indent(temp);
					break;
				case 'b': // temp = multipleIndent(temp);
					break;
				case '2': 
					format.dCol = true;
					format.sCol = false;
					break;
				case '1': 
					format.dCol = false;
					format.sCol = true;
					break;
				case 'e': // temp = blankLine(temp);
					break;
				}

				// if(toClear)
				// format.clear();

			}
			// time to format the string!
			else {
				// count2++;
				comSeenB4 = true;
				format.text += strCurrentLine;
				System.out.println(format.text);
			}
		}
		format.formatAndClear();

	}
}