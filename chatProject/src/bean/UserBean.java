package bean;

import util.TimeParser;

public class UserBean {
	private String id;
	private String pw;
	private String nickName;
	private int lastAccessTime;
			
	public UserBean() {
		super();
		this.id = "";
		this.pw = "";
		this.lastAccessTime = TimeParser.parse(System.currentTimeMillis());
	}
	public UserBean(String id, String pw, String nickName) {
		this.id=id;
		this.pw=pw;
		this.nickName=nickName;
		this.lastAccessTime = TimeParser.parse(System.currentTimeMillis());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(int lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", pw=" + pw + "]";
	}
}