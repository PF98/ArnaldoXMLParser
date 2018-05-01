package parser;

import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class XMLFile {
	private static final String ERROR_MESSAGE = "There's been an error." + System.lineSeparator() + "Error message: %s";
	private static final String DEFAULT_ENTRY_NAME = "entry";
	private static final String DEFAULT_MAIN_TAG_NAME = "table";
	private int lineNumber;
	private String fileName;
	private String entryName;
	private ArrayList<String> titles;
	
	private XMLOutputFactory output;
	private XMLStreamWriter writer;
	private boolean opened;

	public XMLFile(String fileName) {
		this.fileName = fileName;
		lineNumber = 0;
		entryName = DEFAULT_ENTRY_NAME;
		opened = false;
		titles = new ArrayList<String>();
	}
	
	public boolean open(String mainTagName) {
		System.out.println("Starting writing on file...");
		output = XMLOutputFactory.newInstance();
		try {
			writer = output.createXMLStreamWriter(new FileOutputStream(fileName), "utf-8");
			writer.writeStartDocument("utf-8","1.0");
			writer.writeStartElement(mainTagName);
		}
		catch (Exception e) {
			System.out.println(String.format(ERROR_MESSAGE, e.getLocalizedMessage()));
			return false;
		}
		opened = true;
		return true;
	}
	
	public boolean open() {
		return open(DEFAULT_MAIN_TAG_NAME);
	}
	
	public boolean writeLine(ArrayList<String> values) {
		if (!opened) {
			System.out.println("You first need to open the file (XMLFile.open method)");
			return false;
		}
		
		if (values.size() != titles.size()) {
			System.out.println("Error: wrong size for the values parameter");
			return false;
		}
		
		try {
			writer.writeStartElement(entryName);			
			writer.writeAttribute("id", Integer.toString(getLineNumber()));
			for (int i = 0; i < values.size(); i++) {
				writer.writeStartElement(titles.get(i));
				writer.writeCharacters(values.get(i));
				writer.writeEndElement();
			}
			writer.writeEndElement();
		} catch (Exception e) {
			System.out.println(String.format(ERROR_MESSAGE, e.getLocalizedMessage()));
			return false;
		}
		return true;
	}
	
	public boolean close() {
		try {
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println(String.format(ERROR_MESSAGE, e.getLocalizedMessage()));
			return false;
		}
		System.out.println("Successfully finished writing!");
		return true;
	}
	
	public boolean setTitles(ArrayList<String> titles) {
		if (opened) {
			System.out.println("Error: the titles must be set before opening the file");
			return false;
		}
		this.titles = titles;
		return true;
	}
	
	public boolean setEntryName(String newEntryName) {
		if (opened) {
			System.out.println("Error: the entry name must be set before opening the file");
			return false;
		}
		this.entryName = newEntryName;
		return true;
	}
	
	private int getLineNumber() {
		return lineNumber++;
	}
}
