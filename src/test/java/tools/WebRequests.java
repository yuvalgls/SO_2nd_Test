package tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import tests.SOTest;

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
				int responseCode = 0;
				long requestResomnseTime = 0;
				try {
					HttpClient client = new HttpClient();
					PostMethod method = new PostMethod(url);
					StringRequestEntity requestEntity = new StringRequestEntity(
							body, "application/json", "UTF-8");
					method.setRequestEntity(requestEntity);
					long startTime = System.currentTimeMillis();
					responseCode = client.executeMethod(method);
					requestResomnseTime = System.currentTimeMillis()
							- startTime;
					method.getResponseBodyAsStream();
					method.releaseConnection();
				} catch (Exception e) {
				}
				SOTest.logger.info(body + " recived responseCode: "
						+ responseCode + " in " + requestResomnseTime + " ms");
				tools.csvFiles.saveToCsvFile(tools.time.getCurrectDate()
						+ ".csv", String.valueOf(responseCode), body,
						requestResomnseTime);
			}
		};
		pool.execute(r);

	}

	public static void waitForThreadsToFinish() {
		int currentPoolSize;
		int trys = 0;
		try {
			if (trys < 10) {
				do {
					trys++;
					currentPoolSize = ((ThreadPoolExecutor) pool)
							.getActiveCount();
					tools.time.sleep(1000);
				} while (currentPoolSize > 0);
			}
		} catch (Exception e) {

		}
	}
}