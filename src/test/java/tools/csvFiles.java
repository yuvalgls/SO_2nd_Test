package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class csvFiles {
	public static List<String> readCSVFileToArray(String filePath) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<String> list = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				list.addAll(Arrays.asList(line.split(cvsSplitBy)));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void saveToCsvFile(String sFileName, String responseCode,
			String sentDate, long requestResomnseTime) {
		try {
			FileWriter writer = new FileWriter("csvFiles\\" + sFileName);

			writer.append(responseCode);
			writer.append(',');
			writer.append(sentDate);
			writer.append(',');
			writer.append(String.valueOf(requestResomnseTime));
			writer.append('\n');

			// generate whatever data you want

			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
