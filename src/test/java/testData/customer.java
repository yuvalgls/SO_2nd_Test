package testData;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import tests.SOTest;

public class Customer {
	public static Logger logger = Logger.getLogger(Customer.class);
	private static int totalNumberOfCustomers = 0;
	private int customerId;
	private String json;
	private ArrayList<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();

	public Customer(String filePath) {
		logger.info("Creating new customer");
		this.customerId = ++totalNumberOfCustomers;
		logger.info("New customer created, customer id = " + this.customerId);
		logger.info("Starting to read the customer orders from the csv file");
		customerOrders = tools.CsvFiles.createCustomerOrdersList(filePath);
		logger.info(customerOrders.size() + " orders were found to customer #"
				+ customerId);
		logger.info("Creating the full customer json");
		tools.Json json = new tools.Json(customerId);
		json.buildCustomerJson(customerOrders);
		this.json = json.getJsonAsString();
		logger.info("Full customer json: " + this.json);
	}

	public int getCustomerID() {
		return this.customerId;
	}

	public String getJson() {
		return this.json;
	}

	private void sendCustomerOrder(int customerOrderIndex) {
		logger.info("Sending customer #" + this.customerId + " order #"
				+ customerOrders.get(customerOrderIndex).getId());
		tools.WebRequests.sendPostAndSaveData(SOTest.URL, (tools.Json
				.buildJsonFromObject(customerOrders.get(customerOrderIndex))
				.toString()));
	}

	public void sendAllCustomerOrders() {
		logger.info("Sending all customer orders, total of "
				+ this.customerOrders.size() + ", " + SOTest.THREAD_POOL_SIZE
				+ " at a time");
		for (int i = 0; i < this.customerOrders.size(); i++) {
			sendCustomerOrder(i);
		}
	}
}
