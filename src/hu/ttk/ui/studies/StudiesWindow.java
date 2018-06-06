package hu.ttk.ui.studies;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import hu.ttk.data.dao.studies.StudiesDAOProvider;
import hu.ttk.data.dao.studies.StudiesDBDAO;
import hu.ttk.data.helper.BeanTableModel;
import hu.ttk.ui.cv.CVWindow;

public class StudiesWindow extends JFrame {
	
	private static StudiesWindow actWindow;
	
	public StudiesFieldPanel pFields;
	public StudiesDAOProvider dao;
	public StudiesTable tblUsers;
	public StudiesButtonPanel pButton;
	
	
	public StudiesWindow(String title,int width, int lenght){
		super (title);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(width, lenght);//frame helyett this az elej�n , mert m�r l�trej�tt p�d�ny
		actWindow=this;
	}	
	
	//grafikus fel�let
	public void initUI(){
		Container cont=this.getContentPane();
		cont.setLayout(new BorderLayout());
		
		pFields = new StudiesFieldPanel();
		cont.add(BorderLayout.WEST,pFields);
		
		
		dao=new StudiesDBDAO();
		ArrayList data = null;
		try {
			data = dao.getAllStudy();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(CVWindow.getInstance(), "Hiba az adat bet�lt�sekor ("+e.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
			
		BeanTableModel model=new BeanTableModel(data);
		
		tblUsers = new StudiesTable(model,pFields);
		cont.add(BorderLayout.CENTER, tblUsers);
		
		pButton= new StudiesButtonPanel();
		cont.add(BorderLayout.SOUTH,pButton);
	}
	/**
	 * Singleton f�ggv�ny, visszaadja az �ppen aktu�lis fut� pp�ld�nyt
	 * @return
	 */
	public static StudiesWindow getInstance(){
		return actWindow;
	}
}
