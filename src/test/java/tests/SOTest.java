package tests;

import java.util.List;

import org.junit.Test;

import testData.customer;

public class SOTest {

	private String URL = "https://sandbox.simpleorder.com/automation-test";

	@Test
	public void letsTest() {
		List<String> csv = tools.csvFiles
				.readCSVFileToArray("csvFiles/customerData.csv");
		customer customer1 = new customer(csv);
		tools.WebRequests.sendPost(URL, customer1.getJson());
	}
}
