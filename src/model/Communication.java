package model;

import java.io.Serializable;

public class Communication  implements Serializable{
	private int id;
	private int user_id;
	private int event_id;
	private String posted_time;
	private String content;

	public Communication(int id, int user_id, int event_id, String posted_time, String content) {
		this.id = id;
		this.user_id = user_id;
		this.event_id = event_id;
		this.posted_time = posted_time;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public String getPosted_time() {
		return posted_time;
	}

	public void setPosted_time(String posted_time) {
		this.posted_time = posted_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
