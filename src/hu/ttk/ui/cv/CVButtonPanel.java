package hu.ttk.ui.cv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hu.ttk.data.dao.AbstractDBDAO;
import hu.ttk.data.dao.studies.StudiesDBDAO;
import hu.ttk.data.entity.CV;

public class CVButtonPanel extends JPanel implements ActionListener {

	JButton btnSave;
	JButton btnDelete;
	JButton btnNew;

	private boolean isNew = false;

	private final String CMD_SAVE = "SAVE";
	private final String CMD_DELETE = "DEL";
	private final String CMD_NEW = "NEW";

	public CVButtonPanel() {
		btnSave = new JButton("Ment�s");
		btnDelete = new JButton("T�rl�s");
		btnNew = new JButton("�j");

		btnSave.setActionCommand(CMD_SAVE);
		btnDelete.setActionCommand(CMD_DELETE);
		btnNew.setActionCommand(CMD_NEW);

		add(btnSave);
		add(btnDelete);
		add(btnNew);

		btnSave.addActionListener(this);
		btnDelete.addActionListener(this);
		btnNew.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals(CMD_SAVE)) {
			CV cv = CVWindow.getInstance().pFields.getFields();
			if (isNew) {
				// add
				
				if(!anyFieldIsEmpty()){
					try {
						CVWindow.getInstance().dao.addCV(cv);
						JOptionPane.showMessageDialog(CVWindow.getInstance(), "Az �j rekord hozz� lett adva az adatb�zishoz!");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(CVWindow.getInstance(), "Hiba a ment�skor ("+e1.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}else
					JOptionPane.showMessageDialog(CVWindow.getInstance(), "Az �sszes mez� kit�lt�se k�telez�!", "Hiba!", JOptionPane.ERROR_MESSAGE);
			} else {
				// update
				if(!anyFieldIsEmpty()){
					try {
						CVWindow.getInstance().dao.editCV(cv);
						JOptionPane.showMessageDialog(CVWindow.getInstance(), "Sikeresen m�dos�totta a rekordot!");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(CVWindow.getInstance(), "Hiba a m�dos�t�skor ("+e1.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}	
				}else
					JOptionPane.showMessageDialog(CVWindow.getInstance(), "Az �sszes mez� kit�lt�se k�telez�!", "Hiba!", JOptionPane.ERROR_MESSAGE);	
			}
		}else if (cmd.equals(CMD_DELETE)) {
			//delete
			
			if (AbstractDBDAO.getActId() == null) JOptionPane.showMessageDialog(CVWindow.getInstance(), "Nincs sor kijel�lve!", "Hiba!", JOptionPane.ERROR_MESSAGE);
			//kijel�l�s n�lk�l nem t�r�l
			else{
				CV cv = CVWindow.getInstance().tblUsers.getActRow();
				try{
					//el�sz�r lehets�ges �sszekapcsolt rekordok t�rl�se
					CVWindow.getInstance().dao.deleteJob(StudiesDBDAO.getActId());
					CVWindow.getInstance().dao.deleteStudies(StudiesDBDAO.getActId());
					//rekord t�rl�se
					CVWindow.getInstance().dao.deleteCV(cv);
					JOptionPane.showMessageDialog(CVWindow.getInstance(), "Az �n�letrajz t�r�lve lett, a hozz� tartoz� tanulm�nyokkal �s munk�kkal egy�tt!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(CVWindow.getInstance(), "Hiba a t�rl�skor ("+e1.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		} else{
			isNew = true;
		}
	}

	private boolean anyFieldIsEmpty() {
		//kit�ltetlen valamely sz�ks�ges mez�
		boolean empty = false;
		if (CVWindow.getInstance().pFields.getTxtId().getText().trim().isEmpty()) empty = true;
		if (CVWindow.getInstance().pFields.getTxtName().getText().trim().isEmpty()) empty = true;
		if (CVWindow.getInstance().pFields.getTxtBirthplace().getText().trim().isEmpty()) empty = true;
		if (CVWindow.getInstance().pFields.getTxtBirthdate().getText().trim().isEmpty()) empty = true;
		if (CVWindow.getInstance().pFields.getTxtNationality().getText().trim().isEmpty()) empty = true;
		if (CVWindow.getInstance().pFields.getTxtEmail().getText().trim().isEmpty()) empty = true;
		if (CVWindow.getInstance().pFields.getTxtPhone().getText().trim().isEmpty()) empty = true;
		if (CVWindow.getInstance().pFields.getTxtCreateDate().getText().trim().isEmpty()) empty = true;
		return empty;
	}

	public void setIsNew(boolean is) {
		isNew = is;
	}
}
