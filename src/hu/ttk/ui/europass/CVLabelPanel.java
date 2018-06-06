package hu.ttk.ui.europass;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.ttk.data.entity.CV;

public class CVLabelPanel extends JPanel{
	
	private JLabel lblName;
	private JLabel lblBirthplace;
	private JLabel lblBirthdate;
	private JLabel lblNationality;
	private JLabel lblEmail;
	private JLabel lblPhone;
	private JLabel lblCreateDate;
	
	public CVLabelPanel(){
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(7,2));
		
		JLabel lbl1= new JLabel("Név:");
		add(lbl1);
		lblName= new JLabel();
		add(lblName);
		
		JLabel lbl2= new JLabel("Születési hely:");
		add(lbl2);
		lblBirthplace= new JLabel();
		add(lblBirthplace);
		
		JLabel lbl3= new JLabel("Születési idõ:");
		add(lbl3);
		lblBirthdate= new JLabel();
		add(lblBirthdate);
		
		JLabel lbl4= new JLabel("Nemzetiség:");
		add(lbl4);
		lblNationality= new JLabel();
		add(lblNationality);
		
		JLabel lbl5= new JLabel("Email:");
		add(lbl5);
		lblEmail= new JLabel();
		add(lblEmail);
		
		JLabel lbl6= new JLabel("Tel.:");
		add(lbl6);
		lblPhone= new JLabel();
		add(lblPhone);
		
		JLabel lbl7= new JLabel("Létrhozás dátuma:");
		add(lbl7);
		lblCreateDate= new JLabel();
		add(lblCreateDate);
	}
	
	public void setCvLabel(CV cv){
		
		getLblName().setText(cv.getName());
		getLblBirthplace().setText(cv.getBirthplace());
		getLblBirthdate().setText(cv.getBirthdate());
		getLblNationality().setText(cv.getNationality());
		getLblEmail().setText(cv.getEmail());
		getLblPhone().setText(cv.getPhone());
	   	getLblCreateDate().setText(cv.getCreateDate());
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}

	public JLabel getLblBirthplace() {
		return lblBirthplace;
	}

	public void setLblBirthplace(JLabel lblBirthplace) {
		this.lblBirthplace = lblBirthplace;
	}

	public JLabel getLblBirthdate() {
		return lblBirthdate;
	}

	public void setLblBirthdate(JLabel lblBirthdate) {
		this.lblBirthdate = lblBirthdate;
	}

	public JLabel getLblNationality() {
		return lblNationality;
	}

	public void setLblNationality(JLabel lblNationality) {
		this.lblNationality = lblNationality;
	}

	public JLabel getLblEmail() {
		return lblEmail;
	}

	public void setLblEmail(JLabel lblEmail) {
		this.lblEmail = lblEmail;
	}

	public JLabel getLblPhone() {
		return lblPhone;
	}

	public void setLblPhone(JLabel lblPhone) {
		this.lblPhone = lblPhone;
	}

	public JLabel getLblCreateDate() {
		return lblCreateDate;
	}

	public void setLblCreateDate(JLabel lblCreateDate) {
		this.lblCreateDate = lblCreateDate;
	}	
}