package hu.ttk.data.dao.cv;

import java.util.ArrayList;
import java.util.HashMap;

import hu.ttk.data.dao.AbstractDBDAO;
import hu.ttk.data.entity.CV;

public class CVDBDAO extends AbstractDBDAO implements CVDAOProvider{
	public CVDBDAO(){
		super("cv");
	}
	@Override
	public ArrayList getAllCV() throws Exception{
		return getAll(new CV());
	}

	@Override
	public void addCV(CV cv) throws Exception {
		HashMap keyValuePair= new HashMap();
		keyValuePair.put("id", cv.getCvId());
		keyValuePair.put("name", cv.getName());
		keyValuePair.put("birthplace", cv.getBirthplace());
		keyValuePair.put("birthdate", cv.getBirthdate());
		keyValuePair.put("nationality", cv.getNationality());
		keyValuePair.put("email", cv.getEmail());
		keyValuePair.put("phone", cv.getPhone());
		keyValuePair.put("create_date", cv.getCreateDate());
		add(keyValuePair);
		
	}

	@Override
	public void deleteCV(CV cv) throws Exception {
		HashMap keyValuePair= new HashMap();
		keyValuePair.put("id", cv.getCvId());
		keyValuePair.put("name", cv.getName());
		keyValuePair.put("birthplace", cv.getBirthplace());
		keyValuePair.put("birthdate", cv.getBirthdate());
		keyValuePair.put("nationality", cv.getNationality());
		keyValuePair.put("email", cv.getEmail());
		keyValuePair.put("phone", cv.getPhone());
		keyValuePair.put("create_date", cv.getCreateDate());
		delete(keyValuePair);	
	}
	
	@Override
	public void deleteStudies(String where) throws Exception {
		deleteWhere("studies",where);	
	}
	
	public void deleteJob(String where) throws Exception {
		deleteWhere("job",where);	
	}

	@Override
	public void editCV(CV cv) throws Exception {
		HashMap keyValuePair= new HashMap();
		keyValuePair.put("id", cv.getCvId());
		keyValuePair.put("name", cv.getName());
		keyValuePair.put("birthplace", cv.getBirthplace());
		keyValuePair.put("birthdate", cv.getBirthdate());
		keyValuePair.put("nationality", cv.getNationality());
		keyValuePair.put("email", cv.getEmail());
		keyValuePair.put("phone", cv.getPhone());
		keyValuePair.put("create_date", cv.getCreateDate());
		edit(keyValuePair);	
	} 
}
