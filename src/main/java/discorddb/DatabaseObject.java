package discorddb;

import net.dv8tion.jda.api.utils.data.DataObject;

import java.io.*;
import java.util.HashMap;

/**
 * Model for Database Object
 */
public class DatabaseObject {

    private final String dbName;
    private final File dbFile;
    private final HashMap<String, String> cache;

    /**
     * Constructor to initialize the object data
     * @param dbName database name
     * @param dbFile database file
     */
    protected DatabaseObject(String dbName, File dbFile) {
        this.dbName = dbName;
        this.dbFile = dbFile;
        this.cache = new HashMap<>();
        try { initCache(); } catch (IOException ignored) {}
    }

    /**
     * Initialize the cache with the data from the database file
     * @throws IOException for BufferedReader
     */
    private void initCache() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(dbFile));
        DataObject data = DataObject.fromJson(bf.readLine()).getObject("data");
        for(String key : data.keys())
            cache.put(key, data.getString(key));
    }

    /**
     * Get name of {@link DatabaseObject}
     * @return {@link String} name of {@link DatabaseObject}
     */
    public String getName() {
        return dbName;
    }

    /**
     * Get the value corresponding to the given key.<br>
     * (The value is pulled directly from the database cache)
     * @param key {@link String} key of the value
     * @return {@link String} value corresponding to the given key
     */
    public String getValue(String key) {
        if(!cache.containsKey(key)) return null;
        return cache.get(key);
    }

    /**
     * Get the value as an {@link Integer} corresponding to the given key.<br>
     * (The value is pulled directly from the database cache)
     * @param key {@link String} key of the value
     * @return {@link Integer} value corresponding to the given key
     */
    public Integer getValueInt(String key) {
        if(!cache.containsKey(key)) return null;
        return Integer.parseInt(cache.get(key));
    }

    /**
     * Get the value as a {@link Long} corresponding to the given key.<br>
     * (The value is pulled directly from the database cache)
     * @param key {@link String} key of the value
     * @return {@link Long} value corresponding to the given key
     */
    public Long getValueLong(String key) {
        if(!cache.containsKey(key)) return null;
        return Long.parseLong(cache.get(key));
    }

    /**
     * Update the value with the given key.<br>
     * If the key does not exist, it will perform the same action as {@link #addKey addKey} and simply add the key and value relationship.<br>
     * (This updates the cache and the database file in a separate thread)
     * @param key {@link String} key for the new value
     * @param value {@link String} value to replace the old value
     */
    public void updateValue(String key, String value) {
        if(!cache.containsKey(key)) {
            addKey(key, value);
            return;
        }
        cache.replace(key, value);
        updateToDb(key, value);
    }

    /**
     * Add key to value relationship.<br>
     * Adds the key to value relationship to the cache and updates the database file in a separate thread.
     * @param key {@link String} key of value
     * @param value {@link String} value corresponding to key
     */
    public void addKey(String key, String value) {
        cache.put(key, value);
        updateToDb(key, value);
    }

    /**
     * Remove key to value relationship.<br>
     * Removes the key to value relationship from the cache and updates the database file in a separate thread.
     * @param key {@link String} key to be removed
     */
    public void removeKey(String key) {
        cache.remove(key);
        removeFromDb(key);
    }

    /**
     * Runs an action inside a thread to update the database file with the new data
     * @param key {@link String} key to update
     * @param value new {@link String} value
     */
    private void updateToDb(String key, String value) {
        Runnable update = () -> {
            try {
                BufferedReader bf = new BufferedReader(new FileReader(dbFile));
                DataObject result = DataObject.fromJson(bf.readLine());
                DataObject data = result.getObject("data");
                data.put(key, value);
                result.put("data", data);
                FileWriter fw = new FileWriter(dbFile);
                fw.write(result.toString());
                fw.close();
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        };
        ThreadManagement.execute(update);
    }

    /**
     * Runs an action inside a thread to remove key to value relationship with the given key
     * @param key {@link String} key to remove
     */
    private void removeFromDb(String key) {
        Runnable remove = () -> {
            try {
                BufferedReader bf = new BufferedReader( new FileReader(dbFile));
                DataObject result = DataObject.fromJson(bf.readLine());
                DataObject data = result.getObject("data");
                data.remove(key);
                result.put("data", data);
                FileWriter fw = new FileWriter(dbFile);
                fw.write(result.toString());
                fw.close();
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        };
        ThreadManagement.execute(remove);
    }

}
