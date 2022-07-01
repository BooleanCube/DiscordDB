import discorddb.DatabaseManager;
import discorddb.DatabaseObject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Contains all the tests for {@link discorddb.DatabaseObject}
 */
public class DatabaseTests {

    /**
     * Testing to check if every feature in {@link discorddb.DatabaseObject} is working perfectly
     */
    @Test
    public void databaseTests() {
        DatabaseObject database = DatabaseManager.getDatabase("database1");

        Assert.assertNotNull("getName() returns null", database.getName());

        database.addKey("hello", "world");
        Assert.assertEquals("addKey() or getValue() is not working properly", database.getValue("hello"), "world");

        database.updateValue("hello", "1234");
        Assert.assertEquals("updateValue() or getValueInt() is not working properly", 1234, (int) database.getValueInt("hello"));

        Assert.assertNotNull("getFile() returns null", database.getFile());
        Assert.assertNotNull("getKeys() returns null", database.getKeys());
        Assert.assertNotNull("getValues() returns null", database.getValues());

        database.removeKey("hello");
        Assert.assertNull("removeKey() or getValue() is not working properly", database.getValue("hello"));

    }

}
