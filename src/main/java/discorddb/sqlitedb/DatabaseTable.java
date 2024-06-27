package discorddb.sqlitedb;

import java.sql.*;

/**
 * {@link DatabaseTable} represents a table in a SQLite database and provides methods for
 * performing various SQL queries on the table, such as inserting data, searching for data,
 * updating data, and deleting data.
 *
 * @see #insertQuery(String[] columns)
 * @see #getRows(String keyName, String location)
 */
public class DatabaseTable {

    private final Statement statement;
    private final String tableName;
    private int columnCount;

    protected DatabaseTable(Statement stat, String name, int columnCount) {
        this.statement = stat;
        this.tableName = name;
        this.columnCount = columnCount;
    }

    /**
     * Gets the name of hte table
     * @return table name represented in a String
     */
    public String getName() {
        return this.tableName;
    }

    /**
     * Inserts a new row of data into the table with the specified values
     * @param values array of column values that you want to set in the correct order
     * @return boolean indicating whether the sql command execution worked properly (didn't return a {@link ResultSet})
     */
    public boolean insertQuery(String... values) {
        try {
            statement.execute(
                    String.format("INSERT INTO %s VALUES (%s)",
                            this.tableName,
                            String.join(", ", values)
                    )
            );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Searches for rows in the table that matches the specified
     * key value, and returns the values of the specified parameters
     * for those rows.
     * @param keyName name of the key parameter that you want to search for the row by
     * @param location the value you want to match for the key
     * @param columns list of strings holding the column names that are being queried
     * @return 2D Object array of all the column values in all selected rows.
     * @throws SQLException for the sql command execution
     */
    public Object[][] searchQuery(String keyName, String location, String... columns) throws SQLException {
        ResultSet result = statement.executeQuery(
                String.format("SELECT %s FROM %s WHERE %s=%s",
                        String.join(", ", columns),
                        this.tableName,
                        keyName,
                        location
                )
        );

        result.last();
        int rows = result.getRow();
        result.beforeFirst();
        Object[][] values = new Object[rows][columns.length];

        for(int r=1; r<=rows; r++) {
            result.next();
            for(int i=1; i<=columns.length; i++) {
                values[r-1][i-1] = result.getObject(i);
            }
        }

        return values;
    }

    /**
     * Searches for rows in the table that match the specified
     * key value and returns all of the column values for those
     * rows.
     * @param keyName specified String key parameter name
     * @param location specified String key parameter value
     * @return 2D Object array of all the column values in all selected rows.
     * @throws SQLException for sql command execution
     */
    public Object[][] getRows(String keyName, String location) throws SQLException {
        ResultSet result = statement.executeQuery(
                String.format("SELECT * FROM %s WHERE %s=%s",
                        this.tableName,
                        keyName,
                        location
                )
        );

        result.last();
        int rows = result.getRow();
        result.beforeFirst();
        Object[][] values = new Object[rows][this.columnCount];

        for(int r=1; r<=rows; r++) {
            result.next();
            for(int i=1; i<=this.columnCount; i++) {
                values[r-1][i-1] = result.getObject(i);
            }
        }

        return values;
    }

    /**
     * Returns an entire column of values for ALL rows in the table.
     * @param columns list of Strings representing the column names being queried
     * @return 2D Object matrix with the values of each column in all rows
     * @throws SQLException for sql command execution
     */
    public Object[][] getColumns(String... columns) throws SQLException {
        ResultSet result = statement.executeQuery(
                String.format("SELECT %s FROM %s",
                        String.join(", ", columns),
                        this.tableName
                )
        );

        result.last();
        int rows = result.getRow();
        result.beforeFirst();
        Object[][] values = new Object[rows][columns.length];

        for(int r=1; r<=rows; r++) {
            result.next();
            for(int i=1; i<=columns.length; i++) {
                values[r-1][i-1] = result.getObject(i);
            }
        }

        return values;
    }

    /**
     * Updates the value of a specified column for the row
     * that matches the specified key value.
     * @param columnName String array of all column parameter names
     * @param columnValue String array of all column parameter values
     * @param keyName specified String containing the key parameter name
     * @param location specified String containing the key parameter value to match
     * @return boolean indicating whether the sql command execution worked properly (didn't return a {@link ResultSet})
     */
    public boolean updateQuery(String[] columnName, String[] columnValue, String keyName, String location) {
        StringBuilder columns = new StringBuilder();
        for(int i=0; i<columnName.length; i++) {
            columns.append(columnName[i]).append("=").append(columnValue[i]).append(",");
        }

        try {
            statement.execute(
                    String.format("UPDATE %s SET %s WHERE %s=%s",
                            this.tableName,
                            columns.substring(0, columns.length()-1),
                            keyName,
                            location
                    )
            );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the value of a specified column for the row
     * that matches the specified key value. <br>
     * Note: The column pair String list need to be formatted as `key=value`. For example: `"name='John Smith'"`
     * @param keyName specified String containing the key parameter name
     * @param location specified String containing the key parameter value to match
     * @param columnPairs list of Strings that contain column name and value pairs formatted as `key=value`.
     * @return boolean indicating whether the sql command execution worked properly (didn't return a {@link ResultSet})
     */
    public boolean updateQuery(String keyName, String location, String... columnPairs) {
        try {
            statement.execute(
                    String.format("UPDATE %s SET %s WHERE %s=%s",
                            this.tableName,
                            String.join(", ", columnPairs),
                            keyName,
                            location
                    )
            );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes multiple specified rows from the table that matches
     * the specified key value.
     * @param keyName specified String key parameter name
     * @param location specified String key parameter value to match
     * @return boolean indicating whether the sql command execution worked properly (didn't return a {@link ResultSet})
     */
    public boolean deleteQuery(String keyName, String location) {
        try {
            statement.execute(
                    String.format("DELETE FROM %s WHERE %s=%s",
                            this.tableName,
                            keyName,
                            location
                    )
            );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
