package test.java;

import java.util.Map;
import java.util.TreeMap;

import main.java.IDatabaseHandler;
import main.java.Parcelsize;

public class DatabaseMock implements IDatabaseHandler {

	@Override
	public void openConnection(String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createParcelSizeTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertParcelSize(String category, int max_size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, Parcelsize> getParcelSizeTable() {
		Map<Integer, Parcelsize> map = new TreeMap<Integer, Parcelsize>();
		map.put(35, Parcelsize.XS);
		map.put(50, Parcelsize.S);
		map.put(65, Parcelsize.M);
		map.put(80, Parcelsize.L);
		map.put(300, Parcelsize.XL);
		return map;
	}

}
