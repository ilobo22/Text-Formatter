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
					count1 = 0;
					count2 = 0;
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
					case 'c': temp = centerJustify(temp);
						break;
					case 'l': temp = leftJustify(temp);
						break;
					case 't': temp = centerTitle(temp);
						break;
					case 'n': //temp = removeIndent(temp);
						break;
					case 'd': temp = doubleSpace(temp);
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
				finalVal+= temp;
				finalVal += "\n"; //reset for a new string to be formatted
			}	
		}		
	}
	
	//right justifies 
	public String rightJustify(String str) {
		int limit = 80;
		ArrayList<String> strings = new ArrayList<String>();
		String finalString = "";
		
		//breaks strings into 80 character substrings and stores them
		while(str.length() > limit)
		{
			finalString = "";
			strings.add(str.substring(0, limit));
			str = str.substring(limit + 1, str.length());
		}
		strings.add(str);
		
		//combines substrings into one string
		for(int i = 0; i < strings.size(); i++)
		{
			if(i < strings.size() - 1)
				finalString += String.format("%80s", strings.get(i)) + "\n";
			else
				finalString += String.format("%80s", strings.get(i));
		}
		return finalString;
	}
	
	//center justifies the string
	public String centerJustify(String str) {
		String finalString = "";
		int spaces = 0;
		int limit = 80;
		
		//line is under 80 characters
		if(str.length() < limit)
		{
			String[] wordList = str.split("\\s+");
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
		}
		
		//line is over 80 characters
		else
		{
			ArrayList<String> strings = new ArrayList<String>();
			finalString = "";
			
			//breaks strings into 80 character substrings and stores them
			while(str.length() > limit)
			{
				strings.add(str.substring(0, limit+1));
				str = str.substring(limit + 1, str.length());
			}
			strings.add(str);
			
			//combines substrings into one string
			for(int i = 0; i < strings.size(); i++)
			{
				if(i < strings.size() - 1)
					finalString += String.format("%80s", strings.get(i)) + "\n";
				else
				{
					System.out.println(strings.get(i));
					String temp = "";
					String[] wordList = strings.get(i).split("\\s+");
					while(temp.length() <= limit-wordList[wordList.length-1].length())
					{
						temp = "";
						for(int j = 0; j < wordList.length-1; j++)
						{
							temp += wordList[j];
							for(int k = 0; k < spaces; k++)
							{
								temp += " ";
							}
						}
						temp += wordList[wordList.length-1];
						spaces++;
					}
					finalString += temp;
				}
			}
		}
		return finalString;
	}
	
	//left justifies
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
	
	//centers a title
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
	
	public String doubleSpace(String str)
	{
		return str += "\n";
	}
	
	//returns the final string
	public String toString() {
		return finalVal;
	}

}
