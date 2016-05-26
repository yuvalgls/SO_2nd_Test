package testData;

import java.util.ArrayList;
import java.util.List;

public class customer {
	private static int totalNumberOfCustomers = 0;
	private String json;
	int CSV_SIZE = 2;

	private int customer_id;
	private ArrayList<Order> data = new ArrayList<Order>();

	public customer(List<String> orderList) {
		this.customer_id = ++totalNumberOfCustomers;
		fillOrders(orderList);
		json = tools.json.buildCustomerJson(data, customer_id);
	}

	public int getCustomerID() {
		return this.customer_id;
	}

	public String getJson() {
		return this.json;
	}

	private void fillOrders(List<String> orderList) {
		int countIndex = 0;
		do {
			data.add(new Order(orderList.subList(countIndex, countIndex
					+ CSV_SIZE)));
			countIndex += CSV_SIZE;
		} while (countIndex < orderList.size());

	}
}
