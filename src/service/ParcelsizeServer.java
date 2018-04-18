package service;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class ParcelsizeServer {

	public static void main(String[] args) {
		/*
		IDatabaseHandler db = MySQLDatabaseHandler.getDatabaseHandler();
		
		db.createParcelSizeTable();
		db.insertParcelSize("XS", 35);
		db.insertParcelSize("S",  50);
		db.insertParcelSize("M",  65);
		db.insertParcelSize("L",  80);
		db.insertParcelSize("XL", 300);
		*/
		HttpServer server;
		
		
		try {
			server = HttpServerFactory.create("http://localhost:1100/parcel");
			server.start();
			
			
			JOptionPane.showMessageDialog(null, "ParcelSize-Service started!\nClick to end");
			server.stop(0);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
