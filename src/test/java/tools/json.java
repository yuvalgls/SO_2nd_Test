package tools;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import testData.CustomerOrder;
import com.google.gson.Gson;

public class Json {
	JSONObject object = new JSONObject();
	public static Logger logger = Logger.getLogger(Json.class);

	public Json(int customerId) {
		object.put("customer_id", customerId);
	}

	private void addToJSONObject(String str1, JSONObject object) {
		this.object.append(str1, (Object) object);
	}

	public String getJsonAsString() {
		return object.toString();
	}

	public void buildCustomerJson(ArrayList<CustomerOrder> list) {
		for (int index = 0; index < list.size(); index++) {
			addToJSONObject("data", buildJsonFromObject(list.get(index)));
		}
	}

	public static JSONObject preperCustomerOrderJsonObject(int customerID,
			Object order) {
		JSONObject newObj = new JSONObject();
		newObj.put("customer_id", customerID);
		newObj.append("data", buildJsonFromObject(order));
		return newObj;

	}

	public static JSONObject buildJsonFromObject(Object order) {
		Gson gson = new Gson();
		JSONObject request = null;
		String jsonString = gson.toJson(order);
		try {
			request = new JSONObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}
}