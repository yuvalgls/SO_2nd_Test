package tools;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import testData.Order;

public class json {
	static int csvSize = 2;

	public static String buildCustomerJson(ArrayList<Order> ordersList,
			int customerId) {
		JSONObject object = new JSONObject();

		for (int index = 0; index < ordersList.size(); index++) {
			object.append("data", buildJsonFromObject(ordersList.get(index)));
		}

		object.append("customer_id", customerId);
		System.out.println(object.toString());
		String body = "{\"data\": [{\"id\":123,\"name\":\"mush\"},{\"id\":\"123\",\"name\":\"\"}],\"customer_id\":1}";
		System.err.println(body);
		return object.toString();
	}

	public void test() {

	}

	private static JSONObject buildJsonFromObject(Object order) {
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
