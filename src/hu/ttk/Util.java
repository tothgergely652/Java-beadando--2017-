package hu.ttk;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class Util {
/**
 * Biztonságosan Integerré parsol egy Stringet
 * @param value
 * @return
 */
	public static Integer safe2Integer(String value) {
		Integer back;
		if (value == null) {
			back = null;
		} else {
			try {
				int val = Integer.parseInt(value);
				back = new Integer(val);
			} catch (Exception e) {
				back = null;
			}
		}
		return back;
	}
	
	public static Date safe2Date(String value) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Date back = null;
		if(!value.equals("")){
		    try {
		    	back = new java.sql.Date(format.parse(value).getTime()); 
		    } catch (Exception e) {
		    	JOptionPane.showMessageDialog(null, "Hibás dátumformátum ("+e.getMessage()+") !", "Hiba!", JOptionPane.ERROR_MESSAGE);
		        e.printStackTrace();
		    }
		}
		return back;
	}
	
	public static String Date2String(Date value) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String back = null;
		if(value != null){
			try {
		    	back = df.format(value);
		    } catch (Exception e) {
		        e.printStackTrace();
		    } 
		}
		return back;
	}
}
