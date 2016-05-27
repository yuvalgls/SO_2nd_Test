package testData;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import tests.SOTest;

public class Customer {
	public static Logger logger = Logger.getLogger(Customer.class);
	private static int totalNumberOfCustomers = 0;
	private int customerId;
	private String json;

	public Customer(String filePath) {
		logger.info("Creating new customer");
		this.customerId = ++totalNumberOfCustomers;
		ArrayList<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
		customerOrders = tools.CsvFiles.createCustomerOrdersList(filePath);
		logger.info(customerOrders.size() + " orders were found to customer#"
				+ customerId);
		logger.info("Building the json");
		tools.Json json = new tools.Json(customerId);
		json.buildCustomerJson(customerOrders);
		this.json = json.getJsonAsString();
		logger.info("json: " + this.json);
	}

	public int getCustomerID() {
		return this.customerId;
	}

	public String getJson() {
		return this.json;
	}

	public void parseAndSendCustomOrders(String url) {
		logger.info("sending the customer data to " + url + " "
				+ SOTest.THREAD_POOL_SIZE + " at a time");
		tools.Json.parseAndSendCustomerJson(url, this.json);
	}
}
