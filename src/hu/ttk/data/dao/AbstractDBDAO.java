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
		//betöltjük a drivermangerbe a postgres drivert
		Class.forName("org.postgresql.Driver");
		//létrehozzuk a kapcsolatot
		connection=DriverManager.getConnection(url);	
	}
	
	protected void finalize() throws SQLException {
		connection.close();
	}
	
	public ArrayList getAll(Fillable entity) throws Exception {
		//összes rekord egy táblából
		String sql="Select * from "+tableName;
		initConnection();
		System.out.println(sql);
		Statement st = connection.createStatement();
		
		ResultSet rs= st.executeQuery(sql);
		
		ArrayList back = new ArrayList();
		/*
		 * ebben vannak az értékeink
		 */
		while (rs.next()) {
			//létrehozunk egy új példányt a sorhoz
			Fillable ent=entity.getClass().newInstance();
			//ebbe töltük egy sor adatait
			HashMap keyValuePair = new HashMap();
			//egy tábla jellemzõi
			ResultSetMetaData meta = rs.getMetaData();
			//egy tábla oszlopainak száma
			int colCount = meta.getColumnCount();
			for (int i = 1; i <= colCount; i++) { //adatbázisban 1-tõl számozunk
				//i. oszlop neve
				String colName = meta.getColumnName(i);
				//egy oszlop értéke egy sorban
				keyValuePair.put(colName, rs.getObject(colName));
			}
			//egy sorból feltötltött értékkel feltöltjük az sorhoz létrehozott példányt
			ent.fill(keyValuePair);
			//elrakjuk a visszatérõ listába			
			back.add(ent);
		}
		rs.close();
		return back;		
	}
	
	public ArrayList selectWhere(Fillable entity, String where) throws Exception {
		//az adott CV-hez tartozó munkák/tanulmányok lekérdezéséhez
		String sql="Select * from "+tableName+" where cv_id = "+where;
		initConnection();
		System.out.println(sql);
		Statement st = connection.createStatement();
		
		ResultSet rs= st.executeQuery(sql);
		
		ArrayList back = new ArrayList();
		/*
		 * ebben vannak az értékeink
		 */
		while (rs.next()) {
			//létrehozunk egy új példányt a sorhoz
			Fillable ent=entity.getClass().newInstance();
			//ebbe töltük egy sor adatait
			HashMap keyValuePair = new HashMap();
			//egy tábla jellemzõi
			ResultSetMetaData meta = rs.getMetaData();
			//egy tábla oszlopainak száma
			int colCount = meta.getColumnCount();
			for (int i = 1; i <= colCount; i++) { //adatbázisban 1-tõl számozunk
				//i. oszlop neve
				String colName = meta.getColumnName(i);
				//egy oszlop értéke egy sorban
				keyValuePair.put(colName, rs.getObject(colName));
			}
			//egy sorból feltötltött értékkel feltöltjük az sorhoz létrehozott példányt
			ent.fill(keyValuePair);
			//elrakjuk a visszatérõ listába			
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
		//bejérjuk az összes oszlopot
		while (it.hasNext()) {
			String key=(String)it.next();
			sql1+=key+",";
			//kivesszük az oszlop értékét
			Object colValue = keyValuePair.get(key);
			if(colValue instanceof String){
				sql2+="'"+colValue+"',";
			}else{
				sql2+=colValue+",";
			}
			
		}
		//levágjuk a plusz vesszõt
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
				//id alapján törlünk
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
		//bejérjuk az összes oszlopot
		while (it.hasNext()) {
			String key=(String)it.next();
			sql+=key+" = ";
			//kivesszük az oszlop értékét
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
		//levágjuk a plusz vesszõt
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
