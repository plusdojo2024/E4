package model;
import java.io.Serializable;

public class Users implements Serializable {
	private int id;
	private String mailAddress;
	private String password;
	private String name;
	private String birthDate;
	private String telNum;
	private int prefectureId;
	private int eventCategory;
	private int outdoorLevel;
	private String registerYear;
	private int evaluation;
	private int technicParam;
	private int cookParam;
	private int communicationParam;
	private int participantsAmount;
	private int hostedAmount;
	private int iconId;
	public Users(int id, String mailAddress, String password, String name, String birthDate, String telNum,
			int prefectureId, int eventCategory, int outdoorLevel, String registerYear, int evaluation,
			int technicParam, int cookParam, int communicationParam, int participantsAmount, int hostedAmount,
			int iconId) {
		super();
		this.id = id;
		this.mailAddress = mailAddress;
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
		this.telNum = telNum;
		this.prefectureId = prefectureId;
		this.eventCategory = eventCategory;
		this.outdoorLevel = outdoorLevel;
		this.registerYear = registerYear;
		this.evaluation = evaluation;
		this.technicParam = technicParam;
		this.cookParam = cookParam;
		this.communicationParam = communicationParam;
		this.participantsAmount = participantsAmount;
		this.hostedAmount = hostedAmount;
		this.iconId = iconId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public int getPrefectureId() {
		return prefectureId;
	}
	public void setPrefectureId(int prefectureId) {
		this.prefectureId = prefectureId;
	}
	public int getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(int eventCategory) {
		this.eventCategory = eventCategory;
	}
	public int getOutdoorLevel() {
		return outdoorLevel;
	}
	public void setOutdoorLevel(int outdoorLevel) {
		this.outdoorLevel = outdoorLevel;
	}
	public String getRegisterYear() {
		return registerYear;
	}
	public void setRegisterYear(String registerYear) {
		this.registerYear = registerYear;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	public int getTechnicParam() {
		return technicParam;
	}
	public void setTechnicParam(int technicParam) {
		this.technicParam = technicParam;
	}
	public int getCookParam() {
		return cookParam;
	}
	public void setCookParam(int cookParam) {
		this.cookParam = cookParam;
	}
	public int getCommunicationParam() {
		return communicationParam;
	}
	public void setCommunicationParam(int communicationParam) {
		this.communicationParam = communicationParam;
	}
	public int getParticipantsAmount() {
		return participantsAmount;
	}
	public void setParticipantsAmount(int participantsAmount) {
		this.participantsAmount = participantsAmount;
	}
	public int getHostedAmount() {
		return hostedAmount;
	}
	public void setHostedAmount(int hostedAmount) {
		this.hostedAmount = hostedAmount;
	}
	public int getIconId() {
		return iconId;
	}
	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

}