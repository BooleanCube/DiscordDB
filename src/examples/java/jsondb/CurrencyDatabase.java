package singular;

import discorddb.DatabaseManager;
import discorddb.DatabaseObject;

import javax.naming.LimitExceededException;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

public class CurrencyDatabase {

    private static DatabaseObject currency;

    public static void setupDatabases() {
        try {
            DatabaseManager.createDatabase("currency");
            DatabaseObject currency = DatabaseManager.getDatabase("currency");
        } catch (LimitExceededException | FileAlreadyExistsException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getUserBalance(String id) {
        if(currency.getValue(id) == null) {
            currency.addKey(id, "0");
            return 0;
        }
        return currency.getValueInt(id);
    }

    public static void addToUserBalance(String id, int increment) {
        int balance = getUserBalance(id) + increment;
        currency.updateValue(id, String.valueOf(Math.max(0, balance)));
    }

}
