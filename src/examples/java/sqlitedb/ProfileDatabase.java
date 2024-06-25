package sqlitedb;

import discorddb.sqlitedb.DatabaseTable;
import discorddb.sqlitedb.SQLDatabase;

import java.sql.SQLException;

public class ProfileDatabase {

    private static DatabaseTable profiles;

    public static void setupTables() {
        try {
            SQLDatabase.createTable("profiles", "id bigint primary key not null unique", "username varchar(255)", "tag int", "avatar text");
            profiles = SQLDatabase.getTable("profiles");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUsername(String id) throws SQLException {
        return (String)profiles.getRows("id", id)[0][1];
    }

    public static int getTag(String id) throws SQLException {
        return (int)profiles.searchQuery("id", id, "tag")[0][0];
    }

    public static String getAvatar(String id) throws SQLException {
        return (String)profiles.getRows("id", id)[0][3];
    }

    public static void addProfile(String id, String username, int tag, String avatar) {
        profiles.insertQuery(id, username , Integer.toString(tag), avatar);
    }

    public static void updateAvatar(String id, String avatar) {
        profiles.updateQuery( "id", id, String.format("avatar=%s", avatar));
    }

    public static void updateUsername(String id, String username) {
        profiles.updateQuery("id", id, String.format("username=%s", username));
    }

}
