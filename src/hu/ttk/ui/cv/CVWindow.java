package hu.ttk.ui.cv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import hu.ttk.data.dao.cv.CVDAOProvider;
import hu.ttk.data.dao.cv.CVDBDAO;
import hu.ttk.data.helper.BeanTableModel;

public class CVWindow extends JFrame {
	
	private static CVWindow actWindow;
	
	public CVFieldPanel pFields;
	public CVDAOProvider dao;
	public CVTable tblUsers;
	public CVButtonPanel pButton;
	public CVCenterButtonPanel cButton;
	
	
	public CVWindow(String title,int width, int lenght){
		super (title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, lenght);//frame helyett this az elején , mert már létrejött pédány
		actWindow=this;
	}	
	
	//grafikus felület
	public void initUI(){
		Container cont=this.getContentPane();
		cont.setLayout(new BorderLayout());
		
		pFields = new CVFieldPanel();
		cont.add(BorderLayout.WEST,pFields);
		
		dao=new CVDBDAO();
		ArrayList data = null;
		try {
			data = dao.getAllCV();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(CVWindow.getInstance(), "Hiba az adat betöltésekor ("+e.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
			
		BeanTableModel model=new BeanTableModel(data);
		
		tblUsers = new CVTable(model,pFields);
		cont.add(BorderLayout.EAST, tblUsers);
		
		cButton = new CVCenterButtonPanel();
		cont.add(BorderLayout.CENTER,cButton);
		
		pButton= new CVButtonPanel();
		cont.add(BorderLayout.SOUTH,pButton);
	}
	/**
	 * Singleton függvény, visszaadja az éppen aktuális futó példányt
	 * @return
	 */
	public static CVWindow getInstance(){
		return actWindow;
	}
}
