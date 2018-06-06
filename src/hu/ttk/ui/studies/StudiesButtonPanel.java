package hu.ttk.ui.studies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hu.ttk.data.entity.Studies;

public class StudiesButtonPanel extends JPanel implements ActionListener {

	JButton btnSave;
	JButton btnDelete;
	JButton btnNew;

	private boolean isNew = false;

	private final String CMD_SAVE = "SAVE";
	private final String CMD_DELETE = "DEL";
	private final String CMD_NEW = "NEW";

	public StudiesButtonPanel() {
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
			Studies study = StudiesWindow.getInstance().pFields.getFields();
			if (isNew) {
				// add
				
				if(!anyFieldIsEmpty()){
					try {
						StudiesWindow.getInstance().dao.addStudy(study);
						JOptionPane.showMessageDialog(StudiesWindow.getInstance(), "Az �j rekord hozz� lett adva az adatb�zishoz!");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(StudiesWindow.getInstance(), "Hiba a ment�skor ("+e1.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}else
					JOptionPane.showMessageDialog(StudiesWindow.getInstance(), "Az 'Azonos�t�', 'Tanulm�ny neve' �s 'Tanulm�ny kezdete' mez�k kit�lt�se k�telez�!", "Hiba!", JOptionPane.ERROR_MESSAGE);
			} else {
				// update
				if(!anyFieldIsEmpty()){	
					try {
						StudiesWindow.getInstance().dao.editStudy(study);
						JOptionPane.showMessageDialog(StudiesWindow.getInstance(), "Sikeresen m�dos�totta a rekordot!");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(StudiesWindow.getInstance(), "Hiba a m�dos�t�skor ("+e1.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}	
				}else
					JOptionPane.showMessageDialog(StudiesWindow.getInstance(), "Az 'Azonos�t�', 'Tanulm�ny neve' �s 'Tanulm�ny kezdete' mez�k kit�lt�se k�telez�!", "Hiba!", JOptionPane.ERROR_MESSAGE);
			}
		} else if (cmd.equals(CMD_DELETE)) {
			if (StudiesWindow.getInstance().pFields.getTxtId().getText().trim().isEmpty()) JOptionPane.showMessageDialog(StudiesWindow.getInstance(), "Nincs sor kijel�lve!", "Hiba!", JOptionPane.ERROR_MESSAGE);
			else{
				Studies study = StudiesWindow.getInstance().tblUsers.getActRow();
				try{
					StudiesWindow.getInstance().dao.deleteStudy(study);
					JOptionPane.showMessageDialog(StudiesWindow.getInstance(), "Sikeresen t�r�lte a rekordot!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(StudiesWindow.getInstance(), "Hiba a t�rl�skor ("+e1.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			
		} else {
			isNew = true;
		}
	}
	
	private boolean anyFieldIsEmpty() {
		boolean empty = false;
		if (StudiesWindow.getInstance().pFields.getTxtId().getText().trim().isEmpty()) empty = true;
		if (StudiesWindow.getInstance().pFields.getTxtCvId().getText().trim().isEmpty()) empty = true;
		if (StudiesWindow.getInstance().pFields.getTxtStudyName().getText().trim().isEmpty()) empty = true;
		if (StudiesWindow.getInstance().pFields.getTxtStudyStart().getText().trim().isEmpty()) empty = true;
		return empty;
	}

	public void setIsNew(boolean is) {
		isNew = is;
	}
}
