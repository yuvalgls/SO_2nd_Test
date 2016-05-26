package testData;

import java.util.List;

import org.json.JSONObject;

public class Order {
	private int id;
	private String name = "";

	public Order(List<String> subList) {
		setId(Integer.valueOf(subList.get(0)));
		setName(subList.get(1));
	}

	private void setId(int id) {
		this.id = id;
	}

	private void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public JSONObject getOrdersJson() {
		JSONObject object = new JSONObject();
		object.append("id", this.name);
		object.append("name", this.name);
		return object;
	}
}
