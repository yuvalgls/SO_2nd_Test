package tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class WebRequests {
	public static ExecutorService pool = Executors.newFixedThreadPool(5);

	public static void sendPostAndSaveData(final String url, final String body) {
		sendit(url, body);

	}

	public static void exportData(String requestJson, String responseCode,
			long requestResomnseTime) {
		tools.csvFiles.saveToCsvFile("CustomerDataAns.csv", responseCode,
				requestJson, requestResomnseTime);

	}

	public static void sendit(final String url, final String body) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					// System.out.println("Sending data");
					// HttpClient client = new HttpClient();
					// client.getParams().setParameter("http.useragent",
					// "Test Client");
					//
					// PostMethod method = new PostMethod(url);
					//
					// StringRequestEntity requestEntity = new
					// StringRequestEntity(
					// body, "application/json", "UTF-8");
					// method.setRequestEntity(requestEntity);
					// client.executeMethod(method);
					// method.getResponseBodyAsStream();
					// System.out.println(method.getResponseBodyAsString());
					// method.releaseConnection();

					tools.time.sleep(1000);
					// SOTest.logger.info("sending " + body);
					// String responseCode = null;
					// long requestResomnseTime = 0;
					// try {
					// CloseableHttpClient httpClient =
					// HttpClientBuilder.create()
					// .build();
					// HttpPost request = new HttpPost(url);
					// StringEntity params = new StringEntity(body);
					// request.addHeader("content-type", "application/json");
					// request.setEntity(params);
					// long startTime = System.currentTimeMillis();
					// HttpResponse result = httpClient.execute(request);
					// requestResomnseTime = System.currentTimeMillis()
					// - startTime;
					// responseCode = result.getStatusLine().toString();
					// SOTest.logger.info("responseCode: " + responseCode +
					// " in "
					// + requestResomnseTime + " ms");

					// tools.csvFiles.saveToCsvFile(tools.time.getCurrectDate()
					// + ".csv", responseCode, body, requestResomnseTime);

				} catch (Exception e) {
					System.out.println(e);
				}

			}
		};
		pool.execute(r);

	}
	// @Override
	// public void run() {
	// tools.time.sleep(1000);
	// SOTest.logger.info("sending " + body);
	// String responseCode = null;
	// long requestResomnseTime = 0;
	// try {
	// CloseableHttpClient httpClient = HttpClientBuilder.create()
	// .build();
	// HttpPost request = new HttpPost(url);
	// StringEntity params = new StringEntity(body);
	// request.addHeader("content-type", "application/json");
	// request.setEntity(params);
	// long startTime = System.currentTimeMillis();
	// HttpResponse result = httpClient.execute(request);
	// requestResomnseTime = System.currentTimeMillis()
	// - startTime;
	// responseCode = result.getStatusLine().toString();
	// SOTest.logger.info("responseCode: " + responseCode + " in "
	// + requestResomnseTime + " ms");
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	// tools.csvFiles.saveToCsvFile(tools.time.getCurrectDate()
	// + ".csv", responseCode, body, requestResomnseTime);
	// }

}