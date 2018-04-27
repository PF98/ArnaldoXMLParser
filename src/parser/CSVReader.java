package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CSVReader {

	private String fileName;
	private File inputFile;
	Scanner in;
	
	private ArrayList<String> currentLineContent;
	
	public CSVReader(String fileName) {
		this.fileName = fileName;
		inputFile = new File(this.fileName);
		in = null;
		try {
			in = new Scanner(inputFile).useDelimiter("\\n");
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found: error message - " + e.getLocalizedMessage());
			return;
		}
		currentLineContent = new ArrayList<String>();
	}
	
	public boolean readNextLine() {
		if (in.hasNext()) {
			currentLineContent.clear();
			currentLineContent.addAll(Arrays.asList(in.next().split(",")));
			return true;
		}
		return false;
	}
	
	public ArrayList<String> getWholeLine() {
		return currentLineContent;
	}
	
	public boolean hasNextInLine() {
		if (currentLineContent.size() > 0)
			return true;
		return false;
	}
	
	public String getNext() {
		String temp = currentLineContent.get(0);
		currentLineContent.remove(0);
		return temp;
	}
	

	
}
