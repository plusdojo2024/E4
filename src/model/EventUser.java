package model;

import java.io.Serializable;

public class EventUser implements Serializable{
	private int id;
	private int event_id;
	private int user_id;
	private int participation_status;
	public EventUser(int id, int event_id, int user_id, int participation_status) {
		super();
		this.id = id;
		this.event_id = event_id;
		this.user_id = user_id;
		this.participation_status = participation_status;
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
	public int getParticipation_status() {
		return participation_status;
	}
	public void setParticipation_status(int participation_status) {
		this.participation_status = participation_status;
	}
}
