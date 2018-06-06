package hu.ttk.data.dao.job;

import java.util.ArrayList;

import hu.ttk.data.entity.Job;

public interface JobDAOProvider {
	public ArrayList getAllJob() throws Exception;
	public void addJob(Job job) throws Exception;
	public void deleteJob(Job job) throws Exception;
	public void editJob(Job job) throws Exception;
}
