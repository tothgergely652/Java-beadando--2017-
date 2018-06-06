package hu.ttk.ui.europass;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.ttk.data.entity.Job;

public class JobLabelPanel extends JPanel{
	
	private JLabel lblJobName;
	private JLabel lblJobTime;
	private JLabel lblPosition;
	private JLabel lblRole;

	public JobLabelPanel(){
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(3,1));
		
		lblJobTime = new JLabel();
		add(lblJobTime);
		
		Container cont= new Container();
		cont.setLayout(new GridLayout(3,2));
			
			JLabel lbl2 = new JLabel("Munka neve:");
			cont.add(lbl2);
			lblJobName = new JLabel();
			cont.add(lblJobName);
			
			JLabel lbl3 = new JLabel("Beosztás:");
			cont.add(lbl3);
			lblPosition = new JLabel();
			cont.add(lblPosition);
			
			JLabel lbl4 = new JLabel("Feladatok:");
			cont.add(lbl4);
			lblRole = new JLabel();
			cont.add(lblRole);
		
		add(cont);
	}
	
	public JobLabelPanel(ArrayList data) {
		Integer sizeOfData = data.size();
		setLayout(new GridLayout(sizeOfData,2));
		for(int i=0; i<sizeOfData; i++){
			JobLabelPanel stLabel = new JobLabelPanel();
			stLabel.setJobLabel((Job)data.get(i));
			add(stLabel);
		}
	}

	public void setJobLabel(Job job){
		
		getLblJobName().setText(job.getJobName());
		String fin = "";
		if(job.getJobEnd() != null) fin = job.getJobEnd();
			//ha nem írunk be dátumot, akkor null-t ír ki ami nem szép egy önéletrajzban
		getLblJobTime().setText(job.getJobStart()+" - "+fin);
		getLblPosition().setText(job.getPosition());
		getLblRole().setText(job.getRole());
	}

	public JLabel getLblJobName() {
		return lblJobName;
	}

	public void setLblJobName(JLabel lblJobName) {
		this.lblJobName = lblJobName;
	}

	public JLabel getLblJobTime() {
		return lblJobTime;
	}

	public void setLblJobTime(JLabel lblJobTime) {
		this.lblJobTime = lblJobTime;
	}

	public JLabel getLblPosition() {
		return lblPosition;
	}

	public void setLblPosition(JLabel lblPosition) {
		this.lblPosition = lblPosition;
	}

	public JLabel getLblRole() {
		return lblRole;
	}

	public void setLblRole(JLabel lblRole) {
		this.lblRole = lblRole;
	}
}
