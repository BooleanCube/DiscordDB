import discorddb.DatabaseManager;
import org.junit.Assert;
import org.junit.Test;

import javax.naming.LimitExceededException;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

/**
 * Contains all the tests for {@link DatabaseManager}
 */
public class ManagerTests {

    /**
     * Testing to check if every feature in {@link DatabaseManager} is working perfectly
     * @throws FileAlreadyExistsException thrown if a duplicate database was found
     * @throws FileNotFoundException thrown if the files directory cannot be found
     * @throws LimitExceededException thrown if the database count exceeds 10
     */
    @Test
    public void managerTests() throws FileAlreadyExistsException, FileNotFoundException, LimitExceededException {
        Assert.assertTrue("Could not clear all databases", DatabaseManager.clearDatabases());

        try {
            DatabaseManager.createDatabase(null);
            Assert.fail("Could create databases with null name");
        } catch(NullPointerException npe) { Assert.assertTrue(true); }
        Assert.assertTrue("Could not create database1", DatabaseManager.createDatabase("database1"));
        Assert.assertTrue("Could not ignore duplicate database", DatabaseManager.createDatabase("database1"));
        Assert.assertTrue("Could not create database2", DatabaseManager.createDatabase("database2"));
        Assert.assertTrue("Could not create database3", DatabaseManager.createDatabase("database3"));
        Assert.assertTrue("Could not create database4", DatabaseManager.createDatabase("database4"));
        Assert.assertTrue("Could not create database5", DatabaseManager.createDatabase("database5"));
        Assert.assertTrue("Could not create database6", DatabaseManager.createDatabase("database6"));
        Assert.assertTrue("Could not create database7", DatabaseManager.createDatabase("database7"));
        Assert.assertTrue("Could not create database8", DatabaseManager.createDatabase("database8"));
        Assert.assertTrue("Could not create database9", DatabaseManager.createDatabase("database9"));
        Assert.assertTrue("Could not create database10", DatabaseManager.createDatabase("database10"));
        try {
            DatabaseManager.createDatabase("database11");
            Assert.fail("Max database limit was broken");
        } catch(LimitExceededException lee) { Assert.assertTrue(true); }

        Assert.assertNotNull("Could not select database1", DatabaseManager.getDatabase("database1"));
        Assert.assertNotNull("Could not select database2", DatabaseManager.getDatabase("database2"));
        Assert.assertNotNull("Could not select database3", DatabaseManager.getDatabase("database3"));
        Assert.assertNotNull("Could not select database4", DatabaseManager.getDatabase("database4"));
        Assert.assertNotNull("Could not select database5", DatabaseManager.getDatabase("database5"));
        Assert.assertNotNull("Could not select database6", DatabaseManager.getDatabase("database6"));
        Assert.assertNotNull("Could not select database7", DatabaseManager.getDatabase("database7"));
        Assert.assertNotNull("Could not select database8", DatabaseManager.getDatabase("database8"));
        Assert.assertNotNull("Could not select database9", DatabaseManager.getDatabase("database9"));
        Assert.assertNotNull("Could not select database10", DatabaseManager.getDatabase("database10"));

        Assert.assertTrue("Could not delete database5", DatabaseManager.deleteDatabase("database5"));
    }

}
