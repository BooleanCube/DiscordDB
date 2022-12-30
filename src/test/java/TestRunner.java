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
        runManagerTests();
        runDatabaseTests();
    }

    /**
     * Run all the {@link discorddb.DatabaseManager} tests
     */
    static void runManagerTests() {
        Result manager = JUnitCore.runClasses(ManagerTests.class);
        System.out.println("Database Manager Testing =>");
        for(Failure failure : manager.getFailures())
            System.out.println("---> " + failure.toString());
        System.out.println(manager.wasSuccessful() ? "Successful!" : "Failed!");
    }

    /**
     * Run all the {@link discorddb.DatabaseObject} tests
     */
    static void runDatabaseTests() {
        Result database = JUnitCore.runClasses(DatabaseObjectTests.class);
        System.out.println("Database Object Testing =>");
        for(Failure failure : database.getFailures())
            System.out.println("---> " + failure.toString());
        System.out.println(database.wasSuccessful() ? "Successful!" : "Failed!");
    }

}
