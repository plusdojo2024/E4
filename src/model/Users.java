package model;
import java.io.Serializable;

public class Users implements Serializable {
	private int id;
	private String mail_address;
	private String password;
	private String name;
	private String birth_date;
	private String tel_num;
	private int prefecture_id;
	private int event_category;
	private int outdoor_level;
	private String register_year;
	private int evaluation;
	private int technic_param;
	private int cook_param;
	private int communication_param;
	private int participants_amount;
	private int hosted_amount;
	private int icon_id;



	public Users(
			int id,
			String mail_address,
			String password,
			String name,
			String birth_date,
			String tel_num,
			int prefecture_id,
			int event_category,
			int outdoor_level,
			String register_year,
			int evaluation,
			int technic_param,
			int cook_param,
			int communication_param,
			int participants_amount,
			int hosted_amount,
			int icon_id
			) {

		this.id = id;
		this.mail_address = mail_address;
		this.password = password;
		this.name = name;
		this.birth_date = birth_date;
		this.tel_num = tel_num;
		this.prefecture_id = prefecture_id;
		this.event_category = event_category;
		this.outdoor_level = outdoor_level;
		this.register_year = register_year;
		this.evaluation = evaluation;
		this.technic_param = technic_param;
		this.cook_param = cook_param;
		this.communication_param = communication_param;
		this.participants_amount = participants_amount;
		this.hosted_amount = hosted_amount;
		this.icon_id = icon_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
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
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getTel_num() {
		return tel_num;
	}
	public void setTel_num(String tel_num) {
		this.tel_num = tel_num;
	}
	public int getPrefecture_id() {
		return prefecture_id;
	}
	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}
	public int getEvent_category() {
		return event_category;
	}
	public void setEvent_category(int event_category) {
		this.event_category = event_category;
	}
	public int getOutdoor_level() {
		return outdoor_level;
	}
	public void setOutdoor_level(int outdoor_level) {
		this.outdoor_level = outdoor_level;
	}
	public String getRegister_year() {
		return register_year;
	}
	public void setRegister_year(String register_year) {
		this.register_year = register_year;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	public int getTechnic_param() {
		return technic_param;
	}
	public void setTechnic_param(int technic_param) {
		this.technic_param = technic_param;
	}
	public int getCook_param() {
		return cook_param;
	}
	public void setCook_param(int cook_param) {
		this.cook_param = cook_param;
	}
	public int getCommunication_param() {
		return communication_param;
	}
	public void setCommunication_param(int communication_param) {
		this.communication_param = communication_param;
	}
	public int getParticipants_amount() {
		return participants_amount;
	}
	public void setParticipants_amount(int participants_amount) {
		this.participants_amount = participants_amount;
	}
	public int getHosted_amount() {
		return hosted_amount;
	}
	public void setHosted_amount(int hosted_amount) {
		this.hosted_amount = hosted_amount;
	}
	public int getIcon_id() {
		return icon_id;
	}
	public void setIcon_id(int icon_id) {
		this.icon_id = icon_id;
	}

}
