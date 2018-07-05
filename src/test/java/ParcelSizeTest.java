package test.java;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.google.gson.Gson;
import static org.junit.Assert.*;

import main.java.IDatabaseHandler;
import main.java.Parcel;
import main.java.ParcelSizeService;
import main.java.Parcelsize;

public class ParcelSizeTest {
	private Parcel p;
	
	public ParcelSizeTest(){
		p = new Parcel();
	}
	
	private String serializeParcel(int length, int height, int depth){
		Gson g = new Gson();
		p.setLength(length);
		p.setHeight(height);
		p.setDepth(depth);
		return g.toJson(p);
	}
	
	@Test
	public void testParcelSizeXS(){
		IDatabaseHandler db = new DatabaseMock();
		ParcelSizeService service = new ParcelSizeService(db);
		Gson g = new Gson();
		//Test Size XS: 0 - 35 cm
		
		//Test XS.1: 0cm
		String json = serializeParcel(0, 0, 0);
		
		Response resp = service.calculateParcelSize(json);
		json = (String)(resp.getEntity());
		p = g.fromJson(json, Parcel.class);
		
		assertEquals("Expected parcelsize XS but actual size was " + p.getSize() + ": Input 0/0/0", Parcelsize.XS, p.getSize());
		
		//Test XS.2: 20 cm
		json = serializeParcel(11, 10, 9);
		
		resp = service.calculateParcelSize(json);
		json = (String)(resp.getEntity());
		p = g.fromJson(json, Parcel.class);
		
		assertEquals("Expected parcelsize XS but actual size was " + p.getSize() + ": Input 10/10/5", Parcelsize.XS, p.getSize());
		
		//Test XS.3: 35cm
		json = serializeParcel(20, 15, 17);
		
		resp = service.calculateParcelSize(json);
		json = (String)(resp.getEntity());
		p = g.fromJson(json, Parcel.class);
		
		assertEquals("Expected parcelsize XS but actual size was " + p.getSize() + ": Input 20/15/10", Parcelsize.XS, p.getSize());

	}
	
	@Test
	public void testParcelSizeS(){
		IDatabaseHandler db = new DatabaseMock();
		ParcelSizeService service = new ParcelSizeService(db);
		Gson g = new Gson();
		//Test Size S: 36 - 50 cm
		
		//Test S.1: 36cm
		String json = serializeParcel(21, 15, 17);
		
		Response resp = service.calculateParcelSize(json);
		json = (String)(resp.getEntity());
		p = g.fromJson(json, Parcel.class);
		
		assertEquals("Expected parcelsize S but actual size was " + p.getSize() + ": Input 21/15/10", Parcelsize.S, p.getSize());
		
		//Test S.2: 40 cm
		json = serializeParcel(25, 20, 15);
		
		resp = service.calculateParcelSize(json);
		json = (String)(resp.getEntity());
		p = g.fromJson(json, Parcel.class);
		
		assertEquals("Expected parcelsize S but actual size was " + p.getSize() + ": Input 20/20/5", Parcelsize.S, p.getSize());
		
		//Test S.3: 50cm
		json = serializeParcel(20, 30, 25);
		
		resp = service.calculateParcelSize(json);
		json = (String)(resp.getEntity());
		p = g.fromJson(json, Parcel.class);
		
		assertEquals("Expected parcelsize S but actual size was " + p.getSize() + ": Input 20/30/10", Parcelsize.S, p.getSize());
	}
}
