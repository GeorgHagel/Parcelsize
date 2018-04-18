package service;
//package main.java;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class SQLiteHandler implements IDatabaseHandler {
	private Connection c;
	private Statement stmt;
	private boolean open;
	public void openConnection(String url){
		File file = new File(".");
		
		url = "jdbc:sqlite:"+file.getAbsolutePath()+"/database/parcelsize.db";
	    try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection(url);
	         open = true;
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	}
	
	public void createParcelSizeTable() {
		if (open) {
			try {
				stmt = c.createStatement();
				String sql = "CREATE TABLE PARCELSIZE " + 
						  "(ID 			   INTEGER 		PRIMARY KEY     AUTOINCREMENT,"
						+ " CATEGORY       TEXT    						NOT NULL, "
						+ " MAX_SIZE       INTEGER 	   					NOT NULL)";
				stmt.executeUpdate(sql);
				stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void insertParcelSize(String category, int max_size){
		if(open){
			try {
				stmt = c.createStatement();
		        String sql = "INSERT INTO PARCELSIZE (CATEGORY,MAX_SIZE) " +
                        "VALUES ('"+ category+"', "+ max_size+");"; 
		        stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public Map<Integer,Parcelsize> getParcelSizeTable(){
		Map<Integer, Parcelsize> map = new TreeMap<Integer, Parcelsize>();
		try {
			stmt = c.createStatement();
			 ResultSet rs = stmt.executeQuery( "SELECT * FROM PARCELSIZE;" );
		      
		      while ( rs.next() ) {
		    
		         String  cat = rs.getString("category");
		         int max_size  = rs.getInt("max_size");
		         map.put(max_size, Parcelsize.valueOf(cat));
		         
		      }
		      rs.close();
		      stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return map;
	}
}
