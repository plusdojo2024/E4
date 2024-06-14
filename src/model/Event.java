package model;

import java.io.Serializable;

public class Event implements Serializable{
	int id;
	String event_name;
	String event_description;
	String holding_schedule;
	int least_count;
	int max_count;
	int prefecture_id;
	String detail_address;
	String location_name;
	int event_category;
	int holding_user_id;

	public Event(
			int id,
			String event_name,
			String event_description,
			String holding_schedule,
			int least_count,
			int max_count,
			int prefecture_id,
			String detail_address,
			String location_name,
			int event_category,
			int holding_user_id
			) {

		this.id = id;
		this.event_name = event_name;
		this.event_description = event_description;
		this.holding_schedule = holding_schedule;
		this.least_count = least_count;
		this.max_count = max_count;
		this.prefecture_id = prefecture_id;
		this.detail_address = detail_address;
		this.location_name = location_name;
		this.event_category = event_category;
		this.holding_user_id = holding_user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_description() {
		return event_description;
	}

	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}

	public String getHolding_schedule() {
		return holding_schedule;
	}

	public void setHolding_schedule(String holding_schedule) {
		this.holding_schedule = holding_schedule;
	}

	public int getLeast_count() {
		return least_count;
	}

	public void setLeast_count(int least_count) {
		this.least_count = least_count;
	}

	public int getMax_count() {
		return max_count;
	}

	public void setMax_count(int max_count) {
		this.max_count = max_count;
	}

	public int getPrefecture_id() {
		return prefecture_id;
	}

	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}

	public String getDetail_address() {
		return detail_address;
	}

	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public int getEvent_category() {
		return event_category;
	}

	public void setEvent_category(int event_category) {
		this.event_category = event_category;
	}

	public int getHolding_user_id() {
		return holding_user_id;
	}

	public void setHolding_user_id(int holding_user_id) {
		this.holding_user_id = holding_user_id;
	}



}
