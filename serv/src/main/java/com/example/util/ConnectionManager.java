package com.example.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import com.example.model.User;

public class ConnectionManager {
	private MongoUtility mongo;
	private static ConnectionManager instance = null;

	private ConnectionManager() {
		mongo = new MongoUtility("exampleDB", "localhost", 27017);
	}

	public static ConnectionManager get() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;	
	}

	public List<LinkedHashMap<String, Object>> find(String tableName, Map<String, Object> qMap) {
		return mongo.find(tableName, qMap);
	}
}
