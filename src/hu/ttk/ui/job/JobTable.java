package hu.ttk.ui.job;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import hu.ttk.data.entity.Job;
import hu.ttk.data.helper.BeanTableModel;

public class JobTable extends JTable implements MouseListener{

	private JobFieldPanel panel;
	
	public JobTable(BeanTableModel model,JobFieldPanel panel){
		super(model);
		addMouseListener(this);
		this.panel=panel;
	}
	
	public Job getActRow(){
		BeanTableModel model=(BeanTableModel)getModel();
		if (model == null){
			JOptionPane.showMessageDialog(null, "Ki kell jelölni egy sort!", "Hiba!", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else return (Job) model.getRow(getSelectedRow());
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Job job=getActRow();
		//átadjuk az aktuális sort
		panel.setField(job);
		//beállítjuk hogy már nem új így az updatet hívja panel
		JobWindow.getInstance().pButton.setIsNew(false);
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
