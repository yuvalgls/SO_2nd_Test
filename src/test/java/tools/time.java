package tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {
	public static String getCurrectTime() {
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
				.getInstance().getTime());
	}

	public static String getCurrectDate() {
		return new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance()
				.getTime());
	}

	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}