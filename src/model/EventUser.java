package model;

import java.io.Serializable;

public class EventUser implements Serializable{
	private int id;
	private int event_id;
	private int user_id;

	public EventUser(int id, int event_id, int user_id) {
		this.id = id;
		this.event_id = event_id;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
