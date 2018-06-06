package hu.ttk.ui.europass;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import hu.ttk.data.dao.AbstractDBDAO;
import hu.ttk.data.dao.job.JobDAOProvider;
import hu.ttk.data.dao.job.JobDBDAO;
import hu.ttk.data.dao.studies.StudiesDAOProvider;
import hu.ttk.data.dao.studies.StudiesDBDAO;
import hu.ttk.ui.cv.CVWindow;

public class EuropassWindow extends JFrame {
	
	private static EuropassWindow actWindow;
	
	public CVLabelPanel cvLabels;	
	public StudiesLabelPanel stLabels;
	public JobLabelPanel jLabels;
	
	public EuropassWindow(String title,int width, int lenght){
		super (title);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //csak az aktu�lis ablakot z�rja be
		this.setSize(width, lenght);
		this.setResizable(false);
		actWindow = this;
	}	
	
	//grafikus fel�let
	public void initUI(){
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
		jp.setSize(500, 2000);
		//scrollbar ha sz�ks�ges
		JScrollPane jsp = new JScrollPane(jp);
		add(jsp);
		setVisible(true);
		
		cvLabels = new CVLabelPanel();
		//kijel�lt CV lek�r�se
		cvLabels.setCvLabel(AbstractDBDAO.getActCV());
		//sz�rke keret :)
		cvLabels.setBorder(BorderFactory.createLineBorder(Color.gray));
		jp.add(cvLabels);
		
		JLabel lblStudies = new JLabel("Tanulm�nyok:");
		lblStudies.setAlignmentX(Component.CENTER_ALIGNMENT); //k�z�pre igat�t�s
		lblStudies.setFont(lblStudies.getFont().deriveFont(18.0f)); //bet�m�ret
		jp.add(lblStudies);
		
		//CV-hez tartoz� tanulm�nyok lek�r�se
		StudiesDAOProvider sDao = new StudiesDBDAO();
		ArrayList sData = null;
		try {
			sData = sDao.getAllStudy();
			stLabels = new StudiesLabelPanel(sData);
			stLabels.setBorder(BorderFactory.createLineBorder(Color.gray));
			jp.add(stLabels);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(CVWindow.getInstance(), "Hiba az adat bet�lt�sekor ("+e.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		JLabel lblJob = new JLabel("Munk�k:");
		lblJob.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblJob.setFont(lblJob.getFont().deriveFont(18.0f)); //bet�m�ret
		jp.add(lblJob);
		
		//CV-hez tartoz� munk�k lek�r�se
		JobDAOProvider jDao = new JobDBDAO();
		ArrayList jData = null;
		try {
			jData = jDao.getAllJob();
			jLabels = new JobLabelPanel(jData);
			jLabels.setBorder(BorderFactory.createLineBorder(Color.gray));
			jp.add(jLabels);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(CVWindow.getInstance(), "Hiba az adat bet�lt�sekor ("+e.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static EuropassWindow getInstance(){
		return actWindow;
	}

}
