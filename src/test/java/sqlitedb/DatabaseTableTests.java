package sqlitedb;

import discorddb.sqlitedb.DatabaseTable;
import discorddb.sqlitedb.SQLDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Contains all the tests for {@link DatabaseTable}
 */
public class DatabaseTableTests {

    /**
     * Testing to check if every feature in {@link DatabaseTable} is working perfectly
     */
    @Test
    public void tableTests() throws SQLException {

        SQLDatabase.createTable("database1", new String[]{"id int primary key", "name varchar(255)"});

        DatabaseTable database = SQLDatabase.getTable("database1");

        Assert.assertNotNull("getName() returns null", database.getName());

        database.insertQuery("1", "'hello'");
        Assert.assertEquals("insertQuery() is not working properly", "hello", database.searchQuery("id", "1")[0][1].toString());

        database.updateQuery(new String[]{"name"}, new String[]{"'world'"}, "id", "1");
        Assert.assertEquals("updateQuery() or searchQuery() is not working properly", "world", database.searchQuery("id", "1")[0][1].toString());

        Assert.assertEquals("searchQuery(params, name, location) is not working properly", "world", database.searchQuery(new String[]{"name"}, "id", "1")[0][0].toString());

        Assert.assertTrue("deleteQuery() is not working properly", database.deleteQuery("id", "1"));

    }

}
