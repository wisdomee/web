package util;

import bean.RoomBean;

public class Sort {
	public static void mergeSort(RoomBean[] roomArr, int left, int right) {
		int mid = (left + right) / 2;
		
		if (left + 1 == right) {
			return;
		}
		
		mergeSort(roomArr, left, mid);
		mergeSort(roomArr, mid, right);
		
		int idxM = 0;
		int idxL = left;
		int idxR = mid;
		int size = right - left;
		RoomBean[] sorted = new RoomBean[size];
		while (idxL < mid && idxR < right) {
			if (roomArr[idxL].getUserNum() <= roomArr[idxR].getUserNum()) {
				sorted[idxM++] = roomArr[idxL++];
			} else {
				sorted[idxM++] = roomArr[idxR++];
			}
		}
		while (idxL < mid) {
			sorted[idxM++] = roomArr[idxL++];
		}
		while (idxR < right) {
			sorted[idxM++] = roomArr[idxR++];
		}
		
		idxM = 0;
		for (int idx = left; idx < right; idx++) {
			roomArr[idx] = sorted[idxM++];
		}
	}
}
