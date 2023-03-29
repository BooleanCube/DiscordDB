package singular;

public class UsageExample {

    public static void main(String[] args) {

        CurrencyDatabase.setupDatabases();

        String boolId = "525126007330570259";
        int boolBalance = CurrencyDatabase.getUserBalance(boolId);
        System.out.println("This is bool's current balance: " + boolBalance);

        CurrencyDatabase.addToUserBalance(boolId, 100);
        boolBalance = CurrencyDatabase.getUserBalance(boolId);
        System.out.println("This is bool's new balance: " + boolBalance);

    }

}
