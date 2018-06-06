package hu.ttk.ui.studies;

import java.awt.Container;
import java.awt.GridLayout;
import java.sql.Date;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.omg.CORBA.INTERNAL;

import hu.ttk.Util;
import hu.ttk.data.dao.studies.StudiesDBDAO;
import hu.ttk.data.entity.Studies;

public class StudiesFieldPanel extends JPanel{	
	private JTextField txtId;
	private JTextField txtCvId;
	private JTextField txtStudyName;
	private JTextField txtStudyStart;
	private JTextField txtStudyFinish;
	private JTextField txtResult;
	
	public StudiesFieldPanel() {
		
		setLayout(new GridLayout(12,2));
        
		JLabel lblId= new JLabel("Azonosító:");
		add(lblId);
		txtId=new JTextField(3);
		add(txtId);
		
		JLabel lblCvId= new JLabel("CV azonosító:");
		add(lblCvId);
		txtCvId=new JTextField(StudiesDBDAO.getActId());
		txtCvId.setEditable(false);
		add(txtCvId);
		
		JLabel lblStudyName=new JLabel("Tanulmány neve:");
		add(lblStudyName);
		txtStudyName= new JTextField(15);
		add(txtStudyName);
		
		JLabel lblStudyStart=new JLabel("Tanulmány kezdete:");
		add(lblStudyStart);
		txtStudyStart = new JTextField(10);
		add(txtStudyStart);
		
		JLabel lblStudyFinish=new JLabel("Tanulmány vége:");
		add(lblStudyFinish);
		txtStudyFinish = new JTextField(10);
		add(txtStudyFinish);
		
		JLabel lblResult=new JLabel("Eredménye:");
		add(lblResult);
		txtResult = new JTextField(10);
		add(txtResult);
	}
	public void setField(Studies study){
		
	   getTxtId().setText(study.getStudyId().toString());
	   getTxtCvId().setText(study.getCvId().toString());
	   getTxtStudyName().setText(study.getStudyName());
	   getTxtStudyStart().setText(study.getStudyStart());
	   getTxtStudyFinish().setText(study.getStudyFinish());
	   getTxtResult().setText(study.getResult());
	}
	
	public Studies getFields(){
		Integer studyId=Util.safe2Integer(getTxtId().getText());
		Integer cvId=Util.safe2Integer(getTxtCvId().getText());
		String studyName=getTxtStudyName().getText();
		Date studyStart=Util.safe2Date(getTxtStudyStart().getText());
		Date studyFinish=Util.safe2Date(getTxtStudyFinish().getText());
		String result=getTxtResult().getText();
		
		return new Studies(studyId, cvId, studyName, studyStart, studyFinish, result);
	}
	
	public JTextField getTxtId() {
		return txtId;
	}
	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}
	
	public JTextField getTxtCvId() {
		return txtCvId;
	}
	public void setTxtCvId(JTextField txtCvId) {
		this.txtCvId = txtCvId;
	}
	
	public JTextField getTxtStudyName() {
		return txtStudyName;
	}
	public void setTxtStudyName(JTextField txtStudyName) {
		this.txtStudyName = txtStudyName;
	}
	
	public JTextField getTxtStudyStart() {
		return txtStudyStart;
	}
	public void setTxtStudyStart(JTextField txtStudyStart) {
		this.txtStudyStart = txtStudyStart;
	}
	
	public JTextField getTxtStudyFinish() {
		return txtStudyFinish;
	}
	public void setTxtStudyFinish(JTextField txtStudyFinish) {
		this.txtStudyFinish = txtStudyFinish;
	}
	
	public JTextField getTxtResult() {
		return txtResult;
	}
	public void setTxtResult(JTextField txtResult) {
		this.txtResult = txtResult;
	}
}
