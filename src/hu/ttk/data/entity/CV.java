package hu.ttk.data.entity;

import java.sql.Date;
import java.util.HashMap;

import hu.ttk.Util;
import hu.ttk.data.dao.Fillable;

public class CV implements Fillable{
	
	private Integer cvId;
	private String name;
	private String birthplace;
	private Date birthdate;
	private String nationality;
	private String email;
	private String phone;
	private Date createDate;
	
	
	public CV(){}
	
	public CV(Integer cvId, String name, String birthplace, Date birthdate, String nationality, String email, String phone, Date createDate) {
		super();
		this.cvId = cvId;
		this.name = name;
		this.birthplace = birthplace;
		this.birthdate = birthdate;
		this.nationality = nationality;
		this.email = email;
		this.phone = phone;
		this.createDate = createDate;
	}
	
	public Integer getCvId(){
		return cvId;
	}
	
	public void setCvId(Integer cvId){
		this.cvId=cvId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getBirthdate() {
		return Util.Date2String(birthdate);
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getCreateDate() {
		return Util.Date2String(createDate);
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public void fill(HashMap keyValuePair){
		setCvId((Integer)keyValuePair.get("id"));
		setName((String)keyValuePair.get("name"));
		setBirthplace((String)keyValuePair.get("birthplace"));
		setBirthdate((Date)keyValuePair.get("birthdate"));
		setNationality((String)keyValuePair.get("nationality"));
		setEmail((String)keyValuePair.get("email"));
		setPhone((String)keyValuePair.get("phone"));
		setCreateDate((Date)keyValuePair.get("create_date"));
	}
	
}