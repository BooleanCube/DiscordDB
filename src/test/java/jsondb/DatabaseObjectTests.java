package jsondb;

import discorddb.jsondb.DatabaseManager;
import discorddb.jsondb.DatabaseObject;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Contains all the tests for {@link DatabaseObject}
 */
public class DatabaseObjectTests {

    /**
     * Testing to check if every feature in {@link DatabaseObject} is working perfectly
     */
    @Test
    public void databaseTests() {

        DatabaseObject database = DatabaseManager.getDatabase("database1");

        Assert.assertNotNull("getName() returns null", database.getName());

        database.addKey("hello", "world");
        Assert.assertEquals("addKey() or getValue() is not working properly", database.getValue("hello"), "world");

        database.updateValue("hello", "1234");
        Assert.assertEquals("updateValue() or getIntegerValue() is not working properly", (Integer) 1234, database.getIntegerValue("hello"));

        database.addKey("numbers", 69);
        Assert.assertEquals("You can't store integers in the database", 69, database.getValue("numbers"));

        database.addKey("jsondb", DataObject.fromJson("{\"first\":\"json\", \"second\":\"object\"}"));
        DataObject jsonObj = (DataObject) (database.getValue("jsondb"));
        Assert.assertEquals("you can't store json objects in the database", "jsondb", jsonObj.getString("first"));
        Assert.assertEquals("you can't store json objects in the database", "object", jsonObj.getString("second"));

        database.addKey("json2", DataArray.fromJson("[\"string\", \"array\"]"));
        DataArray jsonArr = (DataArray) (database.getValue("json2"));
        Assert.assertEquals("you can't store json arrays in the database", "string", jsonArr.getString(0));
        Assert.assertEquals("you can't store json arrays in the database", "array", jsonArr.getString(1));

        Assert.assertNotNull("getFile() returns null", database.getFile());
        Assert.assertNotNull("getKeys() returns null", database.getKeys());
        Assert.assertNotNull("getValues() returns null", database.getValues());

        database.removeKey("hello");
        Assert.assertNull("removeKey() or getValue() is not working properly", database.getValue("hello"));

    }

}
