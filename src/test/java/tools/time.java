package tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class time {
	public static String getCurrectTime() {
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar
				.getInstance().getTime());
	}

	public static String getCurrectDate() {
		return new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance()
				.getTime());
	}
}
