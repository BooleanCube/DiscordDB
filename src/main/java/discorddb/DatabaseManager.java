package discorddb;


import javax.naming.LimitExceededException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;

/**
 * Database Manager for Discord DB library
 */
public class DatabaseManager {

    private static final HashMap<String, DatabaseObject> databases;
    private static final String directory = ".files/";

    // Static block to init all the current databases and caches
    static {
        databases = new HashMap<>();
        File filesFolder = new File(directory);
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
     * @throws LimitExceededException cannot create more than 10 databases per project for simplicity
     * @throws FileAlreadyExistsException no duplicate databases can be created
     * @throws FileNotFoundException could not find database file directory
     */
    public static boolean createDatabase(String dbName) throws LimitExceededException, FileAlreadyExistsException, FileNotFoundException {
        if(dbName == null)
            throw new NullPointerException("The database name cannot be null!");
        if(databases.size() >= 10)
            throw new LimitExceededException("Cannot create more than 10 databases!");
        if(databases.containsKey(dbName))
            return true;

        try {
            File dbFile = new File(directory + dbName + ".json");
            if(!dbFile.exists()) {
                if(dbFile.createNewFile()) {
                    FileWriter fr = new FileWriter(dbFile);
                    fr.write("{\"data\":{}}");
                    fr.close();
                    databases.put(dbName, new DatabaseObject(dbName, dbFile));
                    System.out.println("Successfully created the database file!");
                }
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
     */
    public static boolean clearDatabases() {
        File filesFolder = new File("files/");
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
     * @return boolean indicating whether the {@link DatabaseObject} was successfully deleted or not
     */
    public static boolean deleteDatabase(String dbName) {
        DatabaseObject database = databases.get(dbName);
        return database.delete();
    }

}
