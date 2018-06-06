package hu.ttk.data.dao.job;

import java.util.ArrayList;
import java.util.HashMap;

import hu.ttk.data.dao.AbstractDBDAO;
import hu.ttk.data.entity.Job;
import hu.ttk.data.entity.Studies;

public class JobDBDAO extends AbstractDBDAO implements JobDAOProvider{
	public JobDBDAO(){
		super("job");
	}
	@Override
	public ArrayList getAllJob() throws Exception{
		//ha nincs kijelölve semmi, az egész Job táblát kiírja
		if(getActId() != null) return selectWhere(new Job(), getActId());
		else  return getAll(new Job());
	}

	@Override
	public void addJob(Job job) throws Exception {
		HashMap keyValuePair= new HashMap();
		keyValuePair.put("id", job.getJobId());
		keyValuePair.put("cv_id", job.getCvId());
		keyValuePair.put("job_name", job.getJobName());
		keyValuePair.put("job_start", job.getJobStart());
		keyValuePair.put("job_end", job.getJobEnd());
		keyValuePair.put("position", job.getPosition());
		keyValuePair.put("role", job.getRole());
		add(keyValuePair);
		
	}

	@Override
	public void deleteJob(Job job) throws Exception {
		HashMap keyValuePair= new HashMap();
		keyValuePair.put("id", job.getJobId());
		keyValuePair.put("cv_id", job.getCvId());
		keyValuePair.put("job_name", job.getJobName());
		keyValuePair.put("job_start", job.getJobStart());
		keyValuePair.put("job_end", job.getJobEnd());
		keyValuePair.put("position", job.getPosition());
		keyValuePair.put("role", job.getRole());
		delete(keyValuePair);
		
	}

	@Override
	public void editJob(Job job) throws Exception {
		HashMap keyValuePair= new HashMap();
		keyValuePair.put("id", job.getJobId());
		keyValuePair.put("cv_id", job.getCvId());
		keyValuePair.put("job_name", job.getJobName());
		keyValuePair.put("job_start", job.getJobStart());
		keyValuePair.put("job_end", job.getJobEnd());
		keyValuePair.put("position", job.getPosition());
		keyValuePair.put("role", job.getRole());
		edit(keyValuePair);	
	} 
}
