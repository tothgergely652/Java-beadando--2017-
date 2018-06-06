package hu.ttk.data.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class BeanTableModel extends DefaultTableModel {

   private  ArrayList rows;
   private   Vector columnsSetMethod;
   private   Vector columnsGetMethod;
   private   Vector columnsName;
   private   Vector columnsHide;
   /**
    * Konstruktor
    */
   public BeanTableModel() {
       rows = new ArrayList();
       columnsSetMethod = new Vector();
       columnsGetMethod = new Vector();
       columnsName = new Vector();
       columnsHide = new Vector();
   }
   /**
    * Konstruktor
    * @param dataRows
    */
   public BeanTableModel(ArrayList dataRows) {
       this.rows = dataRows;
       columnsSetMethod = new Vector();
       columnsGetMethod = new Vector();
       columnsName = new Vector();
       setDataRows(dataRows);
   }
   /**
    * Beállítja az átadott sorokat a modelben mint adat.
    * @param dataRows
    */
   public void setDataRows(ArrayList dataRows) {
        if (dataRows == null || dataRows.size() == 0)
            return;
        setBeanClass(dataRows.get(0));
    }
    /**
     * Kikeresi az átadott objektumból az oszlopkat metódusokat 
     * @param obj
     */
    protected void setBeanClass(Object obj) {
        for (int t = 0; t < obj.getClass().getDeclaredMethods().length; t++) {
            Method met = (obj.getClass().getDeclaredMethods())[t];
            // a set methódusok kigyűjtése
            if (met.getName().indexOf("set") == 0) {
                columnsSetMethod.add(met);
                // a methódus nevének az elejét levágom, az lesz az oszlop
                // neve...
                columnsName.add(met.getName().substring(3));
            }
        }
        columnsGetMethod = new Vector(columnsName.size());
        for (int i = 0; i < columnsName.size(); i++) {
            for (int t = 0; t < obj.getClass().getDeclaredMethods().length; t++) {
                Method met = (obj.getClass().getDeclaredMethods())[t];
                if ((met.getName().indexOf("get") == 0 && met.getName().substring(3).equalsIgnoreCase((String) columnsName.get(i)))
                        || (met.getName().indexOf("is") == 0 && met.getName().substring(2).equalsIgnoreCase((String) columnsName.get(i)))) {
                    columnsGetMethod.add(met);
                }
            }
        }
    }



    /**
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    public Class getColumnClass(int columnIndex) {
        Class c = ((Method) columnsGetMethod.get(columnIndex)).getReturnType();
        if (c == int.class) {
            return Integer.class;
        }
        return c;
    }

    /**
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    public String getColumnName(int column) {
        return (String) columnsName.get(column);
    }

    public void setColumnName(int column, String name) {
        columnsName.set(column, name);
    }

    /**
     * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // TODO aki akarja ezt megírhatja
        return true;
    }

    /**
     * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
     *      int, int)
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Object objects[] = { aValue };
        try {
            if (columnsSetMethod.get(columnIndex) instanceof Method) {
                ((Method) columnsSetMethod.get(columnIndex)).invoke(rows.get(rowIndex), objects);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    /**
     * Visszaadja az oszlopok számát
     */
    public int getColumnCount() {      
        return columnsGetMethod.size();
    }
    /**
     * Visszaadja az sorok számát
     */
    public int getRowCount() {      
        return (rows != null) ? rows.size() : 0;
    }
    /**
     * Visszaadja a megadott index-ű sort( a teljes objeketumot)
     * @param rowIndx
     * @return
     */
    public Object getRow(int rowIndx) {
        return rows.get(rowIndx);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = null;
        try {
            ret = ((Method) columnsGetMethod.get(columnIndex)).invoke(rows.get(rowIndex), null);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.getCause().printStackTrace();
        } 
        return ret;
    }

    
}
