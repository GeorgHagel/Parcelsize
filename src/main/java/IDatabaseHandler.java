package main.java;
//package main.java;

import java.util.Map;

public interface IDatabaseHandler {
	public void openConnection(String url);
	public void createParcelSizeTable();
	public void insertParcelSize(String category, int max_size);
	public Map<Integer,Parcelsize> getParcelSizeTable();
	
}
