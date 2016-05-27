package tests;

import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import testData.Customer;

@RunWith(Parameterized.class)
public class SOTest {
	public static Logger logger = Logger.getLogger(SOTest.class);
	// private static String URL =
	// "https://sandbox.simpleorder.com/automation-test";

	public static String URL = "";
	public static int THREAD_POOL_SIZE;
	public static String CSV_FILE_PATH = "";

	@SuppressWarnings("static-access")
	public SOTest(String url, String threadPool, String csvFilePath) {
		this.URL = url;
		this.THREAD_POOL_SIZE = Integer.valueOf(threadPool);
		this.CSV_FILE_PATH = csvFilePath;
	}

	@Parameters
	public static Collection<String[]> addedNumbers() {
		return Arrays.asList(new String[][] { {
				"https://sandbox.simpleorder.com/automation-test", "5",
				"csvFiles/customerData.csv" } ,{
					"https://sandbox.simpleorder.com/automation-test", "1",
					"csvFiles/customerData1.csv" }});
	}

	@Test
	public void letsTest() {
		Customer customer1 = new Customer(CSV_FILE_PATH);
		customer1.parseAndSendCustomOrders(URL);
		logger.info("Done");
	}
}
