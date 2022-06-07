package discorddb.model;

import net.dv8tion.jda.api.utils.data.DataObject;

import java.io.*;
import java.util.HashMap;

public class DatabaseObject {

    private final String dbName;
    private final File dbFile;
    private final HashMap<String, String> cache;

    public DatabaseObject(String dbName, File dbFile) {
        this.dbName = dbName;
        this.dbFile = dbFile;
        this.cache = new HashMap<>();
        try { initCache(); } catch (IOException e) {}
    }

    private void initCache() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(dbFile));
        DataObject data = DataObject.fromJson(bf.readLine()).getObject("data");
        for(String key : data.keys())
            cache.put(key, data.getString(key));
    }

    public String getName() {
        return dbName;
    }

    public String getValue(String key) {
        if(!cache.containsKey(key)) return null;
        return cache.get(key);
    }

    public Integer getValueInt(String key) {
        if(!cache.containsKey(key)) return null;
        return Integer.parseInt(cache.get(key));
    }

    public Long getValueLong(String key) {
        if(!cache.containsKey(key)) return null;
        return Long.parseLong(cache.get(key));
    }

    public void updateValue(String key, String value) {
        if(!cache.containsKey(key)) {
            addKey(key, value);
            return;
        }
        cache.replace(key, value);
        try {
            updateToDb(key, value);
        } catch (IOException e) {}
    }

    public void addKey(String key, String value) {
        cache.put(key, value);
        try {
            updateToDb(key, value);
        } catch (IOException e) {}
    }

    public void removeKey(String key) {
        cache.remove(key);
        try {
            removeFromDb(key);
        } catch (IOException e) {}
    }

    private void updateToDb(String key, String value) throws IOException {
        BufferedReader bf = new BufferedReader( new FileReader(dbFile));
        DataObject result = DataObject.fromJson(bf.readLine());
        DataObject data = result.getObject("data");
        data.put(key,value);
        result.put("data", data);
        FileWriter fw = new FileWriter(dbFile);
        fw.write(result.toString());
        fw.close();
    }

    private void removeFromDb(String key) throws IOException {
        BufferedReader bf = new BufferedReader( new FileReader(dbFile));
        DataObject result = DataObject.fromJson(bf.readLine());
        DataObject data = result.getObject("data");
        data.remove(key);
        result.put("data", data);
        FileWriter fw = new FileWriter(dbFile);
        fw.write(result.toString());
        fw.close();
    }

}
