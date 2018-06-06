package hu.ttk.ui.europass;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hu.ttk.data.entity.Studies;

public class StudiesLabelPanel extends JPanel{
	
	private JLabel lblStudiesName;
	private JLabel lblStudiesTime;
	private JLabel lblResult;

	public StudiesLabelPanel(){
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(3,1));
		
		lblStudiesTime = new JLabel();
		add(lblStudiesTime);
		
		Container cont= new Container();
		cont.setLayout(new GridLayout(2,2));
			
			JLabel lbl2 = new JLabel("Tanulmány neve:");
			cont.add(lbl2);
			lblStudiesName = new JLabel();
			cont.add(lblStudiesName);
			
			JLabel lbl3 = new JLabel("Eredménye:");
			cont.add(lbl3);
			lblResult = new JLabel();
			cont.add(lblResult);
		
		add(cont);
	}
	
	public StudiesLabelPanel(ArrayList data) {
		Integer sizeOfData = data.size();
		setLayout(new GridLayout(sizeOfData,2));
		for(int i=0; i<sizeOfData; i++){
			StudiesLabelPanel stLabel = new StudiesLabelPanel();
			stLabel.setStudiesLabel((Studies)data.get(i));
			add(stLabel);
		}
	}

	public void setStudiesLabel(Studies study){
		
		getLblStudiesName().setText(study.getStudyName());
		String fin = "";
		if(study.getStudyFinish() != null) fin = study.getStudyFinish();
			//ha nem írunk be dátumot, akkor null-t ír ki ami nem szép egy önéletrajzban
		getLblStudiesTime().setText(study.getStudyStart()+" - "+fin);
		String res = "-";
		if(study.getResult() != null) res = study.getResult();
		getLblResult().setText(res);
	}

	public JLabel getLblStudiesName() {
		return lblStudiesName;
	}

	public void setLblStudiesName(JLabel lblStudiesName) {
		this.lblStudiesName = lblStudiesName;
	}

	public JLabel getLblStudiesTime() {
		return lblStudiesTime;
	}

	public void setLblStudiesTime(JLabel lblStudiesTime) {
		this.lblStudiesTime = lblStudiesTime;
	}

	public JLabel getLblResult() {
		return lblResult;
	}

	public void setLblResult(JLabel lblResult) {
		this.lblResult = lblResult;
	}
}
