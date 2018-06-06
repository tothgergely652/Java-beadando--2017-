package hu.ttk.ui.cv;

import java.awt.Container;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.omg.CORBA.INTERNAL;

import hu.ttk.Util;
import hu.ttk.data.dao.cv.CVDBDAO;
import hu.ttk.data.entity.CV;

public class CVFieldPanel extends JPanel{
	
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtBirthplace;
	private JTextField txtBirthdate;
	private JTextField txtNationality;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtCreateDate;
	
	public CVFieldPanel() {
		
		setLayout(new GridLayout(12,2));
        
		JLabel lblId= new JLabel("Azonosító:");
		add(lblId);
		txtId=new JTextField(3);
		add(txtId);
		
		JLabel lblName=new JLabel("Név:");
		add(lblName);
		txtName= new JTextField(10);
		add(txtName);
		
		JLabel lblBirthplace=new JLabel("Születési hely:");
		add(lblBirthplace);
		txtBirthplace = new JTextField(10);
		add(txtBirthplace);
		
		JLabel lblBirthdate=new JLabel("Születési idõ:");
		add(lblBirthdate);
		txtBirthdate = new JTextField(5);
		add(txtBirthdate);
		
		JLabel lblNationality=new JLabel("Állampolgársága:");
		add(lblNationality);
		txtNationality = new JTextField(10);
		add(txtNationality);
		
		JLabel lblEmail=new JLabel("Email:");
		add(lblEmail);
		txtEmail = new JTextField(10);
		add(txtEmail);
		
		JLabel lblPhone=new JLabel("Telefon:");
		add(lblPhone);
		txtPhone = new JTextField(10);
		add(txtPhone);
		
		JLabel lblCreateDate=new JLabel("Létrehozás ideje:");
		add(lblCreateDate);
		
		//aktuális dátum:
		GregorianCalendar c= new GregorianCalendar();
		int year=c.get(GregorianCalendar.YEAR);
		int month=c.get(GregorianCalendar.MONTH)+1;
		int day=c.get(GregorianCalendar.DAY_OF_MONTH);
		
		String zeroMonth = "";
		if(month<10) zeroMonth="0";
		String zeroDay = "";
		if(day<10) zeroDay="0";
		
		txtCreateDate = new JTextField(year+"-"+zeroMonth+month+"-"+zeroDay+day);
		//feltételezve, hogy a mai nap hozzuk létre az önéletrajzot
		add(txtCreateDate);
	}
	public void setField(CV cv){
		
	   getTxtId().setText(cv.getCvId().toString());
	   getTxtName().setText(cv.getName());
	   getTxtBirthplace().setText(cv.getBirthplace());
	   getTxtBirthdate().setText(cv.getBirthdate());
	   getTxtNationality().setText(cv.getNationality());
	   getTxtEmail().setText(cv.getEmail());
	   getTxtPhone().setText(cv.getPhone());
	   getTxtCreateDate().setText(cv.getCreateDate());
	}
	
	public CV getFields(){
		Integer id=Util.safe2Integer(getTxtId().getText());
		String name=getTxtName().getText();
		String birthplace=getTxtBirthplace().getText();
		Date birthdate=Util.safe2Date(getTxtBirthdate().getText());
		String nationality=getTxtNationality().getText();
		String email=getTxtEmail().getText();
		String phone=getTxtPhone().getText();
		Date createDate=Util.safe2Date(getTxtCreateDate().getText());
		
		return new CV(id, name, birthplace, birthdate, nationality, email, phone, createDate);
	}
	public JTextField getTxtId() {
		return txtId;
	}
	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}
	public JTextField getTxtName() {
		return txtName;
	}
	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}
	public JTextField getTxtBirthplace() {
		return txtBirthplace;
	}
	public void setTxtBirthplace(JTextField txtBirthplace) {
		this.txtBirthplace = txtBirthplace;
	}
	public JTextField getTxtBirthdate() {
		return txtBirthdate;
	}
	public void setTxtBirthdate(JTextField txtBirthdate) {
		this.txtBirthdate = txtBirthdate;
	}
	public JTextField getTxtNationality() {
		return txtNationality;
	}
	public void setTxtNationality(JTextField txtNationality) {
		this.txtNationality = txtNationality;
	}
	public JTextField getTxtEmail() {
		return txtEmail;
	}
	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}
	public JTextField getTxtPhone() {
		return txtPhone;
	}
	public void setTxtPhone(JTextField txtPhone) {
		this.txtPhone = txtPhone;
	}
	public JTextField getTxtCreateDate() {
		return txtCreateDate;
	}
	public void setTxtCreateDate(JTextField txtCreateDate) {
		this.txtCreateDate = txtCreateDate;
	}
}
