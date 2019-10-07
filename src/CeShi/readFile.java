package CeShi;

import java.io.*;

public class readFile {
	
	public static void outPut() throws Exception{
		
		String fileName ="state.txt";
	    FileReader fileReader = new FileReader(fileName);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    String line =bufferedReader.readLine();
	    while (line!=null){
	        System.out.print(line);
	        line = bufferedReader.readLine();
	    }
	    System.out.println();
	    bufferedReader.close();
	    fileReader.close();
		
	}
}
