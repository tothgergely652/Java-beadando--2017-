package hu.ttk.data.dao.studies;

import java.util.ArrayList;
import java.util.HashMap;

import hu.ttk.data.dao.AbstractDBDAO;
import hu.ttk.data.entity.Job;
import hu.ttk.data.entity.Studies;

public class StudiesDBDAO extends AbstractDBDAO implements StudiesDAOProvider{
	public StudiesDBDAO(){
		super("studies");
	}
	@Override
	public ArrayList getAllStudy() throws Exception{
		//ha nincs kijelölve semmi, az egész Studies táblát kiírja
		if(getActId() != null) return selectWhere(new Studies(), getActId());
		else  return getAll(new Studies());
	}

	@Override
	public void addStudy(Studies study) throws Exception {
		HashMap keyValuePair= new HashMap();
		keyValuePair.put("id", study.getStudyId());
		keyValuePair.put("cv_id", study.getCvId());
		keyValuePair.put("study_name", study.getStudyName());
		keyValuePair.put("study_start", study.getStudyStart());
		keyValuePair.put("study_finish", study.getStudyFinish());
		keyValuePair.put("restult", study.getResult());
		add(keyValuePair);
		
	}

	@Override
	public void deleteStudy(Studies study) throws Exception {
		HashMap keyValuePair= new HashMap();
		keyValuePair.put("id", study.getStudyId());
		keyValuePair.put("cv_id", study.getCvId());
		keyValuePair.put("study_name", study.getStudyName());
		keyValuePair.put("study_start", study.getStudyStart());
		keyValuePair.put("study_finish", study.getStudyFinish());
		keyValuePair.put("restult", study.getResult());
		delete(keyValuePair);
		
	}

	@Override
	public void editStudy(Studies study) throws Exception {
		HashMap keyValuePair= new HashMap();
		keyValuePair.put("id", study.getStudyId());
		keyValuePair.put("cv_id", study.getCvId());
		keyValuePair.put("study_name", study.getStudyName());
		keyValuePair.put("study_start", study.getStudyStart());
		keyValuePair.put("study_finish", study.getStudyFinish());
		keyValuePair.put("restult", study.getResult());
		edit(keyValuePair);	
	} 
}
