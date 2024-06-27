package discorddb.jsondb;


import javax.naming.LimitExceededException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;

/**
 * JSON Database Manager for Discord DB library<br>
 * Manages all cached data from the stored JSON databases.
 */
public class DatabaseManager {

    private static final HashMap<String, DatabaseObject> databases;

    /**
     * DatabaseManager Default Constructor
     */
    private DatabaseManager() {}

    // Static block to init all the current databases and caches
    static {
        databases = new HashMap<>();
        File filesFolder = new File(Constant.fileDirectory);
        if(!filesFolder.exists()) filesFolder.mkdir();
        File[] files = filesFolder.listFiles();
        if(files != null) {
            for(File dbFile : files) {
                String dbName = dbFile.getName().replace(".json","");
                DatabaseObject db = new DatabaseObject(dbName, dbFile);
                databases.put(dbName, db);
            }
        }
    }

    /**
     * Creates a {@link DatabaseObject} with a designated file and adds it to the database map
     * @param dbName name of the {@link DatabaseObject}
     * @return boolean indicating success or failure of {@link DatabaseObject} creation
     * @throws LimitExceededException cannot create more than 15 databases per project for simplicity
     * @throws FileAlreadyExistsException no duplicate databases can be created
     * @throws FileNotFoundException could not find database file directory
     */
    public static boolean createDatabase(String dbName) throws LimitExceededException, FileAlreadyExistsException, FileNotFoundException {
        if(dbName == null)
            throw new NullPointerException("The database name cannot be null!");
        if(databases.size() >= Constant.maxDatabaseLimit)
            throw new LimitExceededException("Cannot create more than " + Constant.maxDatabaseLimit + " databases!");
        if(databases.containsKey(dbName))
            return true;

        try {
            File dbFile = new File(Constant.fileDirectory + dbName + ".json");
            if(!dbFile.exists()) {
                if(dbFile.createNewFile()) {
                    FileWriter fr = new FileWriter(dbFile);
                    fr.write("{\"data\":{}}");
                    fr.close();
                    databases.put(dbName, new DatabaseObject(dbName, dbFile));
                    System.out.println("Successfully created the " + dbName + " database file!");
                }
                else throw new RuntimeException("This database file could not be created! This could be due to lack of permission or you can just try again!");
            }
            else throw new FileAlreadyExistsException("This database file has already been created!");
        } catch(IOException ignored) {
            throw new FileNotFoundException("There is no such directory/file!");
        }

        return true;
    }

    /**
     * Returns the {@link DatabaseObject} connected to the database name given from the database map
     * @param dbName name of {@link DatabaseObject}
     * @return {@link DatabaseObject} representing a database
     */
    public static DatabaseObject getDatabase(String dbName) {
        if(!databases.containsKey(dbName)) return null;
        return databases.get(dbName);
    }

    /**
     * Deletes every database including all the data within the databases.<br>
     * <b>(WARNING! THIS STEP CANNOT BE UNDONE!)</b>
     * @return boolean value indicating whether all {@link DatabaseObject} objects were deleted successfully or not.
     */
    public static boolean clearDatabases() {
        File filesFolder = new File(Constant.fileDirectory);
        if(!filesFolder.exists()) return true;
        boolean ret = true;
        for(File file : filesFolder.listFiles()) {
            if(!file.delete()) ret = false;
        }
        databases.clear();
        return ret;
    }

    /**
     * Delete a database and all of its data from the database map.<br>
     * <b>(WARNING! THIS ACTION CANNOT BE UNDONE!)</b>
     * @param dbName name of {@link DatabaseObject}
     * @return boolean value indicating whether the {@link DatabaseObject} was successfully deleted or not
     */
    public static boolean deleteDatabase(String dbName) {
        DatabaseObject database = databases.get(dbName);
        return database.delete();
    }

}
