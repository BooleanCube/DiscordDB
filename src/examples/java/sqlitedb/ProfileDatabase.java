package sqlitedb;

import discorddb.sqlitedb.DatabaseTable;
import discorddb.sqlitedb.SQLDatabase;

import java.sql.SQLException;

public class ProfileDatabase {

    private static DatabaseTable profiles;

    public static void setupTables() {
        try {
            SQLDatabase.createTable("profiles", "id int primary key", "username varchar(255)", "tag int", "avatar text");
            profiles = SQLDatabase.getTable("profiles");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getUsername(String id) throws SQLException {
        return (int)profiles.searchQuery("id", id)[0][1];
    }

    public static int getTag(String id) throws SQLException {
        return (int)profiles.searchQuery("id", id)[0][2];
    }

    public static int getAvatar(String id) throws SQLException {
        return (int)profiles.searchQuery("id", id)[0][3];
    }

    public static void addProfile(String id, String username, int tag, String avatar) throws SQLException {
        profiles.insertQuery(id, username, Integer.toString(tag), avatar);
    }

    public static void updateAvatar(String id, String avatar) throws SQLException {
        profiles.updateQuery(new String[]{"avatar"}, new String[]{avatar}, "id", id);
    }

    public static void updateUsername(String id, String username) throws SQLException {
        profiles.updateQuery(new String[]{"username"}, new String[]{username}, "id", id);
    }

}
