package hu.ttk.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import hu.ttk.data.entity.CV;

public abstract class AbstractDBDAO{
	private String tableName;
	protected AbstractDBDAO(String tableName){
		this.tableName=tableName;
	}
	
	String url = "jdbc:postgresql://localhost:5432/Oneletrajz?user=postgres&password=Engedj_be!";
	Connection connection;
	
	protected void initConnection() throws ClassNotFoundException, SQLException{
		//bet�ltj�k a drivermangerbe a postgres drivert
		Class.forName("org.postgresql.Driver");
		//l�trehozzuk a kapcsolatot
		connection=DriverManager.getConnection(url);	
	}
	
	protected void finalize() throws SQLException {
		connection.close();
	}
	
	public ArrayList getAll(Fillable entity) throws Exception {
		//�sszes rekord egy t�bl�b�l
		String sql="Select * from "+tableName;
		initConnection();
		System.out.println(sql);
		Statement st = connection.createStatement();
		
		ResultSet rs= st.executeQuery(sql);
		
		ArrayList back = new ArrayList();
		/*
		 * ebben vannak az �rt�keink
		 */
		while (rs.next()) {
			//l�trehozunk egy �j p�ld�nyt a sorhoz
			Fillable ent=entity.getClass().newInstance();
			//ebbe t�lt�k egy sor adatait
			HashMap keyValuePair = new HashMap();
			//egy t�bla jellemz�i
			ResultSetMetaData meta = rs.getMetaData();
			//egy t�bla oszlopainak sz�ma
			int colCount = meta.getColumnCount();
			for (int i = 1; i <= colCount; i++) { //adatb�zisban 1-t�l sz�mozunk
				//i. oszlop neve
				String colName = meta.getColumnName(i);
				//egy oszlop �rt�ke egy sorban
				keyValuePair.put(colName, rs.getObject(colName));
			}
			//egy sorb�l felt�tlt�tt �rt�kkel felt�ltj�k az sorhoz l�trehozott p�ld�nyt
			ent.fill(keyValuePair);
			//elrakjuk a visszat�r� list�ba			
			back.add(ent);
		}
		rs.close();
		return back;		
	}
	
	public ArrayList selectWhere(Fillable entity, String where) throws Exception {
		//az adott CV-hez tartoz� munk�k/tanulm�nyok lek�rdez�s�hez
		String sql="Select * from "+tableName+" where cv_id = "+where;
		initConnection();
		System.out.println(sql);
		Statement st = connection.createStatement();
		
		ResultSet rs= st.executeQuery(sql);
		
		ArrayList back = new ArrayList();
		/*
		 * ebben vannak az �rt�keink
		 */
		while (rs.next()) {
			//l�trehozunk egy �j p�ld�nyt a sorhoz
			Fillable ent=entity.getClass().newInstance();
			//ebbe t�lt�k egy sor adatait
			HashMap keyValuePair = new HashMap();
			//egy t�bla jellemz�i
			ResultSetMetaData meta = rs.getMetaData();
			//egy t�bla oszlopainak sz�ma
			int colCount = meta.getColumnCount();
			for (int i = 1; i <= colCount; i++) { //adatb�zisban 1-t�l sz�mozunk
				//i. oszlop neve
				String colName = meta.getColumnName(i);
				//egy oszlop �rt�ke egy sorban
				keyValuePair.put(colName, rs.getObject(colName));
			}
			//egy sorb�l felt�tlt�tt �rt�kkel felt�ltj�k az sorhoz l�trehozott p�ld�nyt
			ent.fill(keyValuePair);
			//elrakjuk a visszat�r� list�ba			
			back.add(ent);
		}
		rs.close();
		return back;		
	}
	
	protected void add(HashMap keyValuePair) throws Exception{
		String sql1 ="insert into "+tableName+" (";
		String sql2 =" values (";
		Set keys = keyValuePair.keySet();
		Iterator it = keys.iterator();
		//bej�rjuk az �sszes oszlopot
		while (it.hasNext()) {
			String key=(String)it.next();
			sql1+=key+",";
			//kivessz�k az oszlop �rt�k�t
			Object colValue = keyValuePair.get(key);
			if(colValue instanceof String){
				sql2+="'"+colValue+"',";
			}else{
				sql2+=colValue+",";
			}
			
		}
		//lev�gjuk a plusz vessz�t
		sql1=sql1.substring(0,sql1.length()-1);

		sql2=sql2.substring(0,sql2.length()-1);
		
		String sql=sql1+")"+sql2+")";
		System.out.println(sql);
		Statement st=connection.createStatement();
		st.executeUpdate(sql);
	}
	
	protected void delete(HashMap keyValuePair) throws Exception{
		String sql ="delete from "+tableName+" where id = ";
		
		Set keys = keyValuePair.keySet();
		Iterator it = keys.iterator();
		Integer id = null;
		while (it.hasNext()) {
			String key=(String)it.next();
			Object colValue = keyValuePair.get(key);
			if (key=="id"){
				//id alapj�n t�rl�nk
				id = (Integer)colValue;
				sql += id.toString();
			}
			
		}
		System.out.println(sql);
		Statement st=connection.createStatement();
		st.executeUpdate(sql);
	
	}
	
	protected void deleteWhere(String tableName, String where) throws Exception{
		String sql ="delete from "+tableName+" where cv_id = "+where;
		
		System.out.println(sql);
		Statement st=connection.createStatement();
		st.executeUpdate(sql);
	
	}
	
	protected void edit(HashMap keyValuePair) throws Exception{
		String sql ="update "+tableName+" set ";
		int id=0;
		Set keys = keyValuePair.keySet();
		Iterator it = keys.iterator();
		//bej�rjuk az �sszes oszlopot
		while (it.hasNext()) {
			String key=(String)it.next();
			sql+=key+" = ";
			//kivessz�k az oszlop �rt�k�t
			Object colValue = keyValuePair.get(key);
			if (key=="id"){
				id = (int)colValue;
			}
			if(colValue instanceof String){
				sql+="'"+colValue+"'";
			}else{
				sql+=colValue;
			}
			sql += ", ";
			
		}
		//lev�gjuk a plusz vessz�t
		sql = sql.substring(0,sql.length()-2)+" where id = "+id;
		
		System.out.println(sql);
		Statement st=connection.createStatement();
		st.executeUpdate(sql);
	
	}
	
	
	private static String actId;
	private static CV actCV;
	
	public static void setActId(String _actId){
		actId = _actId;
	}
	
	public static String getActId(){
		return actId;
	}
	
	public static void setActCV(CV _actCV){
		actCV = _actCV;
	}
	
	public static CV getActCV(){
		return actCV;
	}
}
