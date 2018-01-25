package bean;

import java.util.ArrayList;

public class RoomBean {
	private static int counter;
	private String roomId;
	private ArrayList<UserBean> userList;
	private int userNum;
	
	static {
		counter = 0;
	}
	
	public RoomBean() {
		super();
		this.roomId = String.valueOf(counter++);
		this.userList = new ArrayList<UserBean>();
		this.userNum = 0;
	}
	public RoomBean(int v) { // for test
		this.roomId = String.valueOf(counter++);
		this.userNum = v;
	}

	public String getRoomId() {
		return roomId;
	}
	public ArrayList<UserBean> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<UserBean> userList) {
		this.userList = userList;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}	
	
	@Override
	public String toString() {
		return "RoomBean [RoomId=" + roomId + ", userList=" + userList + "]";
	}

}