package main.java;
//package main.java;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public class MySQLDatabaseHandler implements IDatabaseHandler{
	private ConnectionSource connectionSource;
	private boolean initDone;
	private boolean connected;
	private static MySQLDatabaseHandler handler;
	private Dao<ParcelDimension, String> pDao;
	private MySQLDatabaseHandler() {

	}

	public static MySQLDatabaseHandler getDatabaseHandler() {
		if (handler == null) {
			handler = new MySQLDatabaseHandler();
		}
		return handler;
	}

	private synchronized ConnectionSource connect(String url) {
		
		if (!connected) {
			try {
				connectionSource = new JdbcConnectionSource(url);
				System.out.println("Connected to Database");
				connected = true;
				return connectionSource;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else
			return connectionSource;

	}

	public synchronized void initTables() {
		try {
			//TableUtils.createTableIfNotExists(connectionSource, Board.class);
			TableUtils.createTableIfNotExists(connectionSource, ParcelDimension.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public void openConnection(String url) {
		// TODO Auto-generated method stub
		connect(url);
		try {
			pDao = DaoManager.createDao(connectionSource, ParcelDimension.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void createParcelSizeTable() {
		if(connected)
			initTables();
		
	}

	@Override
	public void insertParcelSize(String category, int max_size) {
		ParcelDimension pd = new ParcelDimension(category, max_size);
		try {
			pDao.create(pd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Map<Integer, Parcelsize> getParcelSizeTable() {
		Map<Integer, Parcelsize> map = new TreeMap<Integer, Parcelsize>();
		String query = "SELECT * FROM parceldimension";
		GenericRawResults<String[]> raw;
		try {
			raw = pDao.queryRaw(query);
			List<String[]> result = raw.getResults();

			for (String[] res : result) {
				map.put(Integer.parseInt(res[2]), Parcelsize.valueOf(res[1]));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}
}
