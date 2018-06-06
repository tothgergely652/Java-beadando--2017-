package hu.ttk.data.entity;

import java.sql.Date;
import java.util.HashMap;

import hu.ttk.Util;
import hu.ttk.data.dao.Fillable;

public class Job implements Fillable{
	
	private Integer jobId;
	private Integer cvId;
	private String jobName;
	private Date jobStart;
	private Date jobEnd;
	private String position;
	private String role;	
	
	public Job(){}
	
	public Job(Integer jobId, Integer cvId, String jobName, Date jobStart, Date jobEnd, String position, String role) {
		super();
		this.jobId = jobId;
		this.cvId = cvId;
		this.jobName = jobName;
		this.jobStart = jobStart;
		this.jobEnd = jobEnd;
		this.position = position;
		this.role = role;
	}
	
	public Integer getJobId(){
		return jobId;
	}
	
	public void setJobId(Integer jobId){
		this.jobId=jobId;
	}
	
	public Integer getCvId(){
		return cvId;
	}
	
	public void setCvId(Integer cvId){
		this.cvId=cvId;
	}
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobStart() {
		return Util.Date2String(jobStart);
	}

	public void setJobStart(Date jobStart) {
		this.jobStart = jobStart;
	}

	public String getJobEnd() {
		return Util.Date2String(jobEnd);
	}

	public void setJobEnd(Date jobEnd) {
		this.jobEnd = jobEnd;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public void fill(HashMap keyValuePair){
		setJobId((Integer)keyValuePair.get("id"));
		setCvId((Integer)keyValuePair.get("cv_id"));
		setJobName((String)keyValuePair.get("job_name"));
		setJobStart((Date)keyValuePair.get("job_start"));
		setJobEnd((Date)keyValuePair.get("job_end"));
		setPosition((String)keyValuePair.get("position"));
		setRole((String)keyValuePair.get("role"));
	}
}