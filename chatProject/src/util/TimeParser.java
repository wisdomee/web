package util;

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
}