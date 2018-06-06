package hu.ttk.ui.cv;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import hu.ttk.data.dao.AbstractDBDAO;
import hu.ttk.data.entity.CV;
import hu.ttk.data.helper.BeanTableModel;
import hu.ttk.ui.europass.CVLabelPanel;

public class CVTable extends JTable implements MouseListener{

	private CVFieldPanel panel;
	
	public CVTable(BeanTableModel model,CVFieldPanel panel){
		super(model);
		addMouseListener(this);
		this.panel=panel;
	}
	
	public CV getActRow(){
		BeanTableModel model=(BeanTableModel)getModel();
		
		return (CV) model.getRow(getSelectedRow());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		CV cv=getActRow();
		//átadjuk az aktuális sort
		panel.setField(cv);
		//kijelölt cvId átadása az altáblák lekérdezéséhez
		AbstractDBDAO.setActId(cv.getCvId().toString());
		//aktuális CV eltárolása az Europass-os nézethez
		AbstractDBDAO.setActCV(cv);
		//beállítjuk hogy már nem új így az updatet hívja panel
		CVWindow.getInstance().pButton.setIsNew(false);
		
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
