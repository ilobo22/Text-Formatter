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

			}
			if (dCol) {

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
				case '2': // temp = twoColumns(temp);
					break;
				case '1': // temp = oneColumn(temp);
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
