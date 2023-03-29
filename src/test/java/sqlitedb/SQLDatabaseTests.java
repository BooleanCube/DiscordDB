package sqlitedb;

import discorddb.jsondb.DatabaseManager;
import discorddb.sqlitedb.SQLDatabase;
import org.junit.Assert;
import org.junit.Test;

import javax.naming.LimitExceededException;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.SQLException;

/**
 * Contains all the tests for {@link SQLDatabase}
 */
public class SQLDatabaseTests {

    /**
     * Testing to check if every feature in {@link SQLDatabase} is working perfectly
     * @throws SQLException for SQL Database reconnection attempts
     */
    @Test
    public void databaseTests() throws SQLException {
        Assert.assertTrue("Could not clear all databases", SQLDatabase.dropAllTables());

        try {
            SQLDatabase.createTable(null, null);
            Assert.fail("Could create databases with null name");
        } catch(NullPointerException npe) { Assert.assertTrue(true); }
        Assert.assertTrue("Could not create table1", SQLDatabase.createTable("table1", new String[]{"id int primary key", "name varchar(255)"}));

        Assert.assertNotNull("Could not select table1", SQLDatabase.getTable("table1"));
        Assert.assertTrue("Couldn't perform INSERT execute() query", SQLDatabase.execute("INSERT INTO table1 VALUES (1, 'HELLO')"));

        Assert.assertTrue("Could not successfully create a backup of the database", SQLDatabase.backupDatabase());

        Assert.assertTrue("Could not delete table1", SQLDatabase.dropTable("table1"));

        Assert.assertTrue("Couldn't create table2", SQLDatabase.createTable("table2", new String[]{"id int primary key", "test int"}));
        Assert.assertTrue("Couldn't create table3", SQLDatabase.createTable("table3", new String[]{"test int", "id int primary key"}));
    }

}
