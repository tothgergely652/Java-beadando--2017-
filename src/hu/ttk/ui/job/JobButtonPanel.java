package hu.ttk.ui.job;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hu.ttk.data.entity.Job;

public class JobButtonPanel extends JPanel implements ActionListener {

	JButton btnSave;
	JButton btnDelete;
	JButton btnNew;

	private boolean isNew = false;

	private final String CMD_SAVE = "SAVE";
	private final String CMD_DELETE = "DEL";
	private final String CMD_NEW = "NEW";

	public JobButtonPanel() {
		btnSave = new JButton("Mentés");
		btnDelete = new JButton("Törlés");
		btnNew = new JButton("Új");

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
			Job job = JobWindow.getInstance().pFields.getFields();
			if (isNew) {
				// add
				if(!anyFieldIsEmpty()){	
					try {
						JobWindow.getInstance().dao.addJob(job);
						JOptionPane.showMessageDialog(JobWindow.getInstance(), "Az új rekord hozzá lett adva az adatbázishoz!");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(JobWindow.getInstance(), "Hiba a mentéskor ("+e1.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}else
					JOptionPane.showMessageDialog(JobWindow.getInstance(), "Az 'Azonosító', 'Munka neve', 'Munka kezdete', 'Beosztás' és a 'Feladatok' mezõk kitöltése kötelezõ!", "Hiba!", JOptionPane.ERROR_MESSAGE);	
				
			} else {
				// update
				if(!anyFieldIsEmpty()){	
					try {
						JobWindow.getInstance().dao.editJob(job);
						JOptionPane.showMessageDialog(JobWindow.getInstance(), "Sikeresen módosította a rekordot!");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(JobWindow.getInstance(), "Hiba a módosításkor ("+e1.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}	
				}else
					JOptionPane.showMessageDialog(JobWindow.getInstance(), "Az 'Azonosító', 'Munka neve', 'Munka kezdete', 'Beosztás' és a 'Feladatok' mezõk kitöltése kötelezõ!", "Hiba!", JOptionPane.ERROR_MESSAGE);	
			}
		} else if (cmd.equals(CMD_DELETE)) {
			//delete
			if (JobWindow.getInstance().pFields.getTxtId().getText().trim().isEmpty()) JOptionPane.showMessageDialog(JobWindow.getInstance(), "Nincs sor kijelölve!", "Hiba!", JOptionPane.ERROR_MESSAGE);
			//beírt id alapján is lehet törölni...
			else{
				Job job = JobWindow.getInstance().tblUsers.getActRow();
				try{
					JobWindow.getInstance().dao.deleteJob(job);
					JOptionPane.showMessageDialog(JobWindow.getInstance(), "Sikeresen törölte a rekordot!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(JobWindow.getInstance(), "Hiba a törléskor ("+e1.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			
		} else {
			isNew = true;
		}
	}

	private boolean anyFieldIsEmpty() {
		boolean empty = false;
		if (JobWindow.getInstance().pFields.getTxtId().getText().trim().isEmpty()) empty = true;
		if (JobWindow.getInstance().pFields.getTxtCvId().getText().trim().isEmpty()) empty = true;
		if (JobWindow.getInstance().pFields.getTxtJobName().getText().trim().isEmpty()) empty = true;
		if (JobWindow.getInstance().pFields.getTxtJobStart().getText().trim().isEmpty()) empty = true;
		if (JobWindow.getInstance().pFields.getTxtPosition().getText().trim().isEmpty()) empty = true;
		if (JobWindow.getInstance().pFields.getTxtRole().getText().trim().isEmpty()) empty = true;
		return empty;
	}
	
	public void setIsNew(boolean is) {
		isNew = is;
	}
}
