package tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

import testData.CustomerOrder;
import tests.SOTest;

public class csvFiles {

	public static ArrayList<CustomerOrder> createCustomerOrdersList(
			String filsPath) {
		SOTest.logger.info("Reading csv file " + filsPath);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		ArrayList<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
		try {
			int lineIndex = 0;
			br = new BufferedReader(new FileReader(filsPath));
			while ((line = br.readLine()) != null) {
				lineIndex++;
				// json.addToJson(Arrays.asList(line.split(cvsSplitBy)));
				customerOrders.add(new CustomerOrder(Arrays.asList(line
						.split(cvsSplitBy))));
			}
			br.close();
			SOTest.logger.info("there were " + lineIndex + " lines");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerOrders;
	}

	public static void saveToCsvFile(String sFileName, CharSequence responseCode,
			String data, long requestResomnseTime) {
		SOTest.logger.info("Exporting to : " + sFileName);
		try {
			FileWriter writer = new FileWriter("csvFiles\\" + sFileName, true);
			writer.append(responseCode);
			writer.append(',');
			writer.append(String.valueOf(requestResomnseTime));
			writer.append(',');
			writer.append(data);
			writer.append('\n');
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			saveToCsvFile(sFileName, responseCode, data, requestResomnseTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
