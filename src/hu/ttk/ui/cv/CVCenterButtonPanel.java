package hu.ttk.ui.cv;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hu.ttk.data.dao.AbstractDBDAO;
import hu.ttk.data.entity.CV;
import hu.ttk.ui.europass.EuropassWindow;
import hu.ttk.ui.job.JobWindow;
import hu.ttk.ui.studies.StudiesWindow;

public class CVCenterButtonPanel extends JPanel{

	private JButton btnEuropass;
	private JButton btnStudies;
	private JButton btnJob;
	
	public CVCenterButtonPanel(){
		setLayout(new GridLayout(6,1));
			
		btnStudies = new JButton("Tanulmányok");
		btnStudies.addActionListener(new ActionListener() { //tudom ez a hivatkozás nagyon csúnya
			public void actionPerformed(ActionEvent e) {
				StudiesWindow sw = new StudiesWindow("Tanulmányok", 800, 500);
				sw.initUI();
				sw.setVisible(true);
			}
		});
		add(btnStudies);
		
		btnJob = new JButton("Munka");
		btnJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JobWindow jw = new JobWindow("Munkák", 800, 500);
				jw.initUI();
				jw.setVisible(true);
			}
		});
		add(btnJob);
		
		btnEuropass = new JButton("Europass");
		btnEuropass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AbstractDBDAO.getActId() == null){
					JOptionPane.showMessageDialog(CVWindow.getInstance(), "Nincs kijelölt rekord!", "Hiba!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					EuropassWindow ew = new EuropassWindow("Europass", 500, 700);
					ew.initUI();
					ew.setVisible(true);
				}
			}
		});
		add(btnEuropass);
	}
}
