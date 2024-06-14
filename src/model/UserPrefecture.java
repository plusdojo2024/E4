package model;

import java.io.Serializable;

public class UserPrefecture implements Serializable{
	private int id;
	private int user_id;
	private int prefecture_id;

	public UserPrefecture(int id, int user_id, int prefecture_id) {
		this.id = id;
		this.user_id = user_id;
		this.prefecture_id = prefecture_id;
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

	public int getPrefecture_id() {
		return prefecture_id;
	}

	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}


}
