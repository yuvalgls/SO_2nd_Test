package tests;

import org.apache.log4j.Logger;
import org.junit.Test;

import testData.customer;

public class SOTest {
	public static Logger logger = Logger.getLogger(SOTest.class);
	private static String URL = "https://sandbox.simpleorder.com/automation-test";

	public static void main(String[] args) {
		customer customer1 = new customer("csvFiles/customerData.csv");
		customer1.parseAndSendCustomOrders(URL);
		tools.WebRequests.waitForThreadsToFinish();
		System.out.println("Done, please view the log file");
	}

	@Test
	public void letsTest() {
		customer customer1 = new customer("csvFiles/customerData.csv");
		customer1.parseAndSendCustomOrders(URL);
		System.out.println("done");
	}
}
