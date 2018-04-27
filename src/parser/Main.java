package parser;


public class Main {

	public static void main(String[] args){
		CSVReader reader = new CSVReader("calendar.txt");
		XMLValues xmlVal;
		
		reader.readNextLine();
		xmlVal = new XMLValues(reader.getWholeLine());
		
		while (reader.readNextLine()) {
			if (!xmlVal.newLine()) {
				System.out.println("Error while reading the file");
				return;
			}
			while (reader.hasNextInLine()) {
				xmlVal.addValue(reader.getNext());
			}
			
		}
		
		XMLFile outputFile = new XMLFile("output.xml");
		outputFile.write(xmlVal);
		
		
	}
}
