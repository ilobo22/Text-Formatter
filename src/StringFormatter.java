import java.io.BufferedReader;
import java.io.IOException;

public class StringFormatter {
	
	public StringFormatter(BufferedReader file) throws IOException {
		
		String strCurrentLine;
		while ((strCurrentLine = file.readLine()) != null) {

			System.out.println(strCurrentLine);
		}
		
	}

}
