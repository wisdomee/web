package bean;

public class ChatBean {
	private String chatId;
	private String userNick;
	private String message;
	
	public ChatBean() {}
	public ChatBean(String chatId, String userNick, String message) {
		this.chatId = chatId;
		this.userNick = userNick;
		this.message = message;
	}
	
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userId) {
		this.userNick = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "ChatBean [userNick=" + userNick + ", message=" + message + "]";
	}	
}