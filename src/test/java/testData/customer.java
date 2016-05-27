package testData;

import java.util.ArrayList;

import tests.SOTest;

public class customer {
	private static int totalNumberOfCustomers = 0;
	private int customerId;
	private String json;

	public customer(String filePath) {
		SOTest.logger.info("Creating new customer");
		this.customerId = ++totalNumberOfCustomers;
		ArrayList<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
		customerOrders = tools.csvFiles.createCustomerOrdersList(filePath);
		SOTest.logger.info(customerOrders.size()
				+ " orders were found to customer#" + customerId);
		SOTest.logger.info("Building the json");
		tools.json json = new tools.json(customerId);
		json.buildCustomerJson(customerOrders);
		this.json = json.getJsonAsString();
		SOTest.logger.info("json: " + this.json);
	}

	public int getCustomerID() {
		return this.customerId;
	}

	public String getJson() {
		return this.json;
	}

	public void parseAndSendCustomOrders(String url) {
		SOTest.logger.info("sending the customer data to " + url);
		tools.json.parseAndSendCustomerJson(url, this.json);
	}
}
