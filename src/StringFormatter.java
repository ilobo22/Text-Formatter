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
			
			if(removeIndent) {
				//finalVal += removeIndent(text) + "\n";
			}
			if (rJust) {
				finalVal += rightJustify(text) + "\n";
			}
			if (cJust) {
				finalVal += centerJustify(text) + "\n";
			}
			if (lJust) {
				
				if(multIndent) {
					finalVal += multipleIndent(text) + "\n";
				}
				else if(indent) {
					finalVal += indent(text) + "\n";
				}
				else {
					finalVal += leftJustify(text) + "\n";
				}
				
			}
//			if (indent) {
//				finalVal += indent(text) + "\n";
//			}
//			if (multIndent) {
//				
//			}

			if (tCenter) {
				finalVal += centerTitle(text) + "\n";
			}
			if (blankline) {
				//finalVal += blankLine(text) + "\n";
			}
			if (singleSpace) {
				//finalVal += singleSpace(text) + "\n";
			}
			if (doubleSpace) {
				//finalVal += doubleSpace(text) + "\n";
			}
			if (sCol) {
				//finalVal += oneColumn(text) + "\n";
			}
			if (dCol) {
				//finalVal += twoColumn(text) + "\n";
			}

			// text.clear();
			text = "";
		}
	}

	private String finalVal;
	
	public String multipleIndent(String str) {
		
		String finalString = "";
		String temp;
		temp = str.replaceAll("(?m)^", "     ");
		System.out.println(temp);
		
		finalString = leftJustify(temp);
		
		
		return finalString;
	}
	
	public String indent(String str) {
		String finalString = "";
		
		String temp;
		
		temp = "     " + str;
		
		finalString = leftJustify(temp);
		
		return finalString;
	}

	
	public String centerJustify(String str) {

		String finalString = "";
		int spaces = 0;
		int limit = 80;
		String[] wordList = str.split("\\W+");
		while(finalString.length() <= limit-wordList[wordList.length-1].length())
		{
			finalString = "";
			for(int i = 0; i < wordList.length-1; i++)
			{
				finalString += wordList[i];
				for(int j = 0; j < spaces; j++)
				{
					finalString += " ";
				}
			}
			finalString += wordList[wordList.length-1];
			spaces++;
		}
		return finalString;
	}
	
	// right justifies
	public String rightJustify(String str) {

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

		return finalString;
	}

	// left justifies
	public String leftJustify(String str) {
		int limit = 80;
		ArrayList<String> strings = new ArrayList<String>();
		String finalString = "";
		
		//breaks strings into 80 character substrings and stores them
		while(str.length() > limit)
		{
			strings.add(str.substring(0, limit));
			str = str.substring(limit + 1, str.length());
		}
		strings.add(str);
		
		//combines substrings into one string
		for(int i = 0; i < strings.size(); i++)
		{
			if(i < strings.size() - 1)
				finalString += strings.get(i) + "\n";
			else
				finalString += strings.get(i);
		}
		return finalString;
		
	}

	public String centerTitle(String str) {
		int limit = 80;
		int numSpaces = limit - (str.length() % limit);
		String spacePadding = "";
		for(int i = 0; i < numSpaces/2; i++)
			spacePadding += " ";
		ArrayList<String> strings = new ArrayList<String>();
		String finalString = "";
		
		//breaks strings into 80 character substrings and stores them
		while(str.length() > limit)
		{
			strings.add(str.substring(0, limit));
			str = str.substring(limit + 1, str.length());
		}
		strings.add(str);
		
		//combines strings into one string
		for(int i = 0; i < strings.size(); i++)
		{
			if(i < strings.size() - 1)
				finalString += strings.get(i) + "\n";
			else
				finalString += spacePadding + strings.get(i) + spacePadding;
		}
		return finalString;
	}
	
	// returns the final string
	public String toString() {
		return finalVal;
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
				}

				switch (strCurrentLine.charAt(1)) {
				case 'r':
					format.rJust = true;
					format.lJust = false;
					format.cJust = false;
					format.tCenter = false;
					format.indent = false;
					format.multIndent = false;
					break;
				case 'c':
					format.cJust = true;
					format.lJust = false;
					format.rJust = false;
					format.tCenter = false;
					format.indent = false;
					format.multIndent = false;
					break;
				case 'l':
					format.rJust = false;
					format.cJust = false;
					format.lJust = true;
					format.tCenter = false;
					format.indent = false;
					//format.multIndent = false;
					break;
				case 't':
					format.tCenter = true;
					format.lJust = false;
					format.rJust = false;
					format.cJust = false;
					format.indent = false;
					format.multIndent = false;
					break;
				case 'n': 
					format.removeIndent = true;
					format.indent = false;
					format.multIndent = false;
					break;
				case 'd': 
					format.doubleSpace = true;
					format.singleSpace = false;
					// temp = doubleSpace(temp);
					break;
				case 's': 
					format.singleSpace = true;
					format.doubleSpace = false;
					// temp = singleSpace(temp);
					break;
				case 'i': 
					format.indent = true;
					format.multIndent = false;
					format.removeIndent = false;
					// temp = indent(temp);
					break;
				case 'b': 
					format.multIndent = true;
					format.indent = false;
					format.removeIndent = false;
					// temp = multipleIndent(temp);
					break;
				case '2': 
					format.dCol = true;
					format.sCol = false;
					// temp = twoColumns(temp);
					break;
				case '1': 
					format.sCol = true;
					format.dCol = false;
					// temp = oneColumn(temp);
					break;
				case 'e': 
					format.blankline = true;
					break;
				}


			}
			// time to format the string!
			else {
				// count2++;
				comSeenB4 = true;
				format.text += strCurrentLine;
			}
		}
		format.formatAndClear();

	}
}
