package main.java;
//package main.java;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/sent")
public class ParcelSizeService {
	
	private Map<Integer, Parcelsize> parcelSizeMap;
	private int MAX_GIRTH = 300;
	private IDatabaseHandler db;
	
	
	public ParcelSizeService() {
		db = MySQLDatabaseHandler.getDatabaseHandler();
		db.openConnection("jdbc:mysql://MySQLParcelsize:3306/ms_parcel_size?user=user&password=mysql");
		
		parcelSizeMap = new HashMap<Integer,Parcelsize>();
		parcelSizeMap = db.getParcelSizeTable();
	
	}
	
	public ParcelSizeService(IDatabaseHandler db){
		this.db = db;
		db.openConnection("");
		parcelSizeMap = new HashMap<Integer,Parcelsize>();
		parcelSizeMap = db.getParcelSizeTable();
	}
	  
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("size")
	public Response calculateParcelSize(String json){
		
		Gson g = new Gson();
		Parcel parcel = g.fromJson(json, Parcel.class);
		
		String resp = "";
		if(parcel != null){
			int girth = parcel.getGirth();
			
			if(girth < MAX_GIRTH){
				int dim = parcel.getLongestSide()+parcel.getShortestSide();
				for (Map.Entry<Integer, Parcelsize> entry : parcelSizeMap.entrySet())
				{
					if(dim <= entry.getKey()){
						parcel.setSize(entry.getValue());
						break;
					}
				
				}
			}else{
				parcel.setSize(Parcelsize.UNDEFINED);
			}
			resp = g.toJson(parcel);
		}else{
			System.out.println("No Parcel transmitted: Could not deserialize JSON-String to Object");
			resp = "No Parcel transmitted: Could not deserialize JSON-String to Object";
		}
		
		System.out.println("Response: " + g.toJson(parcel));
		return Response
			      .status(200)
			      .header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Credentials", "true")
			      .header("Access-Control-Allow-Headers",
			        "origin, content-type, accept, authorization")
			      .header("Access-Control-Allow-Methods", 
			        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			      .entity(resp)
			      .build();
	}
}
