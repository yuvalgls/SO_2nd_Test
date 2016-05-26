package tools;

import javax.xml.ws.Response;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class WebRequests {
	private static String responseCode;
	private static String sentDate = "";
	private static long requestResomnseTime;

	public static void sendPost(String url, String body) {
		// body =
		// "{\"data\": [{\"id\": 123,\"name\": \"mush\"},{\"id\": \"123\",\"name\": \"\"}],\"customer_id\": 1}";
		try (CloseableHttpClient httpClient = HttpClientBuilder.create()
				.build()) {
			HttpPost request = new HttpPost(url);
			StringEntity params = new StringEntity(body);
			request.addHeader("content-type", "application/json");
			request.setEntity(params);

			sentDate = tools.time.getCurrectDate();
			long startTime = System.currentTimeMillis();

			HttpResponse result = httpClient.execute(request);

			requestResomnseTime = System.currentTimeMillis() - startTime;
			String json = EntityUtils.toString(result.getEntity(), "UTF-8");
			responseCode = result.getStatusLine().toString();

		} catch (Exception e) {
			System.out.println(e);
		}
		exportData();
	}

	private static void exportData() {
		tools.csvFiles.saveToCsvFile(tools.time.getCurrectTime() + ".csv",
				responseCode, sentDate, requestResomnseTime);
	}
}
