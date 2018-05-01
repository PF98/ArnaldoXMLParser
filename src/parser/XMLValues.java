package parser;

import java.util.ArrayList;

public class XMLValues {
	private ArrayList<String> titleValues;
	private ArrayList<ArrayList<String>> lineValues;
	private int activeLine;
	private int activeGetCount;
	private int activeGetLineCount;
	
	public XMLValues(ArrayList<String> titleValues) {
		this.titleValues = new ArrayList<String>(titleValues);
		this.lineValues = new ArrayList<ArrayList<String>>();
		activeLine = 0;
		activeGetCount = 0;
		activeGetLineCount = 0;
	}
	
	public boolean addValue(String newValue) {
		if (lineValues.get(activeLine).size() == titleValues.size()) {
			return false;
		}
		lineValues.get(activeLine).add(newValue);
		return true;
	}
	
	public boolean newLine() {
		if (lineValues.size() != 0) {
			if (lineValues.get(activeLine).size() != titleValues.size()) {
				return false;
			}
			activeLine++;
		}
		lineValues.add(new ArrayList<String>());
		return true;
	}
	
	public String getNextTitle() {
		return titleValues.get(activeGetCount);
	}
	
	public String getNext() {
		if (activeGetCount < titleValues.size())
			return lineValues.get(activeGetLineCount).get(activeGetCount++);
		return null;
	}
	
	public boolean hasNextInLine() {
		if (activeGetCount < titleValues.size())
			return true;
		return false;
	}
	
	public boolean goToNextLine() {
		if (activeGetLineCount < lineValues.size() - 1) {
			activeGetLineCount++;
			activeGetCount = 0;
			return true;
		}
		return false;
	}
	
	public int getCurrentLineCount() {
		return activeGetLineCount;
	}
	
	public boolean isComplete() {
		if (lineValues.get(activeLine).size() == titleValues.size())
			return true;
		return false;
	}
}
