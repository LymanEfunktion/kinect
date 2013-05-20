package de.rocovomo.util.hmm.input;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVBuffer
{
	private List<List<String>> csvArray;
	
	public CSVBuffer(Reader reader) {
		CSVReader csvReader = new CSVReader(reader);
		csvArray = new ArrayList<List<String>>();
		String[] array = csvReader.getLine();
		while (array != null) {
			csvArray.add(Arrays.asList(array));
			array = csvReader.getLine();
		}
	}
	
	public List<List<String>> getContent() {
		return csvArray;
	}
}
