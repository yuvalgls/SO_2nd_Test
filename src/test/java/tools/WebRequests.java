package tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import tests.SOTest;

public class WebRequests {
	public static ExecutorService pool = Executors
			.newFixedThreadPool(SOTest.THREAD_POOL_SIZE);
	public static Logger logger = Logger.getLogger(WebRequests.class);

	public static void sendPostAndSaveData(final String url, final String body) {
		sendit(url, body);
	}

	public static void sendit(final String url, final String body) {
		if (((ThreadPoolExecutor) pool).getActiveCount() == 0) {
			pool = Executors.newFixedThreadPool(SOTest.THREAD_POOL_SIZE);
		}
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
					BufferedReader br = new BufferedReader(
							new InputStreamReader(
									method.getResponseBodyAsStream()));
					String readLine;
					while (((readLine = br.readLine()) != null)) {
						// System.err.println(readLine);
						logger.debug("Answer from server : \n" + readLine);
					}
					requestResomnseTime = System.currentTimeMillis()
							- startTime;
					method.getResponseBodyAsStream();
					method.releaseConnection();
				} catch (Exception e) {
				}
				logger.info(body + " recived responseCode: " + responseCode
						+ " in " + requestResomnseTime + " ms from " + url);
				tools.CsvFiles.saveToCsvFile(tools.Time.getCurrectDate()
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
					tools.Time.sleep(1000);
				} while (currentPoolSize > 0);
			}
		} catch (Exception e) {
		}
	}

	public static void killThreadPool() {
		pool.shutdownNow();
	}
}