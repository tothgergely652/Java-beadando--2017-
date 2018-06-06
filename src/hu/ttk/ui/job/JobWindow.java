package hu.ttk.ui.job;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import hu.ttk.data.dao.job.JobDAOProvider;
import hu.ttk.data.dao.job.JobDBDAO;
import hu.ttk.data.helper.BeanTableModel;
import hu.ttk.ui.cv.CVWindow;

public class JobWindow extends JFrame {
	
	private static JobWindow actWindow;
	
	public JobFieldPanel pFields;
	public JobDAOProvider dao;
	public JobTable tblUsers;
	public JobButtonPanel pButton;
	
	
	public JobWindow(String title,int width, int lenght){
		super (title);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(width, lenght);//frame helyett this az elej�n , mert m�r l�trej�tt p�d�ny
		actWindow=this;
	}	
	
	//grafikus fel�let
	public void initUI(){
		Container cont=this.getContentPane();
		cont.setLayout(new BorderLayout());
		
		pFields = new JobFieldPanel();
		cont.add(BorderLayout.WEST,pFields);
		
		
		dao=new JobDBDAO();
		ArrayList data = null;
		try {
			data = dao.getAllJob();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(CVWindow.getInstance(), "Hiba az adat bet�lt�sekor ("+e.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
			
		BeanTableModel model=new BeanTableModel(data);
		
		tblUsers = new JobTable(model,pFields);
		cont.add(BorderLayout.CENTER, tblUsers);
		
		pButton= new JobButtonPanel();
		cont.add(BorderLayout.SOUTH,pButton);
	}
	/**
	 * Singleton f�ggv�ny, visszaadja az �ppen aktu�lis fut� pp�ld�nyt
	 * @return
	 */
	public static JobWindow getInstance(){
		return actWindow;
	}
}
