package sqlitedb;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Main class for running JUnit tests
 */
public class TestRunner {

    /**
     * Main method to run the JUnit Tests
     * @param args arguments
     */
    public static void main(String[] args) {
        runDatabaseTests();
        runTableTests();
    }

    /**
     * Run all the {@link discorddb.sqlitedb.SQLDatabase} tests
     */
    static void runDatabaseTests() {
        Result manager = JUnitCore.runClasses(SQLDatabaseTests.class);
        System.out.println("SQL Database Testing =>");
        for(Failure failure : manager.getFailures())
            System.out.println("---> " + failure.toString());
        System.out.println(manager.wasSuccessful() ? "Successful!" : "Failed!");
    }

    /**
     * Run all the {@link discorddb.sqlitedb.DatabaseTable} tests
     */
    static void runTableTests() {
        Result database = JUnitCore.runClasses(DatabaseTableTests.class);
        System.out.println("Database Table Testing =>");
        for(Failure failure : database.getFailures())
            System.out.println("---> " + failure.toString());
        System.out.println(database.wasSuccessful() ? "Successful!" : "Failed!");
    }

}
