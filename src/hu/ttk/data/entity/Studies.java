package hu.ttk.data.entity;

import java.sql.Date;
import java.util.HashMap;

import hu.ttk.Util;
import hu.ttk.data.dao.Fillable;

public class Studies implements Fillable{
	
	private Integer studyId;
	private Integer cvId;
	private String studyName;
	private Date studyStart;
	private Date studyFinish;
	private String result;	
	
	public Studies(){}
	
	public Studies(Integer studyId, Integer cvId, String studyName, Date studyStart, Date studyFinish, String result) {
		super();
		this.studyId = studyId;
		this.cvId = cvId;
		this.studyName = studyName;
		this.studyStart = studyStart;
		this.studyFinish = studyFinish;
		this.result = result;
	}
	
	public Integer getStudyId(){
		return studyId;
	}
	
	public void setStudyId(Integer studyId){
		this.studyId=studyId;
	}
	
	public Integer getCvId(){
		return cvId;
	}
	
	public void setCvId(Integer cvId){
		this.cvId=cvId;
	}
	
	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}

	public String getStudyStart() {
		return Util.Date2String(studyStart);
	}

	public void setStudyStart(Date studyStart) {
		this.studyStart = studyStart;
	}

	public String getStudyFinish() {
		return Util.Date2String(studyFinish);
	}

	public void setStudyFinish(Date studyFinish) {
		this.studyFinish = studyFinish;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public void fill(HashMap keyValuePair){
		setStudyId((Integer)keyValuePair.get("id"));
		setCvId((Integer)keyValuePair.get("cv_id"));
		setStudyName((String)keyValuePair.get("study_name"));
		setStudyStart((Date)keyValuePair.get("study_start"));
		setStudyFinish((Date)keyValuePair.get("study_finish"));
		setResult((String)keyValuePair.get("restult"));
	}
}