package tools;

import java.util.ArrayList;

import org.json.JSONObject;

import testData.CustomerOrder;
import com.google.gson.Gson;

public class json {
	JSONObject object = new JSONObject();

	public json(int customerId) {
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

	public static void parseAndSendCustomerJson(String url, String json) {
		int customer_id = 0;
		org.json.JSONArray data = null;
		try {
			JSONObject obj = new JSONObject(json);
			data = obj.getJSONArray("data");
			customer_id = obj.getInt("customer_id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Object o : data) {
			JSONObject newObj = new JSONObject();
			newObj.put("customer_id", customer_id);
			newObj.append("data", o);
			tools.WebRequests.sendPostAndSaveData(url, newObj.toString());
		}
		tools.WebRequests.waitForThreadsToFinish();
	}
}