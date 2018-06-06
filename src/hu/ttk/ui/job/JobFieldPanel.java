package hu.ttk.ui.job;

import java.awt.Container;
import java.awt.GridLayout;
import java.sql.Date;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.omg.CORBA.INTERNAL;

import hu.ttk.Util;
import hu.ttk.data.dao.job.JobDBDAO;
import hu.ttk.data.entity.Job;

public class JobFieldPanel extends JPanel{	
	private JTextField txtId;
	private JTextField txtCvId;
	private JTextField txtJobName;
	private JTextField txtJobStart;
	private JTextField txtJobEnd;
	private JTextField txtPosition;
	private JTextArea txtRole;
	
	public JobFieldPanel() {
		
		setLayout(new GridLayout(14,1));
        
		JLabel lblId= new JLabel("Azonosító:");
		add(lblId);
		txtId=new JTextField(3);
		add(txtId);
		
		JLabel lblCvId= new JLabel("CV azonosító:");
		add(lblCvId);
		txtCvId=new JTextField(JobDBDAO.getActId());
		txtCvId.setEditable(false);
		add(txtCvId);
		
		JLabel lblJobName=new JLabel("Munka neve:");
		add(lblJobName);
		txtJobName= new JTextField(10);
		add(txtJobName);
		
		JLabel lblJobStart=new JLabel("Munka kezdete:");
		add(lblJobStart);
		txtJobStart = new JTextField(10);
		add(txtJobStart);
		
		JLabel lblJobEnd=new JLabel("Munka vége:");
		add(lblJobEnd);
		txtJobEnd = new JTextField(10);
		add(txtJobEnd);
		
		JLabel lblPosition=new JLabel("Beosztás:");
		add(lblPosition);
		txtPosition = new JTextField(10);
		add(txtPosition);
		
		JLabel lblRole=new JLabel("Feladatok:");
		add(lblRole);
		txtRole = new JTextArea(50, 15);
		add(txtRole);
		JScrollPane scroll = new JScrollPane (txtRole, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll);
	}
	public void setField(Job study){
		
	   getTxtId().setText(study.getJobId().toString());
	   getTxtCvId().setText(study.getCvId().toString());
	   getTxtJobName().setText(study.getJobName());
	   getTxtJobStart().setText(study.getJobStart());
	   getTxtJobEnd().setText(study.getJobEnd());
	   getTxtPosition().setText(study.getPosition());
	   getTxtRole().setText(study.getRole());
	}
	
	public Job getFields(){
		Integer jobId=Util.safe2Integer(getTxtId().getText());
		Integer cvId=Util.safe2Integer(getTxtCvId().getText());
		String jobName=getTxtJobName().getText();
		Date jobStart=Util.safe2Date(getTxtJobStart().getText());
		Date jobEnd=Util.safe2Date(getTxtJobEnd().getText());
		String position=getTxtPosition().getText();
		String role=getTxtRole().getText();
		
		return new Job(jobId, cvId, jobName, jobStart, jobEnd, position, role);
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
	
	public JTextField getTxtJobName() {
		return txtJobName;
	}
	public void setTxtJobName(JTextField txtJobName) {
		this.txtJobName = txtJobName;
	}
	
	public JTextField getTxtJobStart() {
		return txtJobStart;
	}
	public void setTxtJobStart(JTextField txtJobStart) {
		this.txtJobStart = txtJobStart;
	}
	
	public JTextField getTxtJobEnd() {
		return txtJobEnd;
	}
	public void setTxtJobEnd(JTextField txtJobEnd) {
		this.txtJobEnd = txtJobEnd;
	}
	
	public JTextField getTxtPosition() {
		return txtPosition;
	}
	public void setTxtPosition(JTextField txtPosition) {
		this.txtPosition = txtPosition;
	}
	public JTextArea getTxtRole() {
		return txtRole;
	}
	public void setTxtRole(JTextArea txtRole) {
		this.txtRole = txtRole;
	}
}
