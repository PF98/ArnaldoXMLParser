package parser;


public class Main {

	public static final String FILENAME = "calendar";
	
	public static void main(String[] args){
		CSVReader reader = new CSVReader(String.format("input/%s.txt", FILENAME));
		XMLFile outputFile = new XMLFile(String.format("output/%s.xml", FILENAME));
		
		reader.readTitleLine();
		outputFile.setTitles(reader.getWholeTitleLine());
		
		outputFile.open(FILENAME);
		
		while (reader.readNextLine()) {
			outputFile.writeLine(reader.getWholeLine());
		}
		
		outputFile.close();
		//outputFile.write(xmlVal, FILENAME);
		
		
	}
}
