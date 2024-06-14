package model;

import java.io.Serializable;

public class Comment implements Serializable{
	private int id;
	private int user_id;
	private String comment;

	public Comment(int id, int user_id, String comment) {
		this.id = id;
		this.user_id = user_id;
		this.comment = comment;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}



}
