package sqlitedb;

import discorddb.sqlitedb.DatabaseTable;
import discorddb.sqlitedb.SQLDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Contains all the tests for {@link DatabaseTable}
 */
public class DatabaseTableTests {

    /**
     * Testing to check if every feature in {@link DatabaseTable} is working perfectly
     */
    @Test
    public void tableTests() throws SQLException {

        SQLDatabase.createTable("database1", "id int primary key", "name varchar(255)", "age smallint");

        DatabaseTable database = SQLDatabase.getTable("database1");

        Assert.assertNotNull("getName() returns null", database.getName());

        database.insertQuery("1", "'hello'", "0");
        database.insertQuery("2", "'again'", "0");
        database.insertQuery("3", "'john'", "18");
        Assert.assertEquals(
                "insertQuery() or getRows() is not working properly",
                "hello",
                database.getRows("id", "1")[0][1].toString()
        );

        database.updateQuery(new String[]{"name"}, new String[]{"'world'"}, "id", "2");
        database.updateQuery("id", "3", "name='alex'");
        Assert.assertEquals(
                "updateQuery(keys[], value[], key, location) or getRows() is not working properly",
                "world",
                database.getRows("id", "2")[0][1].toString()
        );

        Assert.assertEquals(
                "searchQuery(name, location, ...columns) is not working properly",
                "alex",
                database.searchQuery("id", "3", "name")[0][0].toString()
        );

        Assert.assertEquals(
                "getColumns(...columns) is not working properly",
                Arrays.deepToString(new Object[] {new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 18}}),
                Arrays.deepToString(database.getColumns("id", "age"))
        );

        Assert.assertTrue(
                "deleteQuery() is not working properly",
                database.deleteQuery("id", "1")
        );

    }

}
