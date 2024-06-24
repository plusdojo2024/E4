package model;

import java.io.Serializable;

public class Chat implements Serializable {
	private String user_name;
	private int iconId;
	private String content;

	public Chat(String user_name, int iconId, String content) {
		this.user_name = user_name;
		this.iconId = iconId;
		this.content = content;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
