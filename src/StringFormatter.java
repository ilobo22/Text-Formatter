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
				text = rightJustify(text);
			}
			if (cJust) {
				text = centerJustify(text) + "\n";
			}
			if (lJust) {

				if (multIndent) {
					text = multipleIndent(text);
				} else if (indent) {
					text = indent(text);
				} else {
					text = leftJustify(text);
				}

			}
			if (tCenter) {
				text = centerTitle(text) + "\n";
			}
			if (blankline) {
				if (indent) {
					text = "     " + text;
				}
				text = "\n" + text;
			}
			if (dCol) {

				if (indent && !blankline) {
					text = "     " + text;
				}
				text = twoColumn(text);
			}
			if (doubleSpace) {
				
				if(sCol) {
					text = text.replaceAll("(.{80})", "$1\n\n");
				} else {
					text = doubleSpace(text);
				}
				
			}

			finalVal += text;

			text = "";
		}
	}

	private String finalVal;

	public String twoColumn(String str) {
		int size = str.length();
		int split = size / 2;

		String first = "";
		String second = "";
		String finalStr = "";

		first = str.substring(0, split);
		second = str.substring(split, size);

		int count = 0;
		int trackingOne = 0;
		int trackingTwo = 0;
		while (trackingOne < first.length() && trackingTwo < second.length()) {
			while (count < 35 && trackingOne < first.length()) {
				finalStr += first.charAt(trackingOne);
				count++;
				trackingOne++;
			}
			if (count != 34) {
				for (int i = 0; i <= (34 - count); i++) {
					finalStr += " ";
				}
			}
			count = 0;
			finalStr += "          ";
			while (count < 35 && trackingTwo < second.length()) {
				finalStr += second.charAt(trackingTwo);
				count++;
				trackingTwo++;
			}
			finalStr += "\n";
			count = 0;
		}
		return finalStr;
	}

	public String doubleSpace(String str) {


		String finalString = "";

		finalString = str.replaceAll("(.{80})", "$1\n");

		return finalString;
	}

	public String multipleIndent(String str) {

		String finalString = "";

		int limit = 70;
		ArrayList<String> strings = new ArrayList<String>();

		while (str.length() > limit) {
			strings.add(str.substring(0, limit));
			str = str.substring(limit, str.length());
		}
		strings.add(str);

		for (int i = 0; i < strings.size(); i++) {
			if (i < strings.size() - 1)
				finalString += "          " + strings.get(i) + "\n";
			else
				finalString += "          " + strings.get(i);
		}

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

		// line is under 80 characters
		if (str.length() < limit) {
			String[] wordList = str.split("\\s+");
			while (finalString.length() <= limit - wordList[wordList.length - 1].length()) {
				finalString = "";
				for (int i = 0; i < wordList.length - 1; i++) {
					finalString += wordList[i];
					for (int j = 0; j < spaces; j++) {
						finalString += " ";
					}
				}
				finalString += wordList[wordList.length - 1];
				spaces++;
			}
		}

		// line is over 80 characters
		else {
			ArrayList<String> strings = new ArrayList<String>();
			finalString = "";

			// breaks strings into 80 character substrings and stores them
			while (str.length() > limit) {
				strings.add(str.substring(0, limit + 1));
				str = str.substring(limit + 1, str.length());
			}
			strings.add(str);

			// combines substrings into one string
			for (int i = 0; i < strings.size(); i++) {
				if (i < strings.size() - 1)
					finalString += String.format("%80s", strings.get(i)) + "\n";
				else {
					String temp = "";
					String[] wordList = strings.get(i).split("\\s+");
					while (temp.length() <= limit - wordList[wordList.length - 1].length()) {
						temp = "";
						for (int j = 0; j < wordList.length - 1; j++) {
							temp += wordList[j];
							for (int k = 0; k < spaces; k++) {
								temp += " ";
							}
						}
						temp += wordList[wordList.length - 1];
						spaces++;
					}
					finalString += temp;
				}
			}
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
			str = str.substring(limit, str.length());
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

		// breaks strings into 80 character substrings and stores them
		while (str.length() > limit) {
			strings.add(str.substring(0, limit));
			str = str.substring(limit, str.length());
		}
		strings.add(str);

		// combines substrings into one string
		for (int i = 0; i < strings.size(); i++) {
			if (i < strings.size() - 1)
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
		for (int i = 0; i < numSpaces / 2; i++)
			spacePadding += " ";
		ArrayList<String> strings = new ArrayList<String>();
		String finalString = "";

		// breaks strings into 80 character substrings and stores them
		while (str.length() > limit) {
			strings.add(str.substring(0, limit));
			str = str.substring(limit + 1, str.length());
		}
		strings.add(str);

		// combines strings into one string
		for (int i = 0; i < strings.size(); i++) {
			if (i < strings.size() - 1)
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
					if (format.dCol) {
						format.rJust = false;
					} else {
						format.rJust = true;
					}
					format.lJust = false;
					format.cJust = false;
					format.tCenter = false;
//					format.indent = false;
//					format.multIndent = false;
					break;
				case 'c':
					if (format.dCol) {
						format.cJust = false;
					} else {
						format.cJust = true;
					}
					format.lJust = false;
					format.rJust = false;
					format.tCenter = false;
//					format.indent = false;
//					format.multIndent = false;
					break;
				case 'l':
					if (format.dCol) {
						format.lJust = false;
					} else {
						format.lJust = true;
					}
					format.rJust = false;
					format.cJust = false;
					format.tCenter = false;
//					format.indent = false;
//					format.multIndent = false;
					break;
				case 't':
					if (format.dCol) {
						format.tCenter = false;
					} else {
						format.tCenter = true;
					}
					format.lJust = false;
					format.rJust = false;
					format.cJust = false;
//					format.indent = false;
//					format.multIndent = false;
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
					format.rJust = false;
					format.cJust = false;
					format.lJust = false;
					format.tCenter = false;
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
