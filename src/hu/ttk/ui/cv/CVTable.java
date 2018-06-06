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
		//�tadjuk az aktu�lis sort
		panel.setField(cv);
		//kijel�lt cvId �tad�sa az alt�bl�k lek�rdez�s�hez
		AbstractDBDAO.setActId(cv.getCvId().toString());
		//aktu�lis CV elt�rol�sa az Europass-os n�zethez
		AbstractDBDAO.setActCV(cv);
		//be�ll�tjuk hogy m�r nem �j �gy az updatet h�vja panel
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
