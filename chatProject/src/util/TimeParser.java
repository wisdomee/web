package util;

import java.text.SimpleDateFormat;

public class TimeParser {
	public static int parse(long milliSecond) {
		return (int) (milliSecond / 3600000);
	}

	public static boolean isExpired(int lastAccess) {
		if ((parse(System.currentTimeMillis()) - lastAccess) > 70) {
			return true;
		}
		return false;
	}

	public static boolean isExpired(int lastAccess, int parsedTime) {
		if ((parsedTime - lastAccess) > 70) {
			return true;
		}
		return false;
	}

	public static String getsignUpDate(int seconds) {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(seconds);
	}

	public static int getLeftHour(int seconds) {
		long currentTime = System.currentTimeMillis();
		int leftHour = (int) (((seconds + 70 * 3600000) - currentTime) / 3600000);
		return leftHour;
	}
}