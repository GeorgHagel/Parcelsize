package main.java;
//package main.java;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ParcelDimension {
	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(canBeNull=false)
	private String category;
	@DatabaseField(canBeNull=false)
	private int max_size;
	public ParcelDimension() {
		// TODO Auto-generated constructor stub
	}
	
	public ParcelDimension(String category2, int max_size2) {
		category = category2;
		max_size = max_size2;
	}
}
