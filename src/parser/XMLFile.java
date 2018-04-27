package parser;

import java.io.FileWriter;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class XMLFile {
	private String fileName;
	public XMLFile(String fileName) {
		this.fileName = fileName;
	}
	
	public boolean write(XMLValues newValues) {
		System.out.println("Starting writing on file...");
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		
		try {
			writer = output.createXMLStreamWriter(new FileWriter(fileName));
			writer.writeStartDocument();
			writer.writeStartElement("calendar");
			do {
				writer.writeStartElement("entry");
				writer.writeAttribute("id", Integer.toString(newValues.getCurrentLineCount()));
				while (newValues.hasNextInLine()) {
					writer.writeStartElement(newValues.getNextTitle());
					writer.writeCharacters(newValues.getNext());
					writer.writeEndElement();
				}
				writer.writeEndElement();
			} while (newValues.goToNextLine()); 
			writer.writeEndElement();
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println("There's been an unspecified error. " + e.getLocalizedMessage());
			return false;
		}
		System.out.println("Printed successfully!");
		return true;
	}
}
