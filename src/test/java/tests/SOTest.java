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
	public static String EXPORT_FILE_NAME = tools.Time.getCurrectDate();
	public static String URL = "";
	public static int THREAD_POOL_SIZE = 1;
	public static String CSV_FILE_PATH = "";

	@SuppressWarnings("static-access")
	public SOTest(String url, String threadPool, String csvFilePath,
			String csvExportFileName) {
		this.URL = url;
		if (Integer.valueOf(threadPool) > 0) {
			this.THREAD_POOL_SIZE = Integer.valueOf(threadPool);
		}
		this.CSV_FILE_PATH = csvFilePath;
		this.EXPORT_FILE_NAME = csvExportFileName;
	}

	@Parameters
	public static Collection<String[]> addedNumbers() {
		return Arrays.asList(new String[][] {
				{ "https://sandbox.simpleorder.com/automation-test", "10",
						"csvFiles/customerData.csv", "1stTestResultsFile" },
				{ "https://sandbox.simpleorder.com/automation-test", "5",
						"csvFiles/customerData1.csv", "2ndTestResultsFile" } });
	}

	@Test
	public void letsTest() {
		// build the customer and read its orders from the CSV file
		Customer customer1 = new Customer(CSV_FILE_PATH);
		// send all of the customer orders to the server
		customer1.sendAllCustomerOrders();
		// if the test finish running before all threads do it will kill the
		// threads
		tools.WebRequests.waitForThreadsToFinish();
		// kill the thread pool for the next tests if not it will send in the
		// same rate as the 1st one
		tools.WebRequests.killThreadPool();
		logger.info("Done, please view the log file under the \"logs\" dir");
	}
}
