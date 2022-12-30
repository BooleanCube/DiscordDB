package discorddb;

import net.dv8tion.jda.api.utils.data.DataObject;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Model for Database Object<br>
 * A DatabaseObject represents a JSON file that stores data in a key-value format.<br>
 * It provides methods for reading, writing, and deleting data within the database.<br>
 * It also maintains a cache of the data in memory for faster access.
 */
public class DatabaseObject {

    private final String dbName;
    private final File dbFile;
    private final HashMap<String, Object> cache;

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
            cache.put(key, data.get(key));
    }

    /**
     * Delete a {@link DatabaseObject} and all the data within the database. This will clear the cache and delete the file.<br>
     * <b>(WARNING! THIS STEP CANNOT BE UNDONE!)</b>
     * @return boolean indicating success of failure of the deletion of the {@link DatabaseObject}
     */
    protected boolean delete() {
        this.cache.clear();
        return this.dbFile.delete();
    }

    /**
     * Get name of {@link DatabaseObject}
     * @return {@link String} name of {@link DatabaseObject}
     */
    public String getName() {
        return dbName;
    }

    /**
     * Get JSON {@link File} of {@link DatabaseObject}
     * @return JSON {@link File} with all the data
     */
    public File getFile() {
        return dbFile;
    }

    /**
     * Get all the keys stored inside the database
     * @return {@link Set <String>} string set of keys
     */
    public Set<String> getKeys() {
        return cache.keySet();
    }

    /**
     * Get all the values stored inside the database
     * @return {@link Collection <String>} string collection of values
     */
    public Collection<Object> getValues() {
        return cache.values();
    }

    /**
     * Get the value corresponding to the given key.<br>
     * (The value is pulled directly from the database cache)
     * @param key {@link String} key of the value
     * @return {@link Object} value corresponding to the given key
     */
    public Object getValue(String key) {
        if(!cache.containsKey(key)) return null;
        return cache.get(key);
    }

    /**
     * Get the value corresponding to the given key.<br>
     * (The value is pulled directly from the database cache)
     * @param key {@link String} key of the value
     * @return {@link DataObject} value corresponding to the given key
     */
    public DataObject getJSONValue(String key) {
        if(!cache.containsKey(key)) return null;
        return (DataObject) cache.get(key);
    }

    /**
     * Get the value corresponding to the given key.<br>
     * (The value is pulled directly from the database cache)
     * @param key {@link String} key of the value
     * @return {@link String} value corresponding to the given key
     */
    public String getStringValue(String key) {
        if(!cache.containsKey(key)) return null;
        return cache.get(key).toString();
    }

    /**
     * Get the value corresponding to the given key.<br>
     * (The value is pulled directly from the database cache)
     * @param key {@link String} key of the value
     * @return {@link Integer} value corresponding to the given key
     */
    public Integer getIntegerValue(String key) {
        String value = this.getStringValue(key);
        if(value == null) return null;
        return Integer.parseInt(value);
    }

    /**
     * Get the value corresponding to the given key.<br>
     * (The value is pulled directly from the database cache)
     * @param key {@link String} key of the value
     * @return {@link Long} value corresponding to the given key
     */
    public Long getLongValue(String key) {
        String value = this.getStringValue(key);
        if(value == null) return null;
        return Long.parseLong(value);
    }

    /**
     * Update the value with the given key.<br>
     * If the key does not exist, it will perform the same action as {@link #addKey addKey} and simply add the key and value relationship.<br>
     * (This updates the cache and the database file in a separate thread)
     * @param key {@link String} key for the new value
     * @param value {@link String} value to replace the old value
     */
    public void updateValue(String key, Object value) {
        if(!cache.containsKey(key)) {
            this.addKey(key, value);
            return;
        }
        cache.replace(key, value);
        this.updateToDb(key, value);
    }

    /**
     * Add key to value relationship.<br>
     * Adds the key to value relationship to the cache and updates the database file in a separate thread.
     * @param key {@link String} key of value
     * @param value {@link String} value corresponding to key
     */
    public void addKey(String key, Object value) {
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
    private void updateToDb(String key, Object value) {
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
                bf.close();
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
                bf.close();
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        };
        ThreadManagement.execute(remove);
    }

}
