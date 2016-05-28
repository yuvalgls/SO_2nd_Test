package testData;

import java.util.List;

public class CustomerOrder {
	private int id;
	private String name = "";

	public CustomerOrder(List<String> list) {
		try {
			this.id = Integer.valueOf(list.get(0));
			this.name = list.get(1);
		} catch (Exception e) {
		}
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	
}