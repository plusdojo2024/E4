package model;

import java.io.Serializable;

public class Event implements Serializable{
	int id;
	String eventName;
	String eventDescription;
	public Event(int id, String eventName, String eventDescription, String holdingSchedule, int leastCount,
			int maxCount, int prefectureId, String detailAddress, String locationName, int eventCategory,
			int holdingUserId, int status) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.holdingSchedule = holdingSchedule;
		this.leastCount = leastCount;
		this.maxCount = maxCount;
		this.prefectureId = prefectureId;
		this.detailAddress = detailAddress;
		this.locationName = locationName;
		this.eventCategory = eventCategory;
		this.holdingUserId = holdingUserId;
		this.status = status;
	}
	String holdingSchedule;
	int leastCount;
	int maxCount;
	int prefectureId;
	String detailAddress;
	String locationName;
	int eventCategory;
	int holdingUserId;
    int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getHoldingSchedule() {
		return holdingSchedule;
	}
	public void setHoldingSchedule(String holdingSchedule) {
		this.holdingSchedule = holdingSchedule;
	}
	public int getLeastCount() {
		return leastCount;
	}
	public void setLeastCount(int leastCount) {
		this.leastCount = leastCount;
	}
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public int getPrefectureId() {
		return prefectureId;
	}
	public void setPrefectureId(int prefectureId) {
		this.prefectureId = prefectureId;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public int getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(int eventCategory) {
		this.eventCategory = eventCategory;
	}
	public int getHoldingUserId() {
		return holdingUserId;
	}
	public void setHoldingUserId(int holdingUserId) {
		this.holdingUserId = holdingUserId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
