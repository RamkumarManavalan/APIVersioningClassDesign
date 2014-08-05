package com.example.util;

import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.UnknownHostException;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

public class MongoUtility {

	private DB db = null;

	public MongoUtility(String dbName, String hostName, int port) {
		try {
			MongoClient mongoClient = new MongoClient(hostName, port);
			db = mongoClient.getDB(dbName);
		} catch(UnknownHostException e) {
			System.out.println("Unknow host exception");
		}
	}

	public List<LinkedHashMap<String, Object>> find(String collectionName, Map<String, Object> queryKVP) {
		if (db == null) {
                        return null;
                }
                DBCollection coll = db.getCollection(collectionName);
                List<DBObject> results = coll.find(createDBObjFromMap(queryKVP)).toArray();
		return createMapFromDBObject(results);
	}

	private DBObject createDBObjFromMap(Map map) {
		DBObject obj = new BasicDBObject();
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			obj.put(mEntry.getKey().toString(), mEntry.getValue());
		}
		return obj;
	}

        private List<LinkedHashMap<String, Object>> createMapFromDBObject(List<DBObject> objs) {
                List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
                for (int i  = 0; i< objs.size(); i++) {
                        LinkedHashMap<String, Object> n = new LinkedHashMap<String, Object>();
                        Iterator it = ((BasicDBObject)(objs.get(i))).entrySet().iterator();
                        while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry)it.next();
                                Object obj =  getObjectFromDBObject(pairs.getValue());
                                if (obj != null) {
                                        n.put(pairs.getKey().toString(), obj);
                                } else {
                                        System.out.println("Warning: Unsupported type (" + pairs.getValue().getClass() + ") for key " + pairs.getKey());
                                }
                        }
                        list.add(n);
                }
                return list;
        }

        private Object getObjectFromDBObject(Object obj) {
                if (obj instanceof com.mongodb.BasicDBList) {
                        List<String> values = new ArrayList<String>();
                        BasicDBList entries = (BasicDBList)obj;
                        Iterator it = entries.iterator();
                        while (it.hasNext()) {
                                values.add((String)it.next());
                        }               
                        return values;
                } else if (     
                        (obj instanceof java.lang.String) ||
                        (obj instanceof java.lang.Double) ||
                        (obj instanceof org.bson.types.ObjectId)
                ) {                     
                        return obj.toString();
                } else {        
                        return null;
                }               
        }
}
